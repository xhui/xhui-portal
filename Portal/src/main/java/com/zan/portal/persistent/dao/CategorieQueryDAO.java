package com.zan.portal.persistent.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.zan.portal.model.DocCategory;

/**
 * Query all categories.
 * 
 * @author zanyoung
 */
@Component
@Scope("prototype")
public class CategorieQueryDAO {

	private static final String SQL = "select category_id, category_name, parent_category_id from doc_categories WHERE page_id = ? order by page_id ASC, parent_category_id ASC, category_name ASC";

	private JdbcTemplate jdbc;

	private Map<Integer, DocCategory> categories;

	@Autowired
	public CategorieQueryDAO(DataSource dataSource) {
		jdbc = new JdbcTemplate(dataSource);
		categories = new HashMap<>();
	}

	public Map<Integer, DocCategory> query(final int pageId) {
		jdbc.query(SQL, new Object[] { pageId }, new RowMapper<DocCategory>() {
			@Override
			public DocCategory mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				int categoryId = rs.getInt("category_id");
				DocCategory c = new DocCategory();
				c.setCategoryId(categoryId);
				c.setName(rs.getString("category_name"));
				c.setPageId(pageId);
				// Temporary
				DocCategory parent = new DocCategory();
				parent.setCategoryId(rs.getInt("parent_category_id"));
				c.setParentCategory(parent);
				categories.put(categoryId, c);
				return c;
			}
		});

		// correct parent category relationship
		for (DocCategory c : categories.values()) {
			DocCategory parent = c.getParentCategory();
			c.setParentCategory(parent.getCategoryId() > 0 ? categories
					.get(parent.getCategoryId()) : null);

			if (c.getParentCategory() != null) {
				c.getParentCategory().addSubCategories(c);
			}
		}

		return categories;
	}
}