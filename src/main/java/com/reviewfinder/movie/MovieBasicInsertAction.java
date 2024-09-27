package com.reviewfinder.movie;

import com.reviewfinder.action.Action;
import com.reviewfinder.action.ActionForward;
import com.reviewfinder.movie.dao.MovieDAO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MovieBasicInsertAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) {
		ActionForward forward = new ActionForward();
		
		// DB에 MOVIE 데이터가 있는지 검사 -> DB에서 MOVIE_DB SELECT
		// 있으면 
		MovieDAO mdao = new MovieDAO();
		boolean checkMovieDB = mdao.checkMoiveDB();
		if(checkMovieDB) {	// DB에 있으면 메인 페이지로 이동
			forward.setRedirect(true);
			forward.setPath("/main/main.jsp"); // 메인페이지 정해지면 교체
		}else {				// DB에 없으면 /movie/InsertMovieDB.mv로 보냄
			forward.setRedirect(true);
			forward.setPath("/movie/InsertMovieDB.mv");
		}
		
		return forward;
	}
}
