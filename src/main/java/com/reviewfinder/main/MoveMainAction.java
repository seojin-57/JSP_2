package com.reviewfinder.main;

import com.reviewfinder.action.Action;
import com.reviewfinder.action.ActionForward;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MoveMainAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) {
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(true);
		forward.setPath("/main/main.jsp");
		return forward;
	}
}
