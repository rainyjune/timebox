package jaunty.beyes;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * 朴素贝叶斯分类器
 * 
 * <p>
 * 
 * c<sub>NB</sub>=arg Max P(c<sub>j</sub>)&prod;<sub>1</sub><sup>C</sup>
 * 
 * P(x<sub>i</sub>|c<sub>j</sub>)
 * 
 * </p>
 * 
 * @author 88250
 * 
 * @version 1.0.0.0, Feb 19, 2008
 */
public class BayesClassifier {

	private TrainingDataManager tdm;

	/**
	 * 
	 * 默认的构造器，初始化训练库路径
	 */
	public BayesClassifier() {

		try {
			Properties properties = new Properties();
			FileInputStream inputFile;
			inputFile = new FileInputStream(
					"/home/daniel/TempData/BayesTextClassifySystem/Training.properties");

			properties.load(inputFile);

			tdm = new TrainingDataManager(properties.getProperty("path"));

		} catch (IOException ex) {

			Logger.getLogger(BayesClassifier.class.getName()).

			log(Level.SEVERE, null, ex);
		}

	}

	/**
	 * 
	 * 计算给定的文本属性向量<code>X</code>在给定的分类<code>Cj</code>中的类条件概率
	 * 
	 * <code>ClassConditionalProbability</code>连乘(&prod;)值
	 * 
	 * @param X
	 *            给定的文本属性向量
	 * 
	 * @param Cj
	 *            给定的类别
	 * 
	 * @return 类条件概率连乘(&prod;)值，即<br>
	 * 
	 *         P(c<sub>j</sub>)&prod;<sub>1</sub><sup>C</sup>
	 *         P(x<sub>i</sub>|c<sub>j</sub>)
	 * 
	 * @see cn.edu.ynu.sei.classifier.util.ClassConditionalProbability
	 */
	float calcProd(String[] X, String Cj) {

		float ret = 0F;

		// 类条件概率连乘

		for (int i = 0; i < X.length; i++) {
			String Xi = X[i];

			ret += ClassConditionalProbability.calculatePxc(Xi, Cj);
		}

		// 再乘以先验概率

		ret *= PriorProbability.calculatePc(Cj);

		return ret;

	}

	/**
	 * 
	 * 对给定的文本进行分类
	 * 
	 * @param text
	 *            给定的文本
	 * 
	 * @return 分类结果
	 */
	@SuppressWarnings("unchecked")
	public String classify(String text) {
		String[] X = ChineseSpliter.split(text, "|").split("|");
		String[] C = tdm.getTraningClassifications();

		float p = 0F;

		List<ClassifyResult> crs = new ArrayList<ClassifyResult>();

		for (int i = 0; i < C.length; i++) {
			String Ci = C[i];
			p = calcProd(X, Ci);

			ClassifyResult cr = new ClassifyResult();

			cr.classification = Ci;
			cr.p = p;

			System.out.println("In process....");
			System.out.println(Ci + "：" + p);

			crs.add(cr);

		}

		java.util.Collections.sort(crs, new Comparator() {
			public int compare(Object o1, Object o2) {
				ClassifyResult m1 = (ClassifyResult) o1;
				ClassifyResult m2 = (ClassifyResult) o2;
				float ret = (float) (m1.p - m2.p);

				if (ret < 0) {
					return 1;
				} else {
					return -1;
				}
			}
		});

		return crs.get(0).classification;

	}
}