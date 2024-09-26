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
	    
	   table{
	      margin:0 auto;
	      width:900px;
	   }
	   
	    .WriteOK {
            border: 1px solid #f7175a; 
            border-radius: 25px; 
            background-color: #f7175a; 
            padding: 10px 20px; 
            color : white;
        }
        
        .BacktoList{
            border: 1px solid #f7175a; 
            border-radius: 25px; 
            background-color: white; 
            padding: 10px 20px; 
            color : #f7175a;
        }
        
     
	</style>
</head>
<body>
	<c:if test="${not param.flag and not empty param.flag }">
		<script>
			alert("게시글 등록 실패. 다시 시도하세요.");
		</script>
	</c:if>
	
   <div>
      <form method="post" name="boardForm" action="/qnaboard/BoardWriteOk.bo">
       
         <table border="0" style="border-collapse:collapse;">
        	  <tr height="30px">
               <th align="center" width="150px" style="color: #f7175a;">
                  카테고리
               </th>
               <td>
                  <select name="category">
		            <option disabled selected>유형을 선택하세요</option>
		            <option id="noticeOption">공지</option>
		            <option>추천</option>
		            <option>문의</option>            
	        </select>
               </td>
            </tr>
            <tr height="30px">
               <th align="center" width="150px" style="color: #f7175a;">
                  제 목
               </th>
               <td>
                  <input name="boardtitle" size="50" maxlength="100" value="" placeholder="제목을 입력하세요">
               </td>
            </tr>
            <tr height="30px">
               <th align="center" width="150px" style="color: #f7175a;">
                  작성자
               </th>
               <td>
                  ${board.username }
               </td>
            </tr>
            <tr height="300px">
               <th align="center" width="150px" style="color: #f7175a;">
                  내 용
               </th>
               <td>
                  <textarea name="boardcontents" style="width:700px;height:250px;" placeholder="내용을 입력하세요"></textarea>               
               </td>
            </tr>
         </table>
         <table style="border:0px;">
            <tr align="right" valign="middle">
               <td>
                	<input type="button" value="작성 완료" class="WriteOK" onclick="document.boardForm.submit();">
                    <input type="button" value="취소" class="BacktoList" onclick="location.href='/qnaboard/BoardList.bo';"></td>
            </tr>
         </table>
      </form>
   </div>
   
 <script>
        function checkUserRole() {
            // 이 부분에 사용자 역할을 체크하는 로직 추가
            let userRole = 'user'; // 예시: 현재 사용자 역할 (실제 로직에 맞게 변경)
            if (userRole !== 'admin') {
                // admin이 아닌 경우 "공지" 옵션 제거
                let noticeOption = document.getElementById('noticeOption');
                if (noticeOption) {
                    noticeOption.remove();
                }
            }
        }

        window.onload = checkUserRole; // 페이지 로드 시 역할 체크
    </script>
</body>
</html>