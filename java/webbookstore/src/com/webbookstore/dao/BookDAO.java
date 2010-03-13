package com.webbookstore.dao;

import java.util.List;
import java.util.Set;

import com.webbookstore.bean.Book;
import com.webbookstore.util.PagingBean;

public interface BookDAO {
	
	/**
	 * 根据图书分类ID获取该分类下的所有书籍信息
	 */
	public Set<Book> getBooksByCatalogId(Integer catalogId);
	
	/**
	 * 根据图书分类ID获取该分类下的所有书籍信息：带分页
	 */
	public List<Book> getBooksByCatalogId(Integer catalogId,PagingBean pb);
	
	/**
	 * 根据图书分类ID获取该分类下的书籍数量
	 */
	public Long getNumsOfBooksByCatalogId(Integer catalogId);
	
	/**
	 * 根据ID获取书本
	 */
	public Book getBookById(Integer bookId);
	
	/**
	 * 对书本进行模糊查询
	 */
	public List<Book> getBooksByName(String hql);
	
	/**
	 * 得到所有的书
	 */
	public List<Book> getAllBooks();
	
}
