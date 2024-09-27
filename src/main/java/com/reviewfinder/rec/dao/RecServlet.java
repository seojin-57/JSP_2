package com.reviewfinder.rec.dao;

import java.io.IOException;
import java.util.List;
import com.reviewfinder.movie.dao.MovieDAO;
import com.reviewfinder.movie.dao.MovieDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RecServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 모델을 통해 데이터를 가져옴
        RecDAO recDAO = new RecDAO();
        List<RecDTO> movies = recDAO.getRandomMovies();

        // 가져온 영화 리스트를 request 객체에 저장
        request.setAttribute("movies", movies);

        // JSP로 포워딩
        request.getRequestDispatcher("rec_16.jsp").forward(request, response);
    }
}
