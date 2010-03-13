package jaunty.beyes;

import java.util.List;

import org.apache.lucene.analysis.cn.smart.hhmm.HHMMSegmenter;
import org.apache.lucene.analysis.cn.smart.hhmm.SegToken;

public class FenCiTest {

	public static void main(String[] args) {
		FenCiTest f = new FenCiTest();
		String sentence = "你好,你是贺岁,我副食店风扇发阿叔发阿叔 个个个搜到发阿叔发阿叔的额 份额";
		System.out.println(f.fenCi(sentence));
	}
	
	public String fenCi(String sentence) {
		StringBuilder ret = new StringBuilder();
		
		HHMMSegmenter h = new HHMMSegmenter();
		List<SegToken> l = h.process(sentence);
		/*
		for (Iterator i = l.iterator(); i.hasNext();) {
			SegToken set = (SegToken) i.next();
			ret.append(st.charArray);
			ret.append(" | ");
		}
		*/
		for (SegToken st : l) {
			ret.append(st.charArray);
			ret.append(" | ");
		}
		
		
		return ret.toString();
	}
}
