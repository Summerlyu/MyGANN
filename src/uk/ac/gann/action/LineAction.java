package uk.ac.gann.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import uk.ac.gann.dao.LoadTrainSuccessRates;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LineAction extends ActionSupport {
	private String lineTrainAmount, lineTrainMoney, lineTestAmount,
			lineTestMoney;
	private String title;
	private int stepnum;
	private String linename;
	private String xdata;
	private String ydata;
	// Json data
	private List listXdata;
	private List listYTrainAmount, listYTrainMoney, listYTestAmount,
			listYTestMoney;
	private List listStepNum;
	private int flg;

	List<Double> amountTrainedSuccessRate = new ArrayList<Double>();
	List<Double> moneyTrainedSuccessRate = new ArrayList<Double>();
	List<Double> amountTestSuccessRate = new ArrayList<Double>();
	List<Double> moneyTestSuccessRate = new ArrayList<Double>();

	public String LineData() throws IOException, SQLException {
		// the name of the line
		linename = "SuccessRate";
		// the name of the title
		title = "The success rate for two set of training and testing data.";
		// each increment(x)
		stepnum = 10;
		// x axis
		xdata = "0,10,20,30,40,50,60,70,80,90";
		// y axis
		ydata = "0,0,0,0,0,0,0,0,0,0";

		ActionContext.getContext().getSession().put("linename", linename);
		ActionContext.getContext().getSession().put("stepnum", stepnum);
		ActionContext.getContext().getSession().put("xdata", xdata);
		ActionContext.getContext().getSession().put("ydata", ydata);

		// the name of the line
		lineTrainAmount = "Trained_Amount";
		lineTrainMoney = "Trained_Money";
		lineTestAmount = "Test_Amount";
		lineTestMoney = "Test_Money";

		LoadTrainSuccessRates loadTrainSuccessRates = new LoadTrainSuccessRates();
		amountTrainedSuccessRate = loadTrainSuccessRates
				.loadSuccessRate("trainedAmount");
		moneyTrainedSuccessRate = loadTrainSuccessRates
				.loadSuccessRate("trainedMoney");
		amountTestSuccessRate = loadTrainSuccessRates
				.loadSuccessRate("testAmount");
		moneyTestSuccessRate = loadTrainSuccessRates
				.loadSuccessRate("testMoney");

		if (flg == 3) {
			listYTrainAmount = new ArrayList();
			listYTrainMoney = new ArrayList();
			listYTestAmount = new ArrayList();
			listYTestMoney = new ArrayList();

			listXdata = new ArrayList();
			listStepNum = new ArrayList();
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("application/json;charset=utf-8");
			response.setCharacterEncoding("utf-8");
			PrintWriter out;

			for (int i = 0; i < 100; i++) {
				listYTrainAmount.add(amountTrainedSuccessRate.get(i));
				listYTrainMoney.add(moneyTrainedSuccessRate.get(i));
				listYTestAmount.add(amountTestSuccessRate.get(i));
				listYTestMoney.add(moneyTestSuccessRate.get(i));
			}
			for (int i = 0; i < 100; i = i + 10) {
				listXdata.add(i);
			}
			listStepNum.add(10);

			JSONObject jsonData = new JSONObject();

			// data of y axis
			jsonData.put("listYTrainAmount", listYTrainAmount);
			jsonData.put("listYTrainMoney", listYTrainMoney);
			jsonData.put("listYTestAmount", listYTestAmount);
			jsonData.put("listYTestMoney", listYTestMoney);

			// data of x axis
			jsonData.put("listXdata", listXdata);

			// the increment of x axis
			jsonData.put("listStepNum", listStepNum);

			out = response.getWriter();

			//System.out.println(jsonData);
			out.println(jsonData);
			out.flush();
			out.close();
			return "dataLine";
		}
		return "dataLine";
	}

	public String getXdata() {
		return xdata;
	}
	public void setXdata(String xdata) {
		this.xdata = xdata;
	}
	public String getYdata() {
		return ydata;
	}
	public void setYdata(String ydata) {
		this.ydata = ydata;
	}
	public int getStepnum() {
		return stepnum;
	}
	public void setStepnum(int stepnum) {
		this.stepnum = stepnum;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLinename() {
		return lineTrainAmount;
	}
	public void setLinename(String linename) {
		this.lineTrainAmount = linename;
	}
	public List getListXdata() {
		return listXdata;
	}
	public void setListXdata(List listXdata) {
		this.listXdata = listXdata;
	}
	public List getListYdata() {
		return listYTrainAmount;
	}
	public void setListYdata(List listYdata) {
		this.listYTrainAmount = listYdata;
	}
	public List getListStepNum() {
		return listStepNum;
	}
	public void setListStepNum(List listStepNum) {
		this.listStepNum = listStepNum;
	}
	public String getLineTrainAmount() {
		return lineTrainAmount;
	}
	public void setLineTrainAmount(String lineTrainAmount) {
		this.lineTrainAmount = lineTrainAmount;
	}
	public String getLineTrainMoney() {
		return lineTrainMoney;
	}
	public void setLineTrainMoney(String lineTrainMoney) {
		this.lineTrainMoney = lineTrainMoney;
	}
	public String getLineTestAmount() {
		return lineTestAmount;
	}
	public void setLineTestAmount(String lineTestAmount) {
		this.lineTestAmount = lineTestAmount;
	}
	public String getLineTestMoney() {
		return lineTestMoney;
	}
	public void setLineTestMoney(String lineTestMoney) {
		this.lineTestMoney = lineTestMoney;
	}
	public List getListYTrainAmount() {
		return listYTrainAmount;
	}
	public void setListYTrainAmount(List listYTrainAmount) {
		this.listYTrainAmount = listYTrainAmount;
	}
	public List getListYTrainMoney() {
		return listYTrainMoney;
	}
	public void setListYTrainMoney(List listYTrainMoney) {
		this.listYTrainMoney = listYTrainMoney;
	}
	public List getListYTestAmount() {
		return listYTestAmount;
	}
	public void setListYTestAmount(List listYTestAmount) {
		this.listYTestAmount = listYTestAmount;
	}
	public List getListYTestMoney() {
		return listYTestMoney;
	}
	public void setListYTestMoney(List listYTestMoney) {
		this.listYTestMoney = listYTestMoney;
	}
	public int getFlg() {
		return flg;
	}
	public void setFlg(int flg) {
		this.flg = flg;
	}
}
