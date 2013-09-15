package com.zan.portal.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.zan.portal.model.Document;
import com.zan.portal.service.DocumentService;

@Component
@Scope("request")
public class DocumentListBean {

	@Inject
	private DocumentService docService;

	private List<Document> documents;

	@PostConstruct
	public void init() {
		documents = new ArrayList<Document>();
		Map<String, String> parameters = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		int cid = ViewUtils.transferInteger(parameters.get("cid"));
		int pageId = ViewUtils.transferInteger(parameters.get("pg"));
		if (cid > -1) {
			documents.addAll(docService.getAllChildDocuments(cid, pageId));
			return;
		}
	}

	public List<Document> getDocuments() {
		if (null == documents) {
			// avoid null pointer.
			documents = new ArrayList<Document>();
		}
		return documents;
	}
}