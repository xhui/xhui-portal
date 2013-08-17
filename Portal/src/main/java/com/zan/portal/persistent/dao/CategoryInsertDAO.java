package com.zan.portal.persistent.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import com.zan.portal.model.Category;

@Component
@Scope("prototype")
public class CategoryInsertDAO implements Serializable{

	private SimpleJdbcInsert insertActor;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.insertActor = new SimpleJdbcInsert(dataSource)
				.withTableName("doc_categories");
	}

	public void insert(Category newly, Category parent) {
		Map<String, Object> parameters = new HashMap<String, Object>(3);
		parameters.put("category_name", newly.getName());
		parameters.put("parent_category_id", parent.getCategoryId());
		parameters.put("page_id", parent.getPageId());
		insertActor.execute(parameters);
	}
}