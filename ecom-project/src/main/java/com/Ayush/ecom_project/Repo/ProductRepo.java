package com.Ayush.ecom_project.Repo;

import com.Ayush.ecom_project.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product , Integer> {

    @Query("SELECT p from Product p where " +
    "lower(p.name) Like lower(concat('%' , :keyword , '%')) or " +
            "lower(p.description) Like lower(concat('%' , :keyword , '%')) or " +
            "lower(p.brand) Like lower(concat('%' , :keyword , '%')) or " +
            "lower(p.category) Like lower(concat('%' , :keyword , '%'))")
    List<Product> searchProducts(String keyword);
}
