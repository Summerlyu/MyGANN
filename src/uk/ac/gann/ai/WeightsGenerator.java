package uk.ac.gann.ai;

import java.util.Random;

public class WeightsGenerator {
	
	public double[] createWeights(int weightsNumber) {
		double[] weights = new double[weightsNumber];
		
		//System.out.println("\n From weightsGenerator. The followings are weights.");
		for (int i = 0; i < weightsNumber; i++) {
			weights[i] = (new Random().nextDouble() * 2 - 1);
			//System.out.println("The "+ i + " weight is: "+weights[i]);
		}
		return weights;
	}
}
