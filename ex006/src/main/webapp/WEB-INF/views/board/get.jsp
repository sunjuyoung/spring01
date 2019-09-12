<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%-- jstl-1.2.jar 파일 필요 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>SB Admin 2 - Register</title>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!-- Custom fonts for this template-->
<link
	href="${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link
	href="${pageContext.request.contextPath}/resources/css/sb-admin-2.min.css"
	rel="stylesheet">

</head>

<body class="bg-gradient-primary">

	<div class="container">
	
<form role = "form" method="get" action="${pageContext.request.contextPath}/board/list">
		<div class="card o-hidden border-0 shadow-lg my-5">
			<div class="card-body p-0">
				<!-- Nested Row within Card Body -->
				<div class="row">

					<div class="col-lg-12">
						<div class="p-5">
							<div class="text-center">
								<h1 class="h4 text-gray-900 mb-4">Create an Account!</h1>
							</div>

							<div class="form-group">
								<input type="text" class="form-control form-control-user"
									id="title" name="title"
									value="<c:out value="${board.title }" />" readonly="readonly">


							</div>
							<div class="form-group">
								<input type="text" style="height: 100px"
									class="form-control form-control-user" id="content"
									name="content" value="<c:out value="${board.content }" />"
									readonly="readonly">
							</div>
							<div class="form-group row">
								<div class="col-sm-6 mb-3 mb-sm-0">
									<input type="text" class="form-control form-control-user"
										id="writer" name="writer"
										value="<c:out value="${board.writer }" />" readonly="readonly">
								</div>

							</div>




							<button data-oper="modify"
								class="btn btn-primary btn-user btn-block "
								onclick="location.href='${pageContext.request.contextPath}/board/modify?bno=<c:out value="${board.bno }" />' ">
								수정</button>


							<button data-oper="remove"
								class="btn btn-danger btn-user btn-block ">
								삭제</button>



							<button data-oper="list" class="btn btn-info btn-user btn-block"
								onclick="location.href='${pageContext.request.contextPath}/board/list'">
								목록</button>

							<hr>
							<a href="index.html" class="btn btn-google btn-user btn-block">
								<i class="fab fa-google fa-fw"></i> Register with Google
							</a> <a href="index.html" class="btn btn-facebook btn-user btn-block">
								<i class="fab fa-facebook-f fa-fw"></i> Register with Facebook
							</a>


						</div>
					</div>
				</div>
			</div>
		</div>
		</form>

	</div>

	<!-- Bootstrap core JavaScript-->
	
	<script type="text/javascript">
	$(document).ready(function(){
		
		var form = $("form");
		
		
		
		
		
		$("button").on("click",function(e){
			e.preventDefault();
		
			
			var oper= $("button").data("oper");
			console.log(oper);
			
			if(oper === "remove"){
				form.attr("action","${pageContext.request.contextPath}/board/remove");
				form.attr("method","POST");
				
				
			}else if (oper === "modify"){
				
				self.location = "/board/list"
			}
			
			
			form.submit();
		});
		

	});
	
	
	</script>
	
	

	<script
		src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script
		src="${pageContext.request.contextPath}/resources/vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script
		src="${pageContext.request.contextPath}/resources/js/sb-admin-2.min.js"></script>

</body>

</html>
