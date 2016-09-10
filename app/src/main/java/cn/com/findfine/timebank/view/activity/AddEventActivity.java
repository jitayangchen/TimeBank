package cn.com.findfine.timebank.view.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.codetroopers.betterpickers.calendardatepicker.CalendarDatePickerDialogFragment;
import com.zcw.togglebutton.ToggleButton;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.com.findfine.timebank.R;

public class AddEventActivity extends BaseActivity implements View.OnClickListener, CalendarDatePickerDialogFragment.OnDateSetListener {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private static final String FRAG_TAG_DATE_PICKER = "fragment_date_picker_name";
    private View rlSelectDate;
    private TextView tvDateContent;
    private ToggleButton tbFrontEover;

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

        init();

    }

    private void init() {
        rlSelectDate = findViewById(R.id.rl_select_date);
        rlSelectDate.setOnClickListener(this);
        tvDateContent = (TextView) findViewById(R.id.tv_date_content);
        tbFrontEover = (ToggleButton) findViewById(R.id.tb_front_cover);

        tbFrontEover.setOnToggleChanged(new ToggleButton.OnToggleChanged() {
            @Override
            public void onToggle(boolean on) {
                Toast.makeText(AddEventActivity.this, on + "", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(AddEventActivity.this, "action_add_event_complete", Toast.LENGTH_SHORT).show();
        }

        return true;
    }
}
