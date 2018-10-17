package com.mmcs.societymaintainance.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class VisitorMgmtActivity extends AppCompatActivity {
EditText edtDate,edt_time_in,edt_time_out;
ImageView imageView;
    Button btn_take_photo;
    private static final int CAMERA_REQUEST = 1888;
    int H, M;
    Calendar calendar;
    int DD, MM, YY;
    static final int DATE_DIALOG_ID = 1;
    static final int TIME_DIALOG_ID = 2;
    static final int TIME_DIALOG_ID2 = 3;
    int cur = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor_mgmt);
        imageView=findViewById(R.id.imageView);
        edtDate=findViewById(R.id.edtDate);
        edt_time_in=findViewById(R.id.edt_time_in);
        edt_time_out=findViewById(R.id.edt_time_out);
        btn_take_photo=findViewById(R.id.btn_take_photo);
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
}
