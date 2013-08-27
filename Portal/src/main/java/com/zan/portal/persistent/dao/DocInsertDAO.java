package com.zan.portal.persistent.dao;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import com.zan.portal.model.Document;

@Component
@Scope("prototype")
public class DocInsertDAO {
	private SimpleJdbcInsert insertActor;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.insertActor = new SimpleJdbcInsert(dataSource)
				.withTableName("docs");
	}

	public void insert(Document doc) {
		Map<String, Object> parameters = new HashMap<String, Object>(5);
		parameters.put("doc_title", doc.getDocTitle());
		parameters.put("category_id", doc.getCategory().getCategoryId());
		parameters.put("doc_desc", doc.getDocDesc());
		parameters.put("content", doc.getDocContent());
		parameters
				.put("last_update", new Timestamp(System.currentTimeMillis()));
		insertActor.execute(parameters);
	}
}
