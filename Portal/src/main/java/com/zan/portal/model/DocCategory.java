package com.zan.portal.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DocCategory implements Serializable {
	private static final long serialVersionUID = 4227008286989552876L;
	private int pageId;
	private int categoryId;
	private String name;
	private DocCategory parentCategory;
	private List<DocCategory> subCategories;

	public DocCategory() {
		subCategories = new ArrayList<DocCategory>();
	}

	public DocCategory(DocCategory c) {
		this();
		setPageId(c.getPageId());
		setCategoryId(c.getCategoryId());
		setName(c.getName());
		addAllSubCategories(c.getSubCategories());
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPageId() {
		return pageId;
	}

	public void setPageId(int pageId) {
		this.pageId = pageId;
	}

	public List<DocCategory> getSubCategories() {
		return subCategories;
	}

	public void addSubCategories(DocCategory c) {
		subCategories.add(c);
	}

	public void addAllSubCategories(List<DocCategory> c) {
		subCategories.addAll(c);
	}

	public boolean isContainSubCategories() {
		return !subCategories.isEmpty();
	}

	public DocCategory getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(DocCategory parentCategory) {
		this.parentCategory = parentCategory;
	}

	public List<DocCategory> getParents() {
		List<DocCategory> categories = new ArrayList<>();
		DocCategory c = parentCategory;
		while (null != c) {
			categories.add(c);
			c = c.getParentCategory();
		}
		Collections.reverse(categories);
		return categories;
	}

	@Override
	public String toString() {
		return "Category [id=" + categoryId + ", name=" + name + "]";
	}
}