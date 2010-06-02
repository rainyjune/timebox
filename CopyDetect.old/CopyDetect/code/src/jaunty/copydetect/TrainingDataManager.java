package jaunty.copydetect;

import jaunty.copydetect.bean.Paper;
import jaunty.copydetect.bean.TrainingData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.wltea.analyzer.IKSegmentation;
import org.wltea.analyzer.Lexeme;

/**
 * 训练数据管理, 将待比较的训练数据(论文)进行分段
 * 我们的训练数据是以段落为单位的.
 * 
 * @author hellojinjie
 * @date May 9, 2010 10:41:27 PM
 */
public class TrainingDataManager {

	private static final Logger log = Logger
	        .getLogger(TrainingDataManager.class);

	private String trainingDataPath = Globals.TRANING_DATA_PATH;

	private List<TrainingData> trainingDataList = new LinkedList<TrainingData>();

	public TrainingDataManager() {
	}

	public TrainingDataManager(String trainingDataPath) {
		this.trainingDataPath = trainingDataPath;
	}

	/**
	 * 准备训练数据
	 * 训练数据的分隔符是什么呢? 也就是段落与段落之间的分隔符是什么?
	 * 暂时先仅考虑 \r\n 为段落之间的分隔符
	 * TODO 以 \r\n 为段落之间的分隔符是否合理?
	 */
	public void prepareTrainingData() {
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

		for (File paperFile : paperFiles) {
			Paper paper = new Paper();
			paper.setTitle(paperFile.getName());

			int paragraphId = 1;
			try {
				BufferedReader reader = new BufferedReader(
				        new InputStreamReader(
				                new FileInputStream(paperFile),
				                Globals.CHARSET_NAME));
				for (String paragraph = reader.readLine(); paragraph != null; paragraph = reader
				        .readLine()) {
					/*
					 * 这个判断竟然不能去掉空白行,貌似只能换种方式了
					 * if (paragraph.equals("") || paragraph.matches("\\s")) {
					 * continue;
					 * }
					 */

					IKSegmentation ik = new IKSegmentation(new StringReader(
					        paragraph));
					List<String> words = new ArrayList<String>();
					for (Lexeme lexeme = ik.next(); lexeme != null; lexeme = ik
					        .next()) {
						words.add(lexeme.getLexemeText());
					}

					if (words.size() == 0) {
						continue;
					}

					TrainingData data = new TrainingData();

					data.setWords(words);
					data.setOriginalText(paragraph);
					data.setPaper(paper);

					data.setParagraphId(paragraphId);
					paragraphId++;

					data.setWordCount(words.size());

					trainingDataList.add(data);
				}
			} catch (FileNotFoundException e) {
				log.fatal("failed to read training data...");
				throw new RuntimeException(e);
			} catch (IOException e) {
				log.fatal("failed to read training data...");
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * @param trainingDataList
	 *            the trainingDataList to set
	 */
	public void setTrainingDataList(List<TrainingData> trainingDataList) {
		this.trainingDataList = trainingDataList;
	}

	/**
	 * @return the trainingDataList
	 */
	public List<TrainingData> getTrainingDataList() {
		return trainingDataList;
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

}
