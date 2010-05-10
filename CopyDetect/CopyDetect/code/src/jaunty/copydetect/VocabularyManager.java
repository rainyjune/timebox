package jaunty.copydetect;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.wltea.analyzer.IKSegmentation;
import org.wltea.analyzer.Lexeme;

/**
 * 词汇表
 * 
 * @author hellojinjie
 * @date May 9, 2010 9:01:50 PM
 */
public class VocabularyManager {

	/**
	 * 在所有训练样例中收集所有的词语形成一个词汇表 不包括停用词.
	 * 对于在训练样例中出现次数仅为一次的词也考虑不将其包括在内,不过目前将其包括在内.
	 * 使用 set 保证里面的词汇不重复
	 */
	private Set<String> vocabulary = new LinkedHashSet<String>();

	/**
	 * 训练数据的目录,也就是待比较的论文的目录
	 */
	private String trainingDataPath = Globals.TRANING_DATA_PATH;

	private static final Logger log = Logger.getLogger(VocabularyManager.class);

	public VocabularyManager() {
	}

	public VocabularyManager(String trainingDataPath) {
		this.trainingDataPath = trainingDataPath;
	}

	/**
	 * 生成词汇表
	 */
	public void buildVocabulary() {

		File trainingDataDirectory = new File(trainingDataPath);
		File[] paperFiles = trainingDataDirectory.listFiles(new FileFilter() {
			@Override
			public boolean accept(File arg0) {
				if (arg0.isFile()) {
					return true;
				} else {
					return false;
				}
			}
		});

		if (paperFiles == null) {
			log.fatal("路径不对?");
			throw new RuntimeException("找不到训练数据...");
		}

		BufferedReader in = null;
		for (File paperFile : paperFiles) {
			try {
				in = new BufferedReader(new InputStreamReader(
				        new FileInputStream(paperFile), Globals.CHARSET_NAME));
			} catch (FileNotFoundException e) {
				log.fatal("failed to read training data...");
				throw new RuntimeException(e);
			} catch (UnsupportedEncodingException e) {
				log.fatal("failed while reading text...");
				throw new RuntimeException(e);
			}

			IKSegmentation ik = new IKSegmentation(in);
			try {
				for (Lexeme lexeme = ik.next(); lexeme != null; lexeme = ik
				        .next()) {
					String word = lexeme.getLexemeText();
					vocabulary.add(word);
				}
			} catch (IOException e) {
				log.fatal("failed while segmenting text...");
				throw new RuntimeException(e);
			}
		}

	}

	/**
	 * 得到词汇表的大小
	 * 
	 * @return
	 */
	public int getvVocabularySize() {
		return vocabulary.size();
	}

	/**
	 * @return the trainingDataPath
	 */
	public String getTrainingDataPath() {
		return trainingDataPath;
	}

	/**
	 * @param trainingDataPath
	 *            the trainingDataPath to set
	 */
	public void setTrainingDataPath(String trainingDataPath) {
		this.trainingDataPath = trainingDataPath;
	}

	/**
	 * @return the vocabulary
	 */
	public Set<String> getVocabulary() {
		return vocabulary;
	}

}
