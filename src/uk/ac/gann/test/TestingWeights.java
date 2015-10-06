package uk.ac.gann.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class TestingWeights {

	double[] _hiddenWeights = new double[8];
	double[] _outputWeights = new double[4];
	String filePathHidden = "D:\\hiddenWeights.txt";
	String filePathOutput = "D:\\outputWeights.txt";

	public double[] readWeightsFromTxt(String path) {
		double[] array = null;
		List<Double> arrayList = new ArrayList<Double>();
		try {

			FileReader fileReader = new FileReader(path);
			BufferedReader buf = new BufferedReader(fileReader);
			String readLine = "";
			while ((readLine = buf.readLine()) != null) {
				arrayList.add(Double.parseDouble(readLine));
			}
			array = new double[arrayList.size()];
			for (int i = 0; i < arrayList.size(); i++) {
				array[i] = arrayList.get(i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return array;
	}

}
