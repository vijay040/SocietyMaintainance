package com.mmcs.societymaintainance.adaptor;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mmcs.societymaintainance.R;
import com.mmcs.societymaintainance.model.HomeItemModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class HomeRecyclerAdaptor  extends RecyclerView.Adapter<HomeRecyclerAdaptor.ViewHolder> {

    ArrayList<HomeItemModel> list;
    Context context;
    private LayoutInflater mInflater;
    ImageView imgUserProfile;
    TextView txtTitle;
    public HomeRecyclerAdaptor(Context context, ArrayList<HomeItemModel> list)
    {
        this.list=list;
        this.context=context;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_home_inf, parent, false);
         imgUserProfile=view.findViewById(R.id.imgUserProfile);
        txtTitle=view.findViewById(R.id.txt_title);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        txtTitle.setText(list.get(position).getTitle()+"");
        Uri uri = Uri.parse("android.resource://com.mmcs.societymaintainance/drawable/"+list.get(position).getImage());
        Log.e("***********",uri.getPath());
        Picasso.get().load(uri).into(imgUserProfile);
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
