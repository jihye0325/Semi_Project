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

import admin.model.vo.Board;

import admin.model.vo.PageInfo;
import admin.model.vo.Search;

public class boardListDao {
	private Properties query = new Properties();

	public boardListDao() {
		String fileName = MemberManagementDao.class.getResource("/sql/management/boardlist-query.xml").getPath();
		try {
			query.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getBoardListCount(Connection conn, Search s) {
		PreparedStatement pstmt = null;
		ResultSet rset =null;
		int boardListCount = 0;
		String sql = query.getProperty("boardListCount");
		
		if(s.getSearchCondition() != null && s.getSearchValue() != null) {
			if(s.getSearchCondition().equals("username")) {
				sql = query.getProperty("boardNameSearchCount");
			}else if(s.getSearchCondition().equals("id")) {
				sql = query.getProperty("boardIdSearchCount");
			}
		}
		try {
			pstmt = conn.prepareStatement(sql);
			
			if(s.getSearchCondition() != null && s.getSearchValue() != null) {
				pstmt.setString(1, s.getSearchValue());
			}
						
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				boardListCount = rset.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return boardListCount;
	}

	public List<Board> selectBoardList(Connection conn, PageInfo pi, Search s) {
		PreparedStatement pstmt = null;
		ResultSet rset =null;
		List<Board> boardList = new ArrayList<>();
		String sql = query.getProperty("selectBoardList");
		//System.out.println("con : "+ s.getSearchCondition());
		//System.out.println("val : " +s.getSearchValue());
		
		if(s.getSearchCondition() != null && s.getSearchValue() != null) {
			if(s.getSearchCondition().equals("id")) {
				sql = query.getProperty("boardIdSearch");
			}else if(s.getSearchCondition().equals("b_title")) {
				sql = query.getProperty("b_titleSearch");
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

				boardList.add(new Board(
						rset.getInt("b_no"),
						rset.getInt("b_id"),
						rset.getInt("user_no"),
						rset.getString("b_title"),
						rset.getDate("b_date"),
						rset.getString("b_content"),
						rset.getDate("b_create_date"),
						rset.getDate("b_modify_date"),
						rset.getInt("b_count"),
						rset.getString("status"),
						rset.getString("companion"),
						rset.getString("b_tag"),
						rset.getString("id")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return boardList;
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
