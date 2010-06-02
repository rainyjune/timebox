package jaunty.copydetect;

import java.util.Arrays;

import jaunty.copydetect.bean.TrainingData;

import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * @author hellojinjie
 * @date May 10, 2010 9:48:12 AM
 */
public class TrainingDataManagerTest {

	private static final Logger log = Logger
	        .getLogger(TrainingDataManagerTest.class);

	/**
	 * Test method for
	 * {@link jaunty.copydetect.TrainingDataManager#prepareTrainingData()}.
	 */
	@Test
	public void testPrepareTrainingData() {
		TrainingDataManager trainingDataManager = new TrainingDataManager();
		//trainingDataManager.setTrainingDataPath("src/jaunty/copydetect");
		trainingDataManager.prepareTrainingData();

		for (TrainingData data : trainingDataManager.getTrainingDataList()) {
			log.debug(data.getPaper().getTitle() + " " + data.getWordCount()
			        + " " + Arrays.deepToString(data.getWords().toArray()));

		}
	}

}
