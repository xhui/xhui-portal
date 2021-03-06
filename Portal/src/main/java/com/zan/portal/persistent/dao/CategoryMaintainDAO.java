package com.zan.portal.persistent.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.zan.portal.model.DocCategory;

@Component
@Scope("prototype")
public class CategoryMaintainDAO {

	private JdbcTemplate jdbc;

	@Autowired
	public CategoryMaintainDAO(DataSource dataSource) {
		jdbc = new JdbcTemplate(dataSource);
	}

	public void delete(DocCategory c) {
		jdbc.update("delete from doc_categories where category_id= ?",
				c.getCategoryId());
	}

	public void update(DocCategory c, DocCategory parent) {
		jdbc.update(
				"update doc_categories set category_name = ?, page_id = ?, parent_category_id= ? where category_id= ?",
				c.getName(), c.getPageId(),
				null == parent ? 0 : parent.getCategoryId(), c.getCategoryId());
	}
}