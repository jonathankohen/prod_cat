package com.kohen.prod_cat.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

@Entity
@Table(name = "products")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(min = 2, max = 200, message = "Name must be 2 characters or longer.")
	private String name;

	@Size(min = 5, max = 200, message = "Description must be 5 characters or longer.")
	private String description;

	private double price;

	@Transient
	private String categoryInput;

	@Transient
	private String productInput;

	@Column(updatable = false)
	private Date createdAt;
	private Date updatedAt;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "categories_products", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
	private List<Category> categories;

	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
	private List<Review> reviews;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getProductInput() {
		return productInput;
	}

	public void setProductInput(String productInput) {
		this.productInput = productInput;
	}

	public String getCategoryInput() {
		return categoryInput;
	}

	public void setCategoryInput(String categoryInput) {
		this.categoryInput = categoryInput;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}

	public String categoriesDescription() {
		String result = "";
		for (int i = 0; i < categories.size(); i++) {
			result += categories.get(i).getName();
			if (i < categories.size() - 1) {
				result += ", ";
			}
		}
		return result;
	}

	public Double getAverageRating() {
		if (reviews.size() == 0) {
			return 0d;
		}
		Double total = 0d;
		for (Review r : reviews) {
			total += r.getRating();
		}
		return total / reviews.size();
	}
}
