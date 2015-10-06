package uk.ac.gann.test;

import static org.junit.Assert.*;
import java.sql.SQLException;
import org.junit.Before;
import org.junit.Test;

import uk.ac.gann.ai.WeightsGenerator;

public class WeightsGeneratorTest {

	@Before
	public void setUp() throws Exception {
	}
	@Test
	public void testCreateWeights() throws SQLException {
		WeightsGenerator wg = new WeightsGenerator();
		int weightsNumber = 5;
		double[] weights = null;
		weights = wg.createWeights(weightsNumber);
		assertTrue(weights.length==weightsNumber);
	}
}
