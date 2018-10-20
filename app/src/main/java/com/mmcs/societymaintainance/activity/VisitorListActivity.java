package com.mmcs.societymaintainance.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mmcs.societymaintainance.R;
import com.mmcs.societymaintainance.adaptor.VisitorAdapter;
import com.mmcs.societymaintainance.model.LoginModel;
import com.mmcs.societymaintainance.model.VisitorModel;
import com.mmcs.societymaintainance.model.VisitorRestMeta;
import com.mmcs.societymaintainance.util.Shprefrences;
import com.mmcs.societymaintainance.util.Singleton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VisitorListActivity extends AppCompatActivity {
    ListView listVisitor;
    ProgressBar progressBar;
    RelativeLayout txtAdd;
    ArrayList<VisitorModel> visitorModels=new ArrayList();
    Shprefrences sh;
    VisitorAdapter visitorAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor_list);
        listVisitor=findViewById(R.id.listVisitor);
        progressBar=findViewById(R.id.progress);
        txtAdd=findViewById(R.id.txtAdd);
        sh=new Shprefrences(this);
        txtAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(VisitorListActivity.this,AddVisitorActivity.class));
            }
        });

        listVisitor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                VisitorAdapter adapter = (VisitorAdapter) adapterView.getAdapter();
                VisitorModel model = adapter.list.get(i);
                Intent intent = new Intent(VisitorListActivity.this, VisitorDetailActivity.class);
                intent.putExtra(getString(R.string.visitor_model), model);
                startActivity(intent);
            }
        });

        setTitle();
        back();
    }
    private void setTitle() {
        TextView title = (TextView) findViewById(R.id.title);
        title.setText(getString(R.string.visitor_list));
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
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.VISIBLE);
        getVisitors("","");
    }

    public void getVisitors(String userid, String branchid) {

        Singleton.getInstance().getApi().getVisitorList(userid, branchid).enqueue(new Callback<VisitorRestMeta>() {
            @Override
            public void onResponse(Call<VisitorRestMeta> call, Response<VisitorRestMeta> response) {
                if(response.body()==null)
                    return;
                visitorModels=response.body().getResponse();
                visitorAdapter=new VisitorAdapter(VisitorListActivity.this,visitorModels);
                listVisitor.setAdapter(visitorAdapter);
                listVisitor.setEmptyView(findViewById(R.id.imz_nodata));
                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<VisitorRestMeta> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
            }
        });
    }



}
