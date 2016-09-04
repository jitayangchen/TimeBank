package cn.com.findfine.timebank.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.com.findfine.timebank.R;
import cn.com.findfine.timebank.data.bean.Event;
import cn.com.findfine.timebank.view.activity.EventDetailActivity;

/**
 * Created by yangchen on 16/9/3.
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private Context context;
    private List<Event> eventList = null;

    public MainAdapter(Context context) {
        this.context = context;
    }

    public List<Event> getEventList() {
        return eventList;
    }

    public void setEventList(List<Event> eventList) {
        if (this.eventList == null) {
            this.eventList = new ArrayList<>();
        }
        this.eventList.addAll(eventList);
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

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.startActivity(new Intent(context, EventDetailActivity.class));
                }
            });
        }
    }
}
