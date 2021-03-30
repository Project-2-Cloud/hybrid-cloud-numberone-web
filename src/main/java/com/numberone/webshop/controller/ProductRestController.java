package com.numberone.webshop.controller;

import com.numberone.webshop.db.ProductRepository;
import com.numberone.webshop.domain.Product;
import com.numberone.webshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/product")
public class ProductRestController {
    @Autowired
    private ProductService productService;

    @PostMapping("/addProduct")
    public Iterable<Product> addProduct(@Valid @RequestBody Product product){
        productService.addProduct(product);
        return productService.getAllProducts();
    }

    @GetMapping("/productsOverview")
    public Iterable<Product> overview(Model model){
        return productService.getAllProducts();
    }

}
