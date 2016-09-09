package cn.com.findfine.timebank.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import cn.com.findfine.timebank.R;
import cn.com.findfine.timebank.view.adapter.MainAdapter;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private RecyclerView recyclerViewMain;
    private View rlMainCover;
    private FloatingActionButton fab;
    private CollapsingToolbarLayout collapsingToolbar;

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

        rlMainCover = findViewById(R.id.rl_main_cover);
        rlMainCover.setOnClickListener(this);

        recyclerViewMain = (RecyclerView) findViewById(R.id.recycler_view_main);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        recyclerViewMain.setLayoutManager(linearLayoutManager);
        MainAdapter adapter = new MainAdapter(mContext);
        recyclerViewMain.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                startActivity(new Intent(MainActivity.this, AddEventActivity.class));
//                fab.setBackgroundTintList(ColorStateList.valueOf(Color.BLUE));
//                rlMainCover.setBackgroundColor(Color.BLUE);
//                collapsingToolbar.setContentScrimColor(Color.BLUE);
                break;
            case R.id.rl_main_cover:
                startActivity(new Intent(mContext, EventDetailActivity.class));
                break;
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

        if (id == R.id.action_settings) {
            startActivity(new Intent(mContext, SettingsActivity.class));
        } else if (id == R.id.action_about) {
            startActivity(new Intent(mContext, AboutActivity.class));
        }

        return true;
    }
}
