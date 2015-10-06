package uk.ac.gann.test;

import static org.junit.Assert.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import uk.ac.gann.dao.LoadTrainSuccessRates;

public class LoadTrainSuccessRatesTest {

	@Before
	public void setUp() throws Exception {
	}
	@After
	public void tearDown() throws Exception {
	}
	@Test
	public void testLoadSuccessRate() throws SQLException {
		List<Double> amountTrainedSuccessRate = new ArrayList<Double>();
		
		LoadTrainSuccessRates loadTrainSuccessRates = new LoadTrainSuccessRates();
		amountTrainedSuccessRate = loadTrainSuccessRates.loadSuccessRate("trainedAmount");
	
		assertTrue(amountTrainedSuccessRate.size()!=0);	
	}
}
