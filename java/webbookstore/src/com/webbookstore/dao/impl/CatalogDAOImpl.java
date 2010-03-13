package com.webbookstore.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.webbookstore.bean.Catalog;
import com.webbookstore.dao.CatalogDAO;
import com.webbookstore.util.BaseDAO;

public class CatalogDAOImpl implements CatalogDAO {
	
	private BaseDAO baseDAO;
	
	/**
	 * 查询所有图书分类
	 */
	@SuppressWarnings("unchecked")
	public List<Catalog> getAllCatalogs() {
		
		Session session = this.baseDAO.getSession();
		Query query=session.createQuery("from com.webbookstore.bean.Catalog ");
		List<Catalog> list = query.list();
		session.close();
		return list;
	}

	public BaseDAO getBaseDAO() {
		return baseDAO;
	}

	public void setBaseDAO(BaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}

}
