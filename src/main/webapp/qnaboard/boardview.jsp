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
	    
	    
	table {
	   margin: 0 auto;
	   width: 900px;
	}
	
	
	
	    .BacktoList {
            border: 1px solid #f7175a; 
            border-radius: 25px; 
            background-color: #f7175a; 
            padding: 10px 20px; 
            color : white;
        }
        
        .Answer{
            border: 1px solid #f7175a; 
            border-radius: 25px; 
            color: #f7175a; 
            padding: 10px 20px; 
            background-color : white;
        }
        
        
</style>
</head>
<body>
	<c:set var="board" value="${requestScope.board }"/>
	<c:set var="replylist" value="${requestScope.replylist }"/>
   <div>
      <table style="border-collapse: collapse;">
         <tr height="30px">
            <th align="center" width="150px" style="color: #f7175a;">제 목</th>
            <td>${board.boardtitle }
            </td>
         </tr>
         <tr height="30px">
            <th align="center" width="150px" style="color: #f7175a;">작성자</th>
            <td>${board.username }</td>
         </tr>
         <tr height="300px">
            <th align="center" width="150px" style="color: #f7175a;">내 용</th>
            <td valign="top" style="padding:10px;">
            ${board.boardcontents }
            </td>
         </tr>
      </table>
      
      <!-- 댓글 -->
      <form name="replyForm" action="/qnaboard/AddReply.bo" method="post">
         <input type="hidden" name="boardnum" value="${board.boardnum }">
         <table>
            <tr>
               <td align="center" width="200px" style="color: #f7175a;">
                  관리자<br><hr>
               </td>
               <td style="padding-left:10px">
                  <textarea name="replycontents" style="width:680px;height:85px;resize:none; border: 1px solid #f7175a; border-radius: 10px; "></textarea><br>
                   <input type="button" value="답변하기" class="Answer" onclick="document.replyForm.submit();"></td>
               </td>
            </tr>
         </table>
      
      <!-- 댓글 리스트 -->
      <hr>
         <table>
            <c:choose>
	    	   <c:when test = "${replylist != null and fn:length(replylist)>0 }">
				<c:forEach var="reply" items="${replylist }">	
						<tr>
	               <td align="center" width="200px">
	               ${reply.username }</td>         
	               <td valign="top" style="padding-left:10px;">
	                  <textarea style="text-align:left;border:0px;width:680px;
	                     height:85px;resize:none;" name="reply${reply.replynum }" id="reply${reply.replynum }" readonly>${reply.replycontents }
	                  </textarea><br>
	                  <a id="" href="javascript:updateReply(${reply.replynum },'${reply.password }')">[수정]</a>
	                  &nbsp;&nbsp;&nbsp;
	                  <a id="" href="javascript:updateReadonlyReply(${reply.replynum })">[수정 하기]</a>
	                  &nbsp;&nbsp;&nbsp;
	                  <a id="" href="javascript:deleteReply(${reply.replynum },'${reply.password }')">[삭제]</a>
	               </td>
	            </tr>
					
					</c:forEach>
				</c:when>
				<c:otherwise> <%-- 내용 없을 때 --%>
				   <tr>
	                     <td align="center" style="color: #f7175a;">
	                     지금 관리자가 내용을 확인하고 있습니다.</td>
	                  </tr>
	       	 </c:otherwise>
	        </c:choose>
         </table>
      </form>
      <br/><br/>
   </div>
	<div style="text-align: center;">
       <input type="button" value="목록" class="BacktoList" onclick="location.href='/qnaboard/BoardList.bo';"></td>
     </div>
   
   	<script>
   	
   	function updateReply(replynum, password){
		let pw = prompt("관리자 비밀번호를 입력하세요.");
		if ( pw == password ){
			document.replyForm.action = "/board/UpdateReply.bo?replynum="+replynum;
			document.replyForm.submit();
		} else {
			
			alert("비밀번호가 일치하지 않습니다")
		}
   	}
   	
   	function updateReadonlyReply(replynum){
		document.getElementById('reply'+replynum).readOnly = false;
   	}
   	
   	function deleteReply(replynum, password){
   		let pw = prompt("관리자 비밀번호를 입력하세요.");
		if ( pw == password ){
			document.replyForm.action = "/board/DeleteReply.bo?replynum="+replynum;
			document.replyForm.submit();
		} else {
			
			alert("비밀번호가 일치하지 않습니다")
		}
   	}
   	
   	</script>

</body>
</html>