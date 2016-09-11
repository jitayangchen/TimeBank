package cn.com.findfine.timebank.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.com.findfine.timebank.R;
import cn.com.findfine.timebank.data.bean.EventInfo;
import cn.com.findfine.timebank.view.activity.EventDetailActivity;

/**
 * Created by yangchen on 16/9/3.
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private Context context;
    private List<EventInfo> eventInfoList = null;

    public MainAdapter(Context context) {
        this.context = context;
    }

    public List<EventInfo> getEventInfoList() {
        return eventInfoList;
    }

    public void setEventInfoList(List<EventInfo> eventInfoList) {
        if (this.eventInfoList == null) {
            this.eventInfoList = new ArrayList<>();
        }
        this.eventInfoList.addAll(eventInfoList);
    }

    public void clearData() {
        if (this.eventInfoList != null && this.eventInfoList.size() > 0) {
            this.eventInfoList.clear();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_main, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.i("YYY", "position === " + position);
    }

    @Override
    public int getItemCount() {
        return 100;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivIcon;
        private TextView tvTitle;
        private TextView tvTargetTime;
        private TextView tvTimeLeft;

        public ViewHolder(View itemView) {
            super(itemView);
            ivIcon = (ImageView) itemView.findViewById(R.id.iv_icon);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvTargetTime = (TextView) itemView.findViewById(R.id.tv_target_time);
            tvTimeLeft = (TextView) itemView.findViewById(R.id.tv_time_left);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.startActivity(new Intent(context, EventDetailActivity.class));
                }
            });
        }
    }
}
