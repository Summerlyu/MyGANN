package uk.ac.gann.test;
import java.sql.SQLException;

import uk.ac.gann.ai.ActivationFunction;
import uk.ac.gann.ai.NeuralNetwork;
import uk.ac.gann.ai.WeightsGenerator;
import uk.ac.gann.dataprocess.DataProcessForTraining;
public class NeuralNetworkTest {

	public static void main(String[] args) throws SQLException {

		DataProcessForTraining _dp = new DataProcessForTraining();
		NeuralNetwork hiddenNetwork = new NeuralNetwork();
		ActivationFunction activationFunction = new ActivationFunction();

		//The data type and the number of input
		int times = 1;
		String type = "age";
		String type2 = "distance";

		int _numLayer = 3;
		double bias = 0;
		double[] _hidWeights;
		int _numInputs = 2;
		double[] age = new double[50 * times];
		double[] distance = new double[50 * times];
		double[] _input = null;
		double[] _hidActivation;
		double[] _hidActivationOutput;

		age = _dp.loadData(type, times);
		distance = _dp.loadData(type2, times);
		_input = _dp.mergeCrossArrays(age, distance, times);

		WeightsGenerator weightsGenerator = new WeightsGenerator();

		// Weight generation both positive and negative weights
		_hidWeights = weightsGenerator.createWeights(_input.length*_numLayer);// _input.length

		_hidActivation = hiddenNetwork.hiddenAndOutputNetwork(_numLayer, _input,
				_hidWeights, _numInputs, bias);
		_hidActivationOutput = new double[_hidActivation.length];
		for (int i = 0; i < _hidActivation.length; i++) {
			_hidActivationOutput[i] = activationFunction
					.logSigmoid(_hidActivation[i]);
			System.out.println("The " + i
					+ " activation output for hidden network is: "
					+ _hidActivationOutput[i]);
		}
	}
}
