package com.reviewfinder.board;

import com.reviewfinder.action.Action;
import com.reviewfinder.action.ActionForward;
import com.reviewfinder.board.dao.BoardDAO;
import com.reviewfinder.board.dao.BoardDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BoardWriteOKAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) {
		ActionForward forward = new ActionForward();
		
		BoardDAO bdao = new BoardDAO();
		BoardDTO bdto = new BoardDTO();
		
		String boardcate = req.getParameter("boardcate");
		String username = req.getParameter("username");
		String boardtitle = req.getParameter("boardtitle");
		String boardcontents = req.getParameter("boardcontents");
		
		bdto.setBoardcate(boardcate);
		bdto.setUsername(username);
		bdto.setBoardtitle(boardtitle);
		bdto.setBoardcontents(boardcontents);
		
		forward.setRedirect(true);
		
		if(bdao.insertBoard(bdto)) {
			int boardnum = bdao.getSeq();

			forward.setPath("/board/BoardView.bo?boardnum="+boardnum);
			
			} else {
			
				forward.setPath("/board/BoardWrite.bo?flag=false");
			}
		
		return forward;
	}
 
}
