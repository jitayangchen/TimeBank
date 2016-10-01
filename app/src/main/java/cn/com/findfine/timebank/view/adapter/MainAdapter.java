package cn.com.findfine.timebank.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import cn.com.findfine.timebank.R;
import cn.com.findfine.timebank.data.bean.EventInfo;
import cn.com.findfine.timebank.data.cache.EventDataCache;
import cn.com.findfine.timebank.util.DateUtil;
import cn.com.findfine.timebank.view.activity.EventDetailActivity;

/**
 * Created by yangchen on 16/9/3.
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private Context context;
    private final EventDataCache eventDataCache;

    public MainAdapter(Context context) {
        this.context = context;
        eventDataCache = EventDataCache.getInstance();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_main, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        EventInfo eventInfo = eventDataCache.get(position);
        if (null != eventInfo) {
            holder.tvTitle.setText(eventInfo.getTitle());

            holder.tvTargetTime.setText(DateUtil.getTimeByMillis(eventInfo.getTargetTime()));
            holder.tvTimeLeft.setText(DateUtil.getDayLeftByTargetMillis(eventInfo.getTargetTime()) + " 天");
        }
    }

    @Override
    public int getItemCount() {
        if (eventDataCache.getEventInfos() == null) {
            return 0;
        }
        return eventDataCache.getEventInfos().size();
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
                    Intent intent = new Intent(context, EventDetailActivity.class);
                    intent.putExtra(EventDetailActivity.POSITION, getLayoutPosition());
                    context.startActivity(intent);
                }
            });
        }
    }

}
