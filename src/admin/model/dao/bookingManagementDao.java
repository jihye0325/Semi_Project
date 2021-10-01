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
import admin.model.vo.Room;
import admin.model.vo.Search;

public class bookingManagementDao {
	private Properties query = new Properties();

	public bookingManagementDao() {
		String fileName = bookingManagementDao.class.getResource("/sql/management/bookingManagement-query.xml")
				.getPath();
		try {
			query.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getRoomCount(Connection conn, Search s) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int roomListCount = 0;
		String sql = query.getProperty("roomListCount");

		if (s.getSearchCondition() != null && s.getSearchValue() != null) {
			if (s.getSearchCondition().equals("r_name")) {
				sql = query.getProperty("roomNameSearchCount");
			}
		}

		try {
			pstmt = conn.prepareStatement(sql);

			if (s.getSearchCondition() != null && s.getSearchValue() != null) {
				pstmt.setString(1, s.getSearchValue());
			}
			rset = pstmt.executeQuery();

			if (rset.next()) {
				roomListCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return roomListCount;

	}

	public List<Room> selectRoomList(Connection conn, PageInfo pi, Search s) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Room> roomList = new ArrayList<>();
		String sql = query.getProperty("selectRoomList");
		System.out.println("con : " + s.getSearchCondition());
		System.out.println("val : " + s.getSearchValue());

		if (s.getSearchCondition() != null && s.getSearchValue() != null) {
			if (s.getSearchCondition().equals("r_name")) {
				sql = query.getProperty("roomNameSearch");
			}
		}

		try {
			pstmt = conn.prepareStatement(sql);

			int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			int paramIndex = 1;

			if (s.getSearchCondition() != null && s.getSearchValue() != null) {
				pstmt.setString(paramIndex++, s.getSearchValue());
			}

			pstmt.setInt(paramIndex++, startRow);
			pstmt.setInt(paramIndex++, endRow);

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

	public Room selectSalseInfo(Connection conn, int r_no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Room r = null;
		String sql = query.getProperty("selectSalseInfo");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, r_no);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				r = new Room(
						rset.getString("r_name"),
						rset.getString("r_tel"),
						rset.getInt("r_pay"),
						rset.getString("r_address"),
						rset.getString("host_name"),
						rset.getInt("money"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return r;
	}
}