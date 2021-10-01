package admin.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import admin.model.dao.MemberManagementDao;
import admin.model.vo.Member;



public class MemberManagementService {
	private MemberManagementDao mmd= new MemberManagementDao();
	
	public List<Member> selectList() {
		Connection conn = getConnection();
		List<Member> memberList = mmd.selectList(conn);
		close(conn);
		
		return memberList;
	
	}

	public List<Member> selectList(String searchCondition, String searchValue) {
		Connection conn = getConnection();
		List<Member> memberList = mmd.selectList(conn,searchCondition,searchValue);
		close(conn);
		
		return memberList;
	}

	public int updateMember(Member m) {
		Connection conn = getConnection();
		
		int result = mmd.updateMember(conn, m);

		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
		return result;
		
	}
	//detail
	public Member selectMember(int user_no) {
		Connection conn = getConnection();
		
		Member memberList = mmd.selectMember(conn,user_no);
		
		
		
		close(conn);
				
		return memberList;
		
	}

	public Member updateAuthority(int user_no) {
		
		Connection conn = getConnection();
		
		Member member = mmd.selectMember(conn, user_no);

		close(conn);
		
		return member;
	}
	
	//권한 수정
	public int updateMemberAuthority(int authority, int user_no) {
		Connection conn = getConnection();
		int result = mmd.updateAuthority(conn,authority, user_no);
		
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
				
		return result;
	}

	public int deleteMember(int user_no) {
		Connection conn = getConnection();
		int result = mmd.deleteMember(conn,user_no);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	
}
