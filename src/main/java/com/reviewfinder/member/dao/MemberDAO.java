package com.reviewfinder.member.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.reviewfinder.mybatis.SqlMapConfig;

public class MemberDAO {
	
	SqlSessionFactory factory = SqlMapConfig.getFactory();
	SqlSession Session;
	
	public MemberDAO() {
		Session = factory.openSession(true);
	}
	
	public int login(String userid, String userpw) { 

		int member = 0;

		HashMap<String, String> datas = new HashMap<>();
		datas.put("userid", userid);
		datas.put("userpw", userpw);
		
		member = Session.selectOne("Member.login", datas);
		return member;
	}

	public boolean join(MemberDTO mdto) {
		boolean result = false;
		if(Session.insert("Member.join", mdto) == 1) {
			result = true;
		}
		return result;
	}
	
	public int checkID(String userid) {
		int cnt = 0;
		cnt = Session.selectOne("Member.checkId", userid);
		return cnt;
	}
	
	public int checkName(String username) {
		int cnt = 0;
		cnt = Session.selectOne("Member.checkName", username);
		return cnt;
	}

}
