package com.Ayush.ecom_project.Controller;

import com.Ayush.ecom_project.Model.Product;
import com.Ayush.ecom_project.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api")
public class ProductController {

    private final ProductService service;

    @Autowired
    public ProductController(ProductService service) {  // constructor injection
        this.service = service;
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProduct(){
        return new ResponseEntity<>(service.getProducts() , HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductsById(@PathVariable int id){

        Product product = service.getProductById(id);
        if (product != null)
            return new ResponseEntity<>(product , HttpStatus.OK);

        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart Product product , @RequestPart MultipartFile imageFile){
        try {
            Product prod = service.addProduct(product, imageFile);
            return new ResponseEntity<>(prod , HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product/{productId}/image")
    public ResponseEntity<byte[]> getImageByProduct(@PathVariable int productId){

        Product product = service.getProductById(productId);

        if (product == null || product.getImageData() == null || product.getImageType() == null) {
            return ResponseEntity.notFound().build();
        }

        byte[] imageFile = product.getImageData();

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(product.getImageType()))
                .body(imageFile);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id , @RequestPart Product product , @RequestPart MultipartFile imageFile){

        Product prod = null;
        try {
            prod = service.updateProduct(id , product , imageFile);
        }
        catch (IOException e) {
            return new ResponseEntity<>("Failed to Update" , HttpStatus.BAD_REQUEST);
        }

        if (prod != null) return new ResponseEntity<>("Updated" , HttpStatus.OK);

        else return new ResponseEntity<>("Failed to Update" , HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){

        Product prod = service.getProductById(id);

        if (prod != null) {
            service.deleteProduct(id);
            return new ResponseEntity<>("Product deleted", HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("Product not found" , HttpStatus.NOT_FOUND);
    }

    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword){

        List<Product> products = service.searchProducts(keyword);

        return new ResponseEntity<>(products , HttpStatus.OK);
    }
}
