package cn.com.findfine.timebank.view.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import cn.com.findfine.timebank.R;

public class AddEventActivity extends BaseActivity implements View.OnClickListener {

    private View rlSelectDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("添加事件");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(this);

        init();

    }

    private void init() {
        rlSelectDate = findViewById(R.id.rl_select_date);
        rlSelectDate.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar:
                onBackPressed();
                break;
            case R.id.rl_select_date:

                break;
        }
    }
}
