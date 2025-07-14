package com.Ayush.ecom_project.Service;

import com.Ayush.ecom_project.Model.Product;
import com.Ayush.ecom_project.Repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepo repo;

    @Autowired
    public ProductService(ProductRepo repo) {  // constructor injection
        this.repo = repo;
    }

    public List<Product> getProducts(){
        return repo.findAll();
    }

    public Product getProductById(int id) {
        return repo.findById(id).orElse(null);
    }

    public Product addProduct(Product product, MultipartFile imageFile) throws IOException {

        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());

        return repo.save(product);
    }

    public Product updateProduct(int id, Product product, MultipartFile imageFile) throws IOException{

        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null.");
        }

        if (imageFile == null) {
            throw new IllegalArgumentException("Image file cannot be null.");
        }

        if (product.getId() == id) {
            product.setImageData(imageFile.getBytes());
            product.setImageName(imageFile.getOriginalFilename());
            product.setImageType(imageFile.getContentType());

            return  repo.save(product);
        }
        else {
            throw new IllegalArgumentException("Product ID does not match the path variable.");
        }
    }

    public void deleteProduct(int id) {
         repo.deleteById(id);
    }

    public List<Product> searchProducts(String keyword) {
        return repo.searchProducts(keyword);
    }
}
