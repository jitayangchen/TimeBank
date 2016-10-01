package cn.com.findfine.timebank.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import cn.com.findfine.timebank.data.cache.EventDataCache;
import cn.com.findfine.timebank.view.fragment.EventDetailFragment;

/**
 * Created by yangchen on 15-11-29.
 */
public class EventDetailAdapter extends FragmentPagerAdapter {

    public EventDetailAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return EventDetailFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return EventDataCache.getInstance().getEventInfos().size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "";
    }

}
