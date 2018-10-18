package com.mmcs.societymaintainance.activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
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
import android.widget.Toast;

import com.mmcs.societymaintainance.R;
import java.util.Calendar;

import es.dmoral.toasty.Toasty;

public class AddResidentActivity extends AppCompatActivity {
    int DD, MM, YY;
    Calendar calendar;
    ImageView imageView;
    Button btn_take_photo,btn_save;
    EditText edt_owner_name,edt_email,edt_mobile,edt_password,edt_present_address,edt_permanent_address,edt_national_id,edt_floor,edt_unit_no;
    private static final int CAMERA_REQUEST = 1888;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_resident);
        imageView=findViewById(R.id.imageView);
        edt_owner_name=findViewById(R.id.edt_owner_name);
        edt_email=findViewById(R.id.edt_email);
        edt_mobile=findViewById(R.id.edt_mobile);
        edt_password=findViewById(R.id.edt_password);
        edt_present_address=findViewById(R.id.edt_present_address);
        edt_permanent_address=findViewById(R.id.edt_permanent_address);
        edt_national_id=findViewById(R.id.edt_national_id);
        edt_floor=findViewById(R.id.edt_floor);
        edt_unit_no=findViewById(R.id.edt_unit_no);
        btn_take_photo=findViewById(R.id.btn_take_photo);
        btn_save=findViewById(R.id.btn_save);
        calendar = Calendar.getInstance();
        DD = calendar.get(Calendar.DAY_OF_MONTH);
        MM = calendar.get(Calendar.MONTH);
        YY = calendar.get(Calendar.YEAR);
        btn_take_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=edt_owner_name.getText().toString();
                String email=edt_email.getText().toString();
                String mobile=edt_mobile.getText().toString();
                String password=edt_password.getText().toString();
                String present_add=edt_present_address.getText().toString();
                String permanent_add=edt_permanent_address.getText().toString();
                String national_id=edt_national_id.getText().toString();
                String floor=edt_floor.getText().toString();
                String unit=edt_unit_no.getText().toString();
                if(name.equals("")){
                    Toasty.error(AddResidentActivity.this,"Enter Owner Name",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(email.equals("")){
                    Toasty.error(AddResidentActivity.this,"Enter Email",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (mobile.trim().isEmpty() || mobile.length() < 10 || mobile.length() > 12) {
                    Toasty.error(AddResidentActivity.this, "Enter Valid Mobile Number", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(password.equals("")){
                    Toasty.error(AddResidentActivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(present_add.equals("")){
                    Toasty.error(AddResidentActivity.this, "Enter Present Address", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(permanent_add.equals("")){
                    Toasty.error(AddResidentActivity.this, "Enter Permanent Address", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(national_id.equals("")){
                    Toasty.error(AddResidentActivity.this, "Enter National Id", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (floor.equals("")){
                    Toasty.error(AddResidentActivity.this, "Select Floor", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (unit.equals("")) {
                    Toasty.error(AddResidentActivity.this, "Select Unit No.", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    Toasty.success(AddResidentActivity.this,"Successfully Added",Toast.LENGTH_SHORT).show();
                }
            }
        });


        setTitle();
        back();
    }
    private void setTitle() {
        TextView title = (TextView) findViewById(R.id.title);
        title.setText(getString(R.string.add_resident));
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

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap mphoto = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(mphoto);
        }
    }
}
