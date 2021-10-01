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

import admin.model.vo.PageInfo;
import admin.model.vo.Report;
import admin.model.vo.Search;

public class reportManagementDao {
	private Properties query = new Properties();

	public reportManagementDao() {
		String fileName = MemberManagementDao.class.getResource("/sql/management/reportManagement-query.xml").getPath();
		try {
			query.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getReportListCount(Connection conn, Search s) {
		PreparedStatement pstmt = null;
		ResultSet rset =null;
		int reportListCount = 0;
		String sql = query.getProperty("reportListCount");
		try {
			pstmt = conn.prepareStatement(sql);
									
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				reportListCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return reportListCount;
	}

	public List<Report> selectReportList(Connection conn, PageInfo pi, Search s) {
		PreparedStatement pstmt = null;
		ResultSet rset =null;
		List<Report> reportList = new ArrayList<>();
		String sql = query.getProperty("selectReportList");
		System.out.println("con : "+ s.getSearchCondition());
		System.out.println("val : " +s.getSearchValue());
		
		if(s.getSearchCondition() != null && s.getSearchValue() != null) {
			if(s.getSearchCondition().equals("id")) {
				sql = query.getProperty("reportMemberIdSearch");
			}else if(s.getSearchCondition().equals("b_title")) {
				sql = query.getProperty("bTitleSearch");
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

				reportList.add(new Report(rset.getInt("report_no"),
						rset.getString("id"),
						rset.getString("report_con"),
						rset.getString("b_title")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return reportList;
		
	}

	public Report reportDetail(Connection conn, int report_no) {

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Report r = null;
		String sql = query.getProperty("reportDetail");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, report_no);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				r = new Report(rset.getInt("b_no"),
						rset.getInt("b_id"),
						rset.getString("id"),
						rset.getString("user_name"),
						rset.getString("report_con"),
						rset.getString("b_title"),
						rset.getString("b_content"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}

	public int deleteBoard(Connection conn, int b_no) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("deleteBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1,b_no);

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
