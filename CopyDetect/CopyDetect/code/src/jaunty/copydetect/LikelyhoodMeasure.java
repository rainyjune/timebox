package jaunty.copydetect;

import jaunty.copydetect.bean.TrainingData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 计算两个段落的似然值
 * 
 * @author hellojinjie
 * @date May 9, 2010 10:20:24 PM
 */
public class LikelyhoodMeasure {

	/* private static final Logger log = Logger.getLogger(LikelyhoodMeasure.class); */
	private Set<String> vocabulary;
	private List<TrainingData> trainingDataList;

	/**
	 * 计算两个段落的似然值
	 * 
	 * @param data0 新实例中的一个段落
	 * @param data1 目标训练数据中的一个段落
	 * @return
	 */
	public float calculate(TrainingData data0, TrainingData data1) {
		
		float result = 1l;

		Set<String> ss = new HashSet<String>();
		for (String s : data0.getWords()) {
			if (vocabulary.contains(s)) {
				ss.add(s);
			}
		}

		float n = data1.getWordCount();
		for (String s : ss) {
			float nk = 0;
			for (String string : data1.getWords()) {
				if (s.equals(string)) {
					nk++;
				}
			}
			result = result * (nk + 1) / (n + vocabulary.size()) * Globals.ZOOM_FACTOR;
		}

		return result;
	}

	/**
	 * @return the vocabulary
	 */
	public Set<String> getVocabulary() {
		return vocabulary;
	}

	/**
	 * @param vocabulary the vocabulary to set
	 */
	public void setVocabulary(Set<String> vocabulary) {
		this.vocabulary = vocabulary;
	}

	/**
	 * @return the trainingDataList
	 */
	public List<TrainingData> getTrainingDataList() {
		return trainingDataList;
	}

	/**
	 * @param trainingDataList the trainingDataList to set
	 */
	public void setTrainingDataList(List<TrainingData> trainingDataList) {
		this.trainingDataList = trainingDataList;
	}

}
