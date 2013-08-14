package com.zan.portal.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.zan.portal.model.Category;
import com.zan.portal.persistent.dao.CategorieQueryDAO;

@Component
@Scope("prototype")
public class CategoryService {

	@Inject
	private CategorieQueryDAO categoriesDAO;

	public List<Category> getAvailableCategories(int pageId) {
		return categoriesDAO.query(pageId);
	}
}