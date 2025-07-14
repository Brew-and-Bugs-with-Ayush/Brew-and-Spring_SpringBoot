package com.Ayush.SpringJpaExample.Service;

import com.Ayush.SpringJpaExample.Model.Product;
import com.Ayush.SpringJpaExample.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo repo;

//     Hardcoded values
//    @Getter  // this annotation used to make getter method
//    List<Product> products = new ArrayList<>(Arrays.asList(
//            new Product(101 , "onePlus" , 2000),
//            new Product(102 , "samsung" , 4000),
//            new Product(103 , "canon" , 3000)
//    ));

    public List<Product> getProducts(){
        return repo.findAll();
    }

    public Product getProductById(int prodId){
//        return products.stream()
//                .filter(p -> p.getProdId() == prodId)
//                .findFirst().orElse(new Product(100 , "no item" , 0));

        return repo.findById(prodId).orElse(new Product());
    }

    public void addProduct(Product prod){
//        products.add(prod);

        repo.save(prod);
    }

    public void updateProduct(Product prod){
//        int index = 0;
//        for(int i = 0 ; i < products.size() ; i++){
//            if (products.get(i).getProdId() == prod.getProdId()){
//                index = i;
//                products.set(index , prod);
//            }
//        }

        repo.save(prod);
    }
    public void deleteProductById(int prodId){
//        int index = 0;
//        for(int i = 0 ; i < products.size() ; i++){
//            if (products.get(i).getProdId() == prodId){
//                index = i;
//                products.remove(index);
//            }
//        }

        repo.deleteById(prodId);
    }
}
