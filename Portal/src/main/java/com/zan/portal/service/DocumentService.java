package com.zan.portal.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.zan.portal.model.Document;
import com.zan.portal.persistent.dao.DocInsertDAO;
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

	public void addNewDoc(Document doc) {
		docInsertDao.insert(doc);
	}

	public List<Document> getDocuments(int categoryId) {
		return queryDao.execute(categoryId);
	}

	public Document queryDocument(int docId) {
		return docQueryDao.findObject(docId);
	}
}