package com.webbookstore.action;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.webbookstore.bean.Book;
import com.webbookstore.bean.Catalog;
import com.webbookstore.service.BookService;
import com.webbookstore.service.CatalogService;
import com.webbookstore.util.PagingBean;

@SuppressWarnings("serial")
public class BookAction extends ActionSupport {
	
	private Integer catalogId;
	private String bookname;
	private CatalogService catalogService;
	private BookService bookService;
	
	// 当前页码
	private Integer currentPag = 1;
	
	@Override
	public String execute() throws Exception {
		
		return SUCCESS;
	}
	
	/**
	 * 显示图书分类列表
	 */
	@SuppressWarnings("unchecked")
	public String browseCatalog()throws Exception{
		
		List<Catalog> catalogs = this.catalogService.getAllCatalogs();
		
		// 获取Request对象
		Map request = (Map)ActionContext.getContext().get("request");
		
		// 将图书分类信息放到Request对象中
		request.put("catalogs", catalogs);
		
		return SUCCESS;
	}
	
	/**
	 * 显示图书分类的所有书本信息
	 */
	@SuppressWarnings("unchecked")
	public String browseBooks()throws Exception{
		
		//Set<Book> books = this.bookService.getBooksByCatalogId(this.catalogId);
		// 获取总页数
		Long allSize = this.bookService.getNumsOfBooksByCatalogId(this.catalogId);
		
		PagingBean pb = new PagingBean(this.currentPag,Integer.parseInt(allSize.toString()));
		
		List<Book> books = this.bookService.getBooksByCatalogId(this.catalogId,pb);
		// 获取Request对象
		Map request = (Map)ActionContext.getContext().get("request");
		
		Map session = (Map)ActionContext.getContext().getSession();
		// 将图书类别ID放入到Session中，供放入购物车后重新进入商品列表用
		session.put("catalogId", catalogId);
		// 将查询结果和分页信息放到Request对象中
		request.put("books", books);
		request.put("pagingBean", pb);
		
		return SUCCESS;
	}
	
	/**
	 * 显示图书搜索界面
	 */
	public String viewBooksQuery()throws Exception{
		
		return SUCCESS;
	}
	/**
	 * 对书本进行模糊查询
	 */
	@SuppressWarnings("unchecked")
	public String getBooks() throws Exception{
		
		// 获取Request
		Map request = (Map) ActionContext.getContext().get("request");
		
		List<Book> books = this.bookService.getBooks(this.bookname);
		System.out.println(books.size());
		System.out.println(this.bookname);
		request.put("books", books);
		
		return SUCCESS;
	}
	
	public CatalogService getCatalogService() {
		return catalogService;
	}

	public void setCatalogService(CatalogService catalogService) {
		this.catalogService = catalogService;
	}

	public Integer getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(Integer catalogId) {
		this.catalogId = catalogId;
	}

	public BookService getBookService() {
		return bookService;
	}

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	public Integer getCurrentPag() {
		return currentPag;
	}

	public void setCurrentPag(Integer currentPag) {
		this.currentPag = currentPag;
	}

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
}
