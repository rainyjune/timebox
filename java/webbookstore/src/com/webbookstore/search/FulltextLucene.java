package com.webbookstore.search;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.KeepOnlyLastCommitDeletionPolicy;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.store.MMapDirectory;
import org.apache.lucene.store.NativeFSLockFactory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.webbookstore.bean.Book;
import com.webbookstore.dao.BookDAO;

public class FulltextLucene implements Search {

	private IndexWriter indexWriter;
	private IndexReader indexReader;
	private FSDirectory dir;

	private BookDAO bookDAO;


	private static final Logger logger = Logger.getLogger(FulltextLucene.class);

	public FulltextLucene() {
		try {
			dir = new MMapDirectory(new File("/opt/apache-tomcat-6.0.18/webapps/webbookstore/index"),
					new NativeFSLockFactory());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Book> keywordSearch(String bookName) {

		List<Book> books = new ArrayList<Book>();

		QueryParser parser = new QueryParser(Version.LUCENE_30, "bookName",
				new SmartChineseAnalyzer(Version.LUCENE_30));
		Query query = null;
		try {
			query = parser.parse(bookName);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		IndexSearcher searcher = new IndexSearcher(getIndexReader());
		TopDocs docs = null;
		try {
			docs = searcher.search(query, 100);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ScoreDoc[] d = docs.scoreDocs;

		logger.info(d.length);

		for (int i = 0; i < d.length; i++) {
			Document document = null;
			try {
				document = searcher.doc(d[i].doc);
			} catch (CorruptIndexException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			Field f = document.getField("id");
			Book b = bookDAO.getBookById(Integer.parseInt(f.stringValue()));
			books.add(b);
		}

		return books;
	}

	/**
	 * 这个方法没用到过..先留着
	 */
	public void init() {
		try {

			indexWriter = new IndexWriter(dir, new SmartChineseAnalyzer(
					Version.LUCENE_30), false,
					new KeepOnlyLastCommitDeletionPolicy(),
					IndexWriter.MaxFieldLength.UNLIMITED);
		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (LockObtainFailedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			indexReader = IndexReader.open(dir);
		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 建立索引
	 * 
	 * @throws SQLException
	 * @throws IOException
	 * @throws LockObtainFailedException
	 * @throws CorruptIndexException
	 */
	public void createIndex() throws SQLException, CorruptIndexException,
			LockObtainFailedException, IOException {

		IndexWriter indexWriter = new IndexWriter(dir,
				new SmartChineseAnalyzer(Version.LUCENE_30), true,
				new KeepOnlyLastCommitDeletionPolicy(),
				IndexWriter.MaxFieldLength.UNLIMITED);

		List<Book> books = bookDAO.getAllBooks();

		for (Book book : books) {

			logger.info(book.getBookId());

			Document d = new Document();

			Field f0 = new Field("id", String.valueOf(book.getBookId()),
					Field.Store.YES, Field.Index.ANALYZED);
			d.add(f0);

			Field f1 = new Field("bookName", book.getBookName(),
					Field.Store.NO, Field.Index.ANALYZED);
			d.add(f1);

			Field f2 = new Field("catelogId",
					String.valueOf(book.getCatalog()), Field.Store.NO,
					Field.Index.ANALYZED);
			d.add(f2);

			indexWriter.addDocument(d);
		}

		indexWriter.close();
	}

	/**
	 * 索引单本书
	 * */
	public void index(Book book) throws CorruptIndexException, IOException {
		logger.info(book.getBookId());

		Document d = new Document();

		Field f0 = new Field("id", String.valueOf(book.getBookId()),
				Field.Store.YES, Field.Index.ANALYZED);
		d.add(f0);

		Field f1 = new Field("bookName", book.getBookName(), Field.Store.NO,
				Field.Index.ANALYZED);
		d.add(f1);

		Field f2 = new Field("catelogId", String.valueOf(book.getCatalog()),
				Field.Store.NO, Field.Index.ANALYZED);
		d.add(f2);

		indexWriter.addDocument(d);
	}

	public synchronized IndexWriter getIndexWriter() {
		if (indexWriter == null) {
			try {

				indexWriter = new IndexWriter(dir, new SmartChineseAnalyzer(
						Version.LUCENE_30), false,
						new KeepOnlyLastCommitDeletionPolicy(),
						IndexWriter.MaxFieldLength.UNLIMITED);
			} catch (CorruptIndexException e) {
				e.printStackTrace();
			} catch (LockObtainFailedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return indexWriter;
	}

	public synchronized IndexReader getIndexReader() {

		if (indexReader == null) {
			try {
				indexReader = IndexReader.open(dir);
			} catch (CorruptIndexException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return indexReader;
	}

	// @Test
	public void testCreateIndex() throws CorruptIndexException,
			LockObtainFailedException, SQLException, IOException {
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				new String[] { "com/webbookstore/action/beans.xml",
						"applicationContext.xml" });
		FulltextLucene fl = (FulltextLucene) ac.getBean("fulltextLucene");
		fl.createIndex();
	}

	@Test
	public void testKeywordSearch() throws ParseException, IOException {
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				new String[] { "com/webbookstore/action/beans.xml",
						"applicationContext.xml" });
		FulltextLucene fl = (FulltextLucene) ac.getBean("fulltextLucene");
		System.out.println(fl.keywordSearch("攻略"));

	}

	public void setBookDAO(BookDAO bookDAO) {
		this.bookDAO = bookDAO;
	}

	public BookDAO getBookDAO() {
		return bookDAO;
	}
}
