package uk.ac.gann.ai;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import uk.ac.gann.dataprocess.DataProcessForTraining;

public class GANNSEngine {

	// Instances declare
	DataProcessForTraining _dataProcessTrain;
	ActivationFunction _activationFunction;
	GeneticAlgorithm _geneticAlgorithm;
	NeuralNetwork _neuralNetwork;
	WeightsGenerator _weightsGenerator;
	SaveWeights _saveWeights;

	int _numOfHiddenNetwork, _numOfOutputNetwork, _numInputs, _numOfNetwork,
			_numOfOutputNetworkInput, _times, _totalLine, _flag;
	double _bias, _tolerance, _successRate, _minFactor, _maxFactor;
	double[] _age, _distance, _totalAmount, _totalMoney, _input, _output,
			_hiddenWeights, _outputWeights, _mergedChildWeights,
			_hidActivatedOutput, _outActivatedOutput, _parent1Hidden,
			_parent1Output, _parent1Merged, _parent2Hidden, _parent2Output,
			_parent2Merged, _mergedOriginWeights, _reorderedhidActivatedOutput;

	boolean[] _evaluateFitnessResult;
	List<double[]> _twoArray = new ArrayList<double[]>();
	String _type;
	boolean _isInitial;

	public void myEngine() throws SQLException, IOException {

		// Instance initialization
		_dataProcessTrain = new DataProcessForTraining();
		_activationFunction = new ActivationFunction();
		_geneticAlgorithm = new GeneticAlgorithm();
		_neuralNetwork = new NeuralNetwork();
		_weightsGenerator = new WeightsGenerator();
		_saveWeights = new SaveWeights();
		_type = "totalAmount";
		//_type = "totalMoney";
		_totalLine = _dataProcessTrain.theTotalLine();
		_bias = 0;
		_numInputs = 2;
		_numOfNetwork = 4;
		_tolerance = 0.15;
		_times = 1;
		_minFactor = -0.2;
		_maxFactor = 0.2; // Initial factor
		_successRate = 0;
		_flag = 0;

		int _deathRate = 0;
		double _targetRate = 70;
		double _previousSuccessRate = 0;
		boolean _shouldLoadData = true;
		boolean _shouldGenerateWeights = true;
		boolean _parentsFound = false;
		boolean _forceEvolution = false;
		boolean _shouldReset = false;
		boolean _saveSuccessRateCondition = false;
		
		while (_times <=100) {

			if (_shouldLoadData) {
				// 1. Load inputs and outputs
				try {
					_input = loadData("inputs", _times);

					System.out.println("The inputs length is: " + _input.length
							+ ". And the time is: " + _times);
					_output = loadData(_type, _times);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				_shouldLoadData = false;
			}

			// 2. Generate random weights for both hidden network and output
			// network
			if (_shouldGenerateWeights) {
				_hiddenWeights = _geneticAlgorithm
						.GeneratePopulation(_numInputs * _numOfNetwork);
				_outputWeights = _geneticAlgorithm
						.GeneratePopulation(_numOfNetwork);
			}

			// 3. Execute hidden network
			_hidActivatedOutput = executeNetwork(_numOfNetwork, _input,
					_hiddenWeights, _numInputs, _bias);
			_reorderedhidActivatedOutput = _dataProcessTrain
					.reOrderArrayAsInterleave(_hidActivatedOutput,
							_numOfNetwork);

			// 4. Execute output network
			_outActivatedOutput = executeNetwork(1,
					_reorderedhidActivatedOutput, _outputWeights,
					_numOfNetwork, _bias);

			// 5. Compute the succuss rate
			_successRate = computeSuccessRate( _output,
					_outActivatedOutput);

			// 6. Compare success rate and find parents
			if (_successRate > _previousSuccessRate) {
				_deathRate = 0;
				if (_flag == 0) { // Means parent1 hasn't been initialized
					_parent1Hidden = _hiddenWeights; // Initial parent1
					_parent1Output = _outputWeights;
				}

				if (_flag == 1) { // Only parent1 has been initialized
					_parent2Hidden = _hiddenWeights; // Initial parent2
					_parent2Output = _outputWeights;
				}

				_flag++;

				if (_flag == 2) {
					_parentsFound = true;
					_flag = 0;
				}

				_previousSuccessRate = _successRate;
				System.out.println(_successRate);
			} else {
				if (_parentsFound) { // Only when parents are found then start
										// counting death rate
					_deathRate++;

					if (_deathRate == 100) {
						_deathRate = 0;
						_forceEvolution = true;
					}
				}
			}

			if (_parentsFound) {
				_shouldGenerateWeights = false;

				if (_successRate > _targetRate) {
					System.out.println("Target Rate Achieved.");

					/*//7. save success rate for testing
					_saveSuccessRateCondition = _dataProcessTrain.saveTrainSuccessRate(
							_type, _times, _successRate);
					if (_saveSuccessRateCondition == true) {
						System.out
								.println("Save the success rate successfully!");
					}*/

					_shouldLoadData = true;
					_successRate = 0;
					_previousSuccessRate = 0;
					_times++;
					
				} else { // 8. These weights are evolved by using Genetic Algorithm
					_parent1Merged = _dataProcessTrain.mergeArrays(_parent1Hidden,
							_parent1Output);
					_parent2Merged = _dataProcessTrain.mergeArrays(_parent2Hidden,
							_parent2Output);

					_evaluateFitnessResult = _geneticAlgorithm.EvaluateFitness(
							_parent1Merged, _parent2Merged, _minFactor,
							_maxFactor);

					// Prevent infinite loop in a local minima

					for (int i = 0; i < _evaluateFitnessResult.length; i++) {
						_shouldReset = true;
						if (_evaluateFitnessResult[i] == false)
							_shouldReset = false; // didn't stuck in a local
													// minima then don't need to
													// reset
					}

					if (_shouldReset || _forceEvolution) {
						System.out
								.println("Stuck in a local minima.  Resetting.");
						_successRate = 0;
						_previousSuccessRate = 0;
						_flag = 0;
						_shouldGenerateWeights = true;
						_parentsFound = false;
						_shouldReset = false;
						_forceEvolution = false;
					} else {
						_mergedOriginWeights = _dataProcessTrain.mergeArrays(
								_hiddenWeights, _outputWeights);
						_mergedChildWeights = _geneticAlgorithm
								.CrossoverAndMutation(_evaluateFitnessResult,
										_mergedOriginWeights);

						_twoArray = _dataProcessTrain.unmergeArray(
								_mergedChildWeights, _hiddenWeights.length,
								_outputWeights.length);
						_hiddenWeights = _twoArray.get(0);
						_outputWeights = _twoArray.get(1);

					}
				} // end else
			}// end if
		}// end while

		// All the data has been trained. Save the final weights
		System.out
				.println("The training is finished! The followings are final weights.");
		for (int i = 0; i < _hiddenWeights.length; i++) {
			System.out.println("The " + i + " hidden weight is: "
					+ _hiddenWeights[i]);
		}
		for (int i = 0; i < _outputWeights.length; i++) {
			System.out.println("The " + i + " output weight is: "
					+ _outputWeights[i]);
		}

		/*if (_type.equals("totalAmount")) {
			_saveWeights
					.saveWeightsToTxt("AmountHiddenWeights", _hiddenWeights);
			_saveWeights
					.saveWeightsToTxt("AmountOutputWeights", _outputWeights);
		} else {
			_saveWeights.saveWeightsToTxt("MoneyHiddenWeights", _hiddenWeights);
			_saveWeights.saveWeightsToTxt("MoneyOutputWeights", _outputWeights);
		}

		System.out.println("Save the weights successfully!");*/

	}// end myEngine()

	// Load data from database as inputs or output
	private double[] loadData(String type, int times) throws SQLException {

		double[] _array = null;
		if (type.equals("inputs")) {
			_age = _dataProcessTrain.loadData("age", times);
			_distance = _dataProcessTrain.loadData("distance", times);
			_array = _dataProcessTrain.mergeCrossArrays(_age, _distance, times);
		} else {
			_array = _dataProcessTrain.loadData(type, times);
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

	// Compute the success rate
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


