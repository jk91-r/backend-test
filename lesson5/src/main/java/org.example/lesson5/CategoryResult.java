package org.example.lesson5;


import lombok.Data;
import java.util.ArrayList;
import java.util.List;

    @Data
    public class CategoryResult {

        private int id;
        private String title;
        private List<ProductResult> products = new ArrayList<>();


    }




