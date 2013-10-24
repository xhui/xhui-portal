package com.zan.portal.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.zan.portal.model.DocCategory;
import com.zan.portal.model.Document;
import com.zan.portal.persistent.dao.DocInsertDAO;
import com.zan.portal.persistent.dao.DocumentMaintainDAO;
import com.zan.portal.persistent.dao.DocumentQueryDAO;
import com.zan.portal.persistent.dao.DocumentsQueryDAO;

@Component
@Scope("prototype")
public class DocumentService {

	@Inject
	private DocInsertDAO docInsertDao;

	@Inject
	private DocumentsQueryDAO queryDao;

	@Inject
	private DocumentQueryDAO docQueryDao;

	@Inject
	private DocumentMaintainDAO docManageDao;

	@Inject
	private CategoryService categoryService;

	public void addNewDoc(Document doc) {
		docInsertDao.insert(doc);
	}

	public List<Document> getDirectChildDocuments(DocCategory category) {
		return queryDao.query(category);
	}

	public List<Document> getAllChildDocuments(int categoryId) {
		Map<Integer, DocCategory> target = new HashMap<Integer, DocCategory>();
		List<DocCategory> childCategories = categoryService
				.getChildCategories(categoryId);
		for (DocCategory category : childCategories) {
			target.put(category.getCategoryId(), category);
		}
		return queryDao.query(target);
	}

	public Document queryDocument(int docId) {
		Document doc = docQueryDao.findObject(docId);
		doc.setCategory(categoryService.getCategory(doc.getCategory()
				.getCategoryId()));
		return doc;
	}

	public void deleteDocument(int docId) {
		docManageDao.delete(docId);
	}

	public void updateDocument(Document doc) {
		docManageDao.update(doc);
	}
}