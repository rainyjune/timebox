package com.webbookstore.search;

import java.util.List;

import com.webbookstore.bean.Book;

public interface Search {
	List<Book> keywordSearch(String bookName);
}
