package jaunty.copydetect;

import jaunty.copydetect.bean.TrainingData;

import java.util.List;

/**
 * 计算先验概率
 * @author hellojinjie
 * @date May 9, 2010 10:32:00 PM
 */
public class PriorProbabilityMeasure {

	private List<TrainingData> trainingDataList;
	
	public PriorProbabilityMeasure(List<TrainingData> data) {
		this.trainingDataList = data;
	}
	
	public float calculate() {
		float result = 1;
		
		result = 1l / (float) trainingDataList.size();
	
		return result;
	}
	
}
