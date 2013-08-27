package com.zan.portal.model;

import java.io.Serializable;

public class Document implements Serializable {
	private static final long serialVersionUID = -8901068876335800870L;

	private String docTitle;
	private String docDesc;
	private String docContent;

	private DocCategory category;

	public String getDocTitle() {
		return docTitle;
	}

	public void setDocTitle(String docTitle) {
		this.docTitle = docTitle;
	}

	public String getDocDesc() {
		return docDesc;
	}

	public void setDocDesc(String docDesc) {
		this.docDesc = docDesc;
	}

	public String getDocContent() {
		return docContent;
	}

	public void setDocContent(String docContent) {
		this.docContent = docContent;
	}

	public DocCategory getCategory() {
		return category;
	}

	public void setCategory(DocCategory category) {
		this.category = category;
	}
}
