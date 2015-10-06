package uk.ac.gann.ai;

//Neural networks
public class NeuralNetwork {

	double[] activation; // This is an activation for hidden network or output network

	// Hidden network and output network implementing method
	public double[] hiddenAndOutputNetwork(int _numOfNetwork, double[] _input,
			double[] _weights, int _numInputs, double _bias) {

		activation = new double[_numOfNetwork*_input.length/2];;
		int wIndex = 0;
		int aIndex = 0;
		int inputIndex = 0;

		for (int k = 0; k < _numOfNetwork; k++) {
			for (int i = 0; i < _input.length / _numInputs; i++) {
				for (int j = 0; j < _numInputs; j++) {
					activation[aIndex] += (_input[inputIndex] * _weights[wIndex]);
					wIndex++;
					inputIndex++;
				}
				activation[aIndex] += _bias;
				wIndex = 0;
				aIndex++;
			}
			inputIndex = 0;
		}
		return activation;
	}
}
