package com.kohen.prod_cat.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kohen.prod_cat.models.LoginUser;
import com.kohen.prod_cat.models.Product;
import com.kohen.prod_cat.models.Review;
import com.kohen.prod_cat.models.User;
import com.kohen.prod_cat.services.ProdServ;
import com.kohen.prod_cat.services.UserServ;

@Controller
public class UserController {

	private static UserServ userServ;
	private static ProdServ prodServ;

	public UserController(UserServ userServ, ProdServ prodServ) {
		UserController.userServ = userServ;
		UserController.prodServ = prodServ;
	}

	@GetMapping("/")
	public String signIn(Model model) {
		model.addAttribute("registerringUser", new User());
		model.addAttribute("loginUser", new LoginUser());
		return "signIn";
	}

	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("registerringUser") User registerringUser, BindingResult result,
			Model model, HttpSession session) {
		if (!registerringUser.getPassword().equals(registerringUser.getConfirm())) {
			result.rejectValue("confirm", "Match", "Confirm Password must match Password!");
		}
		if (userServ.getUser(registerringUser.getEmail()) != null) {
			result.rejectValue("email", "Unique", "Email already in use!");
		}

		if (result.hasErrors()) {
			model.addAttribute("loginUser", new LoginUser());
			return "signIn";
		} else {
			session.setAttribute("user", userServ.create(registerringUser));
			return "redirect:/home";
		}
	}

	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("loginUser") LoginUser loginUser, BindingResult result, Model model,
			HttpSession session) {
		User loggingInUser = userServ.login(loginUser, result);
		if (result.hasErrors()) {
			model.addAttribute("registerringUser", new User());
			return "signIn";
		} else {
			session.setAttribute("user", loggingInUser);
			return "redirect:/home";
		}
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		return "redirect:/";
	}

	@GetMapping("/user")
	public String userProfile(HttpSession session, Model model) {
		User loggedInUser = (User) session.getAttribute("user");
		if (loggedInUser == null) {
			return "redirect:/";
		}
		User userFromDB = userServ.getUser(loggedInUser.getId());
		model.addAttribute("user", userFromDB);

		List<Product> otherProducts = prodServ.getProducts();
		for (Review r : userFromDB.getReviews()) {
			otherProducts.remove(r.getProduct());
		}
		model.addAttribute("productsToReview", otherProducts);
		return "userProfile";
	}
}