package com.reviewfinder.movie.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.reviewfinder.mybatis.SqlMapConfig;

public class MovieDAO {
	SqlSessionFactory factory = SqlMapConfig.getFactory();
	SqlSession session;
	
	public MovieDAO() {
		session = factory.openSession(true);
	}

	// DB에 영화가 있으면 true, 없으면 false
	public boolean checkMoiveDB() {
		boolean result = false;
		int cnt = session.selectOne("Dbtest.checkMoiveDB");
		if(cnt > 0){
			result = true;
		}
		
		return result;
	}

	public void insertMovieDB() {
		
	}
	
	
}
