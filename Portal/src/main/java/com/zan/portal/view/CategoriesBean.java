package com.zan.portal.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.zan.portal.model.DocCategory;
import com.zan.portal.service.CategoryService;

@Component
@Scope("request")
public class CategoriesBean {

	private List<DocCategory> careerCategories;

	private List<DocCategory> lifeCategories;

	@Inject
	private CategoryService categoryService;

	@PostConstruct
	public void init() {

	}

	public List<DocCategory> getCareerCategories() {
		if (null == careerCategories) {
			careerCategories = categoryService.getAvailableCategories(2);
		}
		return careerCategories;
	}

	public List<DocCategory> getLifeCategories() {
		if (null == lifeCategories) {
			lifeCategories = categoryService.getAvailableCategories(1);
		}
		return lifeCategories;
	}
}