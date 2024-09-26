package com.reviewfinder.board;

import java.io.IOException;

import com.reviewfinder.action.Action;
import com.reviewfinder.action.ActionForward;
import com.reviewfinder.board.dao.BoardDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BoardListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) {
		
		ActionForward forward = new ActionForward();
		
		// db조회, /app/board/boardlist.jps
		BoardDAO bdao = new BoardDAO();

		//전체 게시글 개수
		int totalCnt = bdao.getBoardCnt();
	
		//넘겨받은 페이지
		String temp = req.getParameter("page");
		int page = 0;
		page = temp == null ? 1: Integer.parseInt(temp);

		// 페이징 처리 [1][2]…[n]
		int pageSize = 10;
		// 1페이지의 endRow = 10, 4페이지의 endRow = 40
		int endRow = page * 20;
		// 1페이지의 startRow = 1, 4페이지의 startRow = 31
		int startRow = endRow - 19;
		// [1][2]…[10] : [1], [11][12]…[20] : [11]
		int startPage = (page - 1)/pageSize*pageSize+1;
		// [1][2]…[10] : [10], [11][12]…[20] : [20]
		int endPage = startPage + pageSize -1;
		int totalPage = (totalCnt-1)/pageSize+1;
		
		endPage = endPage > totalPage? totalPage : endPage;
		
		
		req.setAttribute("boardList", bdao.getBoardList(startRow, endRow));
		req.setAttribute("totalCnt", totalCnt);
		
		//페이징 처리에 필요한 부분
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("nowPage", page);
		req.setAttribute("startPage", startPage);
		req.setAttribute("endPage", endPage);		
		
		forward.setPath("/qnaboard/boardlist.jsp");
		forward.setRedirect(false);//foward이동
		
		return forward;
	}
	
	
	
}
