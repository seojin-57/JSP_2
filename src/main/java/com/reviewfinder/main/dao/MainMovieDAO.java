package com.reviewfinder.main.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.reviewfinder.mybatis.SqlMapConfig;

public class MainMovieDAO {
	SqlSessionFactory factory = SqlMapConfig.getFactory();
	SqlSession session;

}
