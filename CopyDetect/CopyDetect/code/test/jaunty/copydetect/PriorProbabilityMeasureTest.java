package jaunty.copydetect;

import org.junit.Test;

/**
 * @author hellojinjie
 * @date May 10, 2010 1:19:34 PM
 */
public class PriorProbabilityMeasureTest {

	/**
	 * Test method for
	 * {@link jaunty.copydetect.PriorProbabilityMeasure#calculate()}.
	 */
	@Test
	public void testCalculate() {
		TrainingDataManager trainingDataManager = new TrainingDataManager();
		trainingDataManager.prepareTrainingData();
		PriorProbabilityMeasure priorProbabilityMeasure = new PriorProbabilityMeasure(
		        trainingDataManager.getTrainingDataList());
		System.out.println(priorProbabilityMeasure.calculate());
	}

}
