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

	private static final String SQL = "select category_id, category_name, parent_category_id from doc_categories where page_id = ? order by parent_category_id ASC, category_name ASC";

	private JdbcTemplate jdbc;

	@Autowired
	public CategorieQueryDAO(DataSource dataSource) {
		jdbc = new JdbcTemplate(dataSource);
	}

	public List<Category> query(int pageId) {
		return jdbc.query(SQL, new Object[] { pageId },
				new CategoryResultSetExtractor(pageId));
	}

	private static class CategoryResultSetExtractor implements
			ResultSetExtractor<List<Category>> {
		private ResultSet rs;
		private int pageId;
		private Map<Integer, Category> categoryCache;
		private List<Category> categories;

		private CategoryResultSetExtractor(int pageId) {
			this.categoryCache = new HashMap<Integer, Category>();
			this.categories = new ArrayList<Category>();
			this.pageId = pageId;
		}

		@Override
		public List<Category> extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			this.rs = rs;
			if (rs != null) {
				while (rs.next()) {
					buildCategoryTree(buildCategory());
				}
			}
			return categories;
		}

		private Category buildCategory() throws SQLException {
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
			return c;
		}

		private void buildCategoryTree(Category c) throws SQLException {
			int parentId = rs.getInt("parent_category_id");
			if (parentId == 0) {
				// this is root node.
				categories.add(c);
			} else {
				// this is child node.
				Category parent = categoryCache.get(parentId);
				// should not be null, but if not find, ignore that.
				if (parent != null) {
					parent.addSubCategories(c);
				}
			}
		}
	}
}