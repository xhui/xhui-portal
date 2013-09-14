package com.zan.portal.persistent.dao;

import java.sql.Timestamp;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.zan.portal.model.Document;

@Component
@Scope("prototype")
public class DocumentMaintainDAO {

	private JdbcTemplate jdbc;

	@Autowired
	public DocumentMaintainDAO(DataSource dataSource) {
		jdbc = new JdbcTemplate(dataSource);
	}

	public void delete(int docId) {
		jdbc.update("delete from docs where doc_id= ?", docId);
	}

	public void update(Document doc) {
		jdbc.update(
				"update docs set doc_title = ?, category_id = ?, doc_desc= ?, content=?, last_update=? where doc_id= ?",
				doc.getDocTitle(), doc.getCategory().getCategoryId(),
				doc.getDocDesc(), doc.getDocContent(),
				new Timestamp(System.currentTimeMillis()), doc.getDocId());
	}
}