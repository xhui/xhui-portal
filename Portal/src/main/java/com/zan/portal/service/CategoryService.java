package com.zan.portal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.zan.portal.model.DocCategory;
import com.zan.portal.persistent.dao.CategorieQueryDAO;
import com.zan.portal.persistent.dao.CategoryInsertDAO;
import com.zan.portal.persistent.dao.CategoryMaintainDAO;
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

	public DocCategory getCategory(int categoryId) {
		Map<Integer, DocCategory> categories = queryDAO.query();
		for (DocCategory category : categories.values()) {
			if (category.getCategoryId() == categoryId) {
				return category;
			}
		}
		return null;
	}

	public List<DocCategory> getChildCategories(int categoryId) {
		List<DocCategory> target = new ArrayList<>();
		Map<Integer, DocCategory> categories = queryDAO.query();
		for (DocCategory category : categories.values()) {
			if (category.getCategoryId() == categoryId) {
				target.add(category);
				gatherAllChildCategories(target, category.getSubCategories());
			}
		}
		return target;
	}

	private void gatherAllChildCategories(List<DocCategory> target,
			List<DocCategory> src) {
		for (DocCategory category : src) {
			target.add(category);
			gatherAllChildCategories(target, category.getSubCategories());
		}
	}

	public List<DocCategory> getAvailableCategories(int pageId) {
		List<DocCategory> categoryTree = new ArrayList<>();
		Map<Integer, DocCategory> categories = queryDAO.query();
		for (DocCategory category : categories.values()) {
			if (null == category.getParentCategory()
					&& pageId == category.getPageId()) {
				// No parent, so remove parent
				categoryTree.add(category);
			}
		}
		return categoryTree;
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