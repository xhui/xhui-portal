package com.zan.portal.persistent.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.zan.portal.model.Category;

@Component
@Scope("prototype")
public class CategorieQueryDAO {

	private JdbcTemplate jdbc;
	private static final String SQL = "select category_id, category_name, parent_category_id from doc_categories where page_id = ? order by parent_category_id ASC";

	@Autowired
	public CategorieQueryDAO(DataSource dataSource) {
		jdbc = new JdbcTemplate(dataSource);
	}

	public List<Category> query(final int pageId) {
		return jdbc.query(SQL, new Object[] { pageId },
				new ResultSetExtractor<List<Category>>() {
					@Override
					public List<Category> extractData(ResultSet rs)
							throws SQLException, DataAccessException {
						List<Category> categories = new ArrayList<Category>();
						if (rs != null) {
							Map<Integer, Category> categoryCache = new HashMap<Integer, Category>();
							while (rs.next()) {
								int categoryId = rs.getInt("category_id");
								// get category instance from cache.
								Category c = categoryCache.get(categoryId);
								if (c == null) {
									c = new Category();
									c.setCategoryId(categoryId);
									c.setName(rs.getString("category_name"));
									c.setPageId(pageId);
									categoryCache.put(categoryId, c);
								}
								int parentId = rs.getInt("parent_category_id");
								if (parentId == 0) {
									// this is root node.
									categories.add(c);
								} else {
									// this is child node.
									Category parent = categoryCache
											.get(parentId);
									// should not be null, but if not find,
									// ignore that.
									if (parent != null) {
										parent.addSubCategories(c);
									}
								}
							}
						}
						return categories;
					}
				});
	}
}