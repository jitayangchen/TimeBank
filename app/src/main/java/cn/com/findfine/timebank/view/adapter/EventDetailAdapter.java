package cn.com.findfine.timebank.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cn.com.findfine.timebank.view.fragment.EventDetailFragment;

/**
 * Created by yangchen on 15-11-29.
 */
public class EventDetailAdapter extends FragmentPagerAdapter {

    private final List<Fragment> mFragments = new ArrayList<>();
    private final List<String> mFragmentTitles = new ArrayList<>();

    public EventDetailAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Log.i("YYY", "----getItem----");
        return EventDetailFragment.newInstance();
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitles.get(position);
    }

    public void addFragment(Fragment fragment, String title) {
        mFragments.add(fragment);
        mFragmentTitles.add(title);
    }
}
