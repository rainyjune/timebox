package jaunty.copydetect;

import jaunty.copydetect.bean.Paper;
import jaunty.copydetect.bean.SimilarityResult;
import jaunty.copydetect.bean.TrainingData;

import java.io.BufferedReader;
import java.io.File;
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
 * 计算两个段落的相似度
 * 
 * @author hellojinjie
 * @date May 9, 2010 10:17:19 PM
 */
public class SimilarityMeasure {

	private static final Logger log = Logger.getLogger(SimilarityMeasure.class);

	/**
	 * 需要检测的论文
	 */
	private Paper paper;

	/**
	 * 需要检测的论文的段落
	 */
	private List<TrainingData> paragraphes;

	private TrainingDataManager trainingDataManager;
	private VocabularyManager vocabularyManager;
	private LikelyhoodMeasure likelyhoodMeasure;
	private PriorProbabilityMeasure priorProbabilityMeasure;

	public SimilarityMeasure(String paperPath) {

		init(paperPath);

		trainingDataManager = new TrainingDataManager();
		trainingDataManager.prepareTrainingData();

		vocabularyManager = new VocabularyManager();
		vocabularyManager.buildVocabulary();

		likelyhoodMeasure = new LikelyhoodMeasure();
		likelyhoodMeasure.setTrainingDataList(trainingDataManager
		        .getTrainingDataList());
		likelyhoodMeasure.setVocabulary(vocabularyManager.getVocabulary());

		priorProbabilityMeasure = new PriorProbabilityMeasure(
		        trainingDataManager.getTrainingDataList());
	}

	private void init(String paperPath) {
		try {
			BufferedReader reader = new BufferedReader(
			        new InputStreamReader(
			        new FileInputStream(paperPath),
			        Globals.CHARSET_NAME));
			paper = new Paper();
			paper.setTitle(new File(paperPath).getName());

			paragraphes = new LinkedList<TrainingData>();
			int paragraphId = 1;
			for (String paragraphString = reader.readLine(); paragraphString != null; paragraphString = reader
			        .readLine()) {
				IKSegmentation segmenting = new IKSegmentation(
				        new StringReader(paragraphString));
				List<String> words = new ArrayList<String>();

				for (Lexeme lexeme = segmenting.next(); lexeme != null; lexeme = segmenting
				        .next()) {
					words.add(lexeme.getLexemeText());
				}

				if (words.size() == 0) {
					continue;
				}

				TrainingData paragraph = new TrainingData();
				paragraph.setOriginalText(paragraphString);
				paragraph.setPaper(paper);
				paragraph.setParagraphId(paragraphId);
				paragraphId++;

				paragraph.setWordCount(words.size());
				paragraph.setWords(words);

				paragraphes.add(paragraph);
			}
		} catch (FileNotFoundException e) {
			log.fatal("failed to read training data...");
			throw new RuntimeException(e);
		} catch (IOException e) {
			log.fatal("failed to read training data...");
			throw new RuntimeException(e);
		}
	}

	public SimilarityResult calculate(TrainingData paragraph) {
		List<SimilarityResult> results = new ArrayList<SimilarityResult>();

		for (TrainingData data : trainingDataManager.getTrainingDataList()) {
		    /* 如果训练集中的段落词语个数少于 30 的，忽略 */
//		    if (paragraph.getWordCount() - data.getWordCount() > 20) {
//		        continue;
//		    }
		    
			float likelyhood = likelyhoodMeasure.calculate(paragraph, data);
			float similarity = priorProbabilityMeasure.calculate() * likelyhood;

			SimilarityResult result = new SimilarityResult();
			result.setParagraph(paragraph);
			result.setSimilarity(similarity);
			result.setTrainingData(data);
			results.add(result);
		}

		/* 找到极大后验概率 */

		SimilarityResult result = results.get(0);
		for (SimilarityResult r : results) {
			if (r.getSimilarity() > result.getSimilarity()) {
				result = r;
			}
		}

		/* 遍历的时候不能修改自己? 这样做就抛了java.util.ConcurrentModificationException */
		/*
		 * List<SimilarityResult> rs = new LinkedList<SimilarityResult>();
		 * for (SimilarityResult r : results) {
		 * if (r.getSimilarity() > 100000) {
		 * rs.add(r);
		 * }
		 * }
		 */

		return result;
	}

	public void measure() {
	    if (paragraphes == null) {
	        return;
	    }
	    
		for (TrainingData paragraph : paragraphes) {
			// List<SimilarityResult> results = calculate(paragraph);
			// for (SimilarityResult result : results) {
			// System.out.println(result.getParagraph().getPaper().getTitle()
			// + " 的第 "
			// + result.getParagraph().getParagraphId() + " 段和 "
			// + result.getTrainingData().getPaper().getTitle()
			// + " 的第 "
			// + result.getTrainingData().getParagraphId()
			// + " 段的相似度为 "
			// + result.getSimilarity());
			// }
			// System.out.println();

			SimilarityResult result = calculate(paragraph);
			System.out.println(result.getParagraph().getPaper().getTitle()
			        + " 的第 "
			        + result.getParagraph().getParagraphId() + " 段和 "
			        + result.getTrainingData().getPaper().getTitle()
			        + " 的第 "
			        + result.getTrainingData().getParagraphId()
			        + " 段的相似度为 "
			        + result.getSimilarity());
		}
	}
	
	public List<SimilarityResult> measure(boolean isGUIRunning) {

	   if (paragraphes == null) {
	       return null;
	   }
	   List<SimilarityResult> ret = new LinkedList<SimilarityResult>();
	   
	   for (TrainingData paragraph : paragraphes) {
	       /* 不计算少于 20 字的段落 */
	       if (paragraph.getWordCount() < 30) {
	           continue;
	       }
	       ret.add(calculate(paragraph));
	   }
	   
	   return ret;
	}
}
