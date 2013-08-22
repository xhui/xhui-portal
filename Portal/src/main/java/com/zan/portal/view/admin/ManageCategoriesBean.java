package com.zan.portal.view.admin;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.zan.portal.model.Category;
import com.zan.portal.model.Page;
import com.zan.portal.service.CategoryService;
import com.zan.portal.service.PageService;
import com.zan.portal.utils.Constant;
import com.zan.portal.utils.ErrorCode;
import com.zan.portal.utils.error.ApplicationException;
import com.zan.portal.view.ViewUtils;

@Component
@Scope("view")
public class ManageCategoriesBean implements Serializable {
	private static final long serialVersionUID = -4641424146371360582L;

	@Autowired
	private transient CategoryService categoryService;

	@Autowired
	private transient PageService pageService;

	private Category category;

	private TreeNode rootNode;

	private TreeNode selectedNode;

	private boolean actionUpdate;

	@PostConstruct
	public void init() {
		buildTree();
	}

	private void buildTree() {
		List<Page> pages = pageService.getPages();
		Category rootCategory = new Category();
		rootCategory.setCategoryId(-1);
		rootCategory.setName("Root");
		rootCategory.setPageId(-1);
		rootNode = new DefaultTreeNode(rootCategory, null);
		rootNode.setExpanded(true);

		for (Page p : pages) {
			Category c = new Category();
			c.setName(p.getPageName());
			c.setCategoryId(-1);
			c.setPageId(p.getPageId());
			c.addAllSubCategories(categoryService.getAvailableCategories(p
					.getPageId()));
			TreeNode node = new DefaultTreeNode(c, rootNode);
			node.setExpanded(true);
			buildTree(node, c.getSubCategories());
		}
	}

	private void buildTree(TreeNode parentNode, List<Category> categories) {
		for (Category c : categories) {
			TreeNode node = new DefaultTreeNode(c, parentNode);
			node.setExpanded(true);
			buildTree(node, c.getSubCategories());
		}
	}

	public void preAddNewCategory() {
		category = new Category();
		actionUpdate = false;
	}

	public void addNewCategory() throws ApplicationException {
		if (selectedNode != null) {
			Category parent = (Category) selectedNode.getData();
			categoryService.addNewCategory(category, parent);
			// trigger init to refresh the data.
			buildTree();
			ViewUtils.showSuccessMessage();
			// can only select one node, so only update first
			// not null node.
			return;
		}
		ViewUtils.showFailMessage(ErrorCode.MC_NOTHING_SELECTED);
	}

	public void deleteCategory() throws ApplicationException {
		if (selectedNode != null) {
			if (!selectedNode.isLeaf()) {
				ViewUtils
						.showInfoMessage(Constant.MC_CANNOT_DELETE_CATEGORY_WITH_CHILD);
				return;
			}
			Category c = (Category) selectedNode.getData();
			categoryService.deleteCategory(c);
			// trigger init to refresh the data.
			buildTree();
			// can only select one node, so only update first
			// not null node.
			ViewUtils.showSuccessMessage();
			return;
		}
		ViewUtils.showFailMessage(ErrorCode.MC_NOTHING_SELECTED);
	}

	public void preUpdateNewCategory() {
		actionUpdate = true;
		if (selectedNode != null) {
			category = (Category) selectedNode.getData();
		} else {
			ViewUtils.showFailMessage(ErrorCode.MC_NOTHING_SELECTED);
		}
	}

	public void updateNewCategory() {
		if (selectedNode != null && selectedNode.getParent() != null) {
			Category parent = (Category) selectedNode.getParent().getData();
			// Category newly = (Category) selectedNode.getData();
			// newly.setName(category.getName());
			categoryService.updateCategory(category, parent);
			// trigger init to refresh the data.
			buildTree();
			ViewUtils.showSuccessMessage();
			// can only select one node, so only update first
			// not null node.
			return;
		}
		ViewUtils.showFailMessage(ErrorCode.MC_NOTHING_SELECTED);
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Category getCategory() {
		return category;
	}

	public TreeNode getSelectedNode() {
		return selectedNode;
	}

	public TreeNode getRootNode() {
		return rootNode;
	}

	public void setSelectedNode(TreeNode selectedNode) {
		this.selectedNode = selectedNode;
	}

	public boolean isActionUpdate() {
		return actionUpdate;
	}
}