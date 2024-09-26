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
	    
	    body {
            margin: 10px;
            padding: 10px;
            display: flex;
            flex-direction: column;
        }
		a:visited{
			color:#f7175a;
			text-decoration:none;
		}
		a:hover{
			color:#f7175a;
			text-decoration:none;
			font-weight:bold;
		}
		a:link {
			color:#f7175a;
			text-decoration:none;
		}
		
		
		form {
            border: 1px solid #f7175a; /* 테두리 색상 */
            border-radius: 25px; /* 둥근 모서리 */
               }
               
        .myqnachoice {
            border: 0px solid #f7175a; 
            border-radius: 25px; 
            background-color: #f7175a; 
            padding: 10px 20px; 
            color : white;
            width : 20%;
        }
        
        .write{
        	border: 1px solid #f7175a; 
            border-radius: 25px; 
            background-color: #f7175a; 
            padding: 10px 20px; 
            color : white;
            
        }
        
        .change-color{
        	color: #f7175a;
        }
        
          select[name="category"]{
         	border: 1px solid #f7175a; 
            border-radius: 25px; 
            padding: 10px 25px; 
            background-color: white;
            width : 20%;
            text-align: center;
          }
	</style>

</head>
<body>
	<div>
		<form action="/qnaboard/category_view.bo" id="catechoice" style="display: inline-block; margin-right: 20px; border: 0px;">
        	<select name="category" onchange="document.getElementById('catechoice').submit();">
	            <option disabled selected>카테고리</option>
	            <option>공지</option>
	            <option>추천</option>
	            <option>문의</option>            
	        </select>
    	   	
    		<input type="button" value="내가 작성한 글만 보기" class="myqnachoice"><a href="/qnaboard/Mywrite_view.bo"></a>
    	
		<br/>
		<br/>
		<hr align="left" style="border-top: 2px solid #f7175a; width:100%;"/>
		<c:set var="boardList" value="${requestScope.boardList }"/>
		<c:set var="totalCnt" value="${requestScope.totalCnt }"/>
		<c:set var="nowPage" value="${requestScope.nowPage }"/>
		<c:set var="startPage" value="${requestScope.startPage }"/>
		<c:set var="endPage" value="${requestScope.endPage }"/>
		<c:set var="totalPage" value="${requestScope.totalPage }"/>
	
		<table>
			<tr align="center" valign="middle" class="title">
				<td height="40px" width="8%">카테고리</td>
				<td height="40px" width="4%">번호</td>  
				<td height="40px" width="50%">제목</td>
				<td height="40px" width="15%">작성자</td>
				<td height="40px" width="17%">날짜</td>
			</tr>
			<%-- 글 목록 --%>
		<c:choose>
	       <c:when test = "${boardList != null and fn:length(boardList)>0 }">
				<c:forEach var="board" items="${boardList }">	
				  <c:if test="${board.category == '공지'}">
		          <tr align="center" valign="middle" onmouseover="this.style.background='#bbdefb'"
		            		onmouseout="this.style.background=''">
		            <td height="23px" class="${board.boardcate == '공지' ? 'change-color' : ''}">${board.boardcate }</td>
		               <td height="23px">${board.boardnum }</td>
		               <td height="23px"><a href="/qnaboard/BoardView.bo?boardnum=${board.boardnum }" > ${board.boardtitle }</a> </td>
		               <td height="23px">${board.username }</td>
		               <td height="23px">${board.boarddate }</td>
		            </tr>
		           </c:if>
		           <c:if test="${board.category != '공지'}">
		          <tr align="center" valign="middle" onmouseover="this.style.background='#bbdefb'"
		            		onmouseout="this.style.background=''">
		            <td height="23px" class="${board.boardcate  == '공지' ? 'change-color' : ''}">${board.boardcate }</td>
		               <td height="23px">${board.boardnum }</td>
		               <td height="23px"><a href="/qnaboard/BoardView.bo?boardnum=${board.boardnum }" > ${board.boardtitle }</a> </td>
		               <td height="23px">${board.username }</td>
		               <td height="23px">${board.boarddate }</td>
		            </tr>
		           </c:if>
				</c:forEach>
			</c:when>
				<c:otherwise> <%-- 내용 없을 때 --%>
				<tr style="height:50px;">
		            <td colspan="5" style="text-align:center; color:#f7175a;">아직 등록된 게시물이 없습니다.</td>
		        </tr>
       	 </c:otherwise>
        </c:choose>
		</table><br/>
		<hr align="left" style="border-top: 1px solid #f7175a; width:100%;"/>
			<table style="border: 0px;">
			<tr align="left" valign="middle">
				<td><input type="button" value="글 쓰기" class="write" onclick="location.href='/qnaboard/BoardWrite.bo';"></td>
		</table>
       
			<%-- 페이징 처리 --%>
			
			<table style="border:0px; width:900px;">
				<tr align="center" valign="middle">
					<td>
						<c:if test="${nowPage>1 }">
						<a href="${pageContext.request.contextPath}/qnaboard/BoardList.bo?page=${nowPage-1 }">[&lt;]</a>
						</c:if>
						<c:forEach var="i" begin="${startPage }" end="${endPage }">
							<c:choose>
								<%-- 현재 보고 있는 페이지는 a tag 제거 --%>
								<c:when test="${i == nowPage }">[${i }]</c:when>
								<c:otherwise>
									<a href="${pageContext.request.contextPath}/qnaboard/BoardList.bo?page=${i }">[${i }]</a>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:if test="${nowPage<totalPage }">
						<a href="${pageContext.request.contextPath}/qnaboard/BoardList.bo?page=${nowPage+1 }">[&gt;]</a>
						</c:if>
					</td>
				</tr>
			</table>
			

	</div>

</body>
</html>