package com.webbookstore.service.impl;

import java.util.List;
import java.util.Set;

import com.webbookstore.bean.Book;
import com.webbookstore.dao.BookDAO;
import com.webbookstore.search.Search;
import com.webbookstore.service.BookService;
import com.webbookstore.util.PagingBean;

public class BookServiceImpl implements BookService {
	
	private BookDAO bookDAO;
	private Search search;
	/**
	 * 根据图书分类ID获取该分类下的所有书籍信息
	 */
	public Set<Book> getBooksByCatalogId(Integer catalogId) {
		return this.bookDAO.getBooksByCatalogId(catalogId);
	}
	
	/**
	 * 根据图书分类ID获取该分类下的所有书籍信息：带分页
	 */
	public List<Book> getBooksByCatalogId(Integer catalogId,PagingBean pb){
		
		return this.bookDAO.getBooksByCatalogId(catalogId,pb);
	}
	
	/**
	 * 根据图书分类ID获取该分类下的书籍数量
	 */
	public Long getNumsOfBooksByCatalogId(Integer catalogId){
		
		return this.bookDAO.getNumsOfBooksByCatalogId(catalogId);
	}
	
	/**
	 * 根据ID获取书本
	 */
	public Book getBookById(Integer bookId) {
		return this.bookDAO.getBookById(bookId);
	}
	
	/**
	 * 对书本进行模糊查询
	 */
	public List<Book> getBooks(String bookName) {

		return search.keywordSearch(bookName);
	}
	
	/**
	 * 得到所有的图书
	 */
	public List<Book> getAllBooks() {
		return bookDAO.getAllBooks();
		
	}
	
	public BookDAO getBookDAO() {
		return bookDAO;
	}

	public void setBookDAO(BookDAO bookDAO) {
		this.bookDAO = bookDAO;
	}

	public void setSearch(Search search) {
		this.search = search;
	}

	public Search getSearch() {
		return search;
	}

	
}
