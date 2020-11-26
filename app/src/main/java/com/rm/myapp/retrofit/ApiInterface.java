package com.rm.myapp.retrofit;



import com.rm.myapp.model.DataModel;
import com.rm.myapp.model.GetLocationModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("Register/register_user")
    Call<DataModel> registerUser(
            @Header("Secret-key")String  key,
            @Field("userName") String userName,
            @Field("phoneNumber") String phoneNumber,
            @Field("password") String password,
            @Field("gender") String gender
    );

    @FormUrlEncoded
    @POST("User/add_user_lat_long")
    Call<DataModel> sendLocation(
            @Header("Secret-key")String  key,
            @Field("token") String token,
            @Field("latitude") String latitude,
            @Field("longitude") String longitude

    );

    @FormUrlEncoded
    @POST("User/get_user_lat_long")
    Call<GetLocationModel> getLocation(
            @Header("Secret-key")String  key,
            @Field("token") String token

    );

  /*



    @FormUrlEncoded
    @POST("login")
    Call<OrgMemeberLoginModel> login(
            @Field("role_type") String type,
            @Field("email") String email,
            @Field("password") String password,
            @Field("token") String token
    );

    @FormUrlEncoded
    @POST("PostUserSetting")
    Call<UserSettingModel> postUserSetting(
            @Field("user_id") String user_id,
            @Field("distance") String distance,
            @Field("size") String size,
            @Field("bluetooth_uses") boolean bluetooth_uses,
            @Field("alert") boolean alert,
            @Field("notify") boolean notification
    );

    @FormUrlEncoded
    @POST("postGeofences")
    Call<RegisterModel> createGeoFence(
            @Field("user_id") String user_id,
            @Field("name") String name,
            @Field("lat") String lat,
            @Field("lng") String lng,
            @Field("colour") String colour);


    @FormUrlEncoded
    @POST("countUser")
    Call<AllGeofenceModel> getAllGeofence(
            @Field("lat") String lat,
            @Field("lng") String lng);

    @FormUrlEncoded
    @POST("viewGeofences")
    Call<ViewGeoFenceModel> viewMyGeofence(
            @Field("user_id") String user_id,
            @Field("lat") String lat,
            @Field("lng") String lng);

    @FormUrlEncoded
    @POST("deleteGeofences")
    Call<RegisterModel> deletegeofence(
            @Field("user_id") String user_id,
            @Field("geofences_id") String geofences_id
    );

    @GET("getUserSetting")
    Call<UserSettingModel> getUserSetting(
            @Query("user_id") String user_id
    );


    @FormUrlEncoded
    @POST("getGeofences")
    Call<SavedGeoFenceModel> getUserGeofence(
            @Field("user_id") String user_id
    );

    @FormUrlEncoded
    @POST("getmyGeofences")
    Call<SavedGeoFenceModel> getMyGeoFence(
            @Field("user_id") String user_id
    );

    @GET("getoccupants")
    Call<TotalUserModel> getTotalOccupants();

    @FormUrlEncoded
    @POST("addOrganization")
    Call<RegisterModel> registerOrg(
            @Field("owner_name") String owner_name,
            @Field("organisation_name") String organisation_name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("role_type") String role_type,
            @Field("token") String token
    );

    @FormUrlEncoded
    @POST("addMember")
    Call<RegisterModel> addMember(
            @Field("orgnization_name") String orgnization_name,
            @Field("member_name") String member_name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("orginaztion_owner_user_id") String orginaztion_owner_user_id,
            @Field("role_type") String role_type,
            @Field("token") String token
    );


    @GET("allMember")
    Call<AllMemberModel> getAllMember(
            @Query("orginaztion_owner_user_id") String orgId
    );

    @FormUrlEncoded
    @POST("deleteGeofences")
    Call<RegisterModel> deleteMember(
            @Field("member_user_id") String user_id
    );

    @FormUrlEncoded
    @POST("getoccupants")
    Call<NearMeGeoFenceModel> getNearMeGeoFence(
            @Field("lat") String lat,
            @Field("lng") String lng
    );

    @FormUrlEncoded
    @POST("forgetPassword")
    Call<RegisterModel> forgotPassword(
            @Field("email") String email
    );

    @FormUrlEncoded
    @POST("pushNotification")
    Call<NotyModel> pushNotification(
            @Field("user_id") String user_id,
            @Field("title") String title,
            @Field("message") String message,
            @Field("lat") String lat,
            @Field("lng") String lng


    );

    *//*@GET("https://coronavirus-19-api.herokuapp.com/countries")
    Call<ArrayList<CovidDetailModel>> getCovidData();*//*

    @GET("v1")
    Call<ArrayList<CovidModel>> getCovidData();

    @FormUrlEncoded
    @POST("heatMap")
    Call<HeatModel> getHeatData(
            @Field("user_id") String user_id,
            @Field("lat") String lat,
            @Field("lng") String lng
    );

    @FormUrlEncoded
    @POST("stripe-payment")
    Call<PaymentModel> makePayment(
            @Field("user_id") String user_id,
            @Field("organisation_name") String organisation_name,
            @Field("amount") String amount,
            @Field("cvvNumber") String cvvNumber,
            @Field("ccExpiryYear") String ccExpiryYear,
            @Field("ccExpiryMonth") String ccExpiryMonth,
            @Field("card_no") String card_no
    );*/

}
