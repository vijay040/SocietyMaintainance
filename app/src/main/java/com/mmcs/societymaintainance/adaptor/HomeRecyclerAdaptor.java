package com.mmcs.societymaintainance.adaptor;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mmcs.societymaintainance.R;
import com.mmcs.societymaintainance.activity.AddMemberActivity;
import com.mmcs.societymaintainance.activity.AddResidentActivity;
import com.mmcs.societymaintainance.activity.AddVendorAvtivity;
import com.mmcs.societymaintainance.activity.AttendanceActivity;
import com.mmcs.societymaintainance.activity.ComplaintActivity;
import com.mmcs.societymaintainance.activity.ProfileActivity;
import com.mmcs.societymaintainance.activity.VehicleActivity;
import com.mmcs.societymaintainance.activity.VisitorMgmtActivity;
import com.mmcs.societymaintainance.model.HomeItemModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class HomeRecyclerAdaptor  extends RecyclerView.Adapter<HomeRecyclerAdaptor.ViewHolder> {

    ArrayList<HomeItemModel> list;
    Context context;
    private LayoutInflater mInflater;
    ImageView imgUserProfile;
    TextView txtTitle;
    RelativeLayout layUser;
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
        layUser=view.findViewById(R.id.layUser);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        txtTitle.setText(list.get(position).getTitle()+"");
       // Uri uri = Uri.parse("android.resource://com.mmcs.societymaintainance/drawable/"+list.get(position).getImage());
        imgUserProfile.setBackground(context.getResources().getDrawable(list.get(position).getImage()));
        //Picasso.get().load(uri).into(imgUserProfile);

        layUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (list.get(position).getTitle())
                {
                    case "Profile":
                        context.startActivity(new Intent(context, ProfileActivity.class));
                        break;
                    case "Add Owner":
                        context.startActivity(new Intent(context, AddResidentActivity.class));
                        break;
                    case "Attendance":
                        context.startActivity(new Intent(context, AttendanceActivity.class));
                        break;
                    case "Complaint":
                        context.startActivity(new Intent(context, ComplaintActivity.class));
                        break;
                    case "Visitor Management":
                        context.startActivity(new Intent(context, VisitorMgmtActivity.class));
                        break;

                    case "Add Member":
                        context.startActivity(new Intent(context, AddMemberActivity.class));
                        break;

                }

            }
        });
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
