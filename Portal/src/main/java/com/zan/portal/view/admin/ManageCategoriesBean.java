package com.zan.portal.view.admin;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.zan.portal.model.Category;
import com.zan.portal.model.Page;
import com.zan.portal.service.CategoryService;
import com.zan.portal.service.PageService;

@Component
@Scope("request")
public class ManageCategoriesBean {
	private TreeNode root;

	@Inject
	private CategoryService categoryService;
	@Inject
	private PageService pageService;

	@PostConstruct
	public void init() {
		root = new DefaultTreeNode("Categories", null);
		root.setExpanded(true);
		List<Page> pages = pageService.getPages();
		for (Page p : pages) {
			TreeNode node = new DefaultTreeNode(p.getPageName(), root);
			node.setExpanded(true);
			buildTree(node,
					categoryService.getAvailableCategories(p.getPageId()));
		}
	}

	private void buildTree(TreeNode parentNode, List<Category> categories) {
		for (Category c : categories) {
			TreeNode node = new DefaultTreeNode(c.getName(), parentNode);
			node.setExpanded(true);
			buildTree(node, c.getSubCategories());
		}
	}

	public TreeNode getRoot() {
		return root;
	}
}