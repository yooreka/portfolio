<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>포트폴리오</title>
</head>
<body>
 <h3>[토스트 메시지 입력]</h3>
 메시지 : <input type="text id="toastmessageinput" />
 <br/>
 <input type="button" id="showtoast" value="전송" />
 <br/><br/>
 <h3>전달받은 메시지</h3>
 <div id="nativemessage"
 style="height:200px; overflow-y:auto;"></div>
</body>
<script>
   document.getElementById("showtoast").addEventListener("click", function(e){
	   //NativeApp의 메소드를 호출하는 구문
	   //NativeApp에 MyApp 이라는 이름을 등록하고 ShowToastMessage 라는 메소드를 생성
	  MYApp.showToastMessage(document.getElementById("toastmessageinput").value);
   });
   //Native App에서 호출할 메소드
   function showDisplayMessage(message){
	   document.getElementById("nativemessage")
	   .innerHTML = message + document.getElementById("nativemessage").innerHTML
   }
</script>
</html>