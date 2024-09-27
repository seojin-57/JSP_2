package com.reviewfinder.main;

import java.util.HashMap;

import com.reviewfinder.action.Action;
import com.reviewfinder.action.ActionForward;
import com.reviewfinder.main.dao.MainMovieDAO;
import com.reviewfinder.main.dao.MainMovieDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SelectBoxRankAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) {
		ActionForward forward = new ActionForward();
		MainMovieDAO mdao = new MainMovieDAO();
		
		mdao.getBoxMovie();
		
		forward.setRedirect(true);
		forward.setPath("/main/main.jsp");
		
		return forward;
	}
}
