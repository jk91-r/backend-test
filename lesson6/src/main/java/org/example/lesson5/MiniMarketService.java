package org.example.lesson5;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.util.List;
import static org.example.lesson5.Executor.executeCall;


public class MiniMarketService {

    private final MiniMarket miniMarket;

    public MiniMarketService() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        miniMarket = new Retrofit.Builder()
                .baseUrl("https://minimarket1.herokuapp.com/market/api/v1/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MiniMarket.class);

    }

    public CategoryResult getCategory(Long id){

        return executeCall(miniMarket.getCategory(id));

    }

    public List<Product> getProducts(){

        return executeCall(miniMarket.getProducts());
    }

    public Product createProduct (Product product){

        executeCall(miniMarket.postProduct(product));
        return product;
    }


    public Product getProduct(Long id){

        return executeCall(miniMarket.getProduct(id));
    }


    public Product deleteProduct(Long id){

        return executeCall(miniMarket.deleteProduct(id));
    }

}

