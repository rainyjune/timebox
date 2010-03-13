package jaunty.beyes;

import java.util.List;

import org.apache.lucene.analysis.cn.smart.hhmm.HHMMSegmenter;
import org.apache.lucene.analysis.cn.smart.hhmm.SegToken;
import org.junit.Test;

/**
 * 中文分词器
 */
public class ChineseSpliter {
	/**
	 * 对给定的文本进行中文分词
	 * 
	 * @param text
	 *            给定的文本
	 * @param splitToken
	 *            用于分割的标记,如"|"
	 * @return 分词完毕的文本
	 */
	public static String split(String text, String splitToken) {
		String result = null;
		result = segment(text, splitToken);
		return result;
	}
	
	private static String segment(String text, String splitToken) {
		StringBuilder sb = new StringBuilder();
		
		HHMMSegmenter h = new HHMMSegmenter();
		List<SegToken> tokens = h.process(text);
		
		// 第一个和最后一个我们不要,
		for (int i = 1; i < tokens.size() - 1; i++) {
			sb.append(tokens.get(i).charArray);
			sb.append(splitToken);
		}
		sb.deleteCharAt(sb.length() - 1);
		
		return sb.toString();
	}
	
	@Test
	public void testSegment() {
		System.out.println(segment("你好,我叫金杰", "|"));
	}
}
