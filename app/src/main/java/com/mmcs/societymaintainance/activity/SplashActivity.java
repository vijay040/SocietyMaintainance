package com.mmcs.societymaintainance.activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.mmcs.societymaintainance.R;

public class SplashActivity extends AppCompatActivity {
    ImageView image_wlcm, image_view;
    Animation animBlink, animZoom_out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        image_wlcm = findViewById(R.id.image_wlcm);
        image_view = findViewById(R.id.image_view);
        animBlink = AnimationUtils.loadAnimation(this, R.anim.blink);
        animZoom_out = AnimationUtils.loadAnimation(this, R.anim.zoom_out);
        image_wlcm.startAnimation(animBlink);
        image_view.startAnimation(animZoom_out);
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
                startActivity(intent);
                finish();
            }
        }, 4000);
    }
}



