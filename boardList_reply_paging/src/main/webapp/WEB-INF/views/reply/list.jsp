<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%-- jstl-1.2.jar 파일 필요 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1">

<title></title>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">

<style>


select {
 
  padding: 5px 7px;
  border: none;
  border-radius: 4px;
  background-color: white;
}
</style>

<!-- Google Map API -->
<script src="https://maps.googleapis.com/maps/api/js"></script>

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>


<script>
	$(document).ready(function() {

						var replyUL = $(".chat");

						var bnoValue = 23;

						console.log(bnoValue);
						console.log(replyUL);
							/* 댓글 출력 */
						ajaxList(
								bnoValue,
								function(list) {

									var str = "";

									console.log(list.length);

									for (var i = 0, len = list.length || 0; i < len; i++) {
										str += "<li class='left clearfix' data-rno='"+list[i].rno+"'  data-replyer='"+list[i].replyer+"'>";
										str += "  <div><div class='header'><strong class='primary-font' style='font-size:80%;'>["
												+ list[i].rno
												+ "] "
												+ list[i].replyer + "</strong>";
										str += " <a href='#'><img src='/resources/img/icon_delete_x.png'  width='13' height='14' title='replyD' alt='"+list[i].rno+"'></a> ";
										str += " <a href='#'><img src='/resources/img/icon_Modify_pen.png'  width='13' height='14' title='replyM'  alt='"+list[i].rno+"'></a> ";
										str += "    <small class='pull-right text-muted'>"
												+ list[i].replyDate
												+ "</small></div>";
										str += "    <p style='font-size:80%;'>"
												+ list[i].reply
												+ "</p></div></li>";

									}

									console.log(str);

									replyUL.html(str);

								});

						// 리스트출력
						function ajaxList(bno, callback, error) {

							console.log("bno2 2222" + bno);

							$
									.ajax({
										type : 'get',
										url : '/reply/list/' + bno + '.json',
										contentType : "application/json; charset=utf-8",
										success : function(result) {
											if (callback) {
												callback(result);
											}
										}
									});
						}

						
						var actionForm = $("#actionForm");
						/* 페이지 버튼 이벤트 작업 */
						$("a.pageBtn").on("click",function(e){
							
							e.preventDefault();
							
							actionForm.find("input[name='pageNum']").val($(this).attr("href"));
							actionForm.submit();
							
						});
						
						/* 검색 버튼 이벤트 처리 */
						var searchForm = $("#searchForm");
						$("#searchForm button").on("click",function(e){
							
							if(!searchForm.find("input[name='keyword']").val()){
								alert("검색어 입력해주세요");
								return false;
							}
							
							if(!searchForm.find("option:selected").val()){
								alert("검색종류를 선택하세요");
								return false;
							}
							
							searchForm.find("input[name='pageNum']").val("1");
							searchForm.submit();
							
						});
						
						
						
					});
</script>
</head>
<body>
	<div class="container">

		<!-- -------------------  board list--------------------------------  -->
		<div class='row'>

			<div class="col-lg-12">

				<!-- /.panel -->
				<div class="panel panel-default">
					<div class="panel-heading">
						<i class="fa fa-comments fa-fw"></i> Board List
						<!-- -------------------게시물 수---------------------  -->
						<select name="amount">
						<option value="">게시물 수</option>
						<option value="5">5</option>
						<option value="10">10</option>
						<option value="20">20</option>
						</select>

						<button id="addReplyBtn" class="btn btn-primary btn-sm pull-right"
							data-oper="new">New</button>
					</div>


					<!-- /.panel-heading -->
					<div class="panel-body">


						<table class="table">

							<thead>
								<tr>
									<td>이름</td>
									<td>내용</td>
									<td>날짜</td>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="#{list }" var="board">
									<tr>
										<td>${board.writer }</td>
										<td>${board.content }</td>
										<td>${board.regDate }</td>
									</tr>

								</c:forEach>

							</tbody>

						</table>


						<!-- ./ end ul -->
					</div>
					<!-- /.panel .chat-panel -->
					<div class="panel-footer row">
						<!----------------------  페이징 ------------------------->
						<ul class="pagination">
							<li><a href="#">Previous</a></li>
								<c:forEach var ="num"  begin="${page.startPage}"  end="${page.endPage}">
									<li class="${page.pageNum == num ? 'active':'' }"><a class = "pageBtn"href="${num }">${num }</a></li><!-- class="active" -->
								</c:forEach>
						
							<li><a href="#">Next</a></li>
						</ul>
						
				<!-- --------------검색---------------------------  -->
			<form id="searchForm" action="/reply/list" method="get">
			<button class="btn btn-default pull-right">확인</button>
					<input type="text"  name="keyword" class="pull-right"  >
						<select name="type" class="pull-right">
							<option value="">검색 조건</option>
							<option value="T">제목</option>
							<option value="C">내용</option>
							<option value="W">작성자</option>
							<option value="TC">제목/내용</option>
						</select>
						
					<input type="hidden" name="pageNum" value="${page.pageNum }">
					<input type="hidden" name="amount" value="${page.amount }">
					
						</form>
					</div>



				</div>
			</div>
			<!-- ./ end row -->
		</div>
				<form id="actionForm" action="/reply/list" method="get">
					<input type="hidden" name="pageNum" value="${page.pageNum }">
					<input type="hidden" name="amount" value="${page.amount }">
				</form>


		<!-- -------------------  reply-----------------------  -->
		<div class='row'>

			<div class="col-lg-12">

				<!-- /.panel -->
				<div class="panel panel-default">
					<div class="panel-heading">
						<i class="fa fa-comments fa-fw"></i> Reply

						<button id="addReplyBtn" class="btn btn-primary btn-sm pull-right"
							data-oper="new">New</button>
					</div>


					<!-- /.panel-heading -->
					<div class="panel-body">
						<hr>

						<ul class="chat">



						</ul>
						<!-- ./ end ul -->
					</div>
					<!-- /.panel .chat-panel -->
					<div class="panel-footer">
						<!-- 댓글 페이징 -->

					</div>

				</div>
			</div>
			<!-- ./ end row -->
		</div>




	</div>

</body>
</html>