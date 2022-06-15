package org.example.lesson5;


import lombok.Data;
import java.util.ArrayList;
import java.util.List;

    @Data
    public class CategoryResult {

        private Long id;
        private String title;
        private List<Product> products = new ArrayList<>();


    }




