package com.mmcs.societymaintainance.util;

import com.mmcs.societymaintainance.model.LoginModel;
import com.mmcs.societymaintainance.model.LoginResMeta;

import java.util.ArrayList;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface RetrofitApi {


    @FormUrlEncoded
    @POST("apilogin.php")
    Call<LoginResMeta> login(@Field("username") String email, @Field("password") String password, @Field("device_token") String device_token, @Field("fcm_token") String fcm_token, @Field("ddlBranch") String ddlBranch, @Field("ddlLoginType") String ddlLoginType);

    @FormUrlEncoded
    @POST("visitor_get_api.php")
    Call<LoginResMeta> getVisitors(@Field("user_id") String user_id, @Field("branch_id") String branch_id);

    @FormUrlEncoded
    @POST("floorlist_get_api.php")
    Call<LoginResMeta> getFllorList( @Field("user_id") String user_id,@Field("branch_id") String branch_id);

    @FormUrlEncoded
    @POST("unitlist_get_api.php")
    Call<LoginResMeta> getUnitList( @Field("user_id") String user_id,@Field("branch_id") String branch_id);


}
