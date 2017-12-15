package cn.com.findfine.timebank.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.codetroopers.betterpickers.calendardatepicker.CalendarDatePickerDialogFragment;
import com.zcw.togglebutton.ToggleButton;

import java.text.ParseException;

import cn.com.findfine.timebank.R;
import cn.com.findfine.timebank.data.bean.EventInfo;
import cn.com.findfine.timebank.data.cache.EventSortCache;
import cn.com.findfine.timebank.data.db.EventDataDao;
import cn.com.findfine.timebank.log.TLog;
import cn.com.findfine.timebank.util.DateUtil;
import cn.com.findfine.timebank.util.Preference;

public class AddEventActivity extends BaseActivity implements View.OnClickListener, CalendarDatePickerDialogFragment.OnDateSetListener {

    private static final String FRAG_TAG_DATE_PICKER = "fragment_date_picker_name";
    private View rlSelectDate;
    private TextView tvDateContent;
    private ToggleButton tbFrontEover;
    private EditText etTitleContent;
    private long targetTime = 0;
    private EventDataDao eventDataDao = null;
    private View rlSelectEvenSort;
    private int sortId = -1;
    private TextView tvSortContent;
    private boolean isCover = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
//        AndroidBug5497Workaround.assistActivity(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("添加事件");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        eventDataDao = EventDataDao.getInstance();
        init();

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN
                | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Log.i("GlobalLayout", "====================onGlobalLayout===================");
                Rect r = new Rect();
                getWindow().getDecorView().getWindowVisibleDisplayFrame(r); //获取当前窗口可视区域大小
                View decorView = getWindow().getDecorView();
                int diff = decorView.getHeight() - r.bottom;
                decorView.setPadding(0, 0, 0, diff);
            }
        });//当在一个视图树中全局布局发生改变或者视图树中的某个视图的可视状态发生改变时，所要调用的回调函数的接口类
    }

    private void init() {
        rlSelectDate = findViewById(R.id.rl_select_date);
        rlSelectDate.setOnClickListener(this);
        tvDateContent = (TextView) findViewById(R.id.tv_date_content);
        tvSortContent = (TextView) findViewById(R.id.tv_sort_content);
        tbFrontEover = (ToggleButton) findViewById(R.id.tb_front_cover);
        etTitleContent = (EditText) findViewById(R.id.et_title_content);
        rlSelectEvenSort = findViewById(R.id.rl_select_event_sort);
        rlSelectEvenSort.setOnClickListener(this);

        tbFrontEover.setOnToggleChanged(new ToggleButton.OnToggleChanged() {
            @Override
            public void onToggle(boolean on) {
//                Toast.makeText(AddEventActivity.this, on + "", Toast.LENGTH_SHORT).show();
                isCover = on;
            }
        });


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_select_date:
                CalendarDatePickerDialogFragment cdp = new CalendarDatePickerDialogFragment()
                        .setOnDateSetListener(this);
                cdp.show(getSupportFragmentManager(), FRAG_TAG_DATE_PICKER);
                break;
            case R.id.rl_select_event_sort:
                Intent intent = new Intent(mContext, SelectEventSortActivity.class);
                intent.putExtra("sort_id", sortId);
                startActivityForResult(intent, 1000);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000 && resultCode == 2000) {
            sortId = data.getIntExtra("sort_id", -1);
            if (sortId != -1) {
                tvSortContent.setText(EventSortCache.EVENT_SORT[sortId]);
            }
        }
    }

    @Override
    public void onDateSet(CalendarDatePickerDialogFragment dialog, long time) {
//        TLog.i(String.valueOf(time));
//        targetTime = time;
//        String formatTime = DateUtil.getTimeByMillis(time);
//        tvDateContent.setText(formatTime);
    }

    @Override
    public void onDateSet(CalendarDatePickerDialogFragment dialog, int year, int monthOfYear, int dayOfMonth) {
        String time = getString(R.string.calendar_date_picker_result_values, year, monthOfYear, dayOfMonth);
        TLog.i(time);
        try {
            targetTime = DateUtil.getMillisByTime(time);
            TLog.i(String.valueOf(targetTime));
            tvDateContent.setText(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_event, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_add_event_complete) {
            String title = etTitleContent.getText().toString();
            if (TextUtils.isEmpty(title)) {
                Toast.makeText(AddEventActivity.this, "标题不能为空", Toast.LENGTH_SHORT).show();
                return false;
            } else if (targetTime == 0) {
                Toast.makeText(AddEventActivity.this, "请选择时间", Toast.LENGTH_SHORT).show();
                return false;
            }

            String eventId = String.valueOf(System.currentTimeMillis());
            EventInfo eventInfo = new EventInfo();
            eventInfo.setEventId(eventId);
            eventInfo.setTitle(title);
            eventInfo.setCompleted(false);
            eventInfo.setType(sortId);
            eventInfo.setContent("");
            eventInfo.setCreateTime(System.currentTimeMillis());
            eventInfo.setTargetTime(targetTime);
            boolean result = eventDataDao.insertEvent(eventInfo);
            if (result && isCover) {
                Preference.saveEventCoverId(eventId);
            }

            Intent intent = new Intent();
            intent.putExtra("event_info", eventInfo);
            setResult(4000, intent);
            finish();
        }

        return true;
    }

//    private ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
//        @Override
//        public void onGlobalLayout() {
//            //如果布局根节点使用了android:fitsSystemWindows="true"属性或者导航栏不在底部，无需处理
////            if (!navigationAtBottom)
////                return;
//            Rect r = new Rect();
//            getWindow().getDecorView().getWindowVisibleDisplayFrame(r); //获取当前窗口可视区域大小
//            int diff;
//            int keyboardHeight;
//            boolean isPopup = false;
//            if (mBarParams.systemWindows) {
//                keyboardHeight = mContentView.getHeight() - r.bottom - navigationBarHeight;
//                if (mBarParams.onKeyboardListener != null) {
//                    if (keyboardHeight > navigationBarHeight)
//                        isPopup = true;
////                    mBarParams.onKeyboardListener.onKeyboardChange(isPopup, keyboardHeight);
//                }
//                return;
//            }
//            if (mChildView != null) {
//                if (mBarParams.isSupportActionBar)
//                    diff = mContentView.getHeight() + statusBarHeight + actionBarHeight - r.bottom;
//                else if (mBarParams.fits)
//                    diff = mContentView.getHeight() + statusBarHeight - r.bottom;
//                else
//                    diff = mContentView.getHeight() - r.bottom;
//                if (mBarParams.fullScreen)
//                    keyboardHeight = diff - navigationBarHeight;
//                else
//                    keyboardHeight = diff;
//                if (mBarParams.fullScreen && diff == navigationBarHeight) {
//                    diff -= navigationBarHeight;
//                }
//                if (keyboardHeight != keyboardHeightPrevious) {
//                    mContentView.setPadding(paddingLeft, paddingTop, paddingRight, diff + paddingBottom);
//                    keyboardHeightPrevious = keyboardHeight;
//                    if (mBarParams.onKeyboardListener != null) {
//                        if (keyboardHeight > navigationBarHeight)
//                            isPopup = true;
//                        mBarParams.onKeyboardListener.onKeyboardChange(isPopup, keyboardHeight);
//                    }
//                }
//            } else {
//                diff = mContentView.getHeight() - r.bottom;
//
//                if (mBarParams.navigationBarEnable && mBarParams.navigationBarWithKitkatEnable) {
//                    if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT || OSUtils.isEMUI3_1()) {
//                        keyboardHeight = diff - navigationBarHeight;
//                    } else {
//                        if (!mBarParams.fullScreen)
//                            keyboardHeight = diff;
//                        else
//                            keyboardHeight = diff - navigationBarHeight;
//                    }
//                    if (mBarParams.fullScreen && diff == navigationBarHeight)
//                        diff -= navigationBarHeight;
//                } else
//                    keyboardHeight = diff;
//                if (keyboardHeight != keyboardHeightPrevious) {
//                    if (mBarParams.isSupportActionBar) {
//                        mContentView.setPadding(0, statusBarHeight + actionBarHeight, 0, diff);
//                    } else if (mBarParams.fits) {
//                        mContentView.setPadding(0, statusBarHeight, 0, diff);
//                    } else
//                        mContentView.setPadding(0, 0, 0, diff);
//                    keyboardHeightPrevious = keyboardHeight;
//                    if (mBarParams.onKeyboardListener != null) {
//                        if (keyboardHeight > navigationBarHeight)
//                            isPopup = true;
////                        mBarParams.onKeyboardListener.onKeyboardChange(isPopup, keyboardHeight);
//                    }
//                }
//            }
//        }
//    }

    private int getNavigationBarHeight(Context context) {
        Resources res = context.getResources();
        int result = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
            return getInternalDimensionSize(res, "navigation_bar_height");
        }
        return result;
    }

    private int getInternalDimensionSize(Resources res, String key) {
        int result = 0;
        try {
            Class clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int resourceId = Integer.parseInt(clazz.getField(key).get(object).toString());
            if (resourceId > 0)
                result = res.getDimensionPixelSize(resourceId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
