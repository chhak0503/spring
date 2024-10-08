package com.ch07.repository.shop;

import com.ch07.entity.shop.Product;
import com.ch07.repository.shop.custom.OrderRepositoryCustom;
import com.ch07.repository.shop.custom.ProductRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer>, ProductRepositoryCustom {
}
