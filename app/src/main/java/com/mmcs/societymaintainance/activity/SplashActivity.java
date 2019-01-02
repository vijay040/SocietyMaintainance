package com.mmcs.societymaintainance.activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.mmcs.societymaintainance.R;
import com.mmcs.societymaintainance.model.LoginModel;
import com.mmcs.societymaintainance.util.Shprefrences;

import java.util.List;

public class SplashActivity extends AppCompatActivity {
    ImageView image_wlcm, image_view;
    Animation animBlink, animZoom_out;
    Shprefrences sh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        LoginActivity.fcmToken= FirebaseInstanceId.getInstance().getToken();
        Log.e("MYTAG", "This is your Firebase token     " + LoginActivity.fcmToken);
        image_wlcm = findViewById(R.id.image_wlcm);
        image_view = findViewById(R.id.image_view);
        animBlink = AnimationUtils.loadAnimation(this, R.anim.blink);
        animZoom_out = AnimationUtils.loadAnimation(this, R.anim.zoom_out);
        image_wlcm.startAnimation(animBlink);
        image_view.startAnimation(animZoom_out);
        sh=new Shprefrences(this);



        Intent intent = new Intent();

        String manufacturer = android.os.Build.MANUFACTURER;

        switch (manufacturer) {

            case "xiaomi":
                intent.setComponent(new ComponentName("com.miui.securitycenter",
                        "com.miui.permcenter.autostart.AutoStartManagementActivity"));
                break;
            case "oppo":
                intent.setComponent(new ComponentName("com.coloros.safecenter",
                        "com.coloros.safecenter.permission.startup.StartupAppListActivity"));

                break;
            case "vivo":
                intent.setComponent(new ComponentName("com.vivo.permissionmanager",
                        "com.vivo.permissionmanager.activity.BgStartUpManagerActivity"));
                break;
        }

        List<ResolveInfo> arrayList =  getPackageManager().queryIntentActivities(intent,
                PackageManager.MATCH_DEFAULT_ONLY);

        if (arrayList.size() > 0) {
            startActivity(intent);
        }


        // set animation listener
        Handler h = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                return false;
            }
        });
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
               if(sh.getBoolean("ISLOGIN",false))
                   intent = new Intent(SplashActivity.this, DrawerActivity.class);
                startActivity(intent);
                finish();
            }
        }, 4000);

    }



}



