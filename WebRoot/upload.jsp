<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="content-type"
	content="application/xhtml+xml; charset=utf-8" />
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="resources/uploadify.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="resources/swfobject.js"></script>
<script type="text/javascript"
	src="resources/jquery.uploadify.v2.0.3.min.js"></script>
<script type="text/javascript">
	function uploadifyUpload() {
		var category = $('#category').val();
		if (category == "0") {
			alert("Please choose the category of data!");
		} else {
			$('#uploadify').uploadifySettings('scriptData', {
				'category' : category
			});
			$('#uploadify').uploadifyUpload();
		}

	}
</script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#uploadify").uploadify({
			'uploader' : 'resources/uploadify.swf',
			'script' : 'servlet/fileUpload',
			'cancelImg' : 'resources/cancel.png',
			'queueID' : 'fileQueue',
			'auto' : false,
			'multi' : true,
			'buttonText' : 'Browse file',

			onComplete : function(evt, queueID, fileObj, response, data) {
				$('<li></li>').appendTo('.files').text(response);
			},
			onError : function(a, b, c, d) {
				alert("Sorry, the uploading is unsuccessfu!");
			}
		});

	});
</script>
</head>
<body>
	<jsp:include page="header.jsp" />
	<div id="contents">
		<div class="clearfix" style="margin-right: 40px;">
			<h1>Import data</h1>
			<br/>
			<p>In this section, you can import inputs and targets.</p>
			
			<br/>
			
			<br/>
			CATAGORY: <select name="category" id="category">
				<option value="0">---Choose the type---</option>
				<option value="inputs">Inputs</option>
				<option value="outputs">Outputs</option>

			</select> <br />
			<br />
			<br /> <input type="file" name="uploadify"
				id="uploadify" />

			<div id="fileQueue"></div>
			<br />
			<br />
			<br />
			<p>
				<a href="javascript:;" onClick="javascript:uploadifyUpload()">Start
					uploading</a>&nbsp; <a
					href="javascript:jQuery('#uploadify').uploadifyClearQueue()">Cancel
					all the upload</a>
			</p>
			<p></p>
			<ol class=files></ol>

		</div>
	</div>



	<jsp:include page="footer.jsp" />
</body>
</html>