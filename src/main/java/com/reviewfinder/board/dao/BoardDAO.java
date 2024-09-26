package com.reviewfinder.board.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.reviewfinder.mybatis.SqlMapConfig;

public class BoardDAO {

	SqlSessionFactory factory = SqlMapConfig.getFactory();
	SqlSession Session;
	
	public BoardDAO() {
		Session = factory.openSession(true);
	}
	
	
	
	
}
