package uk.ac.gann.test;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import uk.ac.gann.dataprocess.ScallingFunction;

public class ScallingFunctionTest {

	@Before
	public void setUp() throws Exception {
	}
	@After
	public void tearDown() throws Exception {
	}
	@Test
	public void testScaleDownDataSetFunction() {
		new ScallingFunction();
		double[] oldArray = { -10, -9, -8, -7, -6, -5, -4, -3, -2, -1, 0, 1, 2,
				3, 4, 5, 6, 7, 8, 9, 10 };
		double[] newArray = null;
		double newMax = 1;
		double newMin = -1;

		newArray = ScallingFunction.scaleDownDataSetFunction(oldArray, newMax, newMin);
		assertEquals(oldArray.length, newArray.length);
	}
}
