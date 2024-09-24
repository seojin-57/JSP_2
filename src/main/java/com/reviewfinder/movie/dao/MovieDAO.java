package com.reviewfinder.movie.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.reviewfinder.mybatis.SqlMapConfig;

public class MovieDAO {
	SqlSessionFactory factory = SqlMapConfig.getFactory();
	SqlSession session;
	
	public MovieDAO() {
		session = factory.openSession(true);
	}

	public boolean checkMoiveDB() {
		
		
		
		return false;
	}
	
	
}
