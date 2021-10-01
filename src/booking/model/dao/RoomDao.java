package booking.model.dao;

import static common.JDBCTemplate.close;

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

import booking.model.vo.Booking;
import booking.model.vo.PayBook;
import booking.model.vo.R_Attachment;
import booking.model.vo.R_PageInfo;
import booking.model.vo.R_comment1;
import booking.model.vo.Room;
import booking.model.vo.Wish;

public class RoomDao {
	private Properties query = new Properties();

	public RoomDao() {
		String fileName = RoomDao.class.getResource("/sql/Room/RoomList-query.xml").getPath();
		try {
			query.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// 전체 room 리스트 조회
	public int getListCount(Connection conn) { // 4번
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int listCount = 0;
		String sql = query.getProperty("getListCount"); // 6번

		try {
			pstmt = conn.prepareStatement(sql); // 7번
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

// 페이징 처리.... 	
	public List<Room> selectRoomList(Connection conn, R_PageInfo pi) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Room> roomList = new ArrayList<>();
		String sql = query.getProperty("selectRoomList");

		try {
			pstmt = conn.prepareStatement(sql);

			int startRow = (pi.getPage() - 1) * pi.getRoomLimit() + 1;
			int endRow = startRow + pi.getRoomLimit() - 1;
			int paramIndex = 1;

			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				roomList.add(new Room(rset.getInt("r_no"), rset.getString("r_name"), rset.getString("r_tel"),
						rset.getString("r_info"), rset.getInt("r_pay"), rset.getString("r_address"),
						rset.getInt("user_no") , rset.getString("user_name")
						, rset.getDate("r_start"), rset.getDate("r_end"),
						rset.getString("r_status"), rset.getInt("r_head")));
			}
			;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return roomList;
	}

// 섬네일 사진 총 개수
	public int getimageListCount(Connection conn,String area) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int listCount = 0;
		String sql = query.getProperty("getimageListCount");
		
		if(area != null && !area.equals("all")) {
			sql =query.getProperty("getAreaListCount");
		}

		try {
			pstmt = conn.prepareStatement(sql);
			
			if(area != null && !area.equals("all")) {
				pstmt.setString(1, area);
			}
			
			rset = pstmt.executeQuery();

			if(rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
	//	System.out.println(listCount);
		return listCount;

	}

// 숙소사진 DB에서 가져오기
	public List<Room> selectRoomImage(Connection conn, R_PageInfo pi,String area) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Room> roomimage = new ArrayList<>();
		String sql = query.getProperty("selectRoomImage");
		
		// 지역 검색
		if(area != null && !area.equals("all")) {
			sql = query.getProperty("selectAreaList");
		}

		try {
			pstmt = conn.prepareStatement(sql);

			int startRow = (pi.getPage() - 1) * pi.getRoomLimit() + 1;
			int endRow = startRow + pi.getRoomLimit() - 1;
			int paramIndex = 1;
			
			// 지역값 있을 때
			if(area != null && !area.equals("all")) {
					pstmt.setString(paramIndex++, area);
			}

			pstmt.setInt(paramIndex++, startRow);
			pstmt.setInt(paramIndex++, endRow);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				roomimage.add(new Room(rset.getInt("r_no"), rset.getString("r_name"), rset.getString("r_info"),
						rset.getString("route"), rset.getString("image_r_name"), rset.getString("r_tel"),
						rset.getInt("r_pay"), rset.getString("r_address"), rset.getInt("user_no"), rset.getString("user_name"),
						rset.getDate("r_start"), rset.getInt("r_head"), rset.getDate("r_end"),
						rset.getString("r_status"), rset.getInt("image_no")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return roomimage;
	}

// 위시리스트 담기
	public int insertWish(Connection conn, int rno, int userno) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String sql = query.getProperty("insertWish");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, rno);
			pstmt.setInt(2, userno);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

// 위시리스트 제거
	public int deleteWish(Connection conn, int rno, int userno) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String sql = query.getProperty("deleteWish");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, userno);
			pstmt.setInt(2, rno);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}

		return result;
	}
// 사용자 위시리스트만 select - <Wish>
	  public List<Wish> selectRoomWishList(Connection conn, int userno, R_PageInfo pi) {
	  PreparedStatement pstmt = null; 
	  ResultSet rset = null;
	  List<Wish> wishlist = new ArrayList<>();
	  String sql = query.getProperty("selectRoomWishList");
	  
	  try {
		pstmt = conn.prepareStatement(sql);
		
		
		pstmt.setInt(1, userno);
		
		rset = pstmt.executeQuery();
		
		while(rset.next()) {
			wishlist.add(new Wish(rset.getInt("r_no"),rset.getInt("user_no")
					
					));
		};
	} catch (SQLException e) {
		e.printStackTrace();
	}finally {
		close(pstmt);
		close(rset);
	}
	  
	  return wishlist;
	}

// 게시물 1개 조회
	 public List<Room> selectRoom(Connection conn, int rno, int userno){
		PreparedStatement pstmt= null;
		ResultSet rset = null;
		/*Room r = null;*/
		List<Room> r = new ArrayList<>(); 
		String sql = query.getProperty("selectRoom");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rno);
			pstmt.setInt(2, rno);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				  r.add( new Room(rset.getInt("r_no")
						  		, rset.getString("r_name") 
								 , rset.getString("r_tel")
								 , rset.getString("r_info")
								 , rset.getInt("r_pay")
								 , rset.getString("r_address")
								 , rset.getInt("user_no")
								 , rset.getString("user_name")
								 , rset.getString("phone")
								 , rset.getString("gender")
								 , rset.getDate("r_start")
								 , rset.getDate("r_end")
								 , rset.getString("r_status")
								 , rset.getInt("r_head")
								 , rset.getInt("image_no")
								 , rset.getString("route")
								 , rset.getString("image_name")
								 , rset.getString("image_r_name")
								 , rset.getInt("image_level")
								 , rset.getDouble("r_score")));
				 
			}
						
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rset);
		}
		return r;
	}

	public List<Wish> selectRoomWishList(Connection conn, int rno, int userno) {
	  PreparedStatement pstmt = null; 
	  ResultSet rset = null;
	  List<Wish> wishlist = new ArrayList<>();
	  String sql = query.getProperty("selectRoomWishList2");
	  
	  try {
		pstmt = conn.prepareStatement(sql);
		
		
		pstmt.setInt(1, rno);
		pstmt.setInt(2, userno);
		
		rset = pstmt.executeQuery();
		
		while(rset.next()) {
			wishlist.add(new Wish(rset.getInt("r_no")
					,rset.getInt("user_no")
					));
		};
	} catch (SQLException e) {
		e.printStackTrace();
	}finally {
		close(pstmt);
		close(rset);
	}
	  
	  return wishlist;
	}
	
//게시글 당 댓글 리스트 조회
	public List<R_comment1> selectReplyList(Connection conn, int rno) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<R_comment1> replyList = new ArrayList<>();
		String sql = query.getProperty("selectReplyList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, rno); 
			pstmt.setInt(2, rno); 
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				replyList.add(new R_comment1(rset.getInt("comment1_no")
											,rset.getInt("r_no")
											,rset.getInt("user_no")
											,rset.getString("user_name")
											,rset.getString("nickName")
											,rset.getString("comment_info")
											,rset.getTimestamp("comment_create_date")
											,rset.getInt("score")
											,rset.getString("gender")
											,rset.getDouble("avg_score")
											));
				
			}
			 System.out.println("dao : " + replyList);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		
		
		return replyList;
	}
// ---- 댓글 -----------   -----------   -----------   -----------   -----------   ----------- 
	public int insertReply(Connection conn, R_comment1 r) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("insertReply");
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1,r.getR_no());
			pstmt.setInt(2, r.getUser_no());
			pstmt.setString(3, r.getComment_info());
			pstmt.setInt(4, r.getScore());
			
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

//룸디테일 -> 예약요청 select
	public Room selectRoom(Connection conn, int rno) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Room r = null;
		String sql = query.getProperty("selectRoom");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, rno);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				  r = new Room(rset.getInt("r_no")
						  , rset.getString("r_name")
						  , rset.getString("r_tel")
						  , rset.getString("r_info")
						  , rset.getInt("r_pay")
						  , rset.getString("r_address")
						  , rset.getInt("user_no")
						  , rset.getString("user_name")
						  , rset.getString("phone")
						  , rset.getString("gender")
						  , rset.getDate("r_start")
						  , rset.getDate("r_end")
						  , rset.getString("r_status")
						  , rset.getInt("r_head")
				          , rset.getInt("image_no")
				          , rset.getString("route")
				          , rset.getString("image_name")
				          , rset.getString("image_r_name")
				          , rset.getInt("image_level")
				          , rset.getDouble("r_score"));
				 
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rset);
		}
		return r;
	}
//작은사진
	public List<R_Attachment> sroom(Connection conn, int rno) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<R_Attachment> sroom = new ArrayList<>();
		String sql = query.getProperty("sroom");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,rno);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				sroom.add(new R_Attachment(rset.getInt("r_no"),
											rset.getInt("image_no"),
											rset.getString("route"),
											rset.getString("image_name"),
											rset.getString("image_r_name"),
											rset.getInt("image_level")));
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rset);
		}
		return sroom;
		
		
	}
//예약확인에 insert하기
	public int insertBook(Connection conn, Room r , String r_start, String r_end) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("insertBook");
		try {
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setInt(1, r.getUser_no());
			pstmt.setInt(2, r.getR_no());
			pstmt.setString(3, r_start);
			pstmt.setString(4, r_end);
			pstmt.setInt(5, r.getR_r_head());
			
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}
// 변경된 인원수, 날짜, 금액 
	public int updateRoom(Connection conn, Room r, String r_start, String r_end) {
		PreparedStatement pstmt = null;
		int result=0;
		// 이게 잘 되면 R_STATUS를 N으로 바껴서 리스트에 안보이게 하거나
		// 예약버튼을 눌렀을때 ROOM_BOOKING에 존재하는 것은 할 수 없게 하거나
		String sql=query.getProperty("updateRoom");
		try {
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1,r_start);
			pstmt.setString(2,r_end);
			pstmt.setInt(3, r.getR_r_head());
			pstmt.setInt(4, r.getSum());
			pstmt.setInt(5, r.getR_no());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public Room selectRoom(Connection conn, Room r, String r_start, String r_end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = query.getProperty("selectRoom");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, r.getR_no());
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				  r = new Room(rset.getInt("r_no")
						  , rset.getString("r_name")
						  , rset.getString("r_tel")
						  , rset.getString("r_info")
						  , rset.getInt("r_pay")
						  , rset.getString("r_address")
						  , rset.getInt("user_no")
						  , rset.getString("user_name")
						  , rset.getString("phone")
						  , rset.getString("gender")
						  , rset.getDate("r_start")
						  , rset.getDate("r_end")
						  , rset.getString("r_status")
						  , rset.getInt("r_head")
				          , rset.getInt("image_no")
				          , rset.getString("route")
				          , rset.getString("image_name")
				          , rset.getString("image_r_name")
				          , rset.getInt("image_level")
				          , rset.getDouble("r_score"));
				 
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rset);
		}
		return r;
	}
	
//예약확인쪽에 insert 되면 DB 쪽에 insert
	public int PayBookInsert(Connection conn, PayBook p) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("PayBookInsert");
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, p.getP_no());
			pstmt.setString(2, p.getPgname());
			pstmt.setInt(3, p.getMoney());
			pstmt.setString(4, p.getP_paid());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int bookingInsert(Connection conn, Booking b) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("insertBook");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b.getUserno());
			pstmt.setInt(2, b.getBooking_no());
			pstmt.setString(3, b.getR_start());
			pstmt.setString(4, b.getR_end());
			pstmt.setInt(5, b.getHead());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
		
		
		
		
		
	



	


}
