package com.zan.portal.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.zan.portal.model.DocCategory;
import com.zan.portal.service.CategoryService;

@Component
@Scope("request")
public class PageBean {

	private List<DocCategory> categories;

	private int pageId;

	@Inject
	private CategoryService categoryService;

	@PostConstruct
	public void init() {
		categories = new ArrayList<DocCategory>();
		Map<String, String> parameters = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		pageId = ViewUtils.transferInteger(parameters.get("pg"));
		if (pageId > -1) {
			categories.addAll(categoryService.getAvailableCategories(pageId));
		}
	}

	public List<DocCategory> getCategories() {
		if (null == categories) {
			// avoid null pointer
			categories = new ArrayList<DocCategory>();
		}
		return categories;
	}

	public int getPageId() {
		return pageId;
	}
}