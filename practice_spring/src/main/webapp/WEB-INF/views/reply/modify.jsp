<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- jstl-1.2.jar 파일 필요 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title></title>


<!-- Bootstrap core CSS -->
<link href="/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">



<!-- Custom styles for this template -->
<link href="/resources/css/simple-sidebar.css" rel="stylesheet">

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">

<style>
select {
	padding: 2px 4px;
	border: 1px solid transparent;
	border-color: #d7dde2;
	margin-left: 20px;
	text-align: center;
	vertical-align: middle;
	cursor: pointer;
	font-family: Consolas;
}

form#searchForm {
	margin-top: 20px;
	float: right;
}

input {
	border: 0px solid transparent;
	width : 400px;
}

textarea{
border: 0px solid transparent;
}

div.content-body {
	height: 800px;
	margin: 10px 10px 10px 10px;
}
</style>

<!-- Google Map API -->
<script src="https://maps.googleapis.com/maps/api/js"></script>

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script type="text/javascript" src="/resources/js/reply.js"></script>
<script>
	$(document).ready(function() {

		var form = $("#newB");
		$("button").on("click",function(e){
			
			form.submit();
			
		});
	


						


					});
</script>
</head>
<body>


	<div class="d-flex" id="wrapper">

		<!-- Sidebar -->
		<div class="bg-light border-right" id="sidebar-wrapper">
			<div class="sidebar-heading">Start Bootstrap</div>
			<div class="list-group list-group-flush">
				<a href="#" class="list-group-item list-group-item-action bg-light">Dashboard</a> <a href="#" class="list-group-item list-group-item-action bg-light">Shortcuts</a> <a href="#" class="list-group-item list-group-item-action bg-light">Overview</a> <a href="#" class="list-group-item list-group-item-action bg-light">Events</a> <a href="#" class="list-group-item list-group-item-action bg-light">Profile</a> <a href="#" class="list-group-item list-group-item-action bg-light">Status</a>
			</div>
		</div>
		<!-- /#sidebar-wrapper -->

		<!-- Page Content -->
		<div id="page-content-wrapper">

			<nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
				<button class="btn btn-primary" id="menu-toggle">Toggle Menu</button>

				<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>

				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav ml-auto mt-2 mt-lg-0 pull-right">
						<li class="nav-item active"><a class="nav-link" href="#">Home <span class="sr-only">(current)</span>
						</a></li>
						<li class="nav-item"><a class="nav-link" href="#">Link</a></li>
						<li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> Dropdown </a>
							<div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
								<a class="dropdown-item" href="#">Action</a> <a class="dropdown-item" href="#">Another action</a>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="#">Something else here</a>
							</div></li>
					</ul>
				</div>
			</nav>

			<div class="container-fluid">

				<!-- -------------------  board list--------------------------------  -->
				<div class='row'>

					<div class="col-lg-12">
					 <form id="newB" action="/reply/modify" method="POST">

						<!-- /.panel -->
						<div class="panel panel-default">
							<div class="panel-heading">
								<i class="fa fa-comments fa-fw"></i>
									<input type="text"  id ="title" name="title"  value="${board.title}" >
									<button type="button">글 작성</button>

							</div>


							<!-- /.panel-heading -->
							<div class="content-body">


								<textarea id="content"rows="40" cols="117" name="content" >${board.content}</textarea>
								
								<!-- ./ end ul -->
							</div>
							<!-- /.panel .chat-panel -->

						</div>
						
						<!--POST방식은 반드시 CSRF 전송  -->
						<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token}" />
						<input type="hidden" name="writer" value='<sec:authentication property="principal.member.userName"/>'>
						<input type="hidden" name="userid" value='<sec:authentication property="principal.username"/>'>
						
						
 					</form>


					</div>
				</div>
				<!-- ./ end row -->
			</div>
			<form id="actionForm" action="/reply/list" method="get">
				<input type="hidden" name="pageNum" value="${page.cri.pageNum }"> 
				<input type="hidden" name="amount" value="${page.cri.amount }">
			</form>






		</div>
	</div>
	<!-- /#page-content-wrapper -->


	<!-- /#wrapper -->
	<!-- Bootstrap core JavaScript -->
	<script src="/resources/vendor/jquery/jquery.min.js"></script>
	<script src="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Menu Toggle Script -->
	<script>
		$("#menu-toggle").click(function(e) {
			e.preventDefault();
			$("#wrapper").toggleClass("toggled");
		});
	</script>
</body>
</html>