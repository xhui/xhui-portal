package com.zan.portal.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.zan.portal.model.Category;
import com.zan.portal.persistent.dao.CategorieQueryDAO;
import com.zan.portal.persistent.dao.CategoryInsertDAO;

@Component
@Scope("prototype")
public class CategoryService implements Serializable{

	@Inject
	private CategorieQueryDAO queryDAO;

	@Inject
	private CategoryInsertDAO insertDAO;

	public List<Category> getAvailableCategories(int pageId) {
		return queryDAO.query(pageId);
	}

	public void addNewCategory(Category newly, Category parent) {
		insertDAO.insert(newly, parent);
	}
}