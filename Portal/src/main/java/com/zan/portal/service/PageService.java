package com.zan.portal.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.zan.portal.model.Page;
import com.zan.portal.persistent.dao.PagesQueryDAO;

@Component
@Scope("prototype")
public class PageService {

	@Inject
	private PagesQueryDAO pagesQueryDAO;

	public List<Page> getPages() {
		return pagesQueryDAO.execute();
	}
}
