package uk.ac.gann.dataprocess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import uk.ac.gann.utils.DBConnectionJdbc;

public class DataProcessForTraining {

	// Private data arrays initialized
	private double[] age = null;
	private double[] distance = null;
	private double[] totalAmount = null;
	private double[] totalMoney = null;
	private double[] array = null;
	
	// Loading data according to the type of data and the mount of data you need
	public double[] loadData(String type, int times) throws SQLException {
		// SQL strings
		String sql_age = "select SaleRecordTrainNew.age from SaleRecordTrainNew";
		String sql_distance = "select SaleRecordTrainNew.distance from SaleRecordTrainNew";
		String sql_totalAmount = "select SaleRecordTrainNew.totalAmount from SaleRecordTrainNew";
		String sql_totalMoney = "select SaleRecordTrainNew.totalMoney from SaleRecordTrainNew";

		// Decide the size of each array
		age = new double[times * 50];
		distance = new double[times * 50];
		totalAmount = new double[times * 50];
		totalMoney = new double[times * 50];
		array = new double[times * 50];

		if (type == "age") {
			for (int i = 0; i < times * 50; i++) {
				age[i] = executeSql(sql_age)[i];
			}
			array = age;
		} else if (type == "distance") {
			for (int i = 0; i < times * 50; i++) {
				distance[i] = executeSql(sql_distance)[i];
			}
			array = distance;
		} else if (type == "totalAmount") {
			for (int i = 0; i < times * 50; i++) {
				totalAmount[i] = executeSql(sql_totalAmount)[i];
			}
			array = totalAmount;
		} else if (type == "totalMoney") {
			for (int i = 0; i < times * 50; i++) {
				totalMoney[i] = executeSql(sql_totalMoney)[i];
			}
			array = totalMoney;
		}
		return array;
	}

	// SQL execution
	private double[] executeSql(String sql) throws SQLException {
		PreparedStatement pstmt = null;
		List<String> list = new ArrayList<String>();// Using list instead of
													// array is we don't know
													// the length of data
		pstmt = DBConnectionJdbc.getCon().prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {// Add data into list
			list.add(rs.getString(1));
		}
		double[] array = new double[list.size()];

		if (list != null && list.size() > 0) {

			for (int i = 0; i < list.size(); i++) {
				array[i] = Double.parseDouble(list.get(i));// Copy data from
															// list into array
			}
		}
		return array;
	}

	// Merge age and distance into one array as inputs of Neural network
	public double[] mergeCrossArrays(double[] age, double[] distance, int times) {
		double[] input = new double[100 * times];
		int j = 0, z = 0;
		for (int i = 0; i < 50 * times; i++) {
			input[z] = age[j];
			z++;
			input[z] = distance[j];
			//System.out.println("The " + z + " mergedInput is:" + input[z]);
			z++;
			j++;

		}

		return input;
	}

	public double[] reOrderArrayAsInterleave(double[] _input, int _groupSize) {
		int _numberOfGroups = _input.length / _groupSize;
		double[] _reorderedArray = new double[_input.length];
		int _index = 0;
		int _index2 = 0;
		int _bp = 0;
		for (int j = 0; j < _numberOfGroups; j++) {
			for (int i = 0; i < _groupSize; i++){
				_reorderedArray[_index] = _input[_bp + _index2];
				_index++;
				_bp += _numberOfGroups;
				
				if (_bp == _input.length - _numberOfGroups)
					_bp = 0;
			}
			_index2++;
		}
		return _reorderedArray;
	}

	// Merge hidden weights and output weights into one weights for crossover
	// and mutation
	public double[] mergeArrays(double[] array1, double[] array2) {
		double[] array = new double[array1.length + array2.length];
		for (int i = 0; i < array1.length; i++) {
			array[i] = array1[i];
		}
		for (int i = array1.length, j = 0; i < array1.length + array2.length; i++, j++) {
			array[i] = array2[j];
		}
		return array;
	}

	// unmerge the array
	public List<double[]> unmergeArray(double[] array, int array1Length, int array2Length ){
		List<double[]> twoArray = new ArrayList<double[]>();
		double[] array1 = new double[array1Length];
		double[] array2 = new double[array2Length];
		for(int i=0;i<array1Length;i++){
			array1[i] = array[i];
		}
		for(int i=0,j=array1Length;i<array2Length;i++,j++){
			array2[i] = array[j];
		}
		twoArray.add(array1);
		twoArray.add(array2);
		
		return twoArray;
	}
	
	// Search the total rows of data
	public int theTotalLine() throws SQLException{
		int totalLine = 0;
		PreparedStatement pstmt = null;
		String sql = null;
		
		// sql string
		String sql_totalLine = "select count(*) from SaleRecordTrainNew";
		pstmt = DBConnectionJdbc.getCon().prepareStatement(sql_totalLine);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			totalLine = rs.getInt(1);
		}

		return totalLine;
	}
	

	
	public boolean saveTrainSuccessRate(String type, int times, double success_rate){
		PreparedStatement pstmt=null;
		String sql = "insert into dbo.TrainSuccessRateAmount(_type, _time, success_rate) values(?,?,?)";
		String sql2 = "insert into dbo.TrainSuccessRateMoney(_type, _time, success_rate) values(?,?,?)";
		try {
			if(type.equals("totalAmount")){
				pstmt = DBConnectionJdbc.getCon().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			}else{
				pstmt = DBConnectionJdbc.getCon().prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);
			}
			pstmt.setString(1, type);
			pstmt.setInt(2, times);
			pstmt.setDouble(3, success_rate);
			
			int result = pstmt.executeUpdate();
			if (result >= 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnectionJdbc.endConnection();
		}
		return false;
	}
}
