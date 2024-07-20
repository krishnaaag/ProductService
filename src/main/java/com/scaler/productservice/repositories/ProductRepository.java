package com.scaler.productservice.repositories;

import com.scaler.productservice.models.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    //JpaRepository<T, ID> - T means generic input, meaning which model we want to interact with.
    // Here we want to interact with Product model. Id means data type of PK of table.

    //Product Repo should contain all the methods(CRUD) related to Product model.

    /*
    To make JPA Repository (interface) compatible with Repository, we hve to do 2 things:
    1. Repository should be an interface(product repo).
    2. Repository should extend JPA Repository.
     */

    List<Product> findByPriceIsGreaterThan(Double price);
    //select * from products where price > ?

    List<Product> findProductByTitleLike(String word); // case sensitive
    //select * from products where title like '%iphone%'

    List<Product> findByTitleLikeIgnoreCase(String word); // case insensitive.

    List<Product> findTop5ByTitleContains(String word);
    //select * from products where title like '' LIMIT 5

//    List<Product> findTopByTitleContains(int top, String word);

    List<Product> findProductsByTitleContainsAndPriceGreaterThan(
            String word,
            Double price
    );

    List<Product> findProductsByTitleContainsOrderById(String word);

    Optional<Product> findProductById(Long id);

    @Override
    List<Product> findAll(Sort sort);
}
