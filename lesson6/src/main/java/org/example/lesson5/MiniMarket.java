package org.example.lesson5;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface MiniMarket {


    @GET("categories/{id}")
    Call<CategoryResult> getCategory(@Path("id") Long id);

    @GET("products")
    Call<List<Product>> getProducts();

    @POST("products")
    Call<ResponseBody> postProduct(@Body Product product);


    @GET("products/{id}")
    Call<Product> getProduct(@Path("id") Long id);


    @DELETE("products/{id}")
    Call<Product> deleteProduct(@Path("id") Long id);


}
