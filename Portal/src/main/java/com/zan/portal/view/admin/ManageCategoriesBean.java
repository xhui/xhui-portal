package com.zan.portal.view.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.zan.portal.model.DocCategory;
import com.zan.portal.model.Document;
import com.zan.portal.model.Page;
import com.zan.portal.service.CategoryService;
import com.zan.portal.service.DocumentService;
import com.zan.portal.service.PageService;
import com.zan.portal.utils.Constant;
import com.zan.portal.utils.ErrorCode;
import com.zan.portal.utils.error.ApplicationException;
import com.zan.portal.view.ViewUtils;

@Component
@Scope("view")
public class ManageCategoriesBean implements Serializable {

	private static final long serialVersionUID = -4641424146371360582L;

	private static final int ROOT_PAGE = -1;
	private static final int PAGE_ITEM = -1;

	@Autowired
	private transient CategoryService categoryService;

	@Autowired
	private transient PageService pageService;

	@Autowired
	private transient DocumentService documentService;

	private DocCategory category;

	private TreeNode rootNode;

	private TreeNode selectedNode;

	private boolean actionUpdate;

	private Document documentEntry;

	private int updateDocId;

	private List<Document> documents;

	@PostConstruct
	public void init() {
		buildTree();
	}

	private void buildTree() {
		List<Page> pages = pageService.getPages();
		DocCategory rootCategory = new DocCategory();
		rootCategory.setCategoryId(PAGE_ITEM);
		rootCategory.setName("Root");
		rootCategory.setPageId(ROOT_PAGE);
		rootNode = new DefaultTreeNode(rootCategory, null);
		rootNode.setExpanded(true);

		for (Page p : pages) {
			DocCategory c = new DocCategory();
			c.setName(p.getPageName());
			c.setCategoryId(PAGE_ITEM);
			c.setPageId(p.getPageId());
			c.addAllSubCategories(categoryService.getAvailableCategories(p
					.getPageId()));
			TreeNode node = new DefaultTreeNode(c, rootNode);
			node.setExpanded(true);
			buildTree(node, c.getSubCategories());
		}
	}

	private void buildTree(TreeNode parentNode, List<DocCategory> categories) {
		for (DocCategory c : categories) {
			TreeNode node = new DefaultTreeNode(c, parentNode);
			node.setExpanded(true);
			buildTree(node, c.getSubCategories());
		}
	}

	private void refreshDocuments() {
		documents = new ArrayList<Document>();
		documents
				.addAll(documentService.getDocuments(category.getCategoryId()));
	}

	public void preAddNewCategory() {
		category = new DocCategory();
		actionUpdate = false;
	}

	public void preUpdateNewCategory() {
		actionUpdate = true;
		if (selectedNode != null) {
			DocCategory original = (DocCategory) selectedNode.getData();
			category = new DocCategory(original);
		} else {
			ViewUtils.showFailMessage(ErrorCode.MC_NOTHING_SELECTED);
		}
	}

	public void doUpdateCategory() throws ApplicationException {
		preManage(new ManageHandler() {
			@Override
			public boolean handle() throws ApplicationException {
				if (actionUpdate) {
					if (category.getCategoryId() == PAGE_ITEM) {
						ViewUtils.showInfoMessage(Constant.MC_CANNOT_EDIT_ROOT);
					} else {
						DocCategory parent = (null == selectedNode.getParent()) ? null
								: (DocCategory) selectedNode.getParent()
										.getData();
						categoryService.updateCategory(category, parent);
						return true;
					}
				} else {
					DocCategory parent = (DocCategory) selectedNode.getData();
					category.setPageId(parent.getPageId());
					if (parent.getCategoryId() == PAGE_ITEM) {
						categoryService.addNewCategory(category, null);
					} else {
						categoryService.addNewCategory(category, parent);
					}
				}
				return false;
			}
		});
	}

	public void doDeleteCategory() throws ApplicationException {
		preManage(new ManageHandler() {
			@Override
			public boolean handle() {
				DocCategory c = (DocCategory) selectedNode.getData();
				if (c.getCategoryId() == PAGE_ITEM) {
					ViewUtils.showInfoMessage(Constant.MC_CANNOT_EDIT_ROOT);
				} else {
					if (!selectedNode.isLeaf()) {
						ViewUtils
								.showInfoMessage(Constant.MC_CANNOT_DELETE_CATEGORY_WITH_CHILD);
					} else {
						categoryService.deleteCategory(c);
						return true;
					}
				}
				return false;
			}
		});
	}

	private void preManage(ManageHandler h) throws ApplicationException {
		if (selectedNode != null) {
			DocCategory c = (DocCategory) selectedNode.getData();
			if (c.getPageId() == ROOT_PAGE) {
				ViewUtils.showInfoMessage(Constant.MC_CANNOT_EDIT_ROOT);
			} else {
				if (h.handle()) {
					// trigger init to refresh the data.
					buildTree();
					ViewUtils.showSuccessMessage();
				}
			}
		} else {
			ViewUtils.showFailMessage(ErrorCode.MC_NOTHING_SELECTED);
		}
	}

	public void preShowDocs() {
		if (selectedNode != null) {
			DocCategory original = (DocCategory) selectedNode.getData();
			category = new DocCategory(original);
			refreshDocuments();
		} else {
			ViewUtils.showFailMessage(ErrorCode.MC_NOTHING_SELECTED);
		}
	}

	public void preAddDoc() {
		if (selectedNode != null) {
			DocCategory original = (DocCategory) selectedNode.getData();
			category = new DocCategory(original);
			documentEntry = new Document();
			documentEntry.setCategory(category);
		} else {
			ViewUtils.showFailMessage(ErrorCode.MC_NOTHING_SELECTED);
		}
	}

	public void preUpdateDoc() {
		// category is already selected.
		documentEntry = documentService.queryDocument(updateDocId);
	}

	public void doUpdateDoc() throws ApplicationException {
		preManage(new ManageHandler() {
			@Override
			public boolean handle() {
				DocCategory c = (DocCategory) selectedNode.getData();
				if (c.getCategoryId() == PAGE_ITEM) {
					ViewUtils.showInfoMessage(Constant.MC_CANNOT_EDIT_ROOT);
				} else {
					documentEntry.setCategory(new DocCategory(c));
					if (documentEntry.getDocId() > 0) {
						documentService.updateDocument(documentEntry);
					} else {
						documentService.addNewDoc(documentEntry);
					}
					refreshDocuments();
					return true;
				}
				return false;
			}
		});
	}

	public void doDeleteDoc() throws ApplicationException {
		documentService.deleteDocument(updateDocId);
		refreshDocuments();
	}

	private static interface ManageHandler {
		boolean handle() throws ApplicationException;
	}

	public void setCategory(DocCategory category) {
		this.category = category;
	}

	public DocCategory getCategory() {
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

	public Document getDocumentEntry() {
		return documentEntry;
	}

	public void setDocumentEntry(Document documentEntry) {
		this.documentEntry = documentEntry;
	}

	public List<Document> getDocuments() {
		return documents;
	}

	public void setUpdateDocId(int updateDocId) {
		this.updateDocId = updateDocId;
	}
}