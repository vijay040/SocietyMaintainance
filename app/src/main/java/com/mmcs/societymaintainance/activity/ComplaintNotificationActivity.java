package com.mmcs.societymaintainance.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mmcs.societymaintainance.R;

public class ComplaintNotificationActivity extends AppCompatActivity {
    TextView txtDepartment,txtTitle,txtDate,txt_c_des;
    Button reject,accept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_notification);
        txtDepartment=findViewById(R.id.txtDepartment);
        txtTitle=findViewById(R.id.txtTitle);
        txtDate=findViewById(R.id.txtDate);
        txt_c_des=findViewById(R.id.txt_c_des);
        accept=findViewById(R.id.accept);
        reject=findViewById(R.id.reject);
        back();
        setTitle();
    }
    private void back() {
        RelativeLayout drawerIcon = (RelativeLayout) findViewById(R.id.drawerIcon);
        drawerIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void setTitle() {
        TextView title = (TextView) findViewById(R.id.title);
        title.setText(getString(R.string.comp_detail));
    }
}
