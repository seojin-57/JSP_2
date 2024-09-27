package com.reviewfinder.main;

import com.reviewfinder.action.Action;
import com.reviewfinder.action.ActionForward;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SelectBoxRankAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) {
		ActionForward forward = new ActionForward();
		
		System.out.println("select");
		
		return forward;
	}
}
