package com.kohen.prod_cat.repos;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kohen.prod_cat.models.Review;

@Repository
public interface RevRepo extends CrudRepository<Review, Long> {
	@Query(value = "SELECT * FROM reviews WHERE user_id = ?1 AND product_id = ?2", nativeQuery = true)
	List<Review> matchingReviews(Long user_id, Long product_id);
}
