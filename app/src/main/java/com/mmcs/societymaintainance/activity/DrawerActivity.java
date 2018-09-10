package com.mmcs.societymaintainance.activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuAdapter;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.mmcs.societymaintainance.R;
import com.mmcs.societymaintainance.adaptor.HomeRecyclerAdaptor;
import com.mmcs.societymaintainance.adaptor.SideBarAdaptor;
import com.mmcs.societymaintainance.fragment.FragmentHome;
import com.mmcs.societymaintainance.model.HomeItemModel;
import com.mmcs.societymaintainance.util.Shprefrences;

import java.net.URLEncoder;
import java.util.ArrayList;

public class DrawerActivity extends AppCompatActivity {
    TextView txtRequest,txtComplaint,txtParking,txtNotice,txtMeeting,txtBilling,txtAddVendor,txtlogout;
    DrawerLayout drawerLayout;
    RelativeLayout drawerIcon;
    ListView listView;
    public static FragmentManager fragmentManager;
    Shprefrences sh;
    public static ArrayList<HomeItemModel> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        listView=findViewById(R.id.listItem);
        sh=new Shprefrences(this);
        String type= sh.getString("TYPE","");

        HomeItemModel item=  new HomeItemModel();

       if(type.equalsIgnoreCase("Owner"))
       {
           list =new ArrayList<>();
           item.setImage(R.drawable.ic_user);
           item.setTitle("Profile");
           list.add(item);

           item=  new HomeItemModel();
           item.setTitle("Complaint");
           item.setImage(R.drawable.ic_complain);
           list.add(item);

           item=  new HomeItemModel();
           item.setTitle("Add Member");
           item.setImage(R.drawable.ic_add_member);
           list.add(item);
       }
       else if(type.equalsIgnoreCase("User"))
       {


           list =new ArrayList<>();
           item.setImage(R.drawable.ic_user);
           item.setTitle("Profile");
           list.add(item);

           item=  new HomeItemModel();
           item.setTitle("Attendance");
           item.setImage(R.drawable.ic_attendance);
           list.add(item);
       }
       else if(type.equalsIgnoreCase("Management"))
       {
           list =new ArrayList<>();
           item.setImage(R.drawable.ic_user);
           item.setTitle("Profile");
           list.add(item);

           item= new HomeItemModel();
           item.setTitle("Add Owner");
           item.setImage(R.drawable.ic_recidency);
           list.add(item);


           item=  new HomeItemModel();
           item.setTitle("Visitor Management");
           item.setImage(R.drawable.ic_visitor);
           list.add(item);

           item=  new HomeItemModel();
           item.setTitle("Add Member");
           item.setImage(R.drawable.ic_add_member);
           list.add(item);
       }


        SideBarAdaptor adaptor=new SideBarAdaptor(this,list);
        listView.setAdapter(adaptor);


        fragmentManager = getSupportFragmentManager();
        pushFragment(new FragmentHome());
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        drawerIcon = (RelativeLayout) findViewById(R.id.drawerIcon);
        final ImageView imgDrawer = findViewById(R.id.imgDrawer);
        ImageView whatsapp=(ImageView) findViewById(R.id.whatsapp);
      imgDrawer.setBackground(ContextCompat.getDrawable(DrawerActivity.this, R.drawable.ic_menu));
        setTitle();

        drawerIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                    drawerLayout.closeDrawer(Gravity.LEFT);
                    imgDrawer.setBackground(ContextCompat.getDrawable(DrawerActivity.this, R.drawable.ic_menu));
                } else {
                    drawerLayout.openDrawer(Gravity.LEFT);
                    imgDrawer.setBackground(ContextCompat.getDrawable(DrawerActivity.this, R.drawable.ic_back));
                }
            }
        });

    }
    private void setTitle() {
        TextView title = (TextView) findViewById(R.id.title);
        title.setText(getString(R.string.home_page));
    }

    public static void pushFragment(Fragment fragment){
        if (fragment != null) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.frame_container, fragment);
            transaction.commit();
        } else {
            Log.e("DrawerActivity", "Error in creating fragment");
        }
    }
    @Override
    public void onBackPressed() {
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START);
            ImageView imgDrawer = findViewById(R.id.imgDrawer);
            imgDrawer.setBackground(ContextCompat.getDrawable(DrawerActivity.this, R.drawable.ic_menu));
        } else {
            super.onBackPressed();
        }
    }

}