<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<jsp:include page="hd.jsp"></jsp:include>
<script type="text/javascript" src="js/jquery.flexslider-min.js"></script>
<script type="text/javascript" charset="utf-8">
	var $ = jQuery.noConflict();
	$(window).load(function() {
		$('.flexslider').flexslider({
			animation : "slide"
		});
	});
</script>
</head>
<body>
	<jsp:include page="main_header.jsp"></jsp:include>
	<div id="wrap">
		<div class="main_content_top">
			<div class="slider">
				<h2>Recent activities:</h2>
				<div class="flexslider">
					<ul class="slides">
						<li><a href="page.jsp"><img
								src="images/slider-image1.jpg" alt="" title="" border="0" /></a>
							<div class="flex-caption">
								<h2>My first champion</h2>
								<p>See this is the first champion as a test.</p>
							</div></li>
						<li><a href="page.jsp"><img
								src="images/slider-image2.jpg" alt="" title="" border="0" /></a>
							<div class="flex-caption">
								<h2>My first champion</h2>
								<p>See this is the first champion as a test.</p>
							</div></li>
						<li><a href="page.jsp"><img
								src="images/slider-image3.jpg" alt="" title="" border="0" /></a>
							<div class="flex-caption">
								<h2>My first champion</h2>
								<p>See this is the first champion as a test.</p>
							</div></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="main_content">
			<div class="section_one_three">
				<div class='section_wrapper'>
					<img src="images/career.png" alt="" height="200px" align="middle" />
				</div>
				<h2 class="centered_title">Career</h2>
				<p class="centered_text">My career, technical, management, self
					build up.</p>
			</div>
			<div class="section_one_three">
				<div class='section_wrapper'>
					<img src="images/life.png" alt="" height="200px" align="middle" />
				</div>
				<h2 class="centered_title">Life</h2>
				<p class="centered_text">Life related, traveling, reading,
					entertainment, films etc.</p>
			</div>
			<div class="section_one_three">
				<div class='section_wrapper'>
					<img src="images/contact.png" alt="" height="200px" align="middle" />
				</div>
				<h2 class="centered_title">Contact</h2>
				<p class="centered_text">Contact me through email, weibo, qq and
					others.</p>
			</div>
			<div class="clear"></div>
		</div>
		<div class="clear"></div>
	</div>
	<jsp:include page="main_footer.jsp"></jsp:include>
</body>
</html>