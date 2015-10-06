<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">


<script type="text/javascript" src="js/jquery-1.7.1.js"></script>
<script type='text/javascript' src='js/jquery-1.3.2.min.js'></script>
<script type="text/javascript" src="js/highcharts.js"></script>
<script type="text/javascript" src="js/modules/exporting.js"></script>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<script type="text/javascript">
	function startTraining() {
		if (confirm("Are you sure to start training?")) {
			alert("The training is being processed now. It may take a long time. Please wait...");
		}
	}

	function stopTraining() {
		if (confirm("Are you sure to stop training?")) {
			alert("The training is stopped.");
		}
	}
</script>

<script type="text/javascript">
	var chart;
	$(document)
			.ready(
					function() {
						chart = new Highcharts.Chart(
								{
									credits : {

										href : '',
										text : ''

									},
									chart : {
										renderTo : 'container',
										defaultSeriesType : 'spline',
										zoomType : 'x'

									},
									title : {
										text : 'The initial success rate for two sets of training data'
									},
									subtitle : {
										text : '${title}'
									},
									xAxis : {

										categories : [
<%=request.getSession().getAttribute("xdata")%>
	],

										labels : {
											step :
<%=request.getSession().getAttribute("stepnum")%>
	}

									},
									yAxis : {
										min : 0,

										title : {
											text : 'SuccessRate'
										}
									},
									tooltip : {
										enabled : true,
										formatter : function() {
											return 'Rate:' + this.y + "<br/>"
													+ this.x;
										}
									},
									plotOptions : {
										line : {
											dataLabels : {
												enabled : false
											},
											enableMouseTracking : true
										}
									},
									series : [ {
										name : '${linename}',

										data : [
<%=request.getSession().getAttribute("ydata")%>
	]
									} ]
								});

					});
</script>

<script>
	var objXmlHttp = null;
	function CreateXMLHTTP() {
		if (window.ActiveXObject) {
			objXmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		} else {
			if (window.XMLHttpRequest) {
				objXmlHttp = new XMLHttpRequest();
			} else {
				alert("new is error!");
			}
		}
	}

	function GetSendData() {
		var strSendUrl = "LineAction!LineData.action?&flg=3";
		CreateXMLHTTP();
		objXmlHttp.open("POST", strSendUrl, true);

		objXmlHttp.onreadystatechange = function() {
			if (objXmlHttp.readyState == 4) {
				if (objXmlHttp.status == 200 || objXmlHttp.status == 0) {
					var list = eval("(" + objXmlHttp.responseText + ")");

					$(document)
							.ready(
									function() {
										chart = new Highcharts.Chart(
												{
													credits : {

														href : '',
														text : ''

													},
													chart : {
														renderTo : 'container',
														defaultSeriesType : 'line',
														zoomType : 'x'

													},
													title : {
														text : 'The success rate for two sets of training data'
													},
													subtitle : {
														text : '${title}'
													},
													xAxis : {

														categories : list.listXdata,

														labels : {
															step : list.listStepNum
														},
														title : {
															text : 'Each 100 rows '
														}

													},
													yAxis : {
														min : 70,
														max : 90,
														lineWidth : 1,

														title : {
															text : 'SuccessRate'
														}
													},
													tooltip : {
														enabled : true,
														formatter : function() {
															return 'Rate:'
																	+ this.y
																	+ "<br/>"
																	+ this.x;
														}
													},
													plotOptions : {
														line : {
															dataLabels : {
																enabled : false
															},
															enableMouseTracking : true
														}
													},
													legend : {
														layout : 'vertical',
														align : 'right',
														verticalAlign : 'middle',
														borderWidth : 0
													},
													series : [
															{
																name : 'TrainAmount',

																data : list.listYTrainAmount
															},
															{
																name : 'TrainMoney',

																data : list.listYTrainMoney
															} ]
												});

									});
				}
			}
		};
		objXmlHttp.send(null);
	}

	setInterval("GetSendData()", 10000);
</script>

</head>

<body>
	<jsp:include page="header.jsp" />
	<div id="contents">
		<div class="clearfix">
			<div class="main">
				<h1>Network Training</h1>
				<p>In this section, you can start training or stop training your
					network.</p>
				<form action="TrainAction!trainingControl.action?&flg=1"
					method="post" class="message">
					<input type="submit" value="Start Training" onClick="startTraining();">
				</form>
				<form action="trainLine.jsp"
					method="post" class="message">
					<input type="submit" value="Stop Training" onClick="stopTraining();">
				</form>
				<br /> <br /> <br />
				<form>
					<div id="container"
						style="width: 1000px; height: 500px; margin: 0 auto;"></div>

				</form>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp" />

	<!--Back to the top-->
	<script type="text/javascript">
		$(function() {
			var $backToTopTxt = "", $backToTopEle = $(
					'<div class="backToTop"></div>').appendTo($("body")).text(
					$backToTopTxt).attr("title", $backToTopTxt).click(
					function() {
						$("html, body").animate({
							scrollTop : 0
						}, 120);
					}), $backToTopFun = function() {
				var st = $(document).scrollTop(), winh = $(window).height();
				(st > 0) ? $backToTopEle.show() : $backToTopEle.hide();
			};
			$(window).bind("scroll", $backToTopFun);
			$(function() {
				$backToTopFun();
			});
		});
	</script>
</body>
</html>
