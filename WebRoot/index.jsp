<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/bootstrap.css" />

<script src="js/jquery-1.7.1.js"></script>
<script src="js/bootstrap.js"></script>
<!--Back to the top-->
<script type="text/javascript">
	$(function() {
		var $backToTopTxt = "", $backToTopEle = $(
				'<div class="backToTop"></div>').appendTo($("body")).text(
				$backToTopTxt).attr("title", $backToTopTxt).click(function() {
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
		<div id="adbox">
			<div class="clearfix">
				<img src="images/m_nn2.jpg" alt="Img" height="382" width="594">
				<div class="detail">
					<p>WELCOME TO GANN PATTERN RECOGNITION SYSTEM</p>
				</div>
			</div>
		</div>
		<div class="highlight">
			<div class="clearfix">

				<h1>Introduction of the system</h1>
				<p>The last few decades have witnessed an increasing number of
					various research on applying data mining techniques to customise
					service to develop the customer relationship. Companies have a
					great demand to gain a competitive advantage through collecting a
					huge amount of data and mining associations from sales records or
					customersâ purchase histories which help reveal consumersâ
					behaviours, preferences and consumption patterns. The main research
					objective is to study, investigate and discover whether the
					distance between a consumerâs home and store, coupled with their
					age will affect their consumptions. Such information can be used to
					generate commercial recommendations that improve the quality of
					customer service, enhances goods consumption ratios, gains
					competitive benefits and establishes long-term and pleasant
					relationships with customers. This paper proposes a new algorithm
					by extracting accurate and apprehensible rules from shopping
					records data sets via trained Artificial Neural Networks (ANNs)
					using Genetic Algorithm. This technique learns from experience and
					facilitates the detection of unknown relationships between a set of
					input data and an outcome. These two algorithms are ubiquitous in
					recent years because they have a proven track record in diverse
					data mining and decision-support applications.</p>
			</div>
		</div>
		<div class="featured">
			<h2>Methodology Comparison</h2>
			<ul class="clearfix">
				<li>
					<div class="frame1">
						<div class="box">
							<img src="images/m_desisionTree.png" alt="Img" height="130"
								width="197">
						</div>
					</div>
					<p>
						<b>Decision Trees</b> A decision tree is a diagram which utilises
						each inner node to represent a test on an attribute. Leaf nodes
						illustrate class labels and each branch denotes a result of the
						test...
					</p>
					<button class="more" data-toggle="modal" data-target="#myModal">READ
						MORE</button>
				<li>
					<div class="frame1">
						<div class="box">
							<img src="images/m_kNearest.png" alt="Img" height="130"
								width="197">
						</div>
					</div>
					<p>
						<b>k-Nearest Neighbour (KNN) </b> In pattern recognition, the
						k-nearest neighbor algorithm is a non parametric lazy learning
						algorithm, based on learning by analogy, which is designed for
						classification...
					</p> <a href="http://www.dkriesel.com/en/science/neural_networks"
					class="more">Read More</a>
				</li>
				<li>
					<div class="frame1">
						<div class="box">
							<img src="images/m_nns.png" alt="Img" height="130" width="197">
						</div>
					</div>
					<p>
						<b>Artificial Neural Networks (ANNs)</b> JArtificial neural
						networks are a type computational methodology extensively used for
						pattern recognition and data classification...
					</p> <a href="http://arxiv.org/ftp/cs/papers/0308/0308031.pdf"
					class="more">Read More</a>
				</li>
				<li>
					<div class="frame1">
						<div class="box">
							<img src="images/m_ga.jpg" alt="Img" height="130" width="197">
						</div>
					</div>
					<p>
						<b>Genetic Algorithm (GA)</b> A genetic algorithm (GA) is a
						heuristic which is routinely used to optimise the solution. It
						belongs to evolutionary algorithms (EA). A metaphor quoted by
						Westphal & Blaxton (1998):
					</p> <a href="http://www.obitko.com/tutorials/genetic-algorithms/"
					class="more">Read More</a>
				</li>
			</ul>
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
					<h4 class="modal-title" id="myModalLabel">Decision Trees</h4>
				</div>
				<div class="modal-body" style="height: 500px; width: 500px;">
					In pattern recognition, the k-nearest neighbor algorithm is a non
					parametric lazy learning algorithm, based on learning by analogy,
					which is designed for classification. The k-nearest neighbor
					algorithm is also the simplest of all machine learning algorithms.
					Non-parametric means that there is no need to make any hypotheses
					on the underlying data distribution and it is very convenient
					practically because most of the real-world data do not accord with
					the theoretical assumptions made. Besides it, it is also a “lazy”
					algorithm. In other words, it does not use the training data points
					to do any generalization. <br />
					<br />To be more concise, KNN keeps the entire training data set
					and the training process efficient. However, except being sensitive
					to irrelevant or redundant features because all features contribute
					to the similarity and thus to the classiﬁcation, it still suffers
					from some other limitations. It can have poor performance at
					runtime if the dataset is large. So KNN might be overwhelmed by
					some latest techniques such as Neural Networks or Support Vector
					Machine when deals with challenging classification missions. <br />
					<br />Despite its flaws, k-means are still widely used in
					clustering in practice (Wu, et al., 2008). This algorithm is
					simple, intuitive, easily understandable, scalable and easily
					adapted to process streaming data. In terms of large datasets,
					appropriate measures can be taken by using k-dimensional trees or
					exploiting the trigonal disparity to avoid the comparisons among
					each data point with all the centroids during the processing.

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="footer.jsp" />



</body>
</html>