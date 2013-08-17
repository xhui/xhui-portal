package com.zan.portal.model;

import java.io.Serializable;

public class Page implements Serializable {
	private static final long serialVersionUID = -7237608927613497315L;

	private int pageId;
	private String pageName;

	public int getPageId() {
		return pageId;
	}

	public void setPageId(int pageId) {
		this.pageId = pageId;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
}
