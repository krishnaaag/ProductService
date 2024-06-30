package com.scaler.productservice.services;

import com.scaler.productservice.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {

    Product getSingleProduct(Long productId);

    List<Product> getAllProducts();



}
