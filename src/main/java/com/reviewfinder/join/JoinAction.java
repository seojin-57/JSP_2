package com.reviewfinder.join;

import java.io.IOException;

import com.reviewfinder.action.ActionForward;
import com.reviewfinder.member.dao.MemberDAO;
import com.reviewfinder.member.dao.MemberDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JoinAction implements com.reviewfinder.action.Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) {

		ActionForward forward = new ActionForward();
		
		MemberDAO mdao = new MemberDAO();
		MemberDTO mdto = new MemberDTO();
		
		String userid = req.getParameter("userid");
		String userpw = req.getParameter("userpw");
		String username = req.getParameter("username");
		String usergrade = req.getParameter("usergrade");
		
		mdto.setUserid(userid);
		mdto.setUserpw(userpw);
		mdto.setUsername(username);
		mdto.setUsergrade(usergrade);
		
		forward.setRedirect(true);
		
		if(mdao.join(mdto)) { 
			forward.setPath("http://localhost:8081/join/login.jsp");
			
		} else {
			
			forward.setPath("http://localhost:8081/join/join_view.jsp");
		}
		

		return forward;
	}

}
