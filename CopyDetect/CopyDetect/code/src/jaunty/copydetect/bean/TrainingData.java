package jaunty.copydetect.bean;

import java.util.List;

/**
 * 训练数据.
 * 亦即某篇论文的某一段文字
 * 
 * @author hellojinjie
 * @date May 9, 2010 10:43:23 PM
 */
public class TrainingData {

	/**
	 * 这段文字是哪篇论文中的
	 */
	private Paper paper;

	/**
	 * 在原论文中的第几段
	 */
	private int paragraphId;

	/**
	 * 未分词前的文字
	 */
	private String originalText;

	/**
	 * 词语的个数, 是分词后的词,不是字
	 */
	private int wordCount;

	/**
	 * 分词后的词语
	 */
	private List<String> words;

	/**
	 * @return the paper
	 */
	public Paper getPaper() {
		return paper;
	}

	/**
	 * @param paper
	 *            the paper to set
	 */
	public void setPaper(Paper paper) {
		this.paper = paper;
	}

	/**
	 * @return the paragraphId
	 */
	public int getParagraphId() {
		return paragraphId;
	}

	/**
	 * @param paragraphId
	 *            the paragraphId to set
	 */
	public void setParagraphId(int paragraphId) {
		this.paragraphId = paragraphId;
	}

	/**
	 * @return the originalText
	 */
	public String getOriginalText() {
		return originalText;
	}

	/**
	 * @param originalText
	 *            the originalText to set
	 */
	public void setOriginalText(String originalText) {
		this.originalText = originalText;
	}

	/**
	 * @return the wordCount
	 */
	public int getWordCount() {
		return wordCount;
	}

	/**
	 * @param wordCount
	 *            the wordCount to set
	 */
	public void setWordCount(int wordCount) {
		this.wordCount = wordCount;
	}

	/**
	 * @return the words
	 */
	public List<String> getWords() {
		return words;
	}

	/**
	 * @param words
	 *            the words to set
	 */
	public void setWords(List<String> words) {
		this.words = words;
	}

}
