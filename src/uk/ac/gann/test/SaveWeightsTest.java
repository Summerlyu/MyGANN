package uk.ac.gann.test;

import java.io.IOException;
import uk.ac.gann.ai.SaveWeights;

public class SaveWeightsTest {
	static SaveWeights _saveWeights = new SaveWeights();
	// SaveWeights test
	public static void main(String args[]) throws IOException  {
		double[] array = { 111370.4, 35.9, 74, 2.0, 3.9, 51, 199 };
		_saveWeights.saveWeightsToTxt("test",array);
	}
}
