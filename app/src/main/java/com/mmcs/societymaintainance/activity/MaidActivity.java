package com.mmcs.societymaintainance.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mmcs.societymaintainance.R;

import java.util.Calendar;

import es.dmoral.toasty.Toasty;

public class MaidActivity extends AppCompatActivity {
    EditText edt_dateof_birth,edt_first_name,edt_last_name,edt_mobile,edt_email;
    Calendar calendar;
    int DD, MM, YY;
    Button btn_submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maid);
        edt_dateof_birth=findViewById(R.id.edt_dateof_birth);
        edt_first_name=findViewById(R.id.edt_first_name);
        edt_last_name=findViewById(R.id.edt_last_name);
        edt_mobile=findViewById(R.id.edt_mobile);
        edt_email=findViewById(R.id.edt_email);
        btn_submit=findViewById(R.id.btn_submit);
        calendar = Calendar.getInstance();
        DD = calendar.get(Calendar.DAY_OF_MONTH);
        MM = calendar.get(Calendar.MONTH);
        YY = calendar.get(Calendar.YEAR);
        setTitle();
        back();
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String f_name=edt_first_name.getText().toString();
                String l_name=edt_last_name.getText().toString();
                String mobile=edt_mobile.getText().toString();
                String dob=edt_dateof_birth.getText().toString();
                String email=edt_email.getText().toString();
                if(f_name.equals("")){
                    Toasty.error(MaidActivity.this,"Enter First Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(l_name.equals("")){
                    Toasty.error(MaidActivity.this,"Enter Last Name",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (mobile.trim().isEmpty() || mobile.length() < 10 || mobile.length() > 12) {
                    Toasty.error(MaidActivity.this, "Enter Valid Mobile Number", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(dob.equals("")){
                    Toasty.error(MaidActivity.this, "Enter Date Of Birth", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(email.equals("")){
                    Toasty.error(MaidActivity.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    Toasty.success(MaidActivity.this,"Successfully Added",Toast.LENGTH_SHORT).show();
                }

            }
        });
        edt_dateof_birth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                showDialog(111);
            }
        });
    }
    private void setTitle() {
        TextView title = (TextView) findViewById(R.id.title);
        title.setText(getString(R.string.maid));
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
    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == 111) {
            DatePickerDialog dialog = new DatePickerDialog(this, onDateSetListener, YY, MM, DD);
            dialog.getDatePicker().setMaxDate(System.currentTimeMillis());
            return dialog;
        }
        return onCreateDialog(id);
    }

    DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int y, int m, int d) {
            if ((m + 1) < 10)
                edt_dateof_birth.setText(String.valueOf(y) + "-0" + String.valueOf(m + 1) + "-" + String.valueOf(d));
            else
                edt_dateof_birth.setText(String.valueOf(y) + "-" + String.valueOf(m + 1) + "-" + String.valueOf(d));
        }
    };
}
