package uk.ac.gann.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import uk.ac.gann.dao.LoadTrainSuccessRates;

public class LoadSuccessRateTest {

	public static void main(String args[]) throws SQLException {
		List<Double> amountTrainedSuccessRate = new ArrayList<Double>();
		List<Double> moneyTrainedSuccessRate = new ArrayList<Double>();
		List<Double> amountTestSuccessRate = new ArrayList<Double>();
		List<Double> moneyTestSuccessRate = new ArrayList<Double>();
		
		LoadTrainSuccessRates loadTrainSuccessRates = new LoadTrainSuccessRates();
		amountTrainedSuccessRate = loadTrainSuccessRates.loadSuccessRate("trainedAmount");
		
		for(int i=0; i<amountTrainedSuccessRate.size();i++){
			System.out.println("The "+ i + " amount trained success rate is:  "+amountTrainedSuccessRate.get(i));
		}
		
		moneyTrainedSuccessRate = loadTrainSuccessRates.loadSuccessRate("trainedMoney"); 
		for(int i=0; i<moneyTrainedSuccessRate.size();i++){
			System.out.println("The "+ i + " money trained success rate is:  "+moneyTrainedSuccessRate.get(i));
		}
	}
}
