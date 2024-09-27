package com.reviewfinder.board.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.reviewfinder.mybatis.SqlMapConfig;

public class BoardDAO {

	SqlSessionFactory factory = SqlMapConfig.getFactory();
	SqlSession Session;
	
	public BoardDAO() {
		Session = factory.openSession(true);
	}

	public int getBoardCnt() {
		return Session.selectOne("Board.getBoardCnt");
	}

	public List<BoardDTO> getBoardList(int startRow, int endRow) {
		HashMap<String, Integer> datas = new HashMap<>();
		datas.put("startRow", startRow);
		datas.put("endRow", endRow);
		
		List<BoardDTO> boardList = new ArrayList<>();
		boardList = Session.selectList("Board.getBoardList", datas);
		
		return boardList;
	}

	public boolean CategoryView(String boardcate) {
		boolean result = false;
		int cnt = Session.selectOne("Board.getCategoryCnt");
		System.out.println(cnt);
		if(cnt > 0) {
			result = true;
		}
		return result;
	}

	public boolean insertBoard(BoardDTO bdto) {
		boolean result = false;
		if(Session.insert("Board.insertBoard", bdto ) == 1) {
			result = true;
		}
		return result;
	}

	public int getSeq() {
		return Session.selectOne("Board.getSeq");
	}
	//글 쓸 때 권한 설정 하고 싶어서 만드는중......
	public String getGrade() {
		return Session.selectOne("Board.getGrade");
	}

	 public BoardDTO getDetail(int boardnum) { return
	 (BoardDTO)Session.selectOne("Board.getDetail", boardnum);
	 }
	
	
	
	
	
}
