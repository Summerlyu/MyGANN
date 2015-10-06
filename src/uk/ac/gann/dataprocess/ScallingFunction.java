package uk.ac.gann.dataprocess;

public class ScallingFunction {

	public static void main(String[] args) {
		double[] oldArray = { -10, -9, -8, -7, -6, -5, -4, -3, -2, -1, 0, 1, 2,
				3, 4, 5, 6, 7, 8, 9, 10 };
		double[] newArray = null;
		double newMax = 1;
		double newMin = -1;
		newArray = scaleDownDataSetFunction(oldArray, newMax, newMin);
	}

	//Such as scale data set: -8.....2 to -1...1
	public static double[] scaleDownDataSetFunction(double[] oldArray,
			double newMax, double newMin) {
		double[] newArray = oldArray;
		double oldMax = oldArray[0];
		double oldMin = oldArray[1];

		oldMin = findArrayMinMax(oldArray)[0];
		oldMax = findArrayMinMax(oldArray)[1];
		System.out.println("The oldMax is : " + oldMax + "  The oldMin is : "
				+ oldMin);

		// scall down each member in the array
		for (int i = 0; i < oldArray.length; i++) {
			newArray[i] = (oldArray[i] - ((oldMax + oldMin) / 2))
					/ ((oldMax - oldMin) / (newMax - newMin));
			System.out.println("The " + i + " element of new array is :"
					+ newArray[i]);
		}
		return newArray;
	}

	//Find the min and the max from an array
	public static double[] findArrayMinMax(double[] oldArray){
		double[] minMax = new double[2]; 
		
		for (double i : oldArray) {
			minMax[0] = minMax[0] < i ? minMax[0] : i; //min
			minMax[1] = minMax[1] > i ? minMax[1] : i; //max
		}
		return minMax;
	}
}

  
