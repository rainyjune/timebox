package jaunty.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component(value = "fulltextLucene")
public class FulltextLucene implements FulltextSearch {

	private IndexWriter indexWriter;
	private IndexReader indexReader;
	private FSDirectory dir;
	private DataSource dataSource;

	private static final Logger logger = Logger.getLogger(FulltextLucene.class);

	public DataSource getDataSource() {
		return dataSource;
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public FulltextLucene() {
		try {
			dir = new MMapDirectory(new File("./index"),
					new NativeFSLockFactory());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 这个方法可有可无?
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
	 * 返回命中的贴子
	 * 
	 * @throws ParseException
	 * @throws IOException
	 */
	@Override
	public SearchResult keywordSearch(String searchKey, String type, String fields,
			String terms, String sortBySql, String sortKey, String sortDir,
			String sortDays, String exFidAry, String mApproveFidAry,
			int topicId, String authorAry, String authorName, int[] idAry,
			int start, int perPage) throws ParseException, IOException {
		
		SearchResult sr = new SearchResult();
		
		QueryParser parser = new QueryParser(Version.LUCENE_30, "text",
				new SmartChineseAnalyzer(Version.LUCENE_30));
		Query query = parser.parse(searchKey);
		IndexSearcher searcher = new IndexSearcher(getIndexReader());
		TopDocs docs = searcher.search(query, 10);
		ScoreDoc[] d = docs.scoreDocs;

		logger.info(d.length);
		
		for (int i = 0; i < d.length; i++) {
			Document document = searcher.doc(d[i].doc);
			Field f = document.getField("id");
			logger.info(f);
		}
		return sr;
	}

	/**
	 * 索引整个论坛
	 * 
	 * @throws SQLException
	 * @throws IOException
	 * @throws LockObtainFailedException
	 * @throws CorruptIndexException
	 */
	public void createIndex() throws SQLException, CorruptIndexException,
			LockObtainFailedException, IOException {
		Connection conn = dataSource.getConnection();
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery("select * from phpbb_posts");

		IndexWriter indexWriter = new IndexWriter(dir,
				new SmartChineseAnalyzer(Version.LUCENE_30), true,
				new KeepOnlyLastCommitDeletionPolicy(),
				IndexWriter.MaxFieldLength.UNLIMITED);

		while (rs.next()) {

			logger.info(rs.getInt("post_id"));

			Document d = new Document();

			Field f0 = new Field("id", String.valueOf(rs.getInt("post_id")),
					Field.Store.YES, Field.Index.ANALYZED);
			d.add(f0);

			Field f1 = new Field("subject", rs.getString("post_subject"),
					Field.Store.NO, Field.Index.ANALYZED);
			d.add(f1);

			Field f2 = new Field("text", rs.getString("post_text"),
					Field.Store.NO, Field.Index.ANALYZED);
			d.add(f2);

			Field f3 = new Field("author", rs.getString("post_username"),
					Field.Store.NO, Field.Index.ANALYZED);
			d.add(f3);

			Field f5 = new Field("date", rs.getString("post_time"),
					Field.Store.NO, Field.Index.ANALYZED);
			d.add(f5);

			indexWriter.addDocument(d);
		}

		indexWriter.close();
	}

	/**
	 * 索引单个贴子
	 * */
	public void index(int id, String subject, String text, String author,
			String date) throws CorruptIndexException, IOException {
		Document d = new Document();

		Field f0 = new Field("id", String.valueOf(id), Field.Store.YES,
				Field.Index.ANALYZED);
		d.add(f0);

		Field f1 = new Field("subject", subject, Field.Store.NO,
				Field.Index.ANALYZED);
		d.add(f1);

		Field f2 = new Field("text", text, Field.Store.NO, Field.Index.ANALYZED);
		d.add(f2);

		Field f3 = new Field("author", author, Field.Store.NO,
				Field.Index.ANALYZED);
		d.add(f3);

		Field f5 = new Field("date", date, Field.Store.NO, Field.Index.ANALYZED);
		d.add(f5);

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
				"applicationContext.xml");
		FulltextLucene fl = (FulltextLucene) ac.getBean("fulltextLucene");
		fl.createIndex();
	}

	@Test
	public void testKeywordSearch() throws ParseException, IOException {
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		FulltextLucene fl = (FulltextLucene) ac.getBean("fulltextLucene");
		int[] array = null;
		fl.keywordSearch("你好", "", "", "", "", "", "", "", "", "", 0, "", "",
				array, 0, 0);

	}
}
