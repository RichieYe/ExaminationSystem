package com.richieye.examinationAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.richieye.examinationsystem.R;

import java.util.List;

/**
 * Created by RichieYe on 2016/6/14.
 */

public class RecyclerViewHeadAdapter extends RecyclerView.Adapter<RecyclerViewHeadAdapter.ViewHolder> {

    Context mContext;

    List<String> list;

    public RecyclerViewHeadAdapter(Context context,List<String> list)
    {
        this.mContext=context;
        this.list=list;
        Log.e("RecyclerViewHeadAdapter","1111111111");
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.e("RecyclerViewHeadAdapter","2222222222");
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.recycler_head_items,null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvShow.setText(list.get(position));
        Log.e("RecyclerViewHeadAdapter","333333333");
    }


    @Override
    public int getItemCount() {
        Log.e("RecyclerViewHeadAdapter","444444444");
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvShow;
        public ViewHolder(View itemView) {
            super(itemView);
            tvShow=(TextView)itemView.findViewById(R.id.tvRecyclerView_Head_Item);
            Log.e("RecyclerViewHeadAdapter","5555555555");
        }
    }
}
