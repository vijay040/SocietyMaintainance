package com.mmcs.societymaintainance.activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mmcs.societymaintainance.R;
import com.mmcs.societymaintainance.model.LoginResMeta;
import com.mmcs.societymaintainance.util.Singleton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddComplaintActivity extends AppCompatActivity{
    EditText edt_date,edt_complaint_title,edt_description;
    Button btn_submit;
    ProgressBar progress;
    Calendar calendar;
    int DD, MM, YY;
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
        progress = findViewById(R.id.progress);
        btn_submit=findViewById(R.id.btn_submit);
        calendar = Calendar.getInstance();
        DD = calendar.get(Calendar.DAY_OF_MONTH);
        MM = calendar.get(Calendar.MONTH);
        YY = calendar.get(Calendar.YEAR);
        if ((MM + 1) < 10)
            edt_date.setText(String.valueOf(YY) + "-0" + String.valueOf(MM + 1) + "-" + String.valueOf(DD));
        else
            edt_date.setText(String.valueOf(YY) + "-" + String.valueOf(MM + 1) + "-" + String.valueOf(DD));

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title=edt_complaint_title.getText().toString();
                String description=edt_description.getText().toString();
                String date=edt_date.getText().toString();
                if(title.equals("")){
                    Toasty.error(AddComplaintActivity.this,"Enter Complaint Title",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(description.equals("")){
                    Toasty.error(AddComplaintActivity.this,"Enter Complaint Description",Toast.LENGTH_SHORT).show();
                }
                else{
                    progress.setVisibility(View.VISIBLE);
                  postComplaint("","",title,description,date,date.split("-")[1],date.split("-")[0]);
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
    private void postComplaint(String user_id, String branchId, String title,String des,String date,String month,String year){
        Singleton.getInstance().getApi().postComplaint(user_id, branchId,title,des,date,month,year).enqueue(new Callback<LoginResMeta>() {
            @Override
            public void onResponse(Call<LoginResMeta> call, Response<LoginResMeta> response) {
                progress.setVisibility(View.GONE);
                Toasty.success(AddComplaintActivity.this, "Data Successfully Submitted",
                        Toast.LENGTH_LONG).show();
                finish();
            }

            @Override
            public void onFailure(Call<LoginResMeta> call, Throwable t) {
                progress.setVisibility(View.GONE);
                Toasty.error(AddComplaintActivity.this,"Sorry Try Again", Toast.LENGTH_SHORT).show();
            }
        });
    }
}



