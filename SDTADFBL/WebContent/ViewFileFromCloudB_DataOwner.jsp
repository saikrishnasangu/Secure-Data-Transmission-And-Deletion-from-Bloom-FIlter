<!DOCTYPE html>
<%@page import="util.Bean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.ViewDAO"%>
<html lang="en">
<head>
	<title>Upload</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<link rel="stylesheet" href="css/font-awesome.min.css">
	<link rel="stylesheet" href="css/fontFamily.css">
	<link rel="stylesheet" href="css/fontFamily-1.css">

	<link rel="stylesheet" href="css/animate.css">
	
	<link rel="stylesheet" href="css/owl.carousel.min.css">
	<link rel="stylesheet" href="css/owl.theme.default.min.css">
	<link rel="stylesheet" href="css/magnific-popup.css">

	
	<link rel="stylesheet" href="css/flaticon.css">
	<link rel="stylesheet" href="css/style.css">
</head>
<body>
<%int fid =Integer.parseInt(request.getParameter("fid"));
ArrayList<Bean> al = new ViewDAO().dataOwnerViewFileFromCloudB(fid);
%>
	<div id="colorlib-page">
		<a href="#" class="js-colorlib-nav-toggle colorlib-nav-toggle"><i></i></a>
		<jsp:include page="DataOwnerMenu.jsp"></jsp:include>
		 <!-- END COLORLIB-ASIDE -->
			<div id="colorlib-main" style="margin-top: 0%;margin-left: 10%;">
				<section class="ftco-section ftco-no-pt ftco-no-pb">
					<div class="container px-md-0">
						<div class="row d-flex no-gutters">
							<div class="col-lg-8 col-md-7 order-md-last d-flex align-items-stretch">
								<div class="contact-wrap w-100 p-md-5 p-4">
								
								<%if(!al.isEmpty()){ %>
									<h3 class="mb-4 heading">Your File</h3>
										<div class="row">
											<div class="col-md-12">
												<div class="form-group">
												<% int i = 0;
                       							 for(Bean b:al){
                        						i++;
                        						session.setAttribute(i + "img", b.getFile());
                        						%>
                            <img src="image.jsp?val=<%=i %>" class="tm-service-img" width="400" height="400">
                          <%} %>
												</div>
												
											</div>
										</div>
										<%} %>
								</div>
							</div>
							
						</div>
					</div>
				</section>
			</div><!-- END COLORLIB-MAIN -->
		</div><!-- END COLORLIB-PAGE -->

		<!-- loader -->
		


		<script src="js/jquery.min.js"></script>
		<script src="js/jquery-migrate-3.0.1.min.js"></script>
		<script src="js/popper.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script src="js/jquery.easing.1.3.js"></script>
		<script src="js/jquery.waypoints.min.js"></script>
		<script src="js/jquery.stellar.min.js"></script>
		<script src="js/owl.carousel.min.js"></script>
		<script src="js/jquery.magnific-popup.min.js"></script>
		<script src="js/jquery.animateNumber.min.js"></script>
		<script src="js/scrollax.min.js"></script>
		<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
		<script src="js/google-map.js"></script>
		<script src="js/main.js"></script>
		
	</body>
	</html>