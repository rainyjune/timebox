package jaunty.copydetect.bean;

/**
 * 相似度计算结果
 * 
 * @author hellojinjie
 * @date May 10, 2010 12:16:53 PM
 */
public class SimilarityResult {

	/**
	 * 相似度
	 */
	private float similarity;
	/**
	 * 待检测论文中的一个段落
	 */
	private TrainingData paragraph;
	
	/**
	 * paragraph 与训练集中相似度为 similarity 的一个段落
	 */
	private TrainingData trainingData;

	/**
	 * @return the similarity
	 */
	public float getSimilarity() {
		return similarity;
	}

	/**
	 * @param similarity the similarity to set
	 */
	public void setSimilarity(float similarity) {
		this.similarity = similarity;
	}

	/**
	 * @return the trainingData
	 */
	public TrainingData getTrainingData() {
		return trainingData;
	}

	/**
	 * @param trainingData the trainingData to set
	 */
	public void setTrainingData(TrainingData trainingData) {
		this.trainingData = trainingData;
	}

	/**
	 * @return the paragraph
	 */
	public TrainingData getParagraph() {
		return paragraph;
	}

	/**
	 * @param paragraph the paragraph to set
	 */
	public void setParagraph(TrainingData paragraph) {
		this.paragraph = paragraph;
	}

}
