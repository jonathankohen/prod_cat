package com.kohen.prod_cat.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kohen.prod_cat.models.Product;

@Repository
public interface ProdRepo extends CrudRepository<Product, Long> {
}