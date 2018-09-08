package com.mmcs.societymaintainance.adaptor;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mmcs.societymaintainance.R;
import com.mmcs.societymaintainance.model.HomeItemModel;

import java.util.ArrayList;


public class HomeRecyclerAdaptor  extends RecyclerView.Adapter<HomeRecyclerAdaptor.ViewHolder> {

    ArrayList<HomeItemModel> list;
    Context context;
    private LayoutInflater mInflater;
    public HomeRecyclerAdaptor(Context context, ArrayList<HomeItemModel> list)
    {
        this.list=list;
        this.context=context;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_home_inf, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
