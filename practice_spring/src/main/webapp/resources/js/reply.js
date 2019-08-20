
//댓글 ajax 모듈화
console.log("Replye Module~~~~~");

// 즉시실행함수와 {}를 이용해서 객체를 구성합니다
var replyService = (function() {

	
	//댓글 입력
	function add(reply, callback) {
		
		console.log(" module reply......");
	
		
		$.ajax({
			type : 'post',
			url : '/reply/new',
			data : JSON.stringify(reply),
			contentType : "application/json; charset=utf-8",
			
			success : function(result) {
				if (callback) {
					callback(result);
				}
				
			}
			
		});

	}
	
	// 댓글 출력
	function ajaxList(bno, callback, error) {

		console.log("module reply list");

		$.ajax({
			type : 'get',
			url : '/reply/get/' + bno + '.json',
			contentType : "application/json; charset=utf-8",
			success : function(result) {
				if (callback) {
					callback(result);
				}
			}
		});
	}
	
	//댓글 제거
	function replyDelete(rno,callback){
		console.log("module reply delete");
		
		$.ajax({
			type:'delete',
			url:'/reply/'+rno,
			success:function(result){
				if(callback){
					callback(result);
				}
			}
			
			
			
			
		});
		
		
		
	}
	
	
	
	
	
	//수정
	function replyUpdate(reply,callback){
		console.log("module reply update")
		
		$.ajax({
			type:'put',
			url:'/reply/'+reply.rno,
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify(reply),
			success:function(result){
				if(callback){
					callback(result);
				}
			}
			
			
			
		});
		
		
	}
	
	
	
	

	return {
		add : add,
		ajaxList:ajaxList,
		replyDelete:replyDelete,
		replyUpdate:replyUpdate
	};
})();