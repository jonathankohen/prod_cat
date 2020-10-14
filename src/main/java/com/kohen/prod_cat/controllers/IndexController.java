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
		model.addAttribute("allProds", prodServ.getProds());
		model.addAttribute("allCats", prodServ.getCats());
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
	public String newCategory(@Valid @ModelAttribute("newCat") Category newCat, BindingResult result) {
		if (result.hasErrors()) {
			return "index";
		} else {
			prodServ.create(newCat);
			return "redirect:/";
		}
	}

	@PostMapping("/add_category")
	public String addCatProd(@RequestParam("prod_id") Long prodId, @RequestParam("cat_id") Long catId) {
		Product theProd = prodServ.getProd(prodId);
		Category theCat = prodServ.getCat(catId);
		List<Category> productCategories = theProd.getCategories();
		productCategories.add(theCat);
		prodServ.saveProduct(theProd);
		return "redirect:/";
	}

	public String newProductPlus(Model model) {
		model.addAttribute("newProductPlus", new Product());
		model.addAttribute("allProducts", prodServ.getProds());
		return "product";
	}

	@PostMapping("/Products/new")
	public String createProductWithCategorys(@Valid @ModelAttribute("newProductPlus") Product newProductPlus,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("allProducts", prodServ.getProds());
			return "product";
		} else {
			prodServ.createProdWithCat(newProductPlus);
			return "redirect:/products/new";
		}
	}
}
