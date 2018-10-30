package com.mmcs.societymaintainance.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mmcs.societymaintainance.R;
import com.mmcs.societymaintainance.model.ComplaintModel;

public class ComplaintDetailActivity extends AppCompatActivity {
ComplaintModel complaintModel;
TextView txtDepartment,txtTitle,txtDate,txt_c_des,txtStatus;
EditText edt_comment;
Button cancel,resolved;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_detail);
        complaintModel = (ComplaintModel) getIntent().getSerializableExtra(getString(R.string.comp_model));
        txtDepartment=findViewById(R.id.txtDepartment);
        txtTitle=findViewById(R.id.txtTitle);
        txtDate=findViewById(R.id.txtDate);
        txt_c_des=findViewById(R.id.txt_c_des);
        txtStatus=findViewById(R.id.txtStatus);
        cancel=findViewById(R.id.cancel);
        resolved=findViewById(R.id.resolved);
        edt_comment=findViewById(R.id.edt_comment);

        txtDepartment.setText(getString(R.string.dept)+complaintModel.getDepartment());
        txtTitle.setText(getString(R.string.titl)+complaintModel.getTitle());
        txtDate.setText(getString(R.string.date)+complaintModel.getDate());
        txt_c_des.setText(getString(R.string.desc)+complaintModel.getC_description());
        txtStatus.setText(getString(R.string.status)+complaintModel.getStatus());
        if (complaintModel.getStatus().equalsIgnoreCase("PENDING"))
        {
            edt_comment.setVisibility(View.VISIBLE);
            cancel.setVisibility(View.VISIBLE);
            resolved.setVisibility(View.VISIBLE);

        }

        SpannableStringBuilder sb = new SpannableStringBuilder(txtDepartment.getText());
        ForegroundColorSpan fcs = new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary));

        sb.setSpan(fcs, 0, 11, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        txtDepartment.setText(sb);

        sb = new SpannableStringBuilder(txtTitle.getText());
        fcs = new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary));
        sb.setSpan(fcs, 0, 6, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        txtTitle.setText(sb);

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
}
