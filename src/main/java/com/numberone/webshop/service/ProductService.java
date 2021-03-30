package com.numberone.webshop.service;

import com.numberone.webshop.db.ProductRepository;
import com.numberone.webshop.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    public Product addProduct(Product product){
        return productRepository.save(product);
    }
    public Iterable<Product> getAllProducts(){
        return productRepository.findAll();
    }
    /*
    @PostConstruct
    public void init(){
        productRepository.save(new Product(                        1L,
                "macbook Retina 13.3' ME662 (2013)",
                "3.0GHz Dual-core Haswell Intel Core i5 Turbo Boost up to 3.2 GHz, 3MB L3 cache 8GB (two 4GB SO-DIMMs) of 1600MHz DDR3 SDRAM",
                "https://macbookpics.s3.eu-de.cloud-object-storage.appdomain.cloud/img1.jpeg",
                10,
                2399));
        productRepository.save(new Product(
                2L,
                "Macbook Pro 13.3' Retina MF841LL/A",
                "Macbook Pro 13.3' Retina MF841LL/A Model 2015 Option Ram Care 12/2016",
                "https://macbookpics.s3.eu-de.cloud-object-storage.appdomain.cloud/img2.jpeg",
                15,
                1199
        ));
        productRepository.save(new Product(
                3L,
                "Macbook Pro 15.4' Retina MC975LL/A Model 2012",
                "3.0GHz Dual-core Haswell Intel Core i5 Turbo Boost up to 3.2 GHz, 3MB L3 cache 8GB (two 4GB SO-DIMMs) of 1600MHz DDR3 SDRAM",
                "https://macbookpics.s3.eu-de.cloud-object-storage.appdomain.cloud/img3.jpeg",
                1,
                1800
        ));

    }
    */
}
