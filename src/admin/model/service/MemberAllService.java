package admin.model.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import admin.model.dao.AllListDao;
import admin.model.vo.Member;
import admin.model.vo.PageInfo;
import admin.model.vo.Search;

import static common.JDBCTemplate.*;

public class MemberAllService {
	private AllListDao ald = new AllListDao();
	
	public Map<String, Object> selectMemberList(int page, Search s) {
		Connection conn = getConnection();
		
		int listCount = ald.getMemberListCount(conn,s); 
		System.out.println(listCount);
		
		
		PageInfo pi = new PageInfo(page, listCount,10,10);
		//System.out.println("pi : "+pi);
		
		List<Member> memberList = ald.selectMemberList(conn,pi,s);
		
		Map<String,Object> returnMap = new HashMap<>();
		
		returnMap.put("pi", pi);
		returnMap.put("memberList", memberList);
		//System.out.println(returnMap);
		return returnMap;
	}
}
