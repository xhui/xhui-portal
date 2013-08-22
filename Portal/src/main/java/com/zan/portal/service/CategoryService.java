package com.zan.portal.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.zan.portal.model.Category;
import com.zan.portal.persistent.dao.CategorieQueryDAO;
import com.zan.portal.persistent.dao.CategoryDeleteDAO;
import com.zan.portal.persistent.dao.CategoryInsertDAO;
import com.zan.portal.utils.error.ApplicationException;

@Component
@Scope("prototype")
public class CategoryService {

	@Inject
	private CategorieQueryDAO queryDAO;

	@Inject
	private CategoryInsertDAO insertDAO;

	@Inject
	private CategoryDeleteDAO deleteDAO;

	public List<Category> getAvailableCategories(int pageId) {
		return queryDAO.query(pageId);
	}

	public void addNewCategory(Category newly, Category parent)
			throws ApplicationException {
		insertDAO.insert(newly, parent);
	}

	public void deleteCategory(Category c) {
		deleteDAO.delete(c);
	}
}