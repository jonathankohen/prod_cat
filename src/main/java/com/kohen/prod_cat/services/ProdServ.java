package com.kohen.prod_cat.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kohen.prod_cat.models.Category;
import com.kohen.prod_cat.models.Product;
import com.kohen.prod_cat.repos.CatRepo;
import com.kohen.prod_cat.repos.ProdRepo;

@Service
public class ProdServ {

	private static ProdRepo prodRepo;
	private static CatRepo catRepo;

	public ProdServ(ProdRepo prodRepo, CatRepo catRepo) {
		ProdServ.prodRepo = prodRepo;
		ProdServ.catRepo = catRepo;
	}

	public Product create(Product newProduct) {
		return prodRepo.save(newProduct);
	}

	public Category create(Category newCategory) {
		return catRepo.save(newCategory);
	}

	public List<Category> getCats() {
		return (List<Category>) catRepo.findAll();
	}

	public List<Product> getProds() {
		return (List<Product>) prodRepo.findAll();
	}

	public Category getCat(Long id) {
		Optional<Category> Category = catRepo.findById(id);
		return Category.isPresent() ? Category.get() : null;
	}

	public Product getProd(Long id) {
		Optional<Product> Product = prodRepo.findById(id);
		return Product.isPresent() ? Product.get() : null;
	}

	public Product saveProduct(Product Product) {
		return prodRepo.save(Product);
	}

	public Category createOrRetrieve(String categoryName) {
		Optional<Category> mightExist = catRepo.findCategoryByName(categoryName);
		if (mightExist.isPresent()) {
			return mightExist.get();
		} else {
			return catRepo.save(new Category(categoryName));
		}
	}

	public Product createProdWithCat(Product newProdPlus) {
		List<Category> Cat = new ArrayList<Category>();
		for (String CatName : newProdPlus.getCatInput().split(",")) {
			Cat.add(createOrRetrieve(CatName));
		}
		newProdPlus.setCategories(Cat);
		return prodRepo.save(newProdPlus);
	}
}