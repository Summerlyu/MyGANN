package uk.ac.gann.test;

import java.io.IOException;
import java.sql.SQLException;
import uk.ac.gann.ai.GANNSEngine;

public class GANNSEngineTest{

	public static void main(String args[]) throws SQLException, IOException {
		GANNSEngine gANNSEngine = new GANNSEngine();
		gANNSEngine.myEngine();
	}
}
