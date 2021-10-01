package admin.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import admin.model.vo.Member;

public class MemberManagementDao {
	private Properties query = new Properties();

	public MemberManagementDao() {
		String fileName = MemberManagementDao.class.getResource("/sql/management/MemberManagement-query.xml").getPath();
		try {
			query.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Member> selectList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Member> memberList = new ArrayList<>();
		String sql = query.getProperty("selectList");

		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();

			while (rset.next()) {

				memberList.add(new Member(rset.getInt("user_no"), rset.getString("id"), rset.getString("password"),
						rset.getString("user_name"), rset.getString("phone"), rset.getString("gender"),
						rset.getString("nickname"), rset.getDate("sign_date"), rset.getDate("modify_date"),
						rset.getInt("authority"), rset.getString("status"), rset.getString("address")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return memberList;

	}
	//detail
	public Member selectMember(Connection conn, int user_no) {

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member m = null;
		String sql = query.getProperty("selectMember");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, user_no);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				m = new Member(rset.getInt("user_no"), rset.getString("id"), rset.getString("password"),
						rset.getString("user_name"), rset.getString("phone"), rset.getString("gender"),
						rset.getString("nickname"), rset.getDate("sign_date"), rset.getDate("modify_date"),
						rset.getInt("authority"), rset.getString("status"), rset.getString("address"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return m;
	}

	public int updateMember(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("updateMember");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, m.getUser_name());
			pstmt.setString(2, m.getId());
			pstmt.setInt(3, m.getAuthority());
			pstmt.setInt(4, m.getUser_no());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	
	// 권한 수정 중
	public int updateAuthority(Connection conn, int authority, int user_no) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("updateMemberAuthority");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, authority);
			pstmt.setInt(2, user_no);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;

	}

	public List<Member> selectList(Connection conn, String searchCondition, String searchValue) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Member> memberList = new ArrayList<>();
		String sql = "";
		if (searchCondition.equals("user_name")) {
			sql = query.getProperty("selectUserNameList");
		} else if (searchCondition.equals("id")) {
			sql = query.getProperty("selectIdList");
		}
		try {
			pstmt = conn.prepareStatement(sql);

			if (searchCondition.equals("user_name") || searchCondition.equals("id"))
				pstmt.setString(1, searchValue);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				memberList.add(new Member(rset.getInt("user_no"), rset.getString("id"), rset.getString("password"),
						rset.getString("user_name"), rset.getString("phone"), rset.getString("gender"),
						rset.getString("nickname"), rset.getDate("sign_date"), rset.getDate("modify_date"),
						rset.getInt("authority"), rset.getString("status"), rset.getString("address")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}

		return memberList;
	}

	public int deleteMember(Connection conn, int user_no) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("deleteMember");
		
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1,user_no);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

}
