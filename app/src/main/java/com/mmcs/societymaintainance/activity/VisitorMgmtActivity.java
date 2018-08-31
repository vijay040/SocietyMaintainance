package com.mmcs.societymaintainance.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mmcs.societymaintainance.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class VisitorMgmtActivity extends AppCompatActivity {
EditText edt_curr_datetime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor_mgmt);
        edt_curr_datetime=findViewById(R.id.edt_curr_datetime);
        DateFormat df = new SimpleDateFormat(getString(R.string.date_formate));
        final String createddate = df.format(Calendar.getInstance().getTime());
        edt_curr_datetime.setText(createddate);
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
}
