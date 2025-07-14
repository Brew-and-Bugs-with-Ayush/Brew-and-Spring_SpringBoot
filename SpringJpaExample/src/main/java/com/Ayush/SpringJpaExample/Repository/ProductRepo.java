package com.Ayush.SpringJpaExample.Repository;

import com.Ayush.SpringJpaExample.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product , Integer>{}
