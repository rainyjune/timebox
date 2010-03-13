package com.webbookstore.service;

import java.util.List;

import com.webbookstore.bean.Catalog;

public interface CatalogService {
	
	/**
	 * 查询所有图书分类
	 */
	public List<Catalog> getAllCatalogs();
}
