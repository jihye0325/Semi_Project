package member.model.dao;

import static common.JDBCTemplate.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import member.model.vo.Member;

public class MemberDao {
	// sql
	private Properties query = new Properties();
	
	public MemberDao() {
		String fileName = MemberDao.class.getResource("/sql/member/member-query.xml").getPath();
		try {
			query.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 로그인
	public Member loginMember(Connection conn, String id, String pwd) {
		Member loginUser = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = query.getProperty("loginMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
	
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
	
			rset = pstmt.executeQuery();
	
			if(rset.next()) {
				loginUser = new Member(rset.getInt("user_no"),
										rset.getString("id"),
										rset.getString("password"),
										rset.getString("user_name"),
										rset.getString("phone"),
										rset.getString("gender"),
										rset.getString("nickname"),
										rset.getDate("sign_date"),
										rset.getDate("modify_date"),
										rset.getInt("authority"),
										rset.getString("status"),
										rset.getString("address"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return loginUser;
	}
	
	// 카카오 로그인 API
	
	// 회원가입
	public int insertMember(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("insertMember");
	
		try {
			pstmt = conn.prepareStatement(sql);
			/* 개인용
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserPwd());
			pstmt.setString(3, m.getUserName());
			pstmt.setString(4, m.getPhone());
			pstmt.setString(5, m.getGender());
			pstmt.setString(6, m.getNickName());
			pstmt.setString(7, m.getAddress());
			*/
			// 팀용
			pstmt.setString(1, m.getUserName());
			pstmt.setString(2, m.getUserId());
			pstmt.setString(3, m.getUserPwd());
			pstmt.setString(4, m.getGender());
			pstmt.setString(5, m.getNickName());
			pstmt.setString(6, m.getAddress());
			pstmt.setString(7, m.getPhone());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	// 회원 조회 - userNo
	public Member selectMember(Connection conn, int userNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member mem = null;
		String sql = query.getProperty("selectMember");
	
		try {
			pstmt = conn.prepareStatement(sql);
	
			pstmt.setInt(1, userNo);
	
			rset = pstmt.executeQuery();
	
			if(rset.next()) {
				mem = new Member(rset.getInt("user_no"),
								 rset.getString("id"),
								 rset.getString("password"),
								 rset.getString("user_name"),
								 rset.getString("phone"),
								 rset.getString("gender"),
								 rset.getString("nickname"),
								 rset.getDate("sign_date"),
								 rset.getDate("modify_date"),
								 rset.getInt("authority"),
								 rset.getString("status"),
								 rset.getString("address"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mem;
	}
	
	// 회원정보 수정
	public int updateMember(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("updateMember");
	
		try {
			pstmt = conn.prepareStatement(sql);
	
			pstmt.setString(1, m.getUserPwd());
			pstmt.setString(2, m.getPhone());
			pstmt.setString(3, m.getNickName());
			pstmt.setString(4, m.getAddress());
			pstmt.setInt(5, m.getUserNo());
	
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	// 비밀번호 수정
	public int updateMember(Connection conn, int userNo, String userPwd, String newPwd) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("updatePwd");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, newPwd);
			pstmt.setInt(2, userNo);
			pstmt.setString(3, userPwd);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	

	// 회원 탈퇴
	public int deleteMember(Connection conn, int userNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("deleteMember");
	
		try {
			pstmt = conn.prepareStatement(sql);
	
			pstmt.setInt(1, userNo);
	
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	// 아이디 찾기
	public Member searchId(Connection conn, String userName, String phone) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member mId = null;
		String sql = query.getProperty("searchId");
	
		try {
			pstmt = conn.prepareStatement(sql);
	
			pstmt.setString(1, userName);
			pstmt.setString(2, phone);
	
			rset = pstmt.executeQuery();
	
			if(rset.next()) {
				mId = new Member(rset.getString("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(mId);
		return mId;
	}
	
	// 이메일 인증 API - 비밀번호 찾기
	public int searchPwd(Connection conn, /* int userNo, */String userId, String newPwd) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("searchPwd");
	
		try {
			pstmt = conn.prepareStatement(sql);
	
			pstmt.setString(1, newPwd);
			pstmt.setString(2, userId);
	
			result = pstmt.executeUpdate();
	
			// mPwd = md.searchPwd(conn, /* userNo, */userId, sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(result);
		return result;
	}
	
	// 이메일 인증 API - userId 회원조회
	public Member selectMember(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member mem = null;
		String sql = query.getProperty("selectMemberId");
	
		try {
			pstmt = conn.prepareStatement(sql);
	
			pstmt.setString(1, userId);
	
			rset = pstmt.executeQuery();
	
			if(rset.next()) {
				mem = new Member(rset.getInt("user_no"),
								 rset.getString("id"),
								 rset.getString("password"),
								 rset.getString("user_name"),
								 rset.getString("phone"),
								 rset.getString("gender"),
								 rset.getString("nickname"),
								 rset.getDate("sign_date"),
								 rset.getDate("modify_date"),
								 rset.getInt("authority"),
								 rset.getString("status"),
								 rset.getString("address"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mem;
	}

	// 아이디 중복 체크
	public int idCheck(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String sql = query.getProperty("idCheck");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		return result;
	}

	// 닉네임 중복 체크
	public int nicknameCheck(Connection conn, String nickName) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String sql = query.getProperty("nicknameCheck");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, nickName);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		return result;
	}

	// 아이디 찾기 - jsp용
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

			if (rset.next()) {
				mid = rset.getString("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mid;
	}
}