<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
html {
    font-family: Pretendard, "Apple SD Gothic Neo", "Nanum Gothic", "Malgun Gothic", sans-serif;
}
	* {
    box-sizing: border-box; /* 모든 요소의 박스 모델을 border-box로 설정 */
}
	input{
		background-color: #e9e9e9;
		border: 2px solid #e9e9e9; /* 테두리 색상 */
        border-radius: 10px; /* 둥근 테두리 */
        padding: 10px; /* 내부 여백 */
        width: 300px; /* 너비 설정 */
        outline: none; /* 포커스 시 기본 테두리 제거 */
        }

      input:focus {
            border-color: #f7175a; /* 포커스 시 테두리 색상 변경 */
        }
	
	
	#loginbutton{
		background-color:  #f7175a;
		border: 2px solid  #f7175a; /* 테두리 색상 */
        border-radius: 10px; /* 둥근 테두리 */
        padding: 10px; /* 내부 여백 */
        width:300px; /* 너비 설정 */
        outline: none; /* 포커스 시 기본 테두리 제거 */
        color : #ffffff;
        }
        
       #loginbutton:focus {
            border-color: #e9e9e9; /* 포커스 시 테두리 색상 변경 */
        }
		
	.find_it button{
		background-color: #ffffff;
		color :  #f7175a ; 
		border : none;
        border-radius: 10px; /* 둥근 테두리 */
        width: 300px; /* 너비 설정 */
	}
	
	.join{
		font-style : bold;
		color : #7e7e7e;
		width: 300px; /* 너비 설정 */
	}
	
	.join a{
		color :  #f7175a;
	}
	
</style>
</head>
<body>
	<form action="/join/login.lo" method="post" name="frm">
		<c:if test="${not param.flag and not empty param.flag }">
		<script>
			alert("아이디, 비밀번호가 맞지 않습니다.");
		</script>
	</c:if>
		<p>
			<label>
				<input autocomplete="off" name="userid" placeholder="아이디" type="text">
			</label>
		</p>
		<p>
			<label> 
				<input autocomplete="off" type="password" name="userpw" placeholder="비밀번호"/>
			</label>
		</p>
		<input type="button" id="loginbutton" value="로그인" onclick="login()"/>
	</form>
	<br/>
	<div class="find_it">
		<button onclick="find_it()"> 이메일/비밀번호를 잊으셨나요? </button>
	</div>
	<br/>
	<div class="join">
		계정이 없으신가요?
		<a href="/join/join_view.jsp"> 회원가입 </a>
	</div>

	<script>

 	function login(){
	
			let frm = document.frm;
			let userid = frm.userid;
			let userpw = frm.userpw;
		
		if (userid.value === ""){
			alert("아이디를 입력해주세요");
			userid.focus();
			return false;
		}
			
		if( userpw.value === "" ){
			alert("패스워드를 입력하세요");
			userpw.focus();
			return false;
		}
		
		frm.submit();
	
	 }
 	
/*  	function find_it() {
 		location.href="/join/find_it.lo"
	} */

	</script>
</body>
</html>