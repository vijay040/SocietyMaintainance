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
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.mmcs.societymaintainance.R;
import com.mmcs.societymaintainance.fragment.FragmentHome;
import java.net.URLEncoder;

public class DrawerActivity extends AppCompatActivity {
    TextView txtRequest,txtComplaint,txtParking,txtNotice,txtMeeting,txtBilling,txtAddVendor,txtlogout;
    DrawerLayout drawerLayout;
    RelativeLayout drawerIcon;
    public static FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        txtRequest = findViewById(R.id.txtRequest);
        txtComplaint = findViewById(R.id.txtComplaint);
        txtParking = findViewById(R.id.txtParking);
        txtNotice = findViewById(R.id.txtNotice);
        txtMeeting = findViewById(R.id.txtMeeting);
        txtBilling = findViewById(R.id.txtBilling);
        txtlogout = findViewById(R.id.txtlogout);
        txtAddVendor=findViewById(R.id.txtAddVendor);
        fragmentManager = getSupportFragmentManager();
        pushFragment(new FragmentHome());
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        drawerIcon = (RelativeLayout) findViewById(R.id.drawerIcon);
        final ImageView imgDrawer = findViewById(R.id.imgDrawer);
        ImageView whatsapp=(ImageView) findViewById(R.id.whatsapp);
      imgDrawer.setBackground(ContextCompat.getDrawable(DrawerActivity.this, R.drawable.ic_menu));
        setTitle();
      /*  whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openWhatsApp();
            }
        });*/
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
        txtAddVendor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DrawerActivity.this,AddVendorAvtivity.class));
                imgDrawer.setBackground(ContextCompat.getDrawable(DrawerActivity.this, R.drawable.ic_menu));

            }
        });
        txtRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DrawerActivity.this, MakeRequestActivity.class));
                drawerLayout.closeDrawer(Gravity.LEFT);
                imgDrawer.setBackground(ContextCompat.getDrawable(DrawerActivity.this, R.drawable.ic_menu));

            }
        });
        txtComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DrawerActivity.this, ComplaintActivity.class));
                drawerLayout.closeDrawer(Gravity.LEFT);
                imgDrawer.setBackground(ContextCompat.getDrawable(DrawerActivity.this, R.drawable.ic_menu));

            }
        });
        txtParking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DrawerActivity.this, "this is Parking", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(Gravity.LEFT);
                imgDrawer.setBackground(ContextCompat.getDrawable(DrawerActivity.this, R.drawable.ic_menu));

            }
        });
        txtNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             startActivity(new Intent(DrawerActivity.this,NoticeActivity.class));
                drawerLayout.closeDrawer(Gravity.LEFT);
                imgDrawer.setBackground(ContextCompat.getDrawable(DrawerActivity.this, R.drawable.ic_menu));

            }
        });
        txtMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DrawerActivity.this, "this is Our Meeting ", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(Gravity.LEFT);
                imgDrawer.setBackground(ContextCompat.getDrawable(DrawerActivity.this, R.drawable.ic_menu));

            }
        });
        txtBilling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DrawerActivity.this, "this is Our billing", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(Gravity.LEFT);
                imgDrawer.setBackground(ContextCompat.getDrawable(DrawerActivity.this, R.drawable.ic_menu));

            }
        });
        txtlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DrawerActivity.this, "successfully logout", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(Gravity.LEFT);
                imgDrawer.setBackground(ContextCompat.getDrawable(DrawerActivity.this, R.drawable.ic_menu));

            }
        });
    }
    private void setTitle() {
        TextView title = (TextView) findViewById(R.id.title);
        title.setText(getString(R.string.home_page));
    }
   /* private  void openWhatsApp(){
        PackageManager packageManager = this.getPackageManager();
        Intent i = new Intent(Intent.ACTION_VIEW);
        String phone="+919910482920";
        String message="This is the Admin Contact ...." +
                "Write Your Message Here";
        try {
            String url = "https://api.whatsapp.com/send?phone="+ phone +"&text=" + URLEncoder.encode(message, "UTF-8");
            i.setPackage("com.whatsapp");
            i.setData(Uri.parse(url));
            if (i.resolveActivity(packageManager) != null) {
               startActivity(i);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }*/
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