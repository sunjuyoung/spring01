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

button {
	float: right;
}

input {
	border: 0px solid transparent;
}

div.content-body {
	height: 400px;
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
		
		var csrfHeaderName = "${_csrf.headerName}";
		var csrfTokenValue = "${_csrf.token}";

		
	    var replyer = null;
	    
	    <sec:authorize access="isAuthenticated()">
	    
	    replyer = '<sec:authentication property="principal.username"/>';   
	    
	</sec:authorize>


		//Ajax Spring secutity header
		//beforeSend 대신 사용가능
		//모든  Ajax전송시 CSRF토큰을 같이 전송하도록 셋팅
		$(document).ajaxSend(function(e,xhr,options){
			xhr.setRequestHeader(csrfHeaderName,csrfTokenValue);
		});

		var replyUL = $(".chat");
		var bnoValue = ${board.bno};

		console.log(bnoValue);
		console.log(replyUL);
	
		showList();
		
		
		
		/* 댓글 출력 */
		function showList(){
			

		
			replyService.ajaxList(bnoValue, function(list) {

			var str = "";

			console.log(list.length);

			for (var i = 0, len = list.length || 0; i < len; i++) {
				str += "<li class='left clearfix' data-rno='"+list[i].rno+"'  data-replyer='"+list[i].replyer+"'>";
				str += "  <div><div class='header'><strong class='primary-font' style='font-size:80%;'>[" + list[i].rno + "] " + list[i].replyer + "</strong>";
				str += " <a href='#' class='replyDel'><img src='/resources/img/icon_delete_x.png'  width='13' height='14' title='replyD' alt='"+list[i].rno+"'></a> ";
				str += " <a href='#' class='replyMd'><img src='/resources/img/icon_Modify_pen.png'  width='13' height='14' title='replyM'  alt='"+list[i].rno+"'></a> ";
				str += "    <small class='pull-right text-muted'>" + list[i].replyDate + "</small></div>";
				str += "    <p style='font-size:80%;'>" + list[i].reply + "</p></div></li>";

			}

			//console.log(str);

			replyUL.html(str);

		});

		}

		var actionForm = $("#actionForm"); //페이지 쪽수 
		var selected = $("select[name='amount']"); //게시물 수 선택
		/* 페이지 버튼 이벤트 작업 */
		$("a.pageBtn").on("click", function(e) {

			e.preventDefault();

			actionForm.find("input[name='pageNum']").val($(this).attr("href")); //클릭시 페이지 쪽수 업데이트 

			actionForm.find("input[name='amount']").val(selected.find("option:selected").val()); //클릭시 한페이지 게시물 수 업데이트 

			actionForm.submit();

		});





		//댓글 입력
		var newReplyaction = $("#newReply");
		$("button.newReplyBtn").on("click", function(e) {


			var reply = {
				replyer : replyer,
				reply : newReplyaction.find("textarea#reply").val(),
				bno : bnoValue
			};

	
			replyService.add(reply, function(result) {
				alert(result);
				newReplyaction.find("textarea#reply").val("");
				
				$("#addReplyBtn").trigger('click');
				showList();
				
				
			});
		

		});

		var form = $("form#DnM");

		//게시물 삭제
		$("button#delBoard").on("click", function(e) {

			if (confirm("삭제하시겠습니까?")) {

				form.submit();
			} else {

				return false;
			}

		});

		//게시물 수정
		$("button#modifyBoard").on("click", function(e) {
			e.preventDefault();

			form.attr("action", "/reply/modify");
			form.attr("method", "get");
			form.submit();

		});
		

		

		//댓글 삭제 수정
		$("ul.chat").on("click","img",function(){
			
			var title = $(this).attr("title");
			var rno = $(this).attr("alt");
			console.log(rno);
			
			if(title == "replyD"){
				replyService.replyDelete(rno,function(result){
				alert(result);
					showList();
				});
			
				
				
			}else if (title == "replyM"){
				
				
				
			}
			


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
				<a href="#" class="list-group-item list-group-item-action bg-light">Dashboard</a> <a href="#" class="list-group-item list-group-item-action bg-light">Gallery</a> <a href="#" class="list-group-item list-group-item-action bg-light">Books</a> <a href="#" class="list-group-item list-group-item-action bg-light">Movies</a> <a href="#" class="list-group-item list-group-item-action bg-light">Members</a> <a href="#" class="list-group-item list-group-item-action bg-light">Profile</a>
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

						<!-- /.panel -->
						<div class="panel panel-default">
							<div class="panel-heading">
								<i class="fa fa-comments fa-fw"></i><label>제목 : </label>
								<c:out value="${board.title }" />

								<form id="DnM" action="/reply/delete" method="POST">

									<button type="submit" id="delBoard">삭제</button>
									<button type="button" id="modifyBoard">수정</button>
									<input type="hidden" name="bno" value="${board.bno }"> <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token }">
								</form>
							</div>


							<!-- /.panel-heading -->
							<div class="content-body">



								<c:out value="${board.content }" />

								<!-- ./ end ul -->
							</div>
							<!-- /.panel .chat-panel -->

						</div>



						<!-- -------------------  reply-----------------------  -->

						<!-- /.panel -->
						<div class="panel panel-default">
							<div class="panel-heading">
								<i class="fa fa-comments fa-fw"></i> Reply

								<button id="addReplyBtn" class="btn btn-primary btn-sm pull-right" data-oper="new" data-toggle="collapse" data-target="#newReply"">New</button>
							</div>


							<!-- /.panel-heading -->
							<div class="panel-body">
								<hr>

								<ul class="chat">



								</ul>
								<!-- ./ end ul -->
								<form id="newReply" action="/reply/new" method="post">
									<div id="newReply" class="collapse">


										<textarea class="form-control" rows="2" id="reply" name="reply"></textarea>
										<button type="button" class="newReplyBtn">입력</button>
										<input type="hidden" name="bno" value="${board.bno }"> 
										<input type="hidden" name="pageNum" value="${page.cri.pageNum }">
										 <input type="hidden" name="amount" value="${page.cri.amount }">

									</div>
								</form>

								<!-- ./ end row -->
								<!----------------------  페이징 ------------------------->
								<ul class="pagination">

									<c:if test="${page.prev}">
										<li><a class="pageBtn" href="${page.cri.pageNum-1}">Previous</a></li>
									</c:if>
									<c:forEach var="num" begin="${page.startPage}" end="${page.endPage}">
										<li class="${page.cri.pageNum == num ? 'active':'' }"><a class="pageBtn" href="${num }">${num }</a></li>
										<!-- class="active" -->
									</c:forEach>
									<c:if test="${page.next}">
										<li><a class="pageBtn" href="${page.cri.pageNum +1}">Next</a></li>
									</c:if>

								</ul>


							</div>


						</div>



					</div>
				</div>
				<!-- ./ end row -->
			</div>
			<form id="actionForm" action="/reply/list" method="get">
				<input type="hidden" name="pageNum" value="${page.cri.pageNum }"> <input type="hidden" name="amount" value="${page.cri.amount }">
			</form>






		</div>
	</div>
	<!-- /#page-content-wrapper -->

	</div>
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