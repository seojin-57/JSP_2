package com.reviewfinder.movie;

import com.reviewfinder.action.Action;
import com.reviewfinder.action.ActionForward;
import com.reviewfinder.movie.dao.MovieDAO;
import com.reviewfinder.movie.dao.MovieDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class TestAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) {
		ActionForward forward = new ActionForward();
			
		String movie_title = req.getParameter("movie_title");
		String movie_date = req.getParameter("movie_date");
		
		MovieDAO mdao = new MovieDAO();
		MovieDTO movie = new MovieDTO();
		
		movie = mdao.selectMovieFromDB(movie_title, movie_date);
		
		int idx = movie.getMovie_poster().indexOf("|");
		int idx2 = movie.getMovie_still_image().indexOf("|");
		
		String temp = movie.getMovie_poster().substring(0,idx);
		String temp2 = movie.getMovie_still_image().substring(0,idx2);
		
		movie.setMovie_poster(temp);
		movie.setMovie_still_image(temp2);
		
		req.setAttribute("moviedto", movie);
		
		forward.setRedirect(false);
		forward.setPath("/movie/movieinfo.jsp");
		
		return forward;
	}
}
