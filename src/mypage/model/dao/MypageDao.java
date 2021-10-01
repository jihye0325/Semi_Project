package mypage.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import board.model.dao.BoardDao;
import board.model.vo.Attachment;
import board.model.vo.Board;
import board.model.vo.PageInfo;
import booking.model.vo.R_Attachment;
import booking.model.vo.Room;
import mypage.model.vo.Message;
import oracle.net.aso.p;
import store.model.vo.Store;

import static common.JDBCTemplate.*;

public class MypageDao {
	private Properties query = new Properties();

	public MypageDao() {
		String fileName = BoardDao.class.getResource("/sql/mypage/mypage-query.xml").getPath();
		try {
			query.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 받은 쪽지의 수
	public int getMessageReveiveListCount(Connection conn, int userNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int listCount = 0;
		String sql = query.getProperty("getMessageReveiveListCount");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userNo);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return listCount;
	}

	// 보낸 쪽지의 수
	public int getMessageSendListCount(Connection conn, int userNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int listCount = 0;
		String sql = query.getProperty("getMessageSendListCount");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userNo);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return listCount;
	}

	// 게시글 수
	public int getBoardListCount(Connection conn, int userNo, String searchValue) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int listCount = 0;
		String sql = query.getProperty("getBoardListCount");

		if (searchValue != null) {
			sql = query.getProperty("getSearchedBoardListCount");
		}

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userNo);

			if (searchValue != null) {
				pstmt.setString(2, searchValue);
			}

			rset = pstmt.executeQuery();

			while (rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return listCount;
	}

	// 페이징 처리 된 받은 쪽지 리스트 출력
	public ArrayList<Message> selectMessageReceiveList(Connection conn, PageInfo pi, int userNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Message> messageList = new ArrayList<Message>();
		String sql = query.getProperty("selectMessageReceiveList");

		try {
			pstmt = conn.prepareStatement(sql);

			int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			int paramIndex = 1;

			pstmt.setInt(paramIndex++, userNo);
			pstmt.setInt(paramIndex++, startRow);
			pstmt.setInt(paramIndex++, endRow);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				messageList.add(new Message(rset.getString("nickname"), rset.getInt("msg_no"),
						rset.getString("msg_title"), rset.getString("msg_content"), rset.getDate("msg_date")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return messageList;
	}

	// 페이징 처리 된 보낸 쪽지 리스트 출력
	public ArrayList<Message> selectMessageSendList(Connection conn, PageInfo pi, int userNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Message> messageList = new ArrayList<Message>();
		String sql = query.getProperty("selectMessageSendList");

		try {
			pstmt = conn.prepareStatement(sql);

			int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			int paramIndex = 1;

			pstmt.setInt(paramIndex++, userNo);
			pstmt.setInt(paramIndex++, startRow);
			pstmt.setInt(paramIndex++, endRow);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				messageList.add(new Message(rset.getString("nickname"), rset.getInt("msg_no"),
						rset.getString("msg_title"), rset.getString("msg_content"), rset.getDate("msg_date")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return messageList;
	}

	// 페이징 처리 된 게시글 리스트 출력
	public List<Board> selectBoardList(Connection conn, PageInfo pi, int userNo, String searchValue) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		List<Board> boardList = new ArrayList<Board>();
		String sql = query.getProperty("selectBoardList");

		if (searchValue != null) {
			sql = query.getProperty("selectSearchedBoardList");
		}
		int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
		int endRow = startRow + pi.getBoardLimit() - 1;
		int paramIndex = 1;

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(paramIndex++, userNo);
			if (searchValue != null) {
				pstmt.setString(paramIndex++, searchValue);
			}
			pstmt.setInt(paramIndex++, startRow);
			pstmt.setInt(paramIndex++, endRow);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				boardList.add(new Board(rset.getInt("b_no"), rset.getInt("b_id"), rset.getString("nickname"),
						rset.getString("b_title"), rset.getDate("b_modify_date"), rset.getInt("b_count")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		return boardList;
	}

	public int insertRoom(Connection conn, Room room, String start, String end) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("insertRoom");
		System.out.println(room.getR_info());

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, room.getR_name());
			pstmt.setString(2, room.getR_tel());
			pstmt.setString(3, room.getR_info());
			pstmt.setInt(4, room.getR_pay());
			pstmt.setString(5, room.getR_address());
			pstmt.setInt(6, room.getUser_no());
			pstmt.setString(7, start);
			pstmt.setString(8, end);
			pstmt.setInt(9, room.getR_head());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	// image 테이블에 이미지 저장
	// room 과 image를 매치시킬 테이블에 각 SEQ의 currval로 저장하여 매칭시킨다.
	public int insertRoomImage(Connection conn, List<R_Attachment> roomimage) {
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		int result1 = 0, result2 = 0;
		String sql1 = query.getProperty("insertRoomImage");
		String sql2 = query.getProperty("matchRommImage");
		try {
			for (int i = 0; i < roomimage.size(); i++) {
				R_Attachment at = roomimage.get(i);
				pstmt1 = conn.prepareStatement(sql1);
				pstmt2 = conn.prepareCall(sql2);

				pstmt1.setString(1, at.getRoute());
				pstmt1.setString(2, at.getImage_name());
				pstmt1.setString(3, at.getImage_r_name());
				pstmt1.setInt(4, at.getImage_level());

				result1 += pstmt1.executeUpdate();
				result2 = pstmt2.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt1);
			close(pstmt2);
		}

		return result2 > 0 ? result1 : 0;
	}

	public List<Room> selectRoomList(Connection conn, int userNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Room> roomList = new ArrayList<Room>();
		String sql = query.getProperty("selectRoomList");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, userNo);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				roomList.add(new Room(rset.getInt("r_no"), rset.getString("r_name"), rset.getString("route"),
						rset.getString("image_r_name")));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return roomList;
	}

	public List<Room> selectRequestList(Connection conn, int userNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Room> requestList = new ArrayList<Room>();
		String sql = query.getProperty("selectRequestList");
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, userNo);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				requestList.add(new Room(rset.getInt("r_no"), rset.getInt("booking_no"), rset.getString("r_name"),
						rset.getString("nickname"), rset.getDate("book_start"), rset.getDate("book_end"),
						rset.getString("route"), rset.getString("image_r_name")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return requestList;
	}

	public List<Room> selectRoomWishList(Connection conn, int userNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Room> roomList = new ArrayList<Room>();
		String sql = query.getProperty("selectRoomWishList");
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, userNo);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				roomList.add(new Room(rset.getInt("r_no"), rset.getString("r_name"), rset.getString("nickname"),
						rset.getString("route"), rset.getString("image_r_name")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return roomList;
	}

	public List<Store> selectStroeWishList(Connection conn, int userNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Store> storeList = new ArrayList<>();
		String sql = query.getProperty("selectStoreWishList");
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, userNo);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				storeList.add(new Store(rset.getInt("s_no"), rset.getString("s_name"), rset.getString("s_tel"),
						rset.getString("route"), rset.getString("image_r_name")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return storeList;
	}

	public List<Room> selectBookList(Connection conn, int userNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Room> roomList = new ArrayList<Room>();
		String sql = query.getProperty("selectBookList");
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, userNo);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				roomList.add(new Room(rset.getInt("r_no"), rset.getInt("booking_no"), rset.getString("booking_status"),
						rset.getString("r_name"), rset.getString("nickname"), rset.getDate("book_start"),
						rset.getDate("book_end"), rset.getString("host_confirm"), rset.getString("route"),
						rset.getString("image_r_name")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return roomList;
	}

	// 예약 확정 처리
	public int confirmBooking(Connection conn, int booking_no) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("confirmBooking");
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, booking_no);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	// 예약 취소 처리
	public int cancleBooking(Connection conn, int booking_no) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("cancleBooking");
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, booking_no);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteMSG(Connection conn, int msgNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("deleteMSG");
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, msgNo);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteSendMSG(Connection conn, int msgNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("deleteSendMSG");
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, msgNo);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int confirmRequest(Connection conn, int booking_no) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("confirmRequest");
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, booking_no);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int cancleRequest(Connection conn, int booking_no) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("cancleRequest");
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, booking_no);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

}
