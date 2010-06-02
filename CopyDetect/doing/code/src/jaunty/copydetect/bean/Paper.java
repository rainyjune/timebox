package jaunty.copydetect.bean;

/**
 * 论文
 * 
 * @author hellojinjie
 * @date May 9, 2010 10:50:41 PM
 */
public class Paper {

	/**
	 * 论文题目
	 */
	private String title;

	/**
	 * 论文没分词前的文本
	 */
	private String originalText;

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
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

}
