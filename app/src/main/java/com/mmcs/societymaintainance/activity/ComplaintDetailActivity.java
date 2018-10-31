package com.mmcs.societymaintainance.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mmcs.societymaintainance.R;
import com.mmcs.societymaintainance.model.ComplaintModel;
import com.mmcs.societymaintainance.model.ComplaintRestMeta;
import com.mmcs.societymaintainance.model.LoginModel;
import com.mmcs.societymaintainance.util.Shprefrences;
import com.mmcs.societymaintainance.util.Singleton;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComplaintDetailActivity extends AppCompatActivity {
ComplaintModel complaintModel;
TextView txtDepartment,txtTitle,txtDate,txt_c_des,txtStatus, txtFloor, txtUnit;
EditText edt_comment;
    LoginModel loginModel;
Button btn_ok,resolved;
    Shprefrences sh;
    ProgressBar progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_detail);
        complaintModel = (ComplaintModel) getIntent().getSerializableExtra(getString(R.string.comp_model));
        txtDepartment=findViewById(R.id.txtDepartment);
        txtTitle=findViewById(R.id.txtTitle);
        sh=new Shprefrences(this);
        loginModel=sh.getLoginModel(getResources().getString(R.string.login_model));
        txtDate=findViewById(R.id.txtDate);
        txt_c_des=findViewById(R.id.txt_c_des);
        txtStatus=findViewById(R.id.txtStatus);
        txtFloor = findViewById(R.id.txtFloor);
        txtUnit = findViewById(R.id.txtUnit);
        btn_ok=findViewById(R.id.btn_ok);
        progress = findViewById(R.id.progress);
        resolved=findViewById(R.id.resolved);
        edt_comment=findViewById(R.id.edt_comment);
        txtFloor.setText(getString(R.string.floor) + complaintModel.getFloor_no());
        txtUnit.setText(getString(R.string.unit_no) + complaintModel.getUnit_no());

        txtDepartment.setText(getString(R.string.dept)+complaintModel.getDepartment());
        txtTitle.setText(getString(R.string.titl)+complaintModel.getTitle());
        txtDate.setText(getString(R.string.date)+complaintModel.getDate());
        txt_c_des.setText(getString(R.string.desc)+complaintModel.getC_description());
        txtStatus.setText(getString(R.string.status)+complaintModel.getStatus());
        if (complaintModel.getStatus().equalsIgnoreCase("PENDING"))
        {
            edt_comment.setVisibility(View.VISIBLE);
            btn_ok.setVisibility(View.VISIBLE);
            resolved.setVisibility(View.VISIBLE);

        }
       else
            btn_ok.setVisibility(View.VISIBLE);

        resolved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg=edt_comment.getText().toString();
                if (msg.equals("")){
                    Toasty.error(ComplaintDetailActivity.this,"Please Enter Your Comment", Toast.LENGTH_SHORT).show();
                }
                else{
                    progress.setVisibility(View.VISIBLE);
                postComplaintStatus(loginModel.getId(),loginModel.getType(),loginModel.getBranch_id(),getString(R.string.resolved),msg);
            }
            }

        });
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
            }

        });

        SpannableStringBuilder sb = new SpannableStringBuilder(txtDepartment.getText());
        ForegroundColorSpan fcs = new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary));

        sb.setSpan(fcs, 0, 11, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        txtDepartment.setText(sb);

        sb = new SpannableStringBuilder(txtTitle.getText());
        fcs = new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary));
        sb.setSpan(fcs, 0, 6, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        txtTitle.setText(sb);

        sb = new SpannableStringBuilder(txtFloor.getText());
        fcs = new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary));
        sb.setSpan(fcs, 0, 9, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        txtFloor.setText(sb);

        sb = new SpannableStringBuilder(txtUnit.getText());
        fcs = new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary));
        sb.setSpan(fcs, 0, 8, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        txtUnit.setText(sb);

        sb = new SpannableStringBuilder(txtDate.getText());
        fcs = new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary));
        sb.setSpan(fcs, 0, 5, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        txtDate.setText(sb);
        sb = new SpannableStringBuilder(txtStatus.getText());
        fcs = new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary));
        sb.setSpan(fcs, 0, 7, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        txtStatus.setText(sb);

        sb = new SpannableStringBuilder(txt_c_des.getText());
        fcs = new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary));
        sb.setSpan(fcs, 0, 12, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        txt_c_des.setText(sb);
        setTitle();
        back();

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
    private void postComplaintStatus(String user_id,String type,String branchId,String status,String comment )
    {

        Singleton.getInstance().getApi().postComplaintStatus(user_id,type,""+branchId,status,comment,complaintModel.getComplain_id()
                ).enqueue(new Callback<ComplaintRestMeta>() {
            @Override
            public void onResponse(Call<ComplaintRestMeta> call, Response<ComplaintRestMeta> response) {
                Toasty.success(ComplaintDetailActivity.this, " Successfully Updated",
                        Toast.LENGTH_LONG).show();
                finish();

            }

            @Override
            public void onFailure(Call<ComplaintRestMeta> call, Throwable t) {
                progress.setVisibility(View.GONE);
                Toasty.error(ComplaintDetailActivity.this,"Sorry Try Again", Toast.LENGTH_SHORT).show();

            }
        });
    }
}