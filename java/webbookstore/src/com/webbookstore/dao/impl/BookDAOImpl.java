package com.webbookstore.dao.impl;

import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;

import com.webbookstore.bean.Book;
import com.webbookstore.bean.Catalog;
import com.webbookstore.dao.BookDAO;
import com.webbookstore.util.BaseDAO;
import com.webbookstore.util.PagingBean;

public class BookDAOImpl implements BookDAO {
	
	private BaseDAO baseDAO;
	
	public Set<Book> getBooksByCatalogId(Integer catalogId) {
		
		Session session = this.baseDAO.getSession();
		Catalog catalog=(Catalog)session.get(Catalog.class, catalogId);
		Set<Book> books = catalog.getBooks();
		
		// 解决Hibernate的懒加载问题
		for(Book b:books){
			b.getCatalog().getCatalogName();
		}
		session.close();
		
		return books;
	}
	
	/**
	 * 根据图书分类ID获取该分类下的所有书籍信息：带分页
	 */
	@SuppressWarnings("unchecked")
	public List<Book> getBooksByCatalogId(Integer catalogId,PagingBean pb){
		
		Session session = this.baseDAO.getSession();
		
		// 构建查询语句
		StringBuffer hql = new StringBuffer("from com.webbookstore.bean.Book book ");
		hql.append("where book.catalog.catalogId = :catalogId ");
		hql.append("order by book.bookId ASC ");
		
		// 创建查询
		Query query = session.createQuery(hql.toString());
		query.setInteger("catalogId", catalogId);
		// 加上分页信息
		query.setFirstResult( (pb.getCurrentPage()-1) * pb.getPageSize() );
		query.setMaxResults(pb.getPageSize());
		
		// 获取查询结果
		List<Book> books = query.list();
		
		// 解决懒加载问题
		for(Book book:books){
			book.getCatalog().getCatalogName();
		}
		session.close();
		
		return books;
	}
	
	/**
	 * 根据图书分类ID获取该分类下的书籍数量
	 */
	@SuppressWarnings("unchecked")
	public Long getNumsOfBooksByCatalogId(Integer catalogId){
		
		Session session = this.baseDAO.getSession();
		
		// 创建查询语句
		StringBuffer hql = new StringBuffer("select count(*) ");
		hql.append("from com.webbookstore.bean.Book book ");
		hql.append("where book.catalog.catalogId = :catalogId ");
		
		// 创建查询
		Query query = session.createQuery(hql.toString());
		query.setInteger("catalogId", catalogId);
		
		// 获取查询结果
		List nums = query.list();
		
		Long num = 0L;
		if(nums != null && nums.size()>0){
			num = (Long)nums.get(0);
		}
		session.close();
		
		return num;
	}
	
	/**
	 * 根据ID获取书本
	 */
	public Book getBookById(Integer bookId) {
		// 获取Session
		Session session = this.baseDAO.getSession();
		// 获取书本
		Book book=(Book)session.get(Book.class, bookId);
		return book;
	}
	
	/**
	 * 对书本进行模糊查询
	 */
	@SuppressWarnings("unchecked")
	public List<Book> getBooksByName(String hql) {
		Session session = this.baseDAO.getSession();
		
		Query query = session.createQuery(hql);
		
		List<Book> list = query.list();
		
		// 解决Hibernate懒加载问题
		for(Book book:list){
			book.getCatalog().getCatalogName();
		}
		session.close();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<Book> getAllBooks() {
		Session session = this.baseDAO.getSession();
		
		String hql = "from com.webbookstore.bean.Book as book";
		
		Query query = session.createQuery(hql);
		
		List<Book> list = query.list();
		
		// 解决Hibernate懒加载问题
		for(Book book:list){
			book.getCatalog().getCatalogName();
		}
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
