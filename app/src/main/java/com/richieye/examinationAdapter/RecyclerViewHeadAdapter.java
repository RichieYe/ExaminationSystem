package com.richieye.examinationAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by RichieYe on 2016/6/14.
 */

public class RecyclerViewHeadAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context mContext;

    List<String> list;

    public RecyclerViewHeadAdapter(Context context,List<String> list)
    {
        this.mContext=context;
        this.list=list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
