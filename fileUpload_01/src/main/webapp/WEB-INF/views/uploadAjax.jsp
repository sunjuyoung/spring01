<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" %>
<%-- jstl-1.2.jar 파일 필요 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1">

<title>SIST_쌍용교육센터</title>

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
$(document).ready(function(){

	$("#uploadBtn").on("click",function(e){

		var formData = new FormData(); //가상의 form태그와 다고 생각하면 됩니다
										//ajax를 이용하는 파일 업로드는 formdata를 이용해서 필요한 파라미터를 담아서 전송하는 방식
		var inputFile = $("input[name='uploadFile']");
		
		var files = inputFile[0].files;
		
		console.log(files);
		
		//add filedata to formdata
		for (var i =0; i<files.length; i++){
			formData.append("uploadFile",files[i]);
		}
		
		
		$.ajax({
			url:'/file/uploadAjaxAction',
			processData : false,
			contentType : false,
			data : formData,
			type : 'POST',
			success : function(result){
				alert("Uploaded");
			}
			
			
		});//end ajax
		
	});
	   

});
</script>
</head>
<body>

<div class="container">

<div class="uploadDiv">
<input type="file" name="uploadFile" multiple>
</div>
<button id = "uploadBtn">Upload</button>

</div>

</body>
</html>