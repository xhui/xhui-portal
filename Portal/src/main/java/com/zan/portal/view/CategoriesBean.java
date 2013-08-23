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

	private List<Category> careerCategories;

	private List<Category> lifeCategories;

	@Inject
	private CategoryService categoryService;

	@PostConstruct
	public void init() {

	}

	public List<Category> getCareerCategories() {
		if (null == careerCategories) {
			careerCategories = categoryService.getAvailableCategories(2);
		}
		return careerCategories;
	}

	public List<Category> getLifeCategories() {
		if (null == lifeCategories) {
			lifeCategories = categoryService.getAvailableCategories(1);
		}
		return lifeCategories;
	}
}