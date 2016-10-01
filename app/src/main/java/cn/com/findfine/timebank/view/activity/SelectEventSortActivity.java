package cn.com.findfine.timebank.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import cn.com.findfine.timebank.R;
import cn.com.findfine.timebank.view.adapter.SelectEventSortAdapter;

public class SelectEventSortActivity extends BaseActivity {

    private RecyclerView rvSelectEventSort;
    private int sortId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_event_sort);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("选择分类");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Intent intent = getIntent();
        sortId = intent.getIntExtra("sort_id", -1);

        init();
    }

    private void init() {
        rvSelectEventSort = (RecyclerView) findViewById(R.id.rv_select_event_sort);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        rvSelectEventSort.setLayoutManager(linearLayoutManager);
        SelectEventSortAdapter adapter = new SelectEventSortAdapter(mContext, sortId);
        rvSelectEventSort.setAdapter(adapter);
    }
}
