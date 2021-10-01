package member.model.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import member.model.dao.MemberDao;
import member.model.vo.Member;
import static common.JDBCTemplate.*;

public class MemberService {
	private MemberDao md = new MemberDao();
	
	// 로그인
	public Member loginMember(String id, String pwd) {
		Connection conn = getConnection();
	
		Member loginUser = md.loginMember(conn, id, pwd);
		
		close(conn);
	
		return loginUser;
	}
	// 카카오 로그인 API
	
	// 회원가입
	public int insertMember(Member m) {
		Connection conn = getConnection();
	
		int result = md.insertMember(conn, m);
	
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
	
		return result;
	}
	
	// 회원정보 수정
	public Member updateMember(Member m) {
		Connection conn = getConnection();
		Member updateMember = null;
	
		int result = md.updateMember(conn, m);
	
		if(result > 0) {
			updateMember = md.selectMember(conn, m.getUserNo());
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
	
		return updateMember;
	}
	
	// 비밀번호 수정
	public Member updatePwd(int userNo, String userPwd, String newPwd) {
		Connection conn = getConnection();
		Member updateMember = null;
		
		int result = md.updateMember(conn, userNo, userPwd, newPwd);
		
		if(result > 0) {
			updateMember = md.selectMember(conn, userNo);
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return updateMember;
	}
	
	// 회원 탈퇴
	public int deleteMember(int userNo) {
		Connection conn = getConnection();
	
		int result = md.deleteMember(conn, userNo);
	
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
	
		return result;
	}
	
	// 아이디 찾기
	public Member searchId(String userName, String phone) {
		Connection conn = getConnection();
	
		Member mId = md.searchId(conn, userName, phone);
	
		if(mId == null) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
	
		System.out.println(mId);
		return mId;
	}
	
	// 비밀번호 찾기
	public int searchPwd(/* int userNo, */String userId, String newPwd) {
		Connection conn = getConnection();
		Member searchPwd = null;
	
		int result = md.searchPwd(conn, /* userNo, */userId, newPwd);
	
		if(result > 0) {
			searchPwd = md.selectMember(conn, userId);
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
	
		System.out.println(result);
		return result;
	}

	// 아이디 중복 체크
	public int idCheck(String userId) {
		Connection conn = getConnection();
		
		int result = md.idCheck(conn, userId);
		
		close(conn);
		
		return result;
	}

	// 닉네임 중복 체크
	public int nicknameCheck(String nickName) {
		Connection conn = getConnection();
		
		int result = md.nicknameCheck(conn, nickName);
		
		close(conn);
		
		return result;
	}
	/*
	// 아이디 찾기 jsp view용
	public String findId(String userName, String phone) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Connection conn = getConnection();
		
		String mid = null;
		
		try {
			String sql = "select id from member where user_name = ? and phone = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userName);
			pstmt.setString(2, phone);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				mid = rset.getString("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mid;
	}
	*/
	// 이메일 인증 API
	/*
	 * public Member confirmEmail() {
	 * 
	 * }
	 */
}