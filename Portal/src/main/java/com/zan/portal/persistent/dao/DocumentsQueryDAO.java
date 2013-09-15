package com.zan.portal.persistent.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.zan.portal.model.DocCategory;
import com.zan.portal.model.Document;

@Component
@Scope("prototype")
public class DocumentsQueryDAO {

	private JdbcTemplate jdbc;

	private List<Document> documents;

	@Autowired
	public DocumentsQueryDAO(DataSource dataSource) {
		jdbc = new JdbcTemplate(dataSource);
		documents = new ArrayList<Document>();
	}

	public List<Document> query(DocCategory c) {
		Map<Integer, DocCategory> targetCategory = new HashMap<Integer, DocCategory>();
		targetCategory.put(c.getCategoryId(), c);
		return query(targetCategory);
	}

	public List<Document> query(final Map<Integer, DocCategory> targetCategory) {
		NamedParameterJdbcTemplate t = new NamedParameterJdbcTemplate(jdbc);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("ids", targetCategory.keySet());

		return t.query(
				"select doc_id, doc_title, doc_desc, last_update, category_id from docs where category_id IN ( :ids )",
				parameters, new RowMapper<Document>() {
					@Override
					public Document mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Document d = new Document();
						d.setCategory(targetCategory.get(rs
								.getInt("category_id")));
						d.setDocId(rs.getInt("doc_id"));
						d.setDocTitle(rs.getString("doc_title"));
						d.setDocDesc(rs.getString("doc_desc"));
						d.setLastUpdate(new Date(rs.getTimestamp("last_update")
								.getTime()));
						documents.add(d);
						return d;
					}
				});
	}
}