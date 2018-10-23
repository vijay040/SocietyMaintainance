package com.mmcs.societymaintainance.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;

import com.mmcs.societymaintainance.R;
import com.mmcs.societymaintainance.adaptor.EmployeeAdapter;
import com.mmcs.societymaintainance.adaptor.OwnerAdapter;
import com.mmcs.societymaintainance.adaptor.VisitorAdapter;
import com.mmcs.societymaintainance.model.EmployeeModel;
import com.mmcs.societymaintainance.model.OwnerModel;
import com.mmcs.societymaintainance.model.OwnerRestMeta;
import com.mmcs.societymaintainance.model.VisitorModel;
import com.mmcs.societymaintainance.model.VisitorRestMeta;
import com.mmcs.societymaintainance.util.Shprefrences;
import com.mmcs.societymaintainance.util.Singleton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OwnerListActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    ListView listOwner;
    ProgressBar progressBar;
    RelativeLayout txtAdd;
    ArrayList<OwnerModel> ownerModels=new ArrayList();
    Shprefrences sh;
    OwnerAdapter ownerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_list);
        listOwner=findViewById(R.id.listOwner);
        progressBar=findViewById(R.id.progress);
        txtAdd=findViewById(R.id.txtAdd);
        SearchView editTextName=(SearchView) findViewById(R.id.edt);
        editTextName.setQueryHint(getString(R.string.search_here));
        editTextName.setOnQueryTextListener(this);
        sh=new Shprefrences(this);
        txtAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OwnerListActivity.this,AddOwnerActivity.class));
            }
        });
        listOwner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                OwnerAdapter adapter = (OwnerAdapter) adapterView.getAdapter();
                OwnerModel model = adapter.list.get(i);
                Intent intent = new Intent(OwnerListActivity.this, OwnerDetailActivity.class);
                intent.putExtra(getString(R.string.owner_model), model);
                startActivity(intent);
            }
        });
        setTitle();
        back();
    }
    private void setTitle() {
        TextView title = (TextView) findViewById(R.id.title);
        title.setText(getString(R.string.owner_list));

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
        getOwnerList("","");
    }

    public void getOwnerList(String userid, String branchid) {

        Singleton.getInstance().getApi().getOwnerList(userid, branchid).enqueue(new Callback<OwnerRestMeta>() {
            @Override
            public void onResponse(Call<OwnerRestMeta> call, Response<OwnerRestMeta> response) {
                if(response.body()==null)
                    return;
                ownerModels=response.body().getResponse();
                ownerAdapter=new OwnerAdapter(OwnerListActivity.this,ownerModels);
                listOwner.setAdapter(ownerAdapter);
                listOwner.setEmptyView(findViewById(R.id.imz_nodata));
                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<OwnerRestMeta> call, Throwable t) {
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
        ArrayList<OwnerModel> newlist=new ArrayList<>();
        for(OwnerModel filterlist:ownerModels)
        {
            String name=filterlist.getName().toLowerCase();
            //String floor =filterlist.getFloor_no().toLowerCase();
           // String unit =filterlist.getFloor_no().toLowerCase();
            String mob =filterlist.getContact().toLowerCase();
            String email =filterlist.getContact().toLowerCase();
            if(name.contains(s)||mob.contains(s)||email.equals(s)) {
                newlist.add(filterlist);
            }
        }
        ownerAdapter.filter(newlist);
        return true;
    }
}
