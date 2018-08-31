package com.mmcs.societymaintainance.activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mmcs.societymaintainance.R;
import java.util.Calendar;

public class AddResidentActivity extends AppCompatActivity {
EditText edt_dateof_birth,edt_possession_date;
    static final int DATE_DIALOG_ID = 1;
    static final int DATE_DIALOG_ID2 = 2;
    int DD, MM, YY;
    int cur = 0;
    Calendar calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_resident);
        edt_dateof_birth=findViewById(R.id.edt_dateof_birth);
        edt_possession_date=findViewById(R.id.edt_possession_date);
        calendar = Calendar.getInstance();
        DD = calendar.get(Calendar.DAY_OF_MONTH);
        MM = calendar.get(Calendar.MONTH);
        YY = calendar.get(Calendar.YEAR);

        edt_dateof_birth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DATE_DIALOG_ID);
            }
        });
        edt_possession_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DATE_DIALOG_ID2);
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
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                System.out.println("onCreateDialog  : " + id);
                cur = DATE_DIALOG_ID;
                DatePickerDialog dialog = new DatePickerDialog(this, onDateSetListener, YY, MM, DD);
                dialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                return dialog;
                case DATE_DIALOG_ID2:
                cur = DATE_DIALOG_ID2;
                System.out.println("onCreateDialog2  : " + id);
                DatePickerDialog dialog2 = new DatePickerDialog(this, onDateSetListener, YY, MM, DD);
                dialog2.getDatePicker().setMaxDate(System.currentTimeMillis());
                return dialog2;
        }
        return null;
    }
    DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int d, int m, int y) {
            if (cur == DATE_DIALOG_ID) {
                // set selected date into textview
                if ((m + 1) < 10)
                    edt_dateof_birth.setText(String.valueOf(d) + "-0" + String.valueOf(m + 1) + "-" + String.valueOf(y));
                else
                    edt_dateof_birth.setText(String.valueOf(d) + "-" + String.valueOf(m + 1) + "-" + String.valueOf(y));
            }
            else {
                if ((m + 1) < 10)
                    edt_possession_date.setText(String.valueOf(d) + "-0" + String.valueOf(m + 1) + "-" + String.valueOf(y));
                else
                    edt_possession_date.setText(String.valueOf(d) + "-" + String.valueOf(m + 1) + "-" + String.valueOf(y));
            }
        }
    };
}
