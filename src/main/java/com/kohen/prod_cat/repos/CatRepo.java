package com.kohen.prod_cat.repos;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kohen.prod_cat.models.Category;

@Repository
public interface CatRepo extends CrudRepository<Category, Long> {

	Optional<Category> findCategoryByName(String name);

}