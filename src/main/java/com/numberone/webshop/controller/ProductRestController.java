package com.numberone.webshop.controller;

import com.google.gson.Gson;
import com.numberone.webshop.db.ProductRepository;
import com.numberone.webshop.domain.Product;
import com.numberone.webshop.service.ProductService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@CrossOrigin(origins = "*")
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
    public Iterable<Product> overview(Model model) {
        return productService.getAllProducts();
    }

    @GetMapping("/showProduct/{id}")
    public Product getProduct(@PathVariable("id") long id) {
        return productService.getProduct(id);
    }

    @PostMapping("/updateProduct/{id}")
    public void updateProduct(@PathVariable("id") long id, @Valid Product product, Model model) {
        System.out.println("test");
        Product foundProduct = null;
        try {
            foundProduct = productService.getProduct(id);
        } catch (Exception e) {
        }
        ;
        if (foundProduct != null) {
            productService.save(product);
        }
    }

    @PostMapping("/addProduct")
    public void addProduct(@Valid Product product, Model model) {
        productService.save(product);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public void deleteProduct(@PathVariable("id") long id, Model model) throws Exception {
        model.addAttribute("test", "testAtt");
        Product product = productService.findByIdTeam(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "id"));
        productService.deleteProduct(id);

    }
    @PostMapping("/buyProducts")
    @ResponseBody
    public void buyProducts(HttpServletRequest request, Model model) {
        String productIdsString = request.getParameter("productIds");
        System.out.println(productIdsString);
        Gson gson = new Gson();
        Long[] productsIdsLongs = gson.fromJson(productIdsString, Long[].class);
        for (Long productId : productsIdsLongs) {
            System.out.println(productId);
        }
        for (Long id : productsIdsLongs){
            Product product = null;
            product = this.getProduct(id);
            if (product != null){
                product.setQuantity(product.getQuantity()-1);
                productService.save(product);
            }
        }
    }
}
