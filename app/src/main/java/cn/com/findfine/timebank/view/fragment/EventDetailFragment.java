package cn.com.findfine.timebank.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.com.findfine.timebank.R;

public class EventDetailFragment extends Fragment {

    public EventDetailFragment() {
    }

    public static EventDetailFragment newInstance() {
        EventDetailFragment fragment = new EventDetailFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_event_detail, container, false);
    }

}
