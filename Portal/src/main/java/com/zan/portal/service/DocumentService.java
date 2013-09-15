package com.zan.portal.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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

	public List<Document> getAllChildDocuments(int categoryId, int pageId) {
		Map<Integer, DocCategory> target = new HashMap<Integer, DocCategory>();
		findTotalSubCategories(target,
				categoryService.getAvailableCategories(pageId));
		for (Entry<Integer, DocCategory> category : target.entrySet()) {
			if (categoryId == category.getKey()) {
				List<Integer> ids = new ArrayList<Integer>();
				ids.add(categoryId);

				Map<Integer, DocCategory> targetCategory = new HashMap<Integer, DocCategory>();
				targetCategory.put(categoryId, category.getValue());
				findTotalSubCategories(targetCategory, category.getValue()
						.getSubCategories());
				return queryDao.query(targetCategory);
			}
		}
		return new ArrayList<Document>();
	}

	private void findTotalSubCategories(Map<Integer, DocCategory> target,
			List<DocCategory> categories) {
		if (null != categories) {
			for (DocCategory c : categories) {
				target.put(c.getCategoryId(), c);
				findTotalSubCategories(target, c.getSubCategories());
			}
		}
	}

	public Document queryDocument(int docId) {
		return docQueryDao.findObject(docId);
	}

	public void deleteDocument(int docId) {
		docManageDao.delete(docId);
	}

	public void updateDocument(Document doc) {
		docManageDao.update(doc);
	}
}