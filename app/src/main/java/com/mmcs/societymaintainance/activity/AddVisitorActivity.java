package com.mmcs.societymaintainance.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.mmcs.societymaintainance.R;
import com.mmcs.societymaintainance.adaptor.FloorPopupAdapter;
import com.mmcs.societymaintainance.adaptor.UnitAdapter;
import com.mmcs.societymaintainance.model.FloorModel;
import com.mmcs.societymaintainance.model.LoginModel;
import com.mmcs.societymaintainance.model.LoginResMeta;
import com.mmcs.societymaintainance.model.ResponseMeta;
import com.mmcs.societymaintainance.model.UnitModel;
import com.mmcs.societymaintainance.model.UnitRestMeta;
import com.mmcs.societymaintainance.util.Shprefrences;
import com.mmcs.societymaintainance.util.Singleton;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;

import es.dmoral.toasty.Toasty;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddVisitorActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
EditText edtDate,edt_time_in,edt_time_out,edt_visitor_name,edt_mobile,edt_address,edt_floor,edt_unit_no;
ImageView imageView;
    Button btn_take_photo,btn_save;
    private static final int CAMERA_REQUEST = 1888;

    private static final int SELECT_PHOTO = 200;
    int H, M;
    Calendar calendar;
    int DD, MM, YY;
    static final int DATE_DIALOG_ID = 1;
    static final int TIME_DIALOG_ID = 2;
    static final int TIME_DIALOG_ID2 = 3;
    int cur = 0;
    Shprefrences sh;
    public static String imgUrl;
    final int MY_PERMISSIONS_REQUEST_WRITE = 103;
    ProgressBar progress;
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
        sh = new Shprefrences(this);
        edt_visitor_name=findViewById(R.id.edt_visitor_name);
        edt_floor=findViewById(R.id.edt_floor);
        edt_mobile=findViewById(R.id.edt_mobile);
        progress = findViewById(R.id.progress);
        edt_address=findViewById(R.id.edt_address);
        edt_unit_no=findViewById(R.id.edt_unit_no);
        progress.setVisibility(View.VISIBLE);
        btn_save=findViewById(R.id.btn_save);
        calendar = Calendar.getInstance();
        DD = calendar.get(Calendar.DAY_OF_MONTH);
        MM = calendar.get(Calendar.MONTH);
        YY = calendar.get(Calendar.YEAR);
        H = calendar.get(Calendar.HOUR_OF_DAY);
        M = calendar.get(Calendar.MINUTE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_WRITE);
        }
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
        if (imgUrl != null && !imgUrl.equalsIgnoreCase(""))
            Picasso.get().load(imgUrl).into(imageView);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date=edtDate.getText().toString();
                String name=edt_visitor_name.getText().toString();
                String mobile=edt_mobile.getText().toString();
                String address=edt_address.getText().toString();
                String floor=edt_floor.getText().toString();
                String unit=edt_unit_no.getText().toString();
                String time_in=edt_time_in.getText().toString();
                String time_out=edt_time_out.getText().toString();
                if(date.equals("")){
                    Toasty.error(AddVisitorActivity.this,"Select Date",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(name.equals("")){
                    Toasty.error(AddVisitorActivity.this,"Enter Visitor Name",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (mobile.trim().isEmpty() || mobile.length() < 10 || mobile.length() > 12) {
                    Toasty.error(AddVisitorActivity.this, "Enter Valid Mobile Number", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(address.equals("")){
                    Toasty.error(AddVisitorActivity.this, "Enter Address", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(floor.equals("")){
                    Toasty.error(AddVisitorActivity.this,"Select Floor",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(unit.equals("")){
                    Toasty.error(AddVisitorActivity.this,"Select Unit",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(time_in.equals("")){
                    Toasty.error(AddVisitorActivity.this,"Select In Time",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(time_out.equals("")){
                    Toasty.error(AddVisitorActivity.this,"Select Out Time",Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    progress.setVisibility(View.VISIBLE);
                    postVisitor("", "", name,date ,mobile,address,floor,unit,time_in,time_out,imageImagePath);
                                   }

            }
        });
        btn_take_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
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
        edt_floor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFloorPopup();
            }
        });
        edt_unit_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showUnitPopup();
            }
        });
        getFloorList("","");
        getUnitList("","");

        setTitle();
        back();
    }
    private void setTitle() {
        TextView title = (TextView) findViewById(R.id.title);
        title.setText(getString(R.string.add_visitor));
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

    Uri fileUri;

    private void selectImage() {
        final CharSequence[] options = {getString(R.string.take_photo), getString(R.string.choose_from_gallery), getString(R.string.cancel)};
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(AddVisitorActivity.this);
        builder.setTitle(getString(R.string.add_photo));
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals(getString(R.string.take_photo))) {
                    /*Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);*/
                    String fileName = System.currentTimeMillis() + ".jpg";
                    ContentValues values = new ContentValues();
                    values.put(MediaStore.Images.Media.TITLE, fileName);
                    fileUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                    startActivityForResult(intent, CAMERA_REQUEST);
                } else if (options[item].equals(getString(R.string.choose_from_gallery))) {
                    openGallery();
                } else if (options[item].equals(getString(R.string.cancel))) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void openGallery() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, SELECT_PHOTO);
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


    AlertDialog alertDialog;
    ArrayList<FloorModel> floorList = new ArrayList<>();
    FloorPopupAdapter floorPopupAdapter;
    private int popupId = 0;
    private String purposeId;
    private void showFloorPopup() {

        floorPopupAdapter = new FloorPopupAdapter(AddVisitorActivity.this, floorList);

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        // ...Irrelevant code for customizing the buttons and title
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.meeting_popup, null);
        final ListView listFloor = dialogView.findViewById(R.id.listFloor);
        //Button btnUpgrade = (Button) dialogView.findViewById(R.id.btnUpgrade);
        final SearchView editTextName = dialogView.findViewById(R.id.edt);
        TextView title = dialogView.findViewById(R.id.title);
        editTextName.setQueryHint(getString(R.string.search_here));
        title.setText(getString(R.string.select_floor));
        editTextName.setOnQueryTextListener(this);
        dialogBuilder.setView(dialogView);
        alertDialog = dialogBuilder.create();
        alertDialog.show();
        popupId = 1;
        listFloor.setAdapter(floorPopupAdapter);

        listFloor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                FloorModel obj = (FloorModel) listFloor.getAdapter().getItem(position);
                edt_floor.setText(obj.getFloor_no());
                purposeId=obj.getFid();
                alertDialog.dismiss();
            }
        });

    }

    ArrayList<UnitModel> unitModels;
    UnitAdapter unitAdapter;

    private void showUnitPopup() {

        unitAdapter = new UnitAdapter(AddVisitorActivity.this, unitModels);

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        // ...Irrelevant code for customizing the buttons and title
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.meeting_popup, null);
        final ListView listUnit = dialogView.findViewById(R.id.listFloor);
        TextView title = dialogView.findViewById(R.id.title);
        final SearchView editTextName = dialogView.findViewById(R.id.edt);
        editTextName.setQueryHint(getString(R.string.search_here));
        editTextName.setOnQueryTextListener(this);
        title.setText(getString(R.string.select_unit));
        //Button btnUpgrade = (Button) dialogView.findViewById(R.id.btnUpgrade);
        dialogBuilder.setView(dialogView);
        alertDialog = dialogBuilder.create();
        popupId = 2;
        alertDialog.show();
        listUnit.setAdapter(unitAdapter);

        listUnit.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                UnitModel obj = (UnitModel) listUnit.getAdapter().getItem(position);
                edt_unit_no.setText(obj.getUnit_no());
                alertDialog.dismiss();
            }
        });

    }
    public void getFloorList(String userid, String branchid) {
        Singleton.getInstance().getApi().getFloorList(userid, branchid).enqueue(new Callback<ResponseMeta>() {
            @Override
            public void onResponse(Call<ResponseMeta> call, Response<ResponseMeta> response) {
               floorList=response.body().getResponse();
                progress.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ResponseMeta> call, Throwable t) {
                progress.setVisibility(View.GONE);

            }
        });
    }

    public void getUnitList(String userid, String branchid) {

        Singleton.getInstance().getApi(). getUnitList(userid, branchid).enqueue(new Callback<UnitRestMeta>() {
            @Override
            public void onResponse(Call<UnitRestMeta> call, Response<UnitRestMeta> response) {
                unitModels=response.body().getResponse();
                progress.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<UnitRestMeta> call, Throwable t) {
                progress.setVisibility(View.GONE);

            }
        });
    }

    private void postVisitor(String userid, String branchid,String txtName ,String txtIssueDate, String txtMobile,String txtAddress,String ddlFloorNo,String ddlUnitNo ,String txtInTime,String txtOutTime, String fileUrl) {
        LoginModel model = sh.getLoginModel(getString(R.string.login_model));
        RequestBody imgFile = null;
        File imagPh = new File(fileUrl);
        Log.e("***********", "*************" + fileUrl);
        if (imagPh != null && (fileUrl!=null && !fileUrl.equalsIgnoreCase("")))
            imgFile = RequestBody.create(MediaType.parse("image/*"), imagPh);
        RequestBody requestUserId = RequestBody.create(MediaType.parse("text/plain"), userid);
        RequestBody requestUserbranch = RequestBody.create(MediaType.parse("text/plain"), branchid);
        RequestBody requesttxtName = RequestBody.create(MediaType.parse("text/plain"), txtName);
        RequestBody requestDate = RequestBody.create(MediaType.parse("text/plain"), txtIssueDate);
        RequestBody requestMobile = RequestBody.create(MediaType.parse("text/plain"), txtMobile);
        RequestBody requestAddress = RequestBody.create(MediaType.parse("text/plain"), txtAddress);
        RequestBody requestFloor = RequestBody.create(MediaType.parse("text/plain"), ddlFloorNo);
        RequestBody requestUnit = RequestBody.create(MediaType.parse("text/plain"), ddlUnitNo);
        RequestBody requestTimein = RequestBody.create(MediaType.parse("text/plain"), txtInTime);
        RequestBody requestTimeout = RequestBody.create(MediaType.parse("text/plain"), txtOutTime);


        Singleton.getInstance().getApi().postVisitor(requestUserId, requestUserbranch, requesttxtName,requestDate ,requestMobile, requestAddress, requestFloor,requestUnit,requestTimein,requestTimeout ,imgFile).enqueue(new Callback<LoginResMeta>() {
            @Override
            public void onResponse(Call<LoginResMeta> call, Response<LoginResMeta> response) {
                Toasty.success(AddVisitorActivity.this, "Successfully Posted", Toast.LENGTH_SHORT).show();
                progress.setVisibility(View.GONE);
                finish();
            }

            @Override
            public void onFailure(Call<LoginResMeta> call, Throwable t) {
              progress.setVisibility(View.GONE);
                Toasty.error(AddVisitorActivity.this,"Sorry Try Again", Toast.LENGTH_SHORT).show();
            }
        });
    }
    String imageImagePath = "";
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            try {
                imageImagePath = getPath(fileUri);
                File file=new File(imageImagePath);
                resize(file,"");

                Bitmap b = decodeUri(fileUri);
                imageView.setImageBitmap(b);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (requestCode == SELECT_PHOTO) {
            if (resultCode == RESULT_OK) {
                Uri selectedImage = data.getData();
                if (selectedImage != null) {
                    imageView.setImageURI(selectedImage);
                    imageImagePath = getPath(selectedImage);
                    File file=new File(imageImagePath);
                    resize(file,"");

                }
            }
        }
    }
    private Bitmap decodeUri(Uri selectedImage) throws FileNotFoundException {
        BitmapFactory.Options o = new BitmapFactory.Options();

        o.inJustDecodeBounds = true;

        BitmapFactory.decodeStream(getContentResolver()
                .openInputStream(selectedImage), null, o);

        final int REQUIRED_SIZE = 72;

        int width_tmp = o.outWidth, height_tmp = o.outHeight;

        int scale = 1;

        while (true) {
            if (width_tmp / 2 < REQUIRED_SIZE || height_tmp / 2 < REQUIRED_SIZE) {
                break;
            }
            width_tmp /= 2;

            height_tmp /= 2;

            scale *= 2;
        }

        BitmapFactory.Options o2 = new BitmapFactory.Options();

        o2.inSampleSize = scale;

        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver()
                .openInputStream(selectedImage), null, o2);

        return bitmap;
    }

    @SuppressWarnings("deprecation")
    private String getPath(Uri selectedImaeUri) {
        String[] projection = {MediaStore.Images.Media.DATA};

        Cursor cursor = managedQuery(selectedImaeUri, projection, null, null,
                null);

        if (cursor != null) {
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

            return cursor.getString(columnIndex);
        }

        return selectedImaeUri.getPath();
    }

    BitmapFactory.Options bmOptions;
    Bitmap bitmap;
    public void resize(File file, String benchMark) {
        try {
            bmOptions = new BitmapFactory.Options();
            bmOptions.inJustDecodeBounds = false;
            bmOptions.inPreferredConfig = Bitmap.Config.RGB_565;
            bmOptions.inDither = true;
            bitmap = BitmapFactory.decodeFile(imageImagePath, bmOptions);
            int w = bitmap.getWidth();
            int h = bitmap.getHeight();
            Log.e("width & Height", "width " + bitmap.getWidth());
            if (bitmap.getWidth() > 1200) {
                w = bitmap.getWidth() * 30 / 100;
                h = bitmap.getHeight() * 30 / 100;
            }

            Log.e("width & Height", "width " + w + " height " + h);
            bitmap = Bitmap.createScaledBitmap(bitmap, w, h, true);

            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, bytes);
            try {
                Log.e("Compressing", "Compressing");
                FileOutputStream fo = new FileOutputStream(file);
                fo.write(bytes.toByteArray());
                fo.close();
            } catch (Exception e) {
                Log.e("Exception", "Image Resizing" + e.getMessage());
            }
        } catch (
                Exception e
                ) {
            Log.e("Exception", "Exception in resizing image");
        }
    }


    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        s = s.toLowerCase();
        switch (popupId) {
            case 1:
                ArrayList<FloorModel> newlist = new ArrayList<>();
                for (FloorModel list : floorList) {
                    String getPurpose = list.getFloor_no().toLowerCase();

                    if (getPurpose.contains(s)) {
                        newlist.add(list);
                    }
                }
                floorPopupAdapter.filter(newlist);
                break;

            case 2:
                ArrayList<UnitModel> newlist1 = new ArrayList<>();
                for (UnitModel list : unitModels) {
                    String getCustomer = list.getUnit_no().toLowerCase();
                    if (getCustomer.contains(s)) {
                        newlist1.add(list);
                    }
                }
                unitAdapter.filter(newlist1);
                break;
        }
        return false;
    }
}
