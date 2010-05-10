package jaunty.copydetect;

public class Main {

	public static void main(String[] args) {
		
		String paperPath = "data/TrainingData/1934.txt";
		
		if (args.length >= 1) {
			paperPath = args[0];
		}
		
		SimilarityMeasure similarityMeasure = new SimilarityMeasure(paperPath);
		similarityMeasure.measure();
	}
}
