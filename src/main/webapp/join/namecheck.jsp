<%@page import="com.reviewfinder.member.dao.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String username = request.getParameter("username");
	
	MemberDAO mdao = new MemberDAO();

	if(mdao.checkName(username) > 0){
		// 회원가입 불가능한 이름
		out.print("불가능");
	}else{
		//회원가입 가능한 이름
		out.print("OK");
	}
	
%>