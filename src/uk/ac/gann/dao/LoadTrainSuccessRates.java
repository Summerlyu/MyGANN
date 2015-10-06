package uk.ac.gann.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import uk.ac.gann.utils.DBConnectionJdbc;

public class LoadTrainSuccessRates {
	List<Double> amountTrainedSuccessRate = new ArrayList<Double>();
	List<Double> moneyTrainedSuccessRate = new ArrayList<Double>();
	List<Double> amountTestSuccessRate = new ArrayList<Double>();
	List<Double> moneyTestSuccessRate = new ArrayList<Double>();

	// Loading success rate according to the type of data 
	public List<Double> loadSuccessRate(String type) throws SQLException {
		String sql_amountTrain = "select TrainSuccessRateAmount.success_rate from TrainSuccessRateAmount";
		String sql_moneyTrain = "select TrainSuccessRateMoney.success_rate from TrainSuccessRateMoney";
		String sql_amountTest = "select TestSuccessRateAmount.success_rate from TestSuccessRateAmount";
		String sql_moneyTest = "select TestSuccessRateMoney.success_rate from TestSuccessRateMoney";
		
		if (type == "trainedAmount") {
			amountTrainedSuccessRate = executeSql(sql_amountTrain);
			return amountTrainedSuccessRate;
		} else if (type == "trainedMoney") {
			moneyTrainedSuccessRate = executeSql(sql_moneyTrain);
			return moneyTrainedSuccessRate;
		} else if (type == "testAmount") {
			amountTestSuccessRate = executeSql(sql_amountTest);
			return amountTestSuccessRate;
		} else if (type == "testMoney") {
			moneyTestSuccessRate = executeSql(sql_moneyTest);
			return moneyTestSuccessRate;
		}
		return null;
	}

	private List<Double> executeSql(String sql) throws SQLException {
		PreparedStatement pstmt = null;
		List<Double> list = new ArrayList<Double>();
		pstmt = DBConnectionJdbc.getCon().prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {// Add data into list
			list.add(Double.parseDouble(rs.getString(1)));
		}
		return list;
	}
}
