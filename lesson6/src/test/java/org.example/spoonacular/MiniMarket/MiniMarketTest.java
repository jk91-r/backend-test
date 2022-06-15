package org.example.spoonacular.MiniMarket;

import org.example.lesson5.CategoryResult;
import org.example.lesson5.MiniMarketService;
import org.example.lesson5.Product;
import org.example.spoonacular.ExtendsDataTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;



public class MiniMarketTest extends ExtendsDataTest {

    private static MiniMarketService service;

    @BeforeAll
    static void beforeAll(){
        service =  new MiniMarketService();

    }

    @Disabled
    @Test
    void testGetCategory() throws Exception {
        CategoryResult result = service.getCategory(1L);
        assertJson(getResourceAsString("expected.json"), result);
        contains("content-type: application/json");

    }

    @Disabled
    @Test
    void testGetProduct() throws Exception {
        List<Product> res = service.getProducts();
        assertJson(getResourceAsString("expectedProd.json"), res);
        contains(" transfer-encoding: chunked");
    }

    @Disabled
    @Test
    void testCreateProduct()  {
        Product product = new Product();
        product.setTitle("Noodle");
        product.setPrice(20);
        product.setId(841L);
        product.setCategoryTitle("Food");

        assertEquals(748619411,service.createProduct(product).hashCode());
    }

    @Disabled
    @Test
    void testGetProductById() throws Exception {
        Product resCreate = service.getProduct(841L);
        assertJson(getResourceAsString("expP.json"),resCreate);
        containsString("price:555");
    }

    @Disabled
    @Test
    void testDeleteProduct(){
        Product deleteProduct = service.deleteProduct(841L);
        System.out.println(deleteProduct);


    }

 }
