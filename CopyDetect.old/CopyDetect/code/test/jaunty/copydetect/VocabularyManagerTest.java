package jaunty.copydetect;

import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * @author hellojinjie
 * @date May 10, 2010 9:04:55 AM
 */
public class VocabularyManagerTest {

	private static final Logger log = Logger.getLogger(VocabularyManagerTest.class);
	
	/**
	 * Test method for {@link jaunty.copydetect.VocabularyManager#buildVocabulary()}.
	 */
	@Test
	public void testBuildVocabulary() {
		VocabularyManager vocabularyManager = new VocabularyManager();
		//vocabularyManager.setTrainingDataPath("src/jaunty/copydetect");
		vocabularyManager.buildVocabulary();
		for (String s : vocabularyManager.getVocabulary()) {
			log.debug(s);
		}
		log.debug(vocabularyManager.getVocabulary().size());
	}

}
