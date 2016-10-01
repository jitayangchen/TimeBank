package cn.com.findfine.timebank.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.etiennelawlor.quickreturn.library.enums.QuickReturnViewType;
import com.etiennelawlor.quickreturn.library.listeners.QuickReturnRecyclerViewOnScrollListener;

import java.util.List;

import cn.com.findfine.timebank.R;
import cn.com.findfine.timebank.data.bean.EventInfo;
import cn.com.findfine.timebank.data.cache.EventDataCache;
import cn.com.findfine.timebank.data.db.EventDataDao;
import cn.com.findfine.timebank.util.DateUtil;
import cn.com.findfine.timebank.util.Preference;
import cn.com.findfine.timebank.view.adapter.MainAdapter;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private RecyclerView recyclerViewMain;
    private View llMainCover;
    private FloatingActionButton fab;
    private CollapsingToolbarLayout collapsingToolbar;
    private EventDataCache eventDataCache;
    private MainAdapter adapter;
    private TextView tvMainTitle;
    private TextView tvMainLeftTime;
    private TextView tvMainFormatDate;

    private int currentPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        init();
    }

    private void init() {

        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);

        llMainCover = findViewById(R.id.ll_main_cover);
        tvMainTitle = (TextView) findViewById(R.id.tv_main_title);
        tvMainLeftTime = (TextView) findViewById(R.id.tv_main_left_time);
        tvMainFormatDate = (TextView) findViewById(R.id.tv_main_format_date);

        llMainCover.setOnClickListener(this);

        recyclerViewMain = (RecyclerView) findViewById(R.id.recycler_view_main);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        recyclerViewMain.setLayoutManager(linearLayoutManager);
        adapter = new MainAdapter(mContext);
        recyclerViewMain.setAdapter(adapter);

        int footerHeight = getResources().getDimensionPixelSize(R.dimen.footer_height);
        QuickReturnRecyclerViewOnScrollListener scrollListener = new QuickReturnRecyclerViewOnScrollListener.Builder(QuickReturnViewType.FOOTER)
                .footer(fab)
                .minFooterTranslation(footerHeight)
                .isSnappable(true)
                .build();
        recyclerViewMain.addOnScrollListener(scrollListener);

        eventDataCache = EventDataCache.getInstance();
        List<EventInfo> eventInfos = EventDataDao.getInstance().queryAllEventByCurrentTime(DateUtil.getTodayStartTime());
        eventDataCache.addAll(eventInfos);
        adapter.notifyDataSetChanged();
        updateCoverInfo();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                startActivityForResult(new Intent(MainActivity.this, AddEventActivity.class), 3000);
//                fab.setBackgroundTintList(ColorStateList.valueOf(Color.BLUE));
//                rlMainCover.setBackgroundColor(Color.BLUE);
//                collapsingToolbar.setContentScrimColor(Color.BLUE);
                break;
            case R.id.ll_main_cover:
                Intent intent = new Intent(mContext, EventDetailActivity.class);
                intent.putExtra(EventDetailActivity.POSITION, currentPosition);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 3000 && resultCode == 4000) {
            EventInfo eventInfo = data.getParcelableExtra("event_info");
            EventDataCache.getInstance().insert(eventInfo);
            updateCoverInfo();
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

//        if (id == R.id.action_settings) {
//            startActivity(new Intent(mContext, SettingsActivity.class));
//        } else if (id == R.id.action_about) {
//            startActivity(new Intent(mContext, AboutActivity.class));
//        }
        if (id == R.id.action_about) {
            startActivity(new Intent(mContext, AboutActivity.class));
        }

        return true;
    }

    private void updateCoverInfo() {
        String eventId = Preference.getEventCoverId();
        if (!TextUtils.isEmpty(eventId)) {
            currentPosition = eventDataCache.getPositionByEventId(eventId);
        }
        EventInfo eventInfo = eventDataCache.get(currentPosition);
        if (null != eventInfo) {
            tvMainTitle.setText(eventInfo.getTitle());
            tvMainLeftTime.setText(DateUtil.getDayLeftByTargetMillis(eventInfo.getTargetTime()) + "");
            tvMainFormatDate.setText(DateUtil.getTimeByMillis(eventInfo.getTargetTime()));
        }
    }

}
