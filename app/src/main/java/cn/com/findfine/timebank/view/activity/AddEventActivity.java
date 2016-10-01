package cn.com.findfine.timebank.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
}
