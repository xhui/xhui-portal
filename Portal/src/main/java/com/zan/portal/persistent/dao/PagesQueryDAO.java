package com.zan.portal.persistent.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.stereotype.Component;

import com.zan.portal.model.Page;

@Component
@Scope("prototype")
public class PagesQueryDAO extends MappingSqlQuery<Page> {

	@Autowired
	public PagesQueryDAO(DataSource dataSource) {
		super(dataSource, "select id, name from pages");
		compile();
	}

	@Override
	protected Page mapRow(ResultSet rs, int rowNum) throws SQLException {
		Page p = new Page();
		p.setPageId(rs.getInt("id"));
		p.setPageName(rs.getString("name"));
		return p;
	}
}