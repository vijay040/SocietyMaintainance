package com.mmcs.societymaintainance.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mmcs.societymaintainance.R;
import com.mmcs.societymaintainance.model.VisitorModel;

public class VisitorDetailActivity extends AppCompatActivity {
    VisitorModel visitorModel;
    TextView txtName,txt_mobile,txt_address,txtFloor,txtUnit,txtIntime,txtOuttime;
    ImageView image_visitor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor_detail);
        visitorModel = (VisitorModel) getIntent().getSerializableExtra(getString(R.string.visitor_model));
        txtName=findViewById(R.id.txtName);
        txt_mobile=findViewById(R.id.txt_mobile);
        txt_address=findViewById(R.id.txt_address);
        txtIntime=findViewById(R.id.txtIntime);
        txtFloor=findViewById(R.id.txtFloor);
        txtUnit=findViewById(R.id.txtUnit);
        txtOuttime=findViewById(R.id.txtOuttime);
        image_visitor=findViewById(R.id.image_visitor);
        txtName.setText(getString(R.string.name) + visitorModel.getName());
        txt_mobile.setText(getString(R.string.mobile_no) + visitorModel.getMobile());
        txt_address.setText(getString(R.string.address) + visitorModel.getAddress());
        txtFloor.setText(getString(R.string.floor) + visitorModel.getFloor_no());
        txtUnit.setText(getString(R.string.unit_no) + visitorModel.getUnit_no());
        txtIntime.setText(getString(R.string.time_in) + visitorModel.getIntime());
        txtOuttime.setText(getString(R.string.time_out) + visitorModel.getOuttime());

        Glide.with(this).load(visitorModel.getImage()).placeholder(R.drawable.no_image).into(image_visitor);
        SpannableStringBuilder sb = new SpannableStringBuilder(txtName.getText());

        // Picasso.get().load(expensemodel.getImage()).placeholder(R.drawable.ic_bill).resize(100,100).into(image_uploaded);
        // Span to set text color to some RGB value
        ForegroundColorSpan fcs = new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary));
        // Span to make text bold
        //    final StyleSpan bss = new StyleSpan(android.graphics.Typeface.BOLD);
        // Set the text color for first 4 characters
        sb.setSpan(fcs, 0, 5, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        txtName.setText(sb);

        sb = new SpannableStringBuilder(txt_mobile.getText());
        fcs = new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary));
        sb.setSpan(fcs, 0, 10, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        txt_mobile.setText(sb);

        sb = new SpannableStringBuilder(txt_address.getText());
        fcs = new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary));
        sb.setSpan(fcs, 0, 8, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        txt_address.setText(sb);

        sb = new SpannableStringBuilder(txtFloor.getText());
        fcs = new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary));
        sb.setSpan(fcs, 0, 9, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        txtFloor.setText(sb);

        sb = new SpannableStringBuilder(txtUnit.getText());
        fcs = new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary));
        sb.setSpan(fcs, 0, 8, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        txtUnit.setText(sb);

        sb = new SpannableStringBuilder(txtIntime.getText());
        fcs = new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary));
        sb.setSpan(fcs, 0, 8, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        txtIntime.setText(sb);


        sb = new SpannableStringBuilder(txtOuttime.getText());
        fcs = new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary));
        sb.setSpan(fcs, 0, 9, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        txtOuttime.setText(sb);



    }
}
