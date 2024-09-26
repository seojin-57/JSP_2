package com.reviewfinder.board;

import java.io.IOException;
import java.util.Iterator;

import com.reviewfinder.action.Action;
import com.reviewfinder.action.ActionForward;
import com.reviewfinder.board.dao.BoardDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CategoryViewAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) {

		ActionForward forward = new ActionForward();
		BoardDAO bdao = new BoardDAO();
		
		String boardcate = req.getParameter("boardcate");
		
		if(bdao.CategoryView(boardcate)) {
			forward.setRedirect(true);
			forward.setPath("/qnaboard/Category_view.bo?boardcate="+boardcate);
		}
		
		return forward;
	}

}
