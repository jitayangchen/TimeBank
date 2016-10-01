package cn.com.findfine.timebank.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import cn.com.findfine.timebank.R;
import cn.com.findfine.timebank.view.adapter.EventDetailAdapter;

public class EventDetailActivity extends BaseActivity {

    public final static String POSITION = "position";

    private ViewPager viewPagerEventDetail;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Intent intent = getIntent();
        position = intent.getIntExtra(POSITION, 0);

        init();
    }

    private void init() {
        viewPagerEventDetail = (ViewPager) findViewById(R.id.view_pager_event_detail);
        EventDetailAdapter adapter = new EventDetailAdapter(getSupportFragmentManager());
        viewPagerEventDetail.setAdapter(adapter);

        if (position != 0) {
            viewPagerEventDetail.setCurrentItem(position);
        }
    }
}
