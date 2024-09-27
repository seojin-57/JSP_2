package com.reviewfinder.join;

import com.reviewfinder.action.Action;
import com.reviewfinder.action.ActionForward;
import com.reviewfinder.member.dao.MemberDAO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) {

		ActionForward forward = new ActionForward();
		
		MemberDAO mdao = new MemberDAO();
		
		String userid = req.getParameter("userid");
		String userpw = req.getParameter("userpw");
	
		forward.setRedirect(true);
		
		if (mdao.login(userid, userpw) == 1){ //로그인 성공(성공 시 메인 페이지로 연결해야함)
			forward.setPath("/index.jsp");
		} else {						//로그인 실패
			forward.setPath("/join/login.jsp?flag=false");
		}
	
		return forward;
	}
}
