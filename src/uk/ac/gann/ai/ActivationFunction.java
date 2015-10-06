package uk.ac.gann.ai;

//This is an activation function for neural network 
public class ActivationFunction {

	// LogSigmoid function. The output is from 0 to 1.
	public double logSigmoid(double x) {
		return (1 / (1 + Math.pow(Math.E, (-1 * x))));
	}
	// TanSigmoid function. The output is from -1 to 1.
	public double tanSigmoid(double x) {
		return (2/( 1 + Math.pow(Math.E,(-2*x)))-1);
	}
}
