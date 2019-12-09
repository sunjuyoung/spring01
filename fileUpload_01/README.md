form 태그를 이용하는 방식은 브라우저의 제한이 없어야 하는 경우 사용
 - 일반적으로 페이지 이동과 동시에 업로드 하는 방식
 - iframe을 이용해서 화면의 이동없이 첨부파일을 처리하는 방식

ajax 이용하는 방식은 첨부파일을 별도로 처리하는 방식
 - input type file 을 이용하고 ajax로 처리하는 방식
 - HTML5 드래그앤드랍 기능이나 JQuery 라이브러리 이용해서 처리하는 방식

브라우저 방식은 다양하지만 서버쪽에서 처리는 비슷하다
응답이 HTML코드인지 JSON등으로 처리하는지 구분하면 된다

서버에서는 첨부파일의 처리를 위해서 어떤 종류의 라이브러리나 API등을 활용할 것인지

API활용 예
commons-fileupload - 가장 일반적으로 사용 서블릿 스펙 3.0이전에도 사용 가능

서블릿 3.0이상 - 자체적인 파일 업로드 처리가 API 상에서 지원

위치 c:\upload


web.xml
네임스페이스 3.1로 수정
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
	id="WebApp_ID" version="3.1">



파일 업로드 고려해야 할 점들

동일한 이름으로 업로드 되었을때 기존 파일이 사라지는 문제

확장자 제한

이미지 파일, 일반파일 구분해서 다운로드 혹은 페이지에서 조회하도록 처리하는 문제

이미지 파일의 경우 원본 파일의 용량이 큰 경우 섬네일 이미지를 생성
