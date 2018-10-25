package com.mmcs.societymaintainance.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;

import com.mmcs.societymaintainance.R;
import com.mmcs.societymaintainance.adaptor.ComplaintAdapter;
import com.mmcs.societymaintainance.adaptor.VisitorAdapter;
import com.mmcs.societymaintainance.model.ComplaintModel;
import com.mmcs.societymaintainance.model.ComplaintRestMeta;
import com.mmcs.societymaintainance.model.VisitorRestMeta;
import com.mmcs.societymaintainance.util.Shprefrences;
import com.mmcs.societymaintainance.util.Singleton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComplaintListActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    ListView listComplaint;
    ProgressBar progressBar;
    RelativeLayout txtAdd;
    ArrayList<ComplaintModel> complaintModels=new ArrayList();
    Shprefrences sh;
    ComplaintAdapter complaintAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_list);
        listComplaint=findViewById(R.id.listComplaint);
        progressBar=findViewById(R.id.progress);
        SearchView editTextName=(SearchView) findViewById(R.id.edt);
        editTextName.setQueryHint(getString(R.string.search_here));
        editTextName.setOnQueryTextListener(this);
        txtAdd=findViewById(R.id.txtAdd);
        sh=new Shprefrences(this);
        back();
        setTitle();
        txtAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ComplaintListActivity.this,AddComplaintActivity.class));
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
        title.setText(getString(R.string.complaint_list));
    }
    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.VISIBLE);
        getComplaintList("","");
    }

    public void getComplaintList(String userid, String branchid) {

        Singleton.getInstance().getApi().getComplaintList(userid, branchid).enqueue(new Callback<ComplaintRestMeta>() {
            @Override
            public void onResponse(Call<ComplaintRestMeta> call, Response<ComplaintRestMeta> response) {
                if(response.body()==null)
                    return;
                complaintModels=response.body().getResponse();
                complaintAdapter=new ComplaintAdapter(ComplaintListActivity.this,complaintModels);
                listComplaint.setAdapter(complaintAdapter);
                listComplaint.setEmptyView(findViewById(R.id.imz_nodata));
                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<ComplaintRestMeta> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
            }
        });
    }
    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        s=s.toLowerCase();
        ArrayList<ComplaintModel> newlist=new ArrayList<>();
        for(ComplaintModel filterlist:complaintModels)
        {
            String title=filterlist.getTitle().toLowerCase();
            String des =filterlist.getC_description().toLowerCase();
            String date =filterlist.getDate().toLowerCase();

            if(title.contains(s)||des.contains(s)||date.contains(s)) {
                newlist.add(filterlist);
            }
        }
        complaintAdapter.filter(newlist);
        return true;
    }
}