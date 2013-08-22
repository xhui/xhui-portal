package com.zan.portal.persistent.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.zan.portal.model.Category;

@Component
@Scope("prototype")
public class CategoryDeleteDAO {

	private JdbcTemplate jdbc;

	@Autowired
	public CategoryDeleteDAO(DataSource dataSource) {
		jdbc = new JdbcTemplate(dataSource);
	}

	public void delete(Category c) {
		jdbc.update("delete from doc_categories where category_id= ?",
				c.getCategoryId());
	}
}