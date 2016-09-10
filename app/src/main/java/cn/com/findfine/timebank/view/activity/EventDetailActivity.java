package cn.com.findfine.timebank.view.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import cn.com.findfine.timebank.R;
import cn.com.findfine.timebank.view.adapter.EventDetailAdapter;
import cn.com.findfine.timebank.view.fragment.EventDetailFragment;

public class EventDetailActivity extends BaseActivity {

    private ViewPager viewPagerEventDetail;

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

        init();
    }

    private void init() {
        viewPagerEventDetail = (ViewPager) findViewById(R.id.view_pager_event_detail);

        EventDetailAdapter adapter = new EventDetailAdapter(getSupportFragmentManager());
        adapter.addFragment(EventDetailFragment.newInstance());
        adapter.addFragment(EventDetailFragment.newInstance());
        adapter.addFragment(EventDetailFragment.newInstance());
        viewPagerEventDetail.setAdapter(adapter);
    }
}
