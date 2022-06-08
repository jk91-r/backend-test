package org.example.lesson5;


import lombok.Data;

@Data
public class Product {

        public static int id;
        private String title;
        private int price;
        private String categoryTitle;

        public Product(int id, String title, int price, String categoryTitle) {
            this.id = id;
            this.title = title;
            this.price = price;
            this.categoryTitle = categoryTitle;
        }
  }
