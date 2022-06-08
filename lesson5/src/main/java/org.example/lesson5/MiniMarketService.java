package org.example.lesson5;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.util.List;
import static org.example.lesson5.Executor.executeCall;


public class MiniMarketService {

    int id;
    String title;
    int price;
    String categoryTitle;

    Product body = new Product(555,"Noodle",555,"Food");
    ProductResult find = new ProductResult(784);




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

    public CategoryResult getCategory(){

        return executeCall(miniMarket.getCategory(1));

    }

    public List<ProductResult> getProduct(){

        return executeCall(miniMarket.getProduct(id,title,price, categoryTitle));
    }

    public Product createProduct(){

        return executeCall(miniMarket.createProduct(body));
    }


    public ProductResult findProduct(){

        return executeCall(miniMarket.findProduct(find));
    }


    public ResponseBody deleteProduct(){
        return executeCall(miniMarket.deleteProduct(784));
    }

}

