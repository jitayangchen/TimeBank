package cn.com.findfine.timebank.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.com.findfine.timebank.R;
import cn.com.findfine.timebank.data.bean.EventInfo;
import cn.com.findfine.timebank.data.cache.EventDataCache;
import cn.com.findfine.timebank.util.DateUtil;
import cn.com.findfine.timebank.view.activity.EventDetailActivity;

public class EventDetailFragment extends Fragment {

    private int position;
    private TextView tvDetailTitle;
    private TextView tvDetailLeftTime;
    private TextView tvDetailFormatDate;

    public EventDetailFragment() {
    }

    public static EventDetailFragment newInstance(int position) {
        EventDetailFragment fragment = new EventDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(EventDetailActivity.POSITION, position);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        position = bundle.getInt(EventDetailActivity.POSITION);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_detail, container, false);
        initView(view);
        init();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void initView(View view) {
        tvDetailTitle = (TextView) view.findViewById(R.id.tv_detail_title);
        tvDetailLeftTime = (TextView) view.findViewById(R.id.tv_detail_left_time);
        tvDetailFormatDate = (TextView) view.findViewById(R.id.tv_detail_format_date);
    }

    private void init() {
        EventInfo eventInfo = EventDataCache.getInstance().get(position);
        tvDetailTitle.setText(eventInfo.getTitle());
        tvDetailLeftTime.setText(DateUtil.getDayLeftByTargetMillis(eventInfo.getTargetTime()) + "");
        tvDetailFormatDate.setText(DateUtil.getTimeByMillis(eventInfo.getTargetTime()));
    }
}
