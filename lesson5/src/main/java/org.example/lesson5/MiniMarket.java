package org.example.lesson5;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface MiniMarket {

    @GET("categories/{id}")
    Call<CategoryResult> getCategory(@Path("id") int id);

    @GET("products")
    Call<List<ProductResult>> getProduct(@Query("id") int id,
                                          @Query("title") String title,
                                          @Query("price") int price,
                                          @Query("categoryTitle") String categoryTitle);

    @POST("products")
    Call<Product> createProduct(@Body Product createProductRequest);


    @GET("products/{id}")
    Call<ProductResult> findProduct(@Path("id") ProductResult id);


    @DELETE("products/{id}")
    Call<ResponseBody> deleteProduct(@Path("id") int id);


}
