<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%-- jstl-1.2.jar 파일 필요 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1">

<title></title>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<style>

div#input:hover, div#output:hover {
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
}

</style>

<!-- Google Map API -->
<script src="https://maps.googleapis.com/maps/api/js"></script>

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


<script>



</script>


</head>
<body>

<div class="container">



<a href="https://accounts.google.com/o/oauth2/v2/auth?
 scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fcalendar%20https%3A%2F%2Fwww.gooleapis.com%2Fauth%2Fcalendar.readonly&
 access_type=offline&
 include_granted_scopes=true&
 state=state_parameter_passthrough_value&
 redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Freply%2Fgallery&
 response_type=code&
 client_id=562424517981-9m79l9lk82per6fej4q5l5nh18l5rbt9.apps.googleusercontent.com">wefewfw</a>

<form action="https://www.googleapis.com//oauth2/v4/token" method="post" enctype="application/x-www-form-urlencoded">
<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"> 
<p>code : <input type="text" name="code" value="4/P7q7W91a-oMsCeLvIaQm6bTrgtp7"></p>
<p>client id :<input type="text" name ="client_id" value="562424517981-9m79l9lk82per6fej4q5l5nh18l5rbt9.apps.googleusercontent.com"></p>
<p>client secret : <input type="text" name ="client_secret" value="H5F-jvMzsVkUuhgclkSqC52-"></p>
<p>redirect uri : <input type="text" name ="redirect_uri" value="http://localhost:8080/reply/gallery"></p>
<p>grant type : <input type="text" name ="grant_type" value="authorization_code"></p>
<button>button</button>

</form>


<form action="uploadFormAction" method="POST" enctype="multipart/form-data">

<input type="file" name="uploadFile" multiple>
<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"> 
<button>Submit</button>
</form>
<hr>

<div class="uploadDiv">
<input type="file" name="ajaxFileUpload" multiple>
<input class="csrf" type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"> 
</div>
<button id="uploadBtn">Upload</button>


</div>
<script
  src="https://code.jquery.com/jquery-3.3.1.js"
  integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
  crossorigin="anonymous"></script>


<script>
$(document).ready(function(){
	
	
	$.ajax({
		url:'/reply/book_info',
		type:'get',
		dataType:'json',
		success:function(result){
			console.log(result.version);
		}
		
		
	});
	
	
	
	
	
	
	
	

	$("button#uploadBtn").on("click",function(e){
		
		var formData = new FormData(); //가상의 form태그와 같다
		
		var inputFile = $("input[name='ajaxFileUpload']");
		
		var files = inputFile[0].files;
		
		console.log(files);
		
		for(var i =0; i<files.length; i++){
			formData.append("uploadFile",files[i]);
		}
		

		var csrfHeaderName = "${_csrf.headerName}";
		var csrfTokenValue = "${_csrf.token}";
		
		console.log(csrfTokenValue);
		
		$.ajax({
			url:'/reply/uploadAjaxAction',
			processData:false,
			contentType:false,
			beforeSend:function(xhr){
				xhr.setRequestHeader(csrfHeaderName,csrfTokenValue);
			},
			data:formData,
			type:'post',
			dataType:'json',
			success:function(result){
				console.log(result);
			}
			
			
		});//ajax end
		
	});//button click
	
});
</script>

</body>
</html>