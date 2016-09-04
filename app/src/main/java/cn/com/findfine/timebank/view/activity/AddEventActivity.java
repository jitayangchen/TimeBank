package cn.com.findfine.timebank.view.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import cn.com.findfine.timebank.R;

public class AddEventActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}
