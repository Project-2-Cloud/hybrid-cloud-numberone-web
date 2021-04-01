package com.numberone.webshop.controller;

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
    @DeleteMapping("/deleteProduct/{id}")
    public void deleteProduct(@PathVariable("id") long id, Model model) throws Exception{
        model.addAttribute("test", "testAtt");
        Product product = productService.findByIdTeam(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "id"));
        productService.deleteProduct(id);

    }
    @PostMapping("/buyProduct/{id}")
    public void buyProduct(@PathVariable("id") long id, Model model){
        Product product;
        product = this.getProduct(id);
        if (product != null){
            product.setQuantity(product.getQuantity()-1);
            productService.save(product);
        }
    }

    // Stuur een POST request met json key "productsIds" met als value de id's van de gekochte producten gescheiden met ",".
    // Voorbeeld: {"productIds": "1,2,3,4,5"}
    @PostMapping("/buyProducts")
    @ResponseBody
    public void buyProducts(HttpServletRequest request, Model model){
        String productIdsString = request.getParameter("productIds");
        String[] strArray = productIdsString.split(",");
        ArrayList<Long> productLongs = new ArrayList<Long>();
        try{
            for (String str : strArray){
                productLongs.add(Long.valueOf(str));
            }
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id");
        }
        for (Long id : productLongs){
            Product product = null;
            product = this.getProduct(id);
            if (product != null){
                product.setQuantity(product.getQuantity()-1);
                productService.save(product);
            }
        }
    }
}
