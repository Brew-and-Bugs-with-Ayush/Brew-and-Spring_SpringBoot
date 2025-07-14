package com.Ayush.SpringJpaExample.Controller;

import com.Ayush.SpringJpaExample.Model.Product;
import com.Ayush.SpringJpaExample.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private ProductService service;

//    @Autowired
//    public ProductController(ProductService service){
//        this.service = service;
//    }

    @GetMapping("/products")
    public List<Product> getProduct(){
        return service.getProducts();
    }

    @GetMapping("/products/{prodId}")
    public Product getProductById(@PathVariable int prodId){
        return service.getProductById(prodId);
    }

    @PostMapping("/products")
    public void addProduct(@RequestBody Product prod){
        service.addProduct(prod);
    }

    @PutMapping("/products")
    public void updateProduct(@RequestBody Product prod){
        service.updateProduct(prod);
    }

    @DeleteMapping("/products/{prodId}")
    public void deleteProductById(@PathVariable int prodId){
        service.deleteProductById(prodId);
    }

}
