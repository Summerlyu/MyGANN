package uk.ac.gann.ai;

import java.sql.SQLException;
import uk.ac.gann.dataprocess.DataProcessForTesting;
import uk.ac.gann.test.TestingWeights;

public class GANNTest {

	// instances declare
	DataProcessForTesting _dataProcessTest;
	ActivationFunction _activationFunction;
	NeuralNetwork _neuralNetwork;
	SaveWeights _saveWeights;
	TestingWeights _testingWeights;
	int _numInputs, _numOfNetwork, _times, _successful, _unSuccessful,
			_totalLine;
	double _bias, _tolerance, _successRate;
	double[] _age, _distance, _totalAmount, _totalMoney, _input, _output,
			_hiddenWeights, _outputWeights, _hidActivatedOutput,
			_outActivatedOutput, _reorderedhidActivatedOutput;

	String _type;
	boolean _saveSuccessRateCondition;

	public void gANNTest() throws SQLException {

		_dataProcessTest = new DataProcessForTesting();
		_activationFunction = new ActivationFunction();
		_neuralNetwork = new NeuralNetwork();
		_saveWeights = new SaveWeights();
		_testingWeights = new TestingWeights();
		//_type = "totalAmount";
		_type = "totalMoney";
		_totalLine = _dataProcessTest.theTotalLine();
		_bias = 0;
		_numInputs = 2;
		_numOfNetwork = 4;
		_tolerance = 0.2;
		_times = 1;
		_successRate = 0;
		_saveSuccessRateCondition = false;

		_hiddenWeights = new double[_numOfNetwork * _numInputs];
		_outputWeights = new double[_numOfNetwork];

		String filePathHidden = null;
		String filePathOutput = null;

		if (_type.equals("totalAmount")) {
			filePathHidden = "D:\\AmountHiddenWeights.txt";
			filePathOutput = "D:\\AmountOutputWeights.txt";
		} else {
			filePathHidden = "D:\\MoneyHiddenWeights.txt";
			filePathOutput = "D:\\MoneyOutputWeights.txt";
		}
		_hiddenWeights = _testingWeights.readWeightsFromTxt(filePathHidden);
		_outputWeights = _testingWeights.readWeightsFromTxt(filePathOutput);

		while (_times <=100) {
			// 1. load the input and output data
			_input = loadData("inputs", _times);
			_output = loadData(_type, _times);

			// 2. Execute hidden network
			_hidActivatedOutput = executeNetwork(_numOfNetwork, _input,
					_hiddenWeights, _numInputs, _bias);
			_reorderedhidActivatedOutput = _dataProcessTest
					.reOrderArrayAsInterleave(_hidActivatedOutput,
							_numOfNetwork);

			// 3. Execute output network
			_outActivatedOutput = executeNetwork(1,
					_reorderedhidActivatedOutput, _outputWeights,
					_numOfNetwork, _bias);

			// 4. Compute success rate
			_successRate = computeSuccessRate( _output,
					_outActivatedOutput);

			// 5. Save success rate for testing
			/*_saveSuccessRateCondition = _dataProcessTest.saveTestSuccessRate(
					_type, _times, _successRate);
			if (_saveSuccessRateCondition == true) {
				System.out.println("Save the success rate successfully!");
			}*/
			System.out.println("The " + _times + " success rate is: "
					+ _successRate);
			_times++;
		}

	}

	// Load data from database as inputs or output
	private double[] loadData(String type, int times) throws SQLException {

		double[] _array = null;
		if (type.equals("inputs")) {
			_age = _dataProcessTest.loadData("age", times);
			_distance = _dataProcessTest.loadData("distance", times);
			_array = _dataProcessTest.mergeCrossArrays(_age, _distance, times);
		} else {
			_array = _dataProcessTest.loadData(type, times);
		}
		return _array;
	}

	// Execute hidden network and output network
	private double[] executeNetwork(int _numOfNetwork, double[] _input,
			double[] _Weights, int _numInputs, double bias) {
		double[] _output = new double[_numOfNetwork
				* (_input.length / _numInputs)];
		double[] _activatedOutputs = new double[_output.length];

		_output = _neuralNetwork.hiddenAndOutputNetwork(_numOfNetwork, _input,
				_Weights, _numInputs, _bias);

		for (int i = 0; i < _activatedOutputs.length; i++) {
			_activatedOutputs[i] = _activationFunction.logSigmoid(_output[i]);
		}
		return _activatedOutputs;
	}

	// use a criteria to compute the success rate
	private double computeSuccessRate(double[] outputs,
			double[] desiredOutput) {
		double successRate = 0;
		double success = 0, unsuccess = 0;
		for (int i = 0; i < desiredOutput.length; i++) {
			// the desiredOutput is near enough to the real output then
			// keep the weights
			if ((desiredOutput[i] - outputs[i] < _tolerance)
					|| (outputs[i] - desiredOutput[i] > -_tolerance)) {
				success++;
			}
			// the activationOutput is not near enough to real output
			else {
				unsuccess++;
			}
		}
		successRate = (success / (unsuccess + success)) * 100;
		return successRate;
	}
}

