package com.zan.portal.service;

import javax.inject.Inject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.zan.portal.model.Document;
import com.zan.portal.persistent.dao.DocInsertDAO;

@Component
@Scope("prototype")
public class DocumentService {

	@Inject
	private DocInsertDAO docInsertDao;

	public void addNewDoc(Document doc) {
		docInsertDao.insert(doc);
	}
}