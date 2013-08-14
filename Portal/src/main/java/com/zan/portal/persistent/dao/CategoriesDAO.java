package com.zan.portal.persistent.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.stereotype.Component;

import com.zan.portal.model.Category;

@Component
@Scope("prototype")
public class CategoriesDAO extends MappingSqlQuery<Category> {

	@Autowired
	public CategoriesDAO(DataSource dataSource) {
		super(
				dataSource,
				"select category_id, category_name, parent_category_id from doc_categories where page_id = ?");
		super.declareParameter(new SqlParameter("page_id", Types.INTEGER));
	}

	@Override
	protected Category mapRow(ResultSet rs, int rowNum) throws SQLException {
		Category c = new Category();
		c.setCategoryId(rs.getInt("category_id"));
		c.setName(rs.getString("category_name"));
		c.setParentCategoryId(rs.getInt("parent_category_id"));
		return c;
	}
}