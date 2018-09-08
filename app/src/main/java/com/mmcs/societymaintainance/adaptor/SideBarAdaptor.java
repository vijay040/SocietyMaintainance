package com.mmcs.societymaintainance.adaptor;

import android.content.Context;
import android.content.Intent;
import android.support.v7.view.menu.MenuAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mmcs.societymaintainance.R;
import com.mmcs.societymaintainance.activity.AddResidentActivity;
import com.mmcs.societymaintainance.activity.AttendanceActivity;
import com.mmcs.societymaintainance.activity.ComplaintActivity;
import com.mmcs.societymaintainance.activity.ProfileActivity;
import com.mmcs.societymaintainance.activity.VisitorMgmtActivity;
import com.mmcs.societymaintainance.model.HomeItemModel;

import java.util.ArrayList;

/**
 * Created by Lenovo on 08-09-2018.
 */

public class SideBarAdaptor extends BaseAdapter {
    ArrayList<HomeItemModel> list;
    Context ctx;

    public SideBarAdaptor(Context ctx, ArrayList<HomeItemModel> list) {
        this.ctx = ctx;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.menu_item_inf, null);
        }

        TextView title = view.findViewById(R.id.txtTitle);
        title.setText(list.get(i).getTitle());
      ImageView imgUserProfile=view.findViewById(R.id.ic);
        imgUserProfile.setBackground(ctx.getResources().getDrawable(list.get(i).getImage()));
        RelativeLayout relativeLayout=view.findViewById(R.id.relativeLayout);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (list.get(i).getTitle())
                {
                    case "Profile":
                        ctx.startActivity(new Intent(ctx, ProfileActivity.class));
                        break;
                    case "Add Owner":
                        ctx.startActivity(new Intent(ctx, AddResidentActivity.class));
                        break;
                    case "Attendance":
                        ctx.startActivity(new Intent(ctx, AttendanceActivity.class));
                        break;
                    case "Complaint":
                        ctx.startActivity(new Intent(ctx, ComplaintActivity.class));
                        break;
                    case "Visitor Management":
                        ctx.startActivity(new Intent(ctx, VisitorMgmtActivity.class));
                        break;

                }

            }
        });
        return view;
    }
}
