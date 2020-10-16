package com.kohen.prod_cat.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kohen.prod_cat.models.Category;
import com.kohen.prod_cat.models.Product;
import com.kohen.prod_cat.models.Review;
import com.kohen.prod_cat.repos.CatRepo;
import com.kohen.prod_cat.repos.ProdRepo;
import com.kohen.prod_cat.repos.RevRepo;

@Service
public class ProdServ {

	private static ProdRepo prodRepo;
	private static CatRepo catRepo;
	private static RevRepo revRepo;

	public ProdServ(ProdRepo prodRepo, CatRepo catRepo, RevRepo revRepo) {
		ProdServ.prodRepo = prodRepo;
		ProdServ.catRepo = catRepo;
		ProdServ.revRepo = revRepo;
	}

	public Product create(Product newProduct) {
		return prodRepo.save(newProduct);
	}

	public Category create(Category newCategory) {
		return catRepo.save(newCategory);
	}

	public Review create(Review newReview) {
		List<Review> matchingReviews = revRepo.matchingReviews(newReview.getUser().getId(),
				newReview.getProduct().getId());
		if (matchingReviews.size() > 0) {
			return null;
		}
		newReview.setId(null);
		return revRepo.save(newReview);
	}

	public List<Category> getCategories() {
		return (List<Category>) catRepo.findAll();
	}

	public List<Product> getProducts() {
		return (List<Product>) prodRepo.findAll();
	}

	public Category getCategory(Long id) {
		Optional<Category> category = catRepo.findById(id);
		return category.isPresent() ? category.get() : null;
	}

	public Product getProduct(Long id) {
		Optional<Product> product = prodRepo.findById(id);
		return product.isPresent() ? product.get() : null;
	}

	public Product saveProduct(Product product) {
		return prodRepo.save(product);
	}

	public Category createOrRetrieve(String categoryName) {
		Optional<Category> mightExist = catRepo.findCategoryByName(categoryName);
		if (mightExist.isPresent()) {
			return mightExist.get();
		} else {
			return catRepo.save(new Category(categoryName));
		}
	}

	public Product createProductWithCategories(Product newProductPlus) {
		List<Category> categories = new ArrayList<Category>();
		for (String categoryName : newProductPlus.getCategoryInput().split(",")) {
			categories.add(createOrRetrieve(categoryName));
		}
		newProductPlus.setCategories(categories);
		return prodRepo.save(newProductPlus);
	}

	public List<Product> productsInCategory(String category) {
		Optional<Category> c = catRepo.findCategoryByName(category);
		return c.isPresent() ? c.get().getProducts() : new ArrayList<Product>();
	}

}