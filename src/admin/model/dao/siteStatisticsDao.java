package admin.model.dao;

import java.io.FileInputStream;
import static common.JDBCTemplate.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class siteStatisticsDao {
	private Properties query = new Properties();

	public siteStatisticsDao() {
		String fileName = MemberManagementDao.class.getResource("/sql/management/siteStatistics-query.xml").getPath();
		try {
			query.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getMemberListCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int memberListCount = 0;
		String sql = query.getProperty("memberListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
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

	public int getBoardListcount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int boardListcount = 0;
		String sql = query.getProperty("boardListcount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				boardListcount = rset.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		
		return boardListcount;
	}

	public int getNoticeListCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int noticeListcount = 0;
		String sql = query.getProperty("noticeListcount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				noticeListcount = rset.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		
		return noticeListcount;
	}

	public int getStoreListCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int storeListcount = 0;
		String sql = query.getProperty("storeListcount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				storeListcount = rset.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		
		return storeListcount;
	}

	public int getRoomListCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int roomListcount = 0;
		String sql = query.getProperty("roomListcount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				roomListcount = rset.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		
		return roomListcount;
	}
	
}
