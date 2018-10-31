package com.mmcs.societymaintainance.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.messaging.RemoteMessage;
import com.mmcs.societymaintainance.R;
import com.mmcs.societymaintainance.model.ComplaintModel;
import com.mmcs.societymaintainance.model.ComplaintRestMeta;
import com.mmcs.societymaintainance.util.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComplaintNotificationActivity extends AppCompatActivity {
    TextView txtDepartment,txtTitle,txtDate,txt_c_des;
    Button reject,accept;
    ComplaintModel model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_notification);
        txtDepartment=findViewById(R.id.txtDepartment);
        txtTitle=findViewById(R.id.txtTitle);
        txtDate=findViewById(R.id.txtDate);
        txt_c_des=findViewById(R.id.txt_c_des);
        accept=findViewById(R.id.accept);
        reject=findViewById(R.id.reject);
        RemoteMessage remoteMessage = (RemoteMessage) getIntent().getExtras().get("NOTIFICATION_VALUE");
        String body = remoteMessage.getNotification().getBody();
        Log.e("body", "body********" + body);
        final String strID[] = body.split("#");
        getComplain(strID[1]);
        back();
        setTitle();
    }

    private void getComplain(String id) {

        Singleton.getInstance().getApi().getComplainById(id).enqueue(new Callback<ComplaintRestMeta>() {
            @Override
            public void onResponse(Call<ComplaintRestMeta> call, Response<ComplaintRestMeta> response) {
                 model=response.body().getResponse().get(0);
            }

            @Override
            public void onFailure(Call<ComplaintRestMeta> call, Throwable t) {

            }
        });
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
