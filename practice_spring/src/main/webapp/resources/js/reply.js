//댓글 ajax 모듈화
console.log("Replye Module~~~~~");

// 즉시실행함수와 {}를 이용해서 객체를 구성합니다
var replyService = (function() {

	function add(reply, callback, error) {
		console.log("reply......");

		$.ajax({
			type : 'post',
			url : '/reply/new',
			data : JSON.stringify(reply),
			contentType : "application/json; charset=utf-8",
			success : function(result, status, xhr) {
				if (callback) {
					console.log("suceesssssssss");
					callback(result);
				}
			},
			error : function(xhr, status, er) {
				if (error) {
					console.log("weofijweoifjewoijfjieow");
					error(er);
				}
			}

		});

	}

	return {
		add : add
	};
})();