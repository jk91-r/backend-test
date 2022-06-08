package org.example.spoonacular.MiniMarket;

import okhttp3.ResponseBody;
import org.example.lesson5.CategoryResult;
import org.example.lesson5.MiniMarketService;
import org.example.lesson5.Product;
import org.example.lesson5.ProductResult;
import org.example.spoonacular.ExtendsDataTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.hamcrest.Matchers.*;


public class MiniMarketTest extends ExtendsDataTest {

    private static MiniMarketService service;

    @BeforeAll
    static void beforeAll(){
        service =  new MiniMarketService();

    }

    @Test
    void testGetCategory() throws Exception {
        CategoryResult result = service.getCategory();
        assertJson(getResourceAsString("expected.json"), result);
        contains("content-type: application/json");

    }

    @Test
    void testGetProduct() throws Exception {
        List<ProductResult> res = service.getProduct();
        assertJson(getResourceAsString("expectedProd.json"), res);
        contains(" transfer-encoding: chunked");
    }

    @Test
    void testCreateProduct()  {
        Product create = service.createProduct();
        contains("title:Noodle,price:555,categoryTitle:Food");
        System.out.println(create);


    }

    @Test
    void testGetProductById() throws Exception {
        ProductResult resCreate = service.findProduct();
        assertJson(getResourceAsString("expP.json"),resCreate);
        containsString("price:555");
    }

    @Test
    void testDeleteProduct(){
        ResponseBody deleteProduct = service.deleteProduct();
        System.out.println(deleteProduct);


    }

 }
