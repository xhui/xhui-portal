package com.zan.portal.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.zan.portal.model.Category;
import com.zan.portal.service.CategoryService;

@Component
@Scope("request")
public class CategoriesBean {
	private List<Category> categories;

	@Inject
	private CategoryService categoryService;

	@PostConstruct
	public void init() {
		categories = categoryService.getAvailableCategories(2);
	}

	public List<Category> getCategories() {
		return categories;
	}
}