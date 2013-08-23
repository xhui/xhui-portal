package com.zan.portal.persistent.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import com.zan.portal.model.Category;
import com.zan.portal.utils.ErrorCode;
import com.zan.portal.utils.error.ApplicationException;

@Component
@Scope("prototype")
public class CategoryInsertDAO {
	private static final Logger log = Logger.getLogger(CategoryInsertDAO.class
			.toString());
	private SimpleJdbcInsert insertActor;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.insertActor = new SimpleJdbcInsert(dataSource)
				.withTableName("doc_categories");
	}

	public void insert(Category newly, Category parent)
			throws ApplicationException {
		Map<String, Object> parameters = new HashMap<String, Object>(3);
		parameters.put("category_name", newly.getName());
		if (null != parent) {
			parameters.put("parent_category_id", parent.getCategoryId());
		}
		parameters.put("page_id", newly.getPageId());
		try {
			insertActor.execute(parameters);
		} catch (DuplicateKeyException ex) {
			if (log.isLoggable(Level.FINE)) {
				log.fine(ex.getLocalizedMessage());
			}
			// duplicated key
			throw new ApplicationException(ErrorCode.MC_DUPLICATED_CATEGORY);
		}
	}
}