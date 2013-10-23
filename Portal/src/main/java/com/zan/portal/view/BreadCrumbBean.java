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
public class BreadCrumbBean {

	private List<DocCategory> categories = new ArrayList<DocCategory>();

	@Inject
	private CategoryService categoryService;

	@PostConstruct
	public void init() {
		categories.clear();
		Map<String, String> parameters = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		int cid = ViewUtils.transferInteger(parameters.get("cid"));
		int pageId = ViewUtils.transferInteger(parameters.get("pg"));
		if (pageId > -1) {
			if (cid > -1) {
				DocCategory category = categoryService.getCategory(pageId, cid);
				if (null != category) {
					categories.addAll(category.getParents());
					categories.add(category);
				}
				return;
			}
		}
	}

	public List<DocCategory> getCategories() {
		return categories;
	}

	public boolean isExisting() {
		return !categories.isEmpty();
	}
}