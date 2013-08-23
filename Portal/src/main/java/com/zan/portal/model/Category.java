package com.zan.portal.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Category implements Serializable {
	private static final long serialVersionUID = 4227008286989552876L;
	private int pageId;
	private int categoryId;
	private String name;
	private List<Category> subCategories;

	public Category() {
		subCategories = new ArrayList<Category>();
	}

	public Category(Category c) {
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

	public List<Category> getSubCategories() {
		return subCategories;
	}

	public void addSubCategories(Category c) {
		subCategories.add(c);
	}

	public void addAllSubCategories(List<Category> c) {
		subCategories.addAll(c);
	}

	public boolean isContainSubCategories() {
		return !subCategories.isEmpty();
	}
}