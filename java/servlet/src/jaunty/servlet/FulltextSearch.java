package jaunty.servlet;

import java.io.IOException;

import org.apache.lucene.queryParser.ParseException;

public interface FulltextSearch {

	SearchResult keywordSearch(String searchKey, String type, String fields, String terms,
			String sort_by_sql, String sort_key, String sort_dir,
			String sort_days, String ex_fid_ary, String m_approve_fid_ary,
			int topic_id, String author_ary, String author_name, int[] id_ary,
			int start, int per_page) throws ParseException, IOException;
}
