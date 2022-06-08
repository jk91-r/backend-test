package org.example.lesson5;

import lombok.Data;

    @Data
    public class ProductResult {

        private int id;
        private String title;
        private int price;
        private String categoryTitle;

        public ProductResult(int id) {
            this.id = id;
        }
    }
