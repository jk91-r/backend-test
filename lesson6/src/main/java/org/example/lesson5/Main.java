package org.example.lesson5;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.db.dao.ProductsMapper;
import org.example.db.model.Products;
import org.example.db.model.ProductsExample;


import java.io.IOException;
import java.util.List;

import static org.apache.ibatis.io.Resources.getResourceAsStream;

public class Main {





   public static void main(String[] args ) throws IOException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
                .build(getResourceAsStream("myBatisConfig.xml"));


        try(SqlSession session = sqlSessionFactory.openSession()) {
            ProductsMapper productsMapper = session.getMapper(ProductsMapper.class);

            Products product = productsMapper.selectByPrimaryKey(841L);
            System.out.println(product);

            ProductsExample example= new ProductsExample();
            example.createCriteria()
                    .andTitleLike("Noodle")
                    .andIdEqualTo(841L);
            List<Products> products = productsMapper.selectByExample(example);
            System.out.println(products);

            example.clear();
            example.createCriteria()
                    .andIdGreaterThan(150L)
                    .andPriceBetween(50, 100);
            products = productsMapper.selectByExample(example);
            System.out.println(products);

            example.clear();
            example.createCriteria()
                    .andTitleLike("Potato")
                    .andIdGreaterThan(100L);
            products = productsMapper.selectByExample(example);
            System.out.println(products);

            productsMapper.deleteByPrimaryKey(841L);

            example.clear();
            example.createCriteria()
                    .andTitleLike("Noodle")
                    .andIdEqualTo(841L);
            products = productsMapper.selectByExample(example);
            System.out.println(products);

       }

    }
}
