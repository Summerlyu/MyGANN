<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link rel="stylesheet" href="css/bootstrap.css" />
<script src="js/jquery-1.7.1.js"></script>
<script src="js/bootstrap.js"></script>

<script type="text/javascript">
	function SolidCircle(centreX, centreY, radius, precision, color) {
		var cx = Math.abs(parseInt(centreX));
		var cy = Math.abs(parseInt(centreY));
		var r = parseInt(radius < 2 ? 60 : radius);
		var p = parseInt(precision < 1 ? 1 : precision);
		var c = color;

		var y;
		for ( var x = cx - r; x <= cx + r; x += p) {
			y = cy - Math.sqrt(Math.pow(r, 2) - Math.pow(cx - x, 2));
			document.write('<img style="background:'
					+ c
					+ '; border:1 solid '
					+ c
					+ '; width:'
					+ p
					+ '; height:'
					+ parseInt(2 * Math.sqrt(Math.pow(r, 2)
							- Math.pow(cx - x, 2)))
					+ '; position:absolute; top:' + parseInt(y) + '; left:'
					+ parseInt(x) + ';">');
		}
	}
</script>

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
</head>

<body>

	<jsp:include page="header.jsp" />

	<div id="contents">
		<div class="clearfix">
			<div class="main">
				<h1>Network Configuration</h1>
				<p>In this section, you can configure your network parameters
					because this system is dynamic. It can deal with different model and
					different patterns.</p>
				<form action="index.jsp" method="post" class="message">
					<label>INPUT NUMBER (Green): </label> <input type="text" value="">
					<label> HIDDEN NETWORK(Yellow): </label> <input type="text"
						value=""> <label> OUTPUT NUMBER(Red):</label> <input
						type="text" value=""> <label>COMMENT</label>
					<textarea></textarea>
					<!-- Button trigger modal -->
					<button class="more" data-toggle="modal"
						data-target="#myModal">View Model</button>
				</form>
			</div>

		</div>


	</div>




	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">Network Architecture</h4>
				</div>
				<div class="modal-body" style="height: 500px; width: 500px;">
					<script language="javascript">
						SolidCircle(150, 150, 24, 1, "green");
						SolidCircle(150, 250, 24, 1, "green");
	
						SolidCircle(300, 100, 24, 1, "yellow");
						SolidCircle(300, 200, 24, 1, "yellow");
						SolidCircle(300, 300, 24, 1, "yellow");

						SolidCircle(450, 200, 24, 1, "red");
					</script>
					
					<svg height="400" width="480"> 
					<line x1="160" y1="140" x2="288" y2="86" style="stroke:rgb(0,0,0);stroke-width:2" />
					<line x1="160" y1="140" x2="288" y2="186" style="stroke:rgb(0,0,0);stroke-width:2" />
					<line x1="160" y1="140" x2="288" y2="286" style="stroke:rgb(0,0,0);stroke-width:2" />
					
					
					<line x1="160" y1="240" x2="288" y2="86" style="stroke:rgb(0,0,0);stroke-width:2" />
					<line x1="160" y1="240" x2="288" y2="186" style="stroke:rgb(0,0,0);stroke-width:2" />
					<line x1="160" y1="240" x2="288" y2="286" style="stroke:rgb(0,0,0);stroke-width:2" />
				
					
					<line x1="288" y1="86" x2="420" y2="186" style="stroke:rgb(0,0,0);stroke-width:2" />
					<line x1="288" y1="186" x2="420" y2="186" style="stroke:rgb(0,0,0);stroke-width:2" />
					<line x1="288" y1="286" x2="420" y2="186" style="stroke:rgb(0,0,0);stroke-width:2" />
						 </svg>
					

				</div>
				<div class="modal-footer">
					<button type="button" class="more" data-dismiss="modal">Confirm</button>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="footer.jsp" />
	
</body>
</html>
