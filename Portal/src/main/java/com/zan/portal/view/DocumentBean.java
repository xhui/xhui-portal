package com.zan.portal.view;

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
public class DocumentBean {

	@Inject
	private DocumentService docService;

	private Document queriedPage;

	@PostConstruct
	public void init() {

		Map<String, String> parameters = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();

		int pid = ViewUtils.transferInteger(parameters.get("pid"));
		if (pid > -1) {
			queriedPage = docService.queryDocument(pid);
			return;
		}
	}

	public Document getQueriedPage() {
		return queriedPage;
	}
}
