package com.zan.portal.persistent.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.stereotype.Component;

import com.zan.portal.model.Document;

@Component
@Scope("prototype")
public class DocumentQueryDAO extends MappingSqlQuery<Document> {

	@Autowired
	public DocumentQueryDAO(DataSource dataSource) {
		super(
				dataSource,
				"select doc_id, doc_title, doc_desc, last_update, content from docs where doc_id = ?");
		super.declareParameter(new SqlParameter(Types.INTEGER));
		compile();
	}

	@Override
	protected Document mapRow(ResultSet rs, int rowNum) throws SQLException {
		Document d = new Document();
		d.setDocId(rs.getInt("doc_id"));
		d.setDocTitle(rs.getString("doc_title"));
		d.setDocDesc(rs.getString("doc_desc"));
		d.setDocContent(rs.getString("content"));
		d.setLastUpdate(new Date(rs.getTimestamp("last_update").getTime()));
		return d;
	}
}