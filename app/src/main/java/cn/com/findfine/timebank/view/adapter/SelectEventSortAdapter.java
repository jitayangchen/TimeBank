package cn.com.findfine.timebank.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.com.findfine.timebank.R;
import cn.com.findfine.timebank.data.cache.EventSortCache;

/**
 * Created by yangchen on 16/9/11.
 */
public class SelectEventSortAdapter extends RecyclerView.Adapter<SelectEventSortAdapter.ViewHolder> {

    private Context context;
    private int sortId;

    public SelectEventSortAdapter(Context context, int sortId) {
        this.context = context;
        this.sortId = sortId;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_select_event_sort, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (sortId == position) {
            holder.ivSelected.setVisibility(View.VISIBLE);
        } else {
            holder.ivSelected.setVisibility(View.GONE);
        }
        holder.tvEventSort.setText(EventSortCache.EVENT_SORT[position]);
    }

    @Override
    public int getItemCount() {
        return EventSortCache.EVENT_SORT.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvEventSort;
        private View ivSelected;

        public ViewHolder(View itemView) {
            super(itemView);
            tvEventSort = (TextView) itemView.findViewById(R.id.tv_event_sort);
            ivSelected = itemView.findViewById(R.id.iv_selected);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.putExtra("sort_id", getLayoutPosition());
                    ((Activity)context).setResult(2000, intent);
                    ((Activity) context).finish();

                }
            });
        }
    }
}
