package com.mmcs.societymaintainance.fragment;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.mmcs.societymaintainance.R;
import com.mmcs.societymaintainance.activity.AddResidentActivity;
import com.mmcs.societymaintainance.activity.AttendanceActivity;
import com.mmcs.societymaintainance.activity.ComplaintActivity;
import com.mmcs.societymaintainance.activity.MakeRequestActivity;
import com.mmcs.societymaintainance.activity.NoticeActivity;
import com.mmcs.societymaintainance.activity.ProfileActivity;
import com.mmcs.societymaintainance.activity.VehicleActivity;
import com.mmcs.societymaintainance.activity.VisitorMgmtActivity;
import com.mmcs.societymaintainance.adaptor.HomeRecyclerAdaptor;
import com.mmcs.societymaintainance.model.HomeItemModel;

import java.util.ArrayList;

public class FragmentHome extends android.support.v4.app.Fragment{
    RelativeLayout layUser,layComplaint,layRequest,layVehicle,layFacility,layParking,layNotice,layMeeting,layBilling,layResidency,layVisitor,layAMCservice,layAttandance;

    RecyclerView rvHome;
    @Override
    public void onCreate( @Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.activity_fragment_home, container, false);
        rvHome=view.findViewById(R.id.rvItems);
        rvHome.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        ArrayList<HomeItemModel> list=new ArrayList<>();
        HomeItemModel item=  new HomeItemModel();
        item.setImage("ic_userprofile");
        item.setTitle("Profile");
        list.add(item);

        HomeRecyclerAdaptor adaptor=new HomeRecyclerAdaptor(getActivity(),list);
        rvHome.setAdapter(adaptor);



        /*layUser=view.findViewById(R.id.layUser);
        layComplaint=view.findViewById(R.id.layComplaint);
        layRequest=view.findViewById(R.id.layRequest);
        layParking=view.findViewById(R.id.layParking);
        layNotice=view.findViewById(R.id.layNotice);
        layMeeting=view.findViewById(R.id.layMeeting);
        layBilling=view.findViewById(R.id.layBilling);
        layResidency=view.findViewById(R.id.layResidency);
        layVisitor=view.findViewById(R.id.layVisitor);
        layAMCservice=view.findViewById(R.id.layAMCservice);
        layFacility=view.findViewById(R.id.layFacility);
        layVehicle=view.findViewById(R.id.layVehicle);
        layAttandance=view.findViewById(R.id.layAttandance);
        layAttandance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),AttendanceActivity.class));
            }
        });
        layVehicle.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),VehicleActivity.class));
            }
        });
        layVisitor.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),VisitorMgmtActivity.class));
            }
        });
        layUser.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),ProfileActivity.class));
            }
        });
        layRequest.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),MakeRequestActivity.class));
            }
        });
        layComplaint.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),ComplaintActivity.class));
            }
        });
        layParking.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Toast.makeText(getActivity(), "this is Parking", Toast.LENGTH_SHORT).show();
            }
        });
        layNotice.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(getActivity(),NoticeActivity.class));
            }
        });
        layMeeting.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Toast.makeText(getActivity(), "this is Our Meeting ", Toast.LENGTH_SHORT).show();
            }
        });
        layBilling.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Toast.makeText(getActivity(),"this is Our Billing Module",Toast.LENGTH_SHORT).show();
            }
        });
        layResidency.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(getActivity(),AddResidentActivity.class));
            }
        });*/
        return view;
    }
}
