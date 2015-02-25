package sample.retrofit.client;

import retrofit.Callback;
import retrofit.http.*;
import sample.retrofit.client.models.RequestUser;
import sample.retrofit.client.models.Response;
import sample.retrofit.client.models.ResponseUser;

public interface ApiSpecs {

    @GET("/user/{user_id}")
    ResponseUser getUserPath(@Path("user_id") long userId);

    @GET("/user")
    ResponseUser getUserQuery(@Query("user_id") long userId);

    @FormUrlEncoded
    @POST("/user")
    ResponseUser getUserField(@Field("user_id") long userId);

    @POST("/user")
    ResponseUser getUserBody(@Body RequestUser user);

    @POST("/user")
    ResponseUser getUserTypedOutput(@Body ServerTypedOutput<RequestUser> output);

    @GET("/user/{user_id}/input")
    Response getUserCustomResponse(@Path("user_id") long userId);

    @GET("/user/{user_id}/error")
    ResponseUser getUserError(@Path("user_id") long userId);

    @GET("/user/{user_id}")
    void getUserCallback(@Path("user_id") long userId, Callback<ResponseUser> callback);

}