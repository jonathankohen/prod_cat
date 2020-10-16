package com.kohen.prod_cat.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.kohen.prod_cat.models.Product;
import com.kohen.prod_cat.models.Review;
import com.kohen.prod_cat.models.User;
import com.kohen.prod_cat.services.ProdServ;

@Controller
public class IndexController {

	private static ProdServ prodServ;

	public IndexController(ProdServ prodServ) {
		IndexController.prodServ = prodServ;
	}

	@GetMapping("/home")
	public String newProductPlus(Model model, HttpSession session) {
		User loggedInUser = (User) session.getAttribute("user");
		if (loggedInUser == null) {
			return "redirect:/";
		}
		model.addAttribute("title", "All Products!");
		model.addAttribute("newProductPlus", new Product());
		model.addAttribute("allProducts", prodServ.getProducts());
		return "product";
	}

	@PostMapping("/products/new")
	public String createProductWithCategorys(@Valid @ModelAttribute("newProductPlus") Product newProductPlus,
			BindingResult result, Model model, HttpSession session) {
		if (result.hasErrors()) {
			model.addAttribute("allProducts", prodServ.getProducts());
			return "product";
		} else {
			User loggedInUser = (User) session.getAttribute("user");
			newProductPlus.setUser(loggedInUser);
			prodServ.createProductWithCategories(newProductPlus);
			return "redirect:/home";
		}
	}

	@GetMapping("/products/{id}")
	public String showProduct(@PathVariable("id") Long id, Model model, HttpSession session) {
		User loggedInUser = (User) session.getAttribute("user");
		if (loggedInUser == null) {
			return "redirect:/";
		}
		model.addAttribute("singleProduct", prodServ.getProduct(id));
		model.addAttribute("newReview", new Review());
		return "review";
	}

	@PostMapping("/products/{id}/review")
	public String reviewProduct(@Valid @ModelAttribute("newReview") Review newReview, BindingResult result,
			@PathVariable("id") Long id, Model model, HttpSession session) {
		User loggedInUser = (User) session.getAttribute("user");
		if (result.hasErrors()) {
			model.addAttribute("singleProduct", prodServ.getProduct(id));
			return "review";
		}
		newReview.setProduct(prodServ.getProduct(id));
		newReview.setUser(loggedInUser);
		Review r = prodServ.create(newReview);
		if (r == null) {
			result.rejectValue("content", "unique", "You have already reviewed this product!");
			model.addAttribute("singleProduct", prodServ.getProduct(id));
			return "review";
		}
		return "redirect:/products/" + id;
	}

	@GetMapping("/category/{category}")
	public String ProductsInCategory(@PathVariable("category") String category, Model model, HttpSession session) {
		User loggedInUser = (User) session.getAttribute("user");
		if (loggedInUser == null) {
			return "redirect:/";
		}
		model.addAttribute("title", "Category: " + category);
		model.addAttribute("newProductPlus", new Product());
		model.addAttribute("allProducts", prodServ.productsInCategory(category));
		return "product";
	}
}

//	@GetMapping("/")
//	public String index(Model model) {
//		model.addAttribute("newProduct", new Product());
//		model.addAttribute("newCategory", new Category());
//		model.addAttribute("allProducts", prodServ.getProducts());
//		model.addAttribute("allCategories", prodServ.getCategories());
//		return "index";
//	}

//	@PostMapping("/products")
//	public String newProduct(@Valid @ModelAttribute("newProduct") Product newProduct, BindingResult result) {
//		if (result.hasErrors()) {
//			return "index";
//		} else {
//			prodServ.create(newProduct);
//			return "redirect:/";
//		}
//	}

//	@PostMapping("/categories")
//	public String newCategory(@Valid @ModelAttribute("newCategory") Category newCategory, BindingResult result) {
//		if (result.hasErrors()) {
//			return "index";
//		} else {
//			prodServ.create(newCategory);
//			return "redirect:/";
//		}
//	}
//
//	@PostMapping("/add_category")
//	public String addCategoryToProduct(@RequestParam("product_id") Long productId,
//			@RequestParam("category_id") Long categoryId) {
//		Product theProduct = prodServ.getProduct(productId);
//		Category theCategory = prodServ.getCategory(categoryId);
//		List<Category> productCategories = theProduct.getCategories();
//		productCategories.add(theCategory);
//		prodServ.saveProduct(theProduct);
//		return "redirect:/";
//	}
