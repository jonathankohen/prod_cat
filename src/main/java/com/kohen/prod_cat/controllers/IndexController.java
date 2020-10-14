package com.kohen.prod_cat.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kohen.prod_cat.models.Category;
import com.kohen.prod_cat.models.Product;
import com.kohen.prod_cat.services.ProdServ;

@Controller
public class IndexController {

	private static ProdServ prodServ;

	public IndexController(ProdServ prodServ) {
		IndexController.prodServ = prodServ;
	}

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("newProduct", new Product());
		model.addAttribute("newCategory", new Category());
		model.addAttribute("allProducts", prodServ.getProducts());
		model.addAttribute("allCategories", prodServ.getCategories());
		return "index";
	}

	@PostMapping("/products")
	public String newProduct(@Valid @ModelAttribute("newProduct") Product newProduct, BindingResult result) {
		if (result.hasErrors()) {
			return "index";
		} else {
			prodServ.create(newProduct);
			return "redirect:/";
		}
	}

	@PostMapping("/categories")
	public String newCategory(@Valid @ModelAttribute("newCategory") Category newCategory, BindingResult result) {
		if (result.hasErrors()) {
			return "index";
		} else {
			prodServ.create(newCategory);
			return "redirect:/";
		}
	}

	@PostMapping("/add_category")
	public String addCategoryToProduct(@RequestParam("product_id") Long productId,
			@RequestParam("category_id") Long categoryId) {
		Product theProduct = prodServ.getProduct(productId);
		Category theCategory = prodServ.getCategory(categoryId);
		List<Category> productCategories = theProduct.getCategories();
		productCategories.add(theCategory);
		prodServ.saveProduct(theProduct);
		return "redirect:/";
	}

	public String newProductPlus(Model model) {
		model.addAttribute("newProductPlus", new Product());
		model.addAttribute("allProducts", prodServ.getProducts());
		return "product";
	}

	@PostMapping("/products/new")
	public String createProductWithCategories(@Valid @ModelAttribute("newProductPlus") Product newProductPlus,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("allProducts", prodServ.getProducts());
			return "product";
		} else {
			prodServ.createProductWithCategory(newProductPlus);
			return "redirect:/products/new";
		}
	}
}
