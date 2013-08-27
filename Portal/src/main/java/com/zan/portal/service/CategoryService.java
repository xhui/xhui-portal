package com.zan.portal.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.zan.portal.model.DocCategory;
import com.zan.portal.persistent.dao.CategorieQueryDAO;
import com.zan.portal.persistent.dao.CategoryMaintainDAO;
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
	private CategoryMaintainDAO updateDAO;

	public List<DocCategory> getAvailableCategories(int pageId) {
		return queryDAO.query(pageId);
	}

	public void addNewCategory(DocCategory newly, DocCategory parent)
			throws ApplicationException {
		insertDAO.insert(newly, parent);
	}

	public void deleteCategory(DocCategory c) {
		updateDAO.delete(c);
	}

	public void updateCategory(DocCategory c, DocCategory parent) {
		updateDAO.update(c, parent);
	}
}