package mypage.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import board.model.vo.Board;
import board.model.vo.PageInfo;
import booking.model.vo.Room;
import mypage.model.dao.MypageDao;
import mypage.model.vo.Message;
import store.model.vo.Store;

public class MypageService {
	private MypageDao mypageDao = new MypageDao();

	public Map<String, Object> selectMessageList(int page, int userNo) {
		Connection conn = getConnection();

		// 쪽지 수
		int rCount = mypageDao.getMessageReveiveListCount(conn, userNo);
		int sCount = mypageDao.getMessageSendListCount(conn, userNo);
		// 페이지 객체 만들기
		PageInfo R_pi = new PageInfo(page, rCount, 10, 10);
		PageInfo S_pi = new PageInfo(page, sCount, 10, 10);
		// 페이징 된 쪽지 목록
		ArrayList<Message> messageReceiveList = mypageDao.selectMessageReceiveList(conn, R_pi, userNo);
		ArrayList<Message> messageSendList = mypageDao.selectMessageSendList(conn, S_pi, userNo);
		
		Map<String, Object> returnMap = new HashMap<String, Object>();

		returnMap.put("R_pi", R_pi);
		returnMap.put("messageReceiveList", messageReceiveList);
		returnMap.put("messageSendList", messageSendList);
		return returnMap;
	}

	// 마이페이지 - 게시글 관리
	public Map<String, Object> selectBoardList(int page, int userNo, String searchValue) {
		Connection conn = getConnection();

		int listCount = mypageDao.getBoardListCount(conn, userNo, searchValue);

		PageInfo pi = new PageInfo(page, listCount, 10, 10);

		List<Board> boardList = mypageDao.selectBoardList(conn, pi, userNo, searchValue);

		Map<String, Object> returnMap = new HashMap<>();

		returnMap.put("pi", pi);
		returnMap.put("boardList", boardList);

		return returnMap;
	}

	public int insertRoom(Room room, String startDate, String endDate) {
		Connection conn = getConnection();

		int result1 = mypageDao.insertRoom(conn, room, startDate, endDate);
		System.out.println("result1="+result1);
		int result2 = mypageDao.insertRoomImage(conn, room.getRoomimage());
		System.out.println("result2="+result2);
		if (result1 > 0 && result2 == room.getRoomimage().size()) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result1 > 0 && result2 == room.getRoomimage().size() ? 1 : 0;
	}

	public List<Room> selectRoomList(int userNo) {
		Connection conn = getConnection();

		List<Room> roomList = mypageDao.selectRoomList(conn, userNo);
		System.out.println("Mypage서비스( roomList ) = " + roomList);

		close(conn);

		return roomList;
	}
	

	public List<Room> selectRequestList(int userNo) {
		Connection conn = getConnection();

		List<Room> requestList = mypageDao.selectRequestList(conn, userNo);

		close(conn);

		return requestList;
	}

	public List<Room> selectRoomWishList(int userNo) {
		Connection conn = getConnection();

		List<Room> roomList = mypageDao.selectRoomWishList(conn, userNo);

		close(conn);

		return roomList;
	}

	public List<Store> selectStoreWishList(int userNo) {
		Connection conn = getConnection();

		List<Store> storeList = mypageDao.selectStroeWishList(conn, userNo);

		close(conn);

		return storeList;
	}

	public List<Room> selectBookList(int userNo) {
		Connection conn = getConnection();

		List<Room> roomList = mypageDao.selectBookList(conn, userNo);

		close(conn);

		return roomList;
	}

	// 예약 확정 처리
	public int confirmBooking(int booking_no) {
		Connection conn = getConnection();
		int result = mypageDao.confirmBooking(conn, booking_no);

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}

	public int cancleBooking(int booking_no) {
		Connection conn = getConnection();
		int result = mypageDao.cancleBooking(conn, booking_no);

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}

	public int deleteMSG(int msgNo) {
		Connection conn = getConnection();
		int result = mypageDao.deleteMSG(conn, msgNo);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}

	public int deleteSendMSG(int msgNo) {
		Connection conn = getConnection();
		int result = mypageDao.deleteSendMSG(conn, msgNo);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}

	public int confirmRequest(int booking_no) {
		Connection conn = getConnection();
		int result = mypageDao.confirmRequest(conn, booking_no);

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}

	public int cancleRequest(int booking_no) {
		Connection conn = getConnection();
		int result = mypageDao.cancleRequest(conn, booking_no);

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}

}
