package com.mmcs.societymaintainance.activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mmcs.societymaintainance.R;

public class NoticeActivity extends AppCompatActivity{
    TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        title = (TextView) findViewById(R.id.title);
        setTitle();
        back();
        }
    private void setTitle(){
        TextView title = (TextView) findViewById(R.id.title);
        title.setText(getString(R.string.news));
          }
    private void back(){
        RelativeLayout drawerIcon = (RelativeLayout) findViewById(R.id.drawerIcon);
        drawerIcon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
            });
            }
            }