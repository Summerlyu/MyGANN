package uk.ac.gann.ai;

public class GeneticAlgorithm {

	public double[] GeneratePopulation(int _amount) {
		WeightsGenerator _wg = new WeightsGenerator();
		return _wg.createWeights(_amount);
	}

	// Check for all true and no solution - if so, start entire algorithm again from the beginning.
	public boolean[] EvaluateFitness(double[] _parent1Weights, double[] _parent2Weights, double _minFactor, double _maxFactor) {
		boolean[] _shouldKeep = new boolean[_parent1Weights.length];

		for (int i = 0; i < _parent1Weights.length; i++) {
			if (_parent1Weights[i] - _parent2Weights[i] < _maxFactor
					&& _parent1Weights[i] - _parent2Weights[i] > _minFactor)
				_shouldKeep[i] = true;
			else
				_shouldKeep[i] = false;
		}
		return _shouldKeep;
	}

	public double[] CrossoverAndMutation(boolean[] _evaluateFitnessResult,
			double[] _weights) {
		WeightsGenerator _wg = new WeightsGenerator();

		double[] _childWeights = new double[_weights.length];

		for (int i = 0; i < _weights.length; i++) {
			if (_evaluateFitnessResult[i])
				_childWeights[i] = _weights[i];// crossover
			else {
				_childWeights[i] = _wg.createWeights(1)[0]; // mutation
			}
		}
		return _childWeights;
	}
}

