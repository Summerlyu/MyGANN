package uk.ac.gann.action;

import java.io.IOException;
import java.sql.SQLException;

import uk.ac.gann.ai.GANNSEngine;

import com.opensymphony.xwork2.ActionSupport;

public class TrainAction extends ActionSupport {

	private int flg;

	public void trainingControl() throws SQLException, IOException {
		if (flg == 1) {
			GANNSEngine gANNSEngine = new GANNSEngine();
			gANNSEngine.myEngine();
		}
	}

	public int getFlg() {
		return flg;
	}

	public void setFlg(int flg) {
		this.flg = flg;
	}

}
