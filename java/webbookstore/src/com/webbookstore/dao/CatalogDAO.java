package com.webbookstore.dao;

import java.util.List;

import com.webbookstore.bean.Catalog;

public interface CatalogDAO {
	
	/**
	 * 查询所有图书分类
	 */
	public List<Catalog> getAllCatalogs();

}
