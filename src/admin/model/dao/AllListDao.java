package admin.model.dao;

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
import admin.model.vo.Notice;
import admin.model.vo.PageInfo;
import admin.model.vo.Search;
import admin.model.vo.Room;
import store.model.vo.Store;

import static common.JDBCTemplate.*;

public class AllListDao {
	private Properties query = new Properties();

	public AllListDao() {
		String fileName = MemberManagementDao.class.getResource("/sql/management/listAll-query.xml").getPath();
		try {
			query.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getMemberListCount(Connection conn, Search s) {
		PreparedStatement pstmt = null;
		ResultSet rset =null;
		int memberListCount = 0;
		String sql = query.getProperty("memberListCount");
		
		if(s.getSearchCondition() != null && s.getSearchValue() != null) {
			if(s.getSearchCondition().equals("username")) {
				sql = query.getProperty("memberNameSearchCount");
			}else if(s.getSearchCondition().equals("id")) {
				sql = query.getProperty("memberIdSearchCount");
			}
		}
		
				
		try {
			pstmt = conn.prepareStatement(sql);
			
			if(s.getSearchCondition() != null && s.getSearchValue() != null) {
				pstmt.setString(1, s.getSearchValue());
			}
						
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				memberListCount = rset.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return memberListCount;
	}

	public List<Member> selectMemberList(Connection conn, PageInfo pi, Search s) {
		PreparedStatement pstmt = null;
		ResultSet rset =null;
		List<Member> memberList = new ArrayList<>();
		String sql = query.getProperty("selectMemberList");
		//System.out.println("con : "+ s.getSearchCondition());
		//System.out.println("val : " +s.getSearchValue());
		
		if(s.getSearchCondition() != null && s.getSearchValue() != null) {
			if(s.getSearchCondition().equals("username")) {
				sql = query.getProperty("memberNameSearch");
			}else if(s.getSearchCondition().equals("id")) {
				sql = query.getProperty("memberIdSearch");
			}
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getPage()- 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			int paramIndex = 1;
			
			
			if(s.getSearchCondition() != null && s.getSearchValue() != null) {
				pstmt.setString(paramIndex++, s.getSearchValue());
			}
			
			
			pstmt.setInt(paramIndex++, startRow);
			pstmt.setInt(paramIndex++, endRow);
			
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

	public List<Store> selecStoretList(Connection conn, PageInfo pi, Search s) {
		PreparedStatement pstmt = null;
		ResultSet rset =null;
		List<Store> storeList = new ArrayList<>();
		String sql = query.getProperty("selectStoreList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getPage()- 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();

			while (rset.next()) {

				storeList.add(new Store(rset.getInt("s_no"),
						rset.getString("s_name"),
						rset.getString("s_tel"),
						rset.getString("s_time"),
						rset.getString("menu"),
						rset.getString("s_address"),
						rset.getInt("s_count"),
						rset.getString("s_status"),
						rset.getDate("s_create_date")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return storeList;
	}
	
	public int getStoreListCount(Connection conn, Search s) {
		PreparedStatement pstmt = null;
		ResultSet rset =null;
		int storeListCount = 0;
		String sql = query.getProperty("storeListCount");
		
		if(s.getSearchCondition() != null && s.getSearchValue() != null) {
			if(s.getSearchCondition().equals("s_name")) {
				sql = query.getProperty("storeNameSearch");
			}
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			if(s.getSearchCondition() != null && s.getSearchValue() != null) {
				pstmt.setString(1, s.getSearchValue());
			}
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				storeListCount = rset.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return storeListCount;
		
	}


	public int deleteStore(Connection conn, int s_no) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("deleteStore");
		
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1,s_no);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
		
	}

	public List<Room> selectRoomList(Connection conn, PageInfo pi, Search s) {
		PreparedStatement pstmt = null;
		ResultSet rset =null;
		List<Room> roomList = new ArrayList<>();
		String sql = query.getProperty("selectRoomList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getPage()- 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();

			while (rset.next()) {

				roomList.add(new Room(rset.getInt("r_no"),
						rset.getString("r_name"),
						rset.getString("r_tel"),
						rset.getString("r_info"),
						rset.getInt("r_pay"),
						rset.getString("r_address"),
						rset.getInt("user_no"),
						rset.getDate("r_start"),
						rset.getDate("r_end"),
						rset.getString("r_status"),
						rset.getInt("r_head")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return roomList;
	}

	public int deleteRoom(Connection conn, int r_no) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("deleteRoom");
		
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1,r_no);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
		
	}
	public List<Notice> selecNoticeList(Connection conn, PageInfo pi, Search s) {
		PreparedStatement pstmt = null;
		ResultSet rset =null;
		List<Notice> noticeList = new ArrayList<>();
		String sql = query.getProperty("selectNoticeList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			
			int startRow = (pi.getPage()- 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				
				noticeList.add(new Notice(rset.getInt("n_no"),
						rset.getInt("user_no"),
						rset.getString("n_title"),
						rset.getString("n_content"),
						rset.getDate("n_date"),
						rset.getInt("n_hit"),
						rset.getString("n_status"),
						rset.getDate("n_modify_date")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return noticeList;
	}
	
	public int deleteNotice(Connection conn, int n_no) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("deleteNotice");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1,n_no);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
		
	}

	public int getNoticeListCount(Connection conn, Search s) {
		PreparedStatement pstmt = null;
		ResultSet rset =null;
		int noticeListCount = 0;
		String sql = query.getProperty("noticeListCount");
		
		if(s.getSearchCondition() != null && s.getSearchValue() != null) {
			if(s.getSearchCondition().equals("n_title")) {
				sql = query.getProperty("noticeTitleSearch");
			}
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			if(s.getSearchCondition() != null && s.getSearchValue() != null) {
				pstmt.setString(1, s.getSearchValue());
			}
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				noticeListCount = rset.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return noticeListCount;
	}
	public int getRoomListCount(Connection conn, Search s) {
		PreparedStatement pstmt = null;
		ResultSet rset =null;
		int roomListCount = 0;
		String sql = query.getProperty("roomListCount");
		
		if(s.getSearchCondition() != null && s.getSearchValue() != null) {
			if(s.getSearchCondition().equals("r_name")) {
				sql = query.getProperty("roomNameSearch");
			}
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			if(s.getSearchCondition() != null && s.getSearchValue() != null) {
				pstmt.setString(1, s.getSearchValue());
			}
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				roomListCount = rset.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return roomListCount;
	}


	
	

}
