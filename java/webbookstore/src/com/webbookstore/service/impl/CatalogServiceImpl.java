package com.webbookstore.service.impl;

import java.util.List;

import com.webbookstore.bean.Catalog;
import com.webbookstore.dao.CatalogDAO;
import com.webbookstore.service.CatalogService;

public class CatalogServiceImpl implements CatalogService {
	
	private CatalogDAO catalogDAO;
	
	/**
	 * 查询所有图书分类
	 */
	public List<Catalog> getAllCatalogs() {
		
		return this.catalogDAO.getAllCatalogs();
	}
	public CatalogDAO getCatalogDAO() {
		return catalogDAO;
	}
	public void setCatalogDAO(CatalogDAO catalogDAO) {
		this.catalogDAO = catalogDAO;
	}

}
