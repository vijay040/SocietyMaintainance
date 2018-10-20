package com.mmcs.societymaintainance.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import com.mmcs.societymaintainance.model.LoginModel;
import com.mmcs.societymaintainance.model.LoginResMeta;
import com.mmcs.societymaintainance.util.Shprefrences;
import com.mmcs.societymaintainance.util.Singleton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VisitorMgmtActivity extends AppCompatActivity {
    EditText edtDate, edt_time_in, edt_time_out, edt_visitor_name, edt_mobile, edt_address, edt_floor, edt_unit_no;
    ImageView imageView;
    Button btn_take_photo, btn_save;
    private static final int CAMERA_REQUEST = 1888;
    int H, M;
    Calendar calendar;
    int DD, MM, YY;
    static final int DATE_DIALOG_ID = 1;
    static final int TIME_DIALOG_ID = 2;
    static final int TIME_DIALOG_ID2 = 3;
    int cur = 0;
    Shprefrences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        sh=new Shprefrences(this);
        setContentView(R.layout.activity_visitor_mgmt);
        imageView = findViewById(R.id.imageView);
        edtDate = findViewById(R.id.edtDate);
        edt_time_in = findViewById(R.id.edt_time_in);
        edt_time_out = findViewById(R.id.edt_time_out);
        btn_take_photo = findViewById(R.id.btn_take_photo);
        edt_visitor_name = findViewById(R.id.edt_visitor_name);
        edt_floor = findViewById(R.id.edt_floor);
        edt_mobile = findViewById(R.id.edt_mobile);
        edt_address = findViewById(R.id.edt_address);
        edt_unit_no = findViewById(R.id.edt_unit_no);
        btn_save = findViewById(R.id.btn_save);
        calendar = Calendar.getInstance();
        DD = calendar.get(Calendar.DAY_OF_MONTH);
        MM = calendar.get(Calendar.MONTH);
        YY = calendar.get(Calendar.YEAR);
        H = calendar.get(Calendar.HOUR_OF_DAY);
        M = calendar.get(Calendar.MINUTE);
        if ((MM + 1) < 10)
            edtDate.setText(String.valueOf(YY) + "-0" + String.valueOf(MM + 1) + "-" + String.valueOf(DD));
        else
            edtDate.setText(String.valueOf(YY) + "-" + String.valueOf(MM + 1) + "-" + String.valueOf(DD));
        if (H < 12 && H >= 0) {
            edt_time_in.setText(String.valueOf(H) + ":" + String.valueOf(M) + " " + "AM");
            edt_time_out.setText(String.valueOf(H) + ":" + String.valueOf(M) + " " + "AM");
        } else {
            H -= 12;
            if (H == 0) {
                H = 12;
            }
            edt_time_in.setText(String.valueOf(H) + ":" + String.valueOf(M) + " " + "PM");
            edt_time_out.setText(String.valueOf(H) + ":" + String.valueOf(M) + " " + "PM");
        }
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date = edtDate.getText().toString();
                String name = edt_visitor_name.getText().toString();
                String mobile = edt_mobile.getText().toString();
                String address = edt_address.getText().toString();
                String floor = edt_floor.getText().toString();
                String unit = edt_unit_no.getText().toString();
                String time_in = edt_time_in.getText().toString();
                String time_out = edt_time_out.getText().toString();
                if (date.equals("")) {
                    Toasty.error(VisitorMgmtActivity.this, "Select Date", Toast.LENGTH_SHORT).show();
                    return;
                } else if (name.equals("")) {
                    Toasty.error(VisitorMgmtActivity.this, "Enter Visitor Name", Toast.LENGTH_SHORT).show();
                    return;
                } else if (mobile.trim().isEmpty() || mobile.length() < 10 || mobile.length() > 12) {
                    Toasty.error(VisitorMgmtActivity.this, "Enter Valid Mobile Number", Toast.LENGTH_SHORT).show();
                    return;
                } else if (address.equals("")) {
                    Toasty.error(VisitorMgmtActivity.this, "Enter Address", Toast.LENGTH_SHORT).show();
                    return;
                } else if (floor.equals("")) {
                    Toasty.error(VisitorMgmtActivity.this, "Select Floor", Toast.LENGTH_SHORT).show();
                    return;
                } else if (unit.equals("")) {
                    Toasty.error(VisitorMgmtActivity.this, "Select Unit", Toast.LENGTH_SHORT).show();
                    return;
                } else if (time_in.equals("")) {
                    Toasty.error(VisitorMgmtActivity.this, "Select In Time", Toast.LENGTH_SHORT).show();
                    return;
                } else if (time_out.equals("")) {
                    Toasty.error(VisitorMgmtActivity.this, "Select Out Time", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Toasty.success(VisitorMgmtActivity.this, "Successfully Added", Toast.LENGTH_SHORT).show();
                }

            }
        });
        btn_take_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });

        edtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                showDialog(DATE_DIALOG_ID);
            }
        });
        edt_time_in.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                showDialog(TIME_DIALOG_ID);
            }
        });
        edt_time_out.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                showDialog(TIME_DIALOG_ID2);

            }
        });
        LoginModel model = sh.getLoginModel(getResources().getString(R.string.login_model));

        Log.e("Branchid"+model.getBranch_name(),"*************branchid**********"+model.getBranch_id());

        getVisitors("11",model.getBranch_id());
        getFloorList("120","1");
        getUnitList("","");
        setTitle();
        back();
    }

    private void setTitle() {
        TextView title = (TextView) findViewById(R.id.title);
        title.setText(getString(R.string.visitor));
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

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                System.out.println("onCreateDialog  : " + id);
                cur = DATE_DIALOG_ID;
                return new DatePickerDialog(this, onDateSetListener, YY, MM, DD);

            case TIME_DIALOG_ID:
                System.out.println("onCreateDialog  : " + id);
                cur = TIME_DIALOG_ID;
                return new TimePickerDialog(this, onTimeSetListener, H, M, false);
            case TIME_DIALOG_ID2:
                System.out.println("onCreateDialog  : " + id);
                cur = TIME_DIALOG_ID2;
                return new TimePickerDialog(this, onTimeSetListener, H, M, false);

        }

        return null;
    }

    TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int h, int m) {
            // timePicker.is24HourView();
            if (cur == TIME_DIALOG_ID) {
                // set selected date into textview
                if (h < 12 && h >= 0) {
                    edt_time_in.setText(String.valueOf(h) + ":" + String.valueOf(m) + " " + "AM");
                } else {
                    h -= 12;
                    if (h == 0) {
                        h = 12;
                    }
                    edt_time_in.setText(String.valueOf(h) + ":" + String.valueOf(m) + " " + "PM");
                }


            } else {
                if (h < 12 && h >= 0) {
                    edt_time_out.setText(String.valueOf(h) + ":" + String.valueOf(m) + " " + "AM");
                } else {
                    h -= 12;
                    if (h == 0) {
                        h = 12;
                    }
                    edt_time_out.setText(String.valueOf(h) + ":" + String.valueOf(m) + " " + "PM");
                }

            }
        }
    };
    DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int y, int m, int d) {
            if ((m + 1) < 10)
                edtDate.setText(String.valueOf(y) + "-0" + String.valueOf(m + 1) + "-" + String.valueOf(d));
            else
                edtDate.setText(String.valueOf(y) + "-" + String.valueOf(m + 1) + "-" + String.valueOf(d));
        }
    };

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap mphoto = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(mphoto);
        }
    }

    public void getVisitors(String userid, String branchid) {

        Singleton.getInstance().getApi().getVisitors(userid, branchid).enqueue(new Callback<LoginResMeta>() {
            @Override
            public void onResponse(Call<LoginResMeta> call, Response<LoginResMeta> response) {

            }

            @Override
            public void onFailure(Call<LoginResMeta> call, Throwable t) {

            }
        });
    }


    public void getFloorList(String userid, String branchid) {

        Singleton.getInstance().getApi().getFllorList(userid, branchid).enqueue(new Callback<LoginResMeta>() {
            @Override
            public void onResponse(Call<LoginResMeta> call, Response<LoginResMeta> response) {

            }

            @Override
            public void onFailure(Call<LoginResMeta> call, Throwable t) {

            }
        });
    }


    public void getUnitList(String userid, String branchid) {

        Singleton.getInstance().getApi(). getUnitList(userid, branchid).enqueue(new Callback<LoginResMeta>() {
            @Override
            public void onResponse(Call<LoginResMeta> call, Response<LoginResMeta> response) {

            }

            @Override
            public void onFailure(Call<LoginResMeta> call, Throwable t) {

            }
        });
    }


}
