package com.mmcs.societymaintainance.util;

import com.mmcs.societymaintainance.model.ComplaintRestMeta;
import com.mmcs.societymaintainance.model.DesignationRestMeta;
import com.mmcs.societymaintainance.model.EmployeeRestMeta;
import com.mmcs.societymaintainance.model.LoginModel;
import com.mmcs.societymaintainance.model.LoginResMeta;
import com.mmcs.societymaintainance.model.OwnerRestMeta;
import com.mmcs.societymaintainance.model.ResponseMeta;
import com.mmcs.societymaintainance.model.UnitRestMeta;
import com.mmcs.societymaintainance.model.VisitorRestMeta;
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
    Call<VisitorRestMeta> getVisitorList(@Field("id") String id,@Field("ddlLoginType") String ddlLoginType, @Field("branch_id") String branch_id);

    @FormUrlEncoded
    @POST("complain_get_api.php")
    Call<ComplaintRestMeta> getComplaintList(@Field("id") String id, @Field("ddlLoginType") String type,@Field("branch_id") String branch_id);


    @FormUrlEncoded
    @POST("owner_get_api.php")
    Call<OwnerRestMeta> getOwnerList(@Field("id") String id,@Field("ddlLoginType") String ddlLoginType, @Field("branch_id") String branch_id);


    @FormUrlEncoded
    @POST("employee_get_api.php")
    Call<EmployeeRestMeta> getEmployeeList(@Field("id") String id,@Field("ddlLoginType") String ddlLoginType, @Field("branch_id") String branch_id);

    @FormUrlEncoded
    @POST("floorlist_get_api.php")
    Call<ResponseMeta> getFloorList( @Field("id") String id,@Field("ddlLoginType") String ddlLoginType,@Field("branch_id") String branch_id);

    @FormUrlEncoded
    @POST("unitlist_get_api.php")
    Call<UnitRestMeta> getUnitList(@Field("id") String id,@Field("ddlLoginType") String ddlLoginType, @Field("branch_id") String branch_id);

    @FormUrlEncoded
    @POST("get_designation_api.php")
    Call<DesignationRestMeta> getDesignationList(@Field("id") String id,@Field("ddlLoginType") String ddlLoginType ,@Field("branch_id") String branch_id);


    @Multipart
    @POST("add_visitor_post_api.php")
    Call<LoginResMeta> postVisitor(@Part("id") RequestBody id, @Part("ddlLoginType") RequestBody ddlLoginType,@Part("branch_id") RequestBody branch_id,
                                     @Part("txtName") RequestBody txtName,@Part("txtIssueDate") RequestBody txtIssueDate ,@Part("txtMobile") RequestBody txtMobile, @Part("txtAddress") RequestBody txtAddress,
                                     @Part("ddlFloorNo") RequestBody ddlFloorNo , @Part("ddlUnitNo") RequestBody ddlUnitNo, @Part("txtInTime") RequestBody txtInTime,@Part("txtOutTime") RequestBody txtOutTime ,@Part("image\"; filename=\"profile.jpg") RequestBody image

    );
    @Multipart
    @POST("add_owner_post_api.php")
    Call<LoginResMeta> postOwner(@Part("id") RequestBody id, @Part("ddlLoginType") RequestBody ddlLoginType,@Part("branch_id") RequestBody branch_id,
                                   @Part("txtOwnerName") RequestBody txtOwnerName,@Part("txtOwnerEmail") RequestBody txtOwnerEmail ,@Part("txtOwnerContact") RequestBody txtOwnerContact, @Part("txtOwnerPreAddress") RequestBody txtOwnerPreAddress,
                                   @Part("txtOwnerPerAddress") RequestBody txtOwnerPerAddress , @Part("txtOwnerNID") RequestBody txtOwnerNID, @Part("o_password") RequestBody o_password,@Part("floor_id") RequestBody floor_id,@Part("unit_id") RequestBody unit_id ,@Part("image\"; filename=\"profile.jpg") RequestBody image

    );

    @FormUrlEncoded
    @POST("add_complain_post_api.php")
    Call<LoginResMeta> postComplaint(@Field("id") String id, @Field("ddlLoginType") String ddlLoginType,@Field("branch_id") String branch_id,@Field("txtCTitle") String txtCTitle
            ,@Field("txtCDescription") String txtCDescription ,@Field("txtCDate") String txtCDate,@Field("xmonth") String xmonth,@Field("xyear") String xyear

    );




    @Multipart
    @POST("add_employee_post_api.php")
    Call<LoginResMeta> postEmployee(@Part("id") RequestBody id,@Part("ddlLoginType") RequestBody ddlLoginType, @Part("branch_id") RequestBody branch_id,
                                   @Part("txtEmpName") RequestBody txtEmpName,@Part("txtEmpEmail") RequestBody txtEmpEmail ,@Part("txtEmpContact") RequestBody txtEmpContact, @Part("txtEmpPreAddress") RequestBody txtEmpPreAddress,
                                   @Part("txtEmpPerAddress") RequestBody txtEmpPerAddress , @Part("txtEmpNID") RequestBody txtEmpNID, @Part("ddlMemberType") RequestBody ddlMemberType,@Part("txtEmpDate") RequestBody txtEmpDate,@Part("txtEndingDate") RequestBody txtEndingDate ,@Part("e_password") RequestBody e_password,@Part("e_status") RequestBody e_status,@Part("added_date") RequestBody added_date,@Part("image\"; filename=\"profile.jpg") RequestBody image

    );



    @FormUrlEncoded
    @POST("update_visitor_api.php")
    Call<UnitRestMeta> updateVisitor(@Field("vid") String vid, @Field("txtOutTime") String txtOutTime);

    @FormUrlEncoded
    @POST("update_employee_api.php")
    Call<UnitRestMeta> updateEmployee(@Field("eid") String eid, @Field("ending_date") String ending_date);

    @FormUrlEncoded
    @POST("update_fcm_token.php")
    Call<UnitRestMeta> updateToken(@Field("id") String id, @Field("ddlLoginType") String ddlLoginType,@Field("fcm_token") String fcm_token);
}
