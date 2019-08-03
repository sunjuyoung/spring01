console.log("Book Ajajx Module");

var bookAPIService = (function(){
	
	
	function list(reply,callback,error){
		console.log("book list");
		
		$.ajax({
			type:'get',
			url:'http://www.aladin.co.kr/ttb/api/ItemList.aspx?ttbkey=ttbsyseoz1647001&QueryType=ItemNewAll&MaxResults=10&start=1&SearchTarget=Book&output=JS&Version=20131101',
			 crossOrigin : true,
			    dataType : "json",
			success:function(result,status,xhr){
				if(callback){
					console.log("booklist ajax success");
					callback(result);
				}
				
			},
			error:function(xhr,status,er){
				if(error){
					console.log("booklist error");
					error(er);
				}
			}
	
			
		});
		
		
		
	}
	
	return{
		list:list
	};
	
	
	
	
})();