package uk.ac.gann.test;

import java.sql.SQLException;
import uk.ac.gann.dataprocess.DataProcessForTraining;

public class DataProcessTest {

	public static void main(String[] args) throws SQLException {
		
		DataProcessForTraining _dp = new DataProcessForTraining();
		double[] age;
		double[] distance;
		String type = "age";
		String type2 = "distance";
		int times = 1;
		double[] crossMergedArray = new double[times*100];
		age = _dp.loadData(type, times);
		System.out.println("Now the " + type + " array' length is : " + times
				* 50);
		for (int i = 0; i < times * 50; i++) {
			System.out.println("The " + i + " age is : " + age[i]);	
		}
		System.out.println();

		distance = _dp.loadData(type2, times);
		System.out.println("Now the " + type2 + " array's length is : " + times
				* 50);
		for (int i = 0; i < times * 50; i++) {
			System.out.println("The " + i + " distance is : " + distance[i]);
		}
		System.out.println();

		crossMergedArray = _dp.mergeCrossArrays(age, distance, times);
		for (int i = 0; i < crossMergedArray.length; i++) {
			System.out.println("The " + i + " mergeCrossArrays is : " + crossMergedArray[i]);
		}
		System.out.println();
	}
}
