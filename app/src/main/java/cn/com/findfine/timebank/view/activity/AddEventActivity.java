package cn.com.findfine.timebank.view.activity;

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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.com.findfine.timebank.R;
import cn.com.findfine.timebank.data.bean.EventInfo;
import cn.com.findfine.timebank.data.db.EventDataDao;
import cn.com.findfine.timebank.log.TLog;

public class AddEventActivity extends BaseActivity implements View.OnClickListener, CalendarDatePickerDialogFragment.OnDateSetListener {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private static final String FRAG_TAG_DATE_PICKER = "fragment_date_picker_name";
    private View rlSelectDate;
    private TextView tvDateContent;
    private ToggleButton tbFrontEover;
    private EditText etTitleContent;
    private long targetTime = 0;
    private EventDataDao eventDataDao = null;

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
        tbFrontEover = (ToggleButton) findViewById(R.id.tb_front_cover);
        etTitleContent = (EditText) findViewById(R.id.et_title_content);

        tbFrontEover.setOnToggleChanged(new ToggleButton.OnToggleChanged() {
            @Override
            public void onToggle(boolean on) {
//                Toast.makeText(AddEventActivity.this, on + "", Toast.LENGTH_SHORT).show();
                List<EventInfo> eventInfos = eventDataDao.queryAll();
                TLog.i(eventInfos.toString());
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
        }
    }

    @Override
    public void onDateSet(CalendarDatePickerDialogFragment dialog, long time) {
        TLog.i(String.valueOf(time));
        targetTime = time;
        String formatTime = sdf.format(new Date(time));
        tvDateContent.setText(formatTime);
    }

    @Override
    public void onDateSet(CalendarDatePickerDialogFragment dialog, int year, int monthOfYear, int dayOfMonth) {

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
                Toast.makeText(AddEventActivity.this, "Title not null", Toast.LENGTH_SHORT).show();
                return false;
            }

            EventInfo eventInfo = new EventInfo();
            eventInfo.setTitle(title);
            eventInfo.setCompleted(false);
            eventInfo.setType(1);
            eventInfo.setContent("YYYYYYYYYYY");
            eventInfo.setCreateTime(System.currentTimeMillis() / 1000);
            eventInfo.setTargetTime(targetTime / 1000);
            eventDataDao.insert(eventInfo);
            Toast.makeText(AddEventActivity.this, "Success", Toast.LENGTH_SHORT).show();
        }

        return true;
    }
}
