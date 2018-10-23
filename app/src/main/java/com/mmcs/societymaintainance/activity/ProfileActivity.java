package com.mmcs.societymaintainance.activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.mmcs.societymaintainance.R;
import com.mmcs.societymaintainance.model.LoginModel;
import com.mmcs.societymaintainance.util.Shprefrences;

public class ProfileActivity extends AppCompatActivity {
    LoginModel loginModel;
    Shprefrences sh;
    ImageView imgProfile;
  EditText edt_txt_first_name,edt_txt_last_name,edt_email_id,edt_Flate_no,edt_phone,edt_Address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        imgProfile=findViewById(R.id.imgProfile);
        edt_txt_first_name=findViewById(R.id.edt_txt_first_name);
        edt_txt_last_name=findViewById(R.id.edt_txt_last_name);
        edt_email_id=findViewById(R.id.edt_email_id);
        edt_Flate_no=findViewById(R.id.edt_Flate_no);
        edt_phone=findViewById(R.id.edt_phone);
        edt_Address=findViewById(R.id.edt_Address);
        sh=new Shprefrences(this);
        loginModel=sh.getLoginModel(getString(R.string.login_model));
        edt_txt_first_name.setText(loginModel.getName());
        edt_email_id.setText(loginModel.getEmail());
        edt_Flate_no.setText(loginModel.getFloor_no());
        edt_phone.setText(loginModel.getContact());
        edt_Address.setText(loginModel.getPre_address());
        setTitle();
        back();

    }
    private void setTitle() {
        TextView title = (TextView) findViewById(R.id.title);
        title.setText(getString(R.string.profile_title));
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
