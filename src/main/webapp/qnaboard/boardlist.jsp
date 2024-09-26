<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

 <link rel="stylesheet" href="boardlist.css">	
 
</head>

<body>
	<div>
		<form action="/qnaboard/category_view.bo" id="catechoice" style="display: inline-block; margin-right: 20px; border: 0px;">
        	<select name="category" onclick="/qnaboard/Category_view.bo">
	            <option value="카테고리">카테고리</option>
	            <option value="공지">공지</option>
	            <option value="추천">추천</option>
	            <option value="문의">문의</option>            
	        </select>
    		<input type="button" value="내가 작성한 글만 보기" class="myqnachoice" onclick="location href='/qnaboard/Mywrite_view.bo'">
    	
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
				  <c:if test="${board.boardcate == '공지'}">
		          <tr align="center" valign="middle" onmouseover="this.style.background='#bbdefb'"
		            		onmouseout="this.style.background=''">
		            <td height="23px" class="${board.boardcate == '공지' ? 'change-color' : ''}">${board.boardcate }</td>
		               <td height="23px">${board.boardnum }</td>
		               <td height="23px"><a href="/qnaboard/BoardView.bo?boardnum=${board.boardnum }" > ${board.boardtitle }</a> </td>
		               <td height="23px">${board.username }</td>
		               <td height="23px">${board.boarddate }</td>
		            </tr>
		           </c:if>
		         </c:forEach>
		         <c:forEach var="board" items="${boardList}">
		              <c:if test="${board.boardcate != '공지'}">
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