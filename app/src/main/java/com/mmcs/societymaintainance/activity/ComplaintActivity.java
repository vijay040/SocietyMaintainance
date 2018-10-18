package com.mmcs.societymaintainance.activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mmcs.societymaintainance.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import es.dmoral.toasty.Toasty;

public class ComplaintActivity extends AppCompatActivity{
    EditText edt_date,edt_complaint_title,edt_description;
    Button btn_submit;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);
        edt_complaint_title=findViewById(R.id.edt_complaint_title);
        edt_description=findViewById(R.id.edt_description);
        btn_submit=findViewById(R.id.btn_submit);
        edt_date=findViewById(R.id.edt_date);
        btn_submit=findViewById(R.id.btn_submit);
        DateFormat df = new SimpleDateFormat(getString(R.string.date_formate));
        final String createddate = df.format(Calendar.getInstance().getTime());
        edt_date.setText(createddate);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title=edt_complaint_title.getText().toString();
                String description=edt_description.getText().toString();
                if(title.equals("")){
                    Toasty.error(ComplaintActivity.this,"Enter Complaint Title",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(description.equals("")){
                    Toasty.error(ComplaintActivity.this,"Enter Complaint Description",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toasty.success(ComplaintActivity.this,"Successfully Added",Toast.LENGTH_SHORT).show();
                }

            }
        });
        setTitle();
        back();
    }
      private void setTitle() {
        TextView title = (TextView) findViewById(R.id.title);
        title.setText(getString(R.string.complaint_title));
    }
    private void back() {
        RelativeLayout drawerIcon = (RelativeLayout) findViewById(R.id.drawerIcon);
        drawerIcon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });
    }
}



