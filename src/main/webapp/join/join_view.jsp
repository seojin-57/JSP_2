<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	p{
		font-size: 12px;
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
	 
	.sendit {
		
		background-color:  #f7175a;
		border: 2px solid  #f7175a; /* 테두리 색상 */
        border-radius: 10px; /* 둥근 테두리 */
        padding: 10px; /* 내부 여백 */
        width:300px; /* 너비 설정 */
        outline: none; /* 포커스 시 기본 테두리 제거 */
        color : #ffffff;
	
	}
	
	.login{
		font-style : bold;
		color : #7e7e7e;
		width: 300px; /* 너비 설정 */
	}
	
	.login a{
		color :  #f7175a;
	}
	
	
	
</style>
</head>
<body>

<form action="/join/join.lo" method="post" name="joinForm">
	<p>
		<label>
			<input type="text" name="userid" id="userid" placeholder="아이디">
		</label>
	</p>
	<p id="idmessage" style="color: #f7175a;"></p>
	<p>
		<label>
			<input type="password" name="userpw" id="userpw" placeholder="비밀번호"/>
		</label>
	</p>
	<p id="pwmessage" style="color: #f7175a;"></p>
	<p>
		<label>
			<input type="text" name="username" id="username" placeholder="이름"/>
		</label>	
	</p>
	<p id="namemessage" style="color: #f7175a;"></p>
	<p> <input type="button" class="sendit" value="회원가입" onclick="sendit()"/> </p>	
	
</form>

	<div class="login">
		이미 가입 하셨나요?
		<a href="/join/login.jsp"> 로그인 </a>
	</div>

	<script src="/join/user.js"> </script>
</body>
</html>