package com.mmcs.societymaintainance.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.mmcs.societymaintainance.R;

import java.util.Calendar;

import es.dmoral.toasty.Toasty;

public class AddEmployeeActivity extends AppCompatActivity {
EditText edt_joining_date,edt_ending_date,edt_name,edt_email,edt_mobile,edt_designation,edt_password,edt_present_address,edt_permanent_address,edt_national_id;
    Calendar calendar;
    int DD, MM, YY;
    ImageView imageView;
    Button btn_take_photo,btn_save;
    private static final int CAMERA_REQUEST = 1888;
    static final int DATE_DIALOG_ID = 1;
    static final int DATE_DIALOG_ID2 = 2;
    int cur = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);
        edt_joining_date=findViewById(R.id.edt_joining_date);
        edt_name=findViewById(R.id.edt_name);
        edt_email=findViewById(R.id.edt_email);
        edt_mobile=findViewById(R.id.edt_mobile);
        edt_password=findViewById(R.id.edt_password);
        edt_present_address=findViewById(R.id.edt_present_address);
        edt_permanent_address=findViewById(R.id.edt_permanent_address);
        edt_national_id=findViewById(R.id.edt_national_id);
        edt_designation=findViewById(R.id.edt_designation);
        edt_ending_date=findViewById(R.id.edt_ending_date);
        btn_save=findViewById(R.id.btn_save);
        imageView=findViewById(R.id.imageView);
        btn_take_photo=findViewById(R.id.btn_take_photo);
        calendar = Calendar.getInstance();
        DD = calendar.get(Calendar.DAY_OF_MONTH);
        MM = calendar.get(Calendar.MONTH);
        YY = calendar.get(Calendar.YEAR);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=edt_name.getText().toString();
                String email=edt_email.getText().toString();
                String mobile=edt_mobile.getText().toString();
                String password=edt_password.getText().toString();
                String present_add=edt_present_address.getText().toString();
                String permanent_add=edt_permanent_address.getText().toString();
                String national_id=edt_national_id.getText().toString();
                String designation=edt_designation.getText().toString();
                String joining_date=edt_joining_date.getText().toString();
                String ending_date=edt_ending_date.getText().toString();
                if(name.equals("")){
                    Toasty.error(AddEmployeeActivity.this,"Enter Name",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(email.equals("")){
                    Toasty.error(AddEmployeeActivity.this,"Enter Email",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (mobile.trim().isEmpty() || mobile.length() < 10 || mobile.length() > 12) {
                    Toasty.error(AddEmployeeActivity.this, "Enter Valid Mobile Number", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(password.equals("")){
                    Toasty.error(AddEmployeeActivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(present_add.equals("")){
                    Toasty.error(AddEmployeeActivity.this, "Enter Present Address", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(permanent_add.equals("")){
                    Toasty.error(AddEmployeeActivity.this, "Enter Permanent Address", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (national_id.equals("")){
                    Toasty.error(AddEmployeeActivity.this, "Enter National Id", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(designation.equals("")){
                    Toasty.error(AddEmployeeActivity.this, "Select Designation", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(joining_date.equals("")){
                    Toasty.error(AddEmployeeActivity.this, "Select Joining Date", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(ending_date.equals("")){
                    Toasty.error(AddEmployeeActivity.this, "Select Joining Date", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    Toasty.success(AddEmployeeActivity.this, "Successfully Added", Toast.LENGTH_SHORT).show();
                }
            }
        });
        edt_joining_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DATE_DIALOG_ID);
            }
        });
        edt_ending_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DATE_DIALOG_ID2);
            }
        });
        btn_take_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });
        setTitle();
        back();

    }
    private void setTitle() {
        TextView title = (TextView) findViewById(R.id.title);
        title.setText(getString(R.string.add_member));
    }
    private void back() {
        RelativeLayout drawerIcon = (RelativeLayout) findViewById(R.id.drawerIcon);
        drawerIcon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                System.out.println("onCreateDialog  : " + id);
                cur = DATE_DIALOG_ID;
                return new DatePickerDialog(this, onDateSetListener, YY, MM, DD);
            case DATE_DIALOG_ID2:
                cur = DATE_DIALOG_ID2;
                System.out.println("onCreateDialog2  : " + id);
                return new DatePickerDialog(this, onDateSetListener, YY, MM, DD);

        }

        return null;
    }

    DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int d, int m, int y) {
            if (cur == DATE_DIALOG_ID) {
                // set selected date into textview
                if ((m + 1) < 10)
                    edt_joining_date.setText(String.valueOf(d) + "-0" + String.valueOf(m + 1) + "-" + String.valueOf(y));
                else
                    edt_joining_date.setText(String.valueOf(d) + "-" + String.valueOf(m + 1) + "-" + String.valueOf(y));

            } else {
                if ((m + 1) < 10)
                    edt_ending_date.setText(String.valueOf(d) + "-0" + String.valueOf(m + 1) + "-" + String.valueOf(y));
                else
                    edt_ending_date.setText(String.valueOf(d) + "-" + String.valueOf(m + 1) + "-" + String.valueOf(y));
            }
        }
    };
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap mphoto = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(mphoto);
        }
    }

}
