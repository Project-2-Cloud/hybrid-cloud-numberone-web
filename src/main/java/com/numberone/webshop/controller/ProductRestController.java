package com.numberone.webshop.controller;

import com.numberone.webshop.db.ProductRepository;
import com.numberone.webshop.domain.Product;
import com.numberone.webshop.service.ProductService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/product")
public class ProductRestController {
    @Autowired
    private ProductService productService;
    /*
    @PostMapping("/addProduct")
    public Iterable<Product> addProduct(@Valid Product product){
        productService.addProduct(product);
        return productService.getAllProducts();
    }
    */
    @GetMapping("/productsOverview")
    public Iterable<Product> overview(Model model){
        return productService.getAllProducts();
    }

    @GetMapping("/showProduct/{id}")
    public Product getProduct(@PathVariable("id") long id) {
        return productService.getProduct(id);
    }
    @PostMapping("/updateProduct/{id}")
    public void updateProduct(@PathVariable("id") long id, @Valid Product product, Model model){
        System.out.println("test");
        Product foundProduct = null;
        try{
            foundProduct = productService.getProduct(id);
        }
        catch (Exception e){};
        if (foundProduct != null){
            productService.save(product);
        }
    }
    @PostMapping("/addProduct")
    public void addProduct(@Valid Product product, Model model){
        productService.save(product);
    }
    @PostMapping("/deleteProduct/{id}")
    public void deleteProduct(@PathVariable("id") long id) throws Exception{
        Product product = productService.findByIdTeam(id).orElseThrow(() -> new IllegalArgumentException("ERROR"));
        productService.deleteProduct(id);
    }
}
