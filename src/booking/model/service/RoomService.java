package booking.model.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import booking.model.dao.RoomDao;
import booking.model.vo.Booking;
import booking.model.vo.PayBook;
import booking.model.vo.R_Attachment;
import booking.model.vo.R_PageInfo;
import booking.model.vo.R_comment1;
import booking.model.vo.Room;
import booking.model.vo.Wish;

import static common.JDBCTemplate.*;

public class RoomService {
	private RoomDao rd = new RoomDao();
/*
	public List<Room> selectRoomList() {
		Connection conn = getConnection();
		List<Room> RoomList=rd.selectRoomList(conn);
		close(conn);
		System.out.println("RoomService : "+ RoomList);
		return RoomList;
	}
*/


	public Map<String, Object> selectRoomList(int page) {  // 2번
		Connection conn = getConnection();
		
//1. 게시글 총 개수
		int listCount = rd.getListCount(conn); // 3번
		//System.out.println("총 게시글 개수 : "+ listCount); // 8번
		
//2. R_PageInfo 페이징 객체 만들기  // 9번
		// public R_PageInfo(int page, int listCount, int pageLimit, int roomLimit) 
		R_PageInfo pi = new R_PageInfo(page, listCount, 10,4); // 10번
		//System.out.println("페이지 정보 pi : " + pi);
		// 결과 : pi : R_PageInfo [page=1, listCount=91, pageLimit=10, roomLimit=3, maxPage=31, startPage=1, endPage=10]
		// test - http://localhost:8800/UnI_JeJu/booking/list?page=23
		
		
// 3. 페이징 처리가 된 게시글 목록 조회
		List<Room> roomList = rd.selectRoomList(conn,pi); // 11번 - room 생성자 만들기 , 12번 - selectRoomList
		//System.out.println("페이징 확인 roomList : " + roomList); // 4개의 게시글이 잘 넘어옴
		
		
		Map<String, Object> returnMap = new HashMap<>();
		
		returnMap.put("pi",pi);
		returnMap.put("roomList",roomList);
		
		return returnMap;
	}
	
//4. 숙소사진  사진레벨 1인 사진을 기준으로 페이징 처리..
	public Map<String, Object> selectRoomImage(int page , int userno, String area ) {
		Connection conn = getConnection();
		
		//게시글 총 개수
		int listCount = rd.getimageListCount(conn, area);
	//	System.out.println("섬네일 사진 개수 : "+ listCount);
		
		//PageInfo 객체 만들기
		R_PageInfo pi = new R_PageInfo(page, listCount, 3,3);
	//	System.out.println("페이지 정보 : " + pi);
		
		//페이징 처리한 게시글 목록 조회
		List<Room> roomimage = rd.selectRoomImage(conn,pi , area);
		System.out.println("이미지페이징 roomimage : " + roomimage);
		
	
		List<Wish>wishList =  rd.selectRoomWishList(conn, userno,pi);
	//	System.out.println("wishList : " + wishList);
		
		Map<String, Object> returnMap = new HashMap<>();
		
		returnMap.put("pi",pi);
		returnMap.put("roomimage",roomimage);
		returnMap.put("wishList",wishList);
		
		return returnMap;
		
	}
//위시리스트 삽입
	public int insertWish(int rno, int userno) {
		Connection conn = getConnection();
		int result = rd.insertWish(conn,rno,userno);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
//위시리스트 제거
	public int deleteWish(int rno, int userno) {
		Connection conn = getConnection();
		
		int result = rd.deleteWish(conn,rno,userno);
		/* System.out.println("Service : "+result); */
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}
//게시글 1개 조회 + 댓글에 대한 부분도 합쳐준다.

	public Map<String, Object> selectRoom(int rno, int userno) {
		Connection conn =getConnection();
		
		List<Room> selectroom = rd.selectRoom(conn, rno,userno);
		
		/* ------------------------------------ */
		selectroom.get(0).setReplyList(rd.selectReplyList(conn,rno));
		System.out.println("33selectroom : " + selectroom);
		
		List<R_Attachment> sroom = rd.sroom(conn,rno);
//		System.out.println("sroom"+sroom);
		/* ------------------------------------ */
		
		List<Wish>wishList =  rd.selectRoomWishList(conn,rno,userno);
//		System.out.println("wishList : " + wishList);
		
		Map<String, Object> returnMap = new HashMap<>();
		
		returnMap.put("sroom",sroom);
		returnMap.put("selectroom",selectroom);
		returnMap.put("wishList",wishList);
		return returnMap;
		
	}
// ---- 댓글추가 + 새로 갱신 된 댓글 리스트 조회 -----------   -----------   -----------   -----------   -----------   -----------  
	public List<R_comment1> insertReply(R_comment1 r) {
		Connection conn = getConnection();
		
		int result = rd.insertReply(conn,r);
		
		List<R_comment1> replayList = null;
		
		
		if(result > 0) {
			commit(conn);
			replayList = rd.selectReplyList(conn, r.getR_no());
			System.out.println("replayList =" + replayList);
		}else {
			rollback(conn);
		}
		return replayList;
	}

	/*
	 * 9.27변경 // 예약요청 들어온 방 select 
	 * public Room selectRoom(int rno) { 
	 * Connection conn = getConnection();
	 * 
	 * Room r = rd.selectRoom(conn, rno); 
	 * System.out.println("예약요청 들어온 방 "+r);
	 * 
	 * 
	 * // if(r != null) { r =rd.updateSelectRoom(conn, rno); // 예약확정되면 Room_Info
	 * 테이블의 // r_status -> 'N'로 변함 }
	 * 
	 * close(conn); return r; 
	 * }
	 */
// 예약 요청이 들어오면 room-booking테이블에 데이터 저장 
	public int insertBook(Room r, String r_start, String r_end) {
		Connection conn = getConnection();
		int result = rd.insertBook(conn,r,r_start,r_end);
	//	System.out.println("insertBook : "+ result);
		
		if(result > 0) {
			commit(conn);
			
		}else {
			rollback(conn);
		}
		return result;
	}

//예약 요청이 들어온 room의 날짜, 금액 , 인원수 변경
	public int updateRoom(Room r, String r_start, String r_end) {
		Connection conn = getConnection();
		
		int result = rd.updateRoom(conn,r,r_start,r_end);
		System.out.println(result);
		if(result > 0) {
			commit(conn);
			r=rd.selectRoom(conn, r,r_start,r_end);
			
		}else {
			rollback(conn);
		}
		return result;
	}

//예약확인쪽에 insert 되면 DB 쪽에 insert
	public int PayBookInsert(PayBook p, Booking b) {
		Connection conn = getConnection();
		
		int result2 = rd.bookingInsert(conn, b);
		int result1 = rd.PayBookInsert(conn, p);
		
	
		
		if(result1 > 0&& result2>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		return (result1 > 0&& result2>0) ?1:0;
		
		
	}

	public int bookingInsert(Booking b) {
		Connection conn = getConnection();
		int result = rd.bookingInsert(conn,b);
		if(result >0) {
			commit(conn);
			
		}else {
			rollback(conn);
		}
		
		return result;
	}

	//예약확인쪽에 insert 되면 DB 쪽에 insert



}
