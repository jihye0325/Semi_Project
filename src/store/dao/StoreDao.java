package store.dao;

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

import qna.model.dao.QnaDao;
import qna.model.vo.Qna;
import store.model.vo.S_Image;
import store.model.vo.S_PageInfo;
import store.model.vo.S_Reply;
import store.model.vo.S_Search;
import store.model.vo.S_Wish;
import store.model.vo.Store;

public class StoreDao {
	private Properties query = new Properties();
	
	// 파일 읽어오기
	public StoreDao () {
		String fileName = StoreDao.class.getResource("/sql/store/store-query.xml").getPath();
		try {
			query.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 게시글 총개수
	public int getListCount(Connection conn, S_Search s, String area) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int listCount = 0;
		String sql = query.getProperty("getListCount"); 
		System.out.println("count 전체");
		
		if(s.getSearchValue() != "") {
			sql = query.getProperty("getSearchListCount");
			System.out.println("count검색결과");
		}
		
		if(area != "" && !area.equals("all")) {
			if(s.getSearchValue() != null ) {
				sql = query.getProperty("getArea_SearchListCount");
				System.out.println("count 지역,검색결과");
			} else {
				sql =query.getProperty("getAreaListCount");
				System.out.println("count 지역, 검색");
			}
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			if(s.getSearchValue() != "") {
				pstmt.setString(1, s.getSearchValue());
				pstmt.setString(2, s.getSearchValue());
			}
			
			if(area != "" && !area.equals("all")) {
				if(s.getSearchValue() != null ) {
					pstmt.setString(1, area);
					pstmt.setString(2, s.getSearchValue());
					pstmt.setString(3, s.getSearchValue());
				} else {
					pstmt.setString(1, area);
				}
			}
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		System.out.println("listcount:" + listCount);
		return listCount;
	}

	// 페이징 처리한 storeList 조회
	public List<Store> selectList(Connection conn, S_PageInfo pi, S_Search s, String area, String array) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Store> storeList = new ArrayList<>();
		String sql = query.getProperty("selectList");
		
		// 검색조건, 검색 값 있을 경우
		if(s.getSearchValue() != "" && area == "") {
			if(array != "" && array.equals("score")) {
				sql = query.getProperty("selectSearchList");
				System.out.println("검색, 별점, 지역x");
			} else if(array!="" && array.equals("date")) {
				sql = query.getProperty("selectSearch_dateList");
				System.out.println("검색, 최신, 지역x");
			} else {
				sql = query.getProperty("selectSearchList");
				System.out.println("검색값ㅇ");	
			}
		} else if(s.getSearchValue() != "" && area != "") {
			if(array.equals("score")&& !area.equals("all")) {
				sql = query.getProperty("selectArea_Search_scoreList");
				System.out.println("검색, 지역, 별점");
			} else if(array.equals("score")&& area.equals("all")) {
				sql = query.getProperty("selectSearchList");
				System.out.println("검색, 전체지역, 별점");
			} else if(array.equals("date")&& !area.equals("all")) {
				sql = query.getProperty("selectArea_Search_dateList");
				System.out.println("검색, 지역, 최신");
			} else if(array.equals("date")&& area.equals("all")) {
				sql = query.getProperty("selectSearch_dateList");
				System.out.println("검색, 전체 지역, 최신");
			}
		}
		
		// 지역 검색
		if(area != "" && !area.equals("all")&& s.getSearchValue() == "") {
			if(array != "" && array.equals("score")) {
				sql = query.getProperty("selectAreaList");
				System.out.println("지역, 별점");
			} else if(array !="" && array.equals("date")) {
				sql = query.getProperty("selectArea_dateList");
				System.out.println("지역, 최신");
			} else {
				sql = query.getProperty("selectAreaList");	
				System.out.println("지역");
			}
		} else if(area.equals("all")&& s.getSearchValue() == "") {
			if(array!= "" && array.equals("score")) {
				sql = query.getProperty("selectList");
				System.out.println("전체, 별점");
			} else if(array!= "" && array.equals("date")){
				sql = query.getProperty("selectDateArrayList");
				System.out.println("전체, 최신");
			} else {
				sql = query.getProperty("selectList");
				System.out.println("전체, 안눌러서 별점순");
			}
		}
		// 식당-> 별점 /최신순
		if(array != "" && area == "" && s.getSearchValue() == "") {
			if(array.equals("score")) {
				// 별점순
				sql = query.getProperty("selectList");
				System.out.println("별점순");
			} else {
				sql = query.getProperty("selectDateArrayList");
				System.out.println("최신순");
			}
		} else if(array == "" && area == "" && s.getSearchValue() == "") {
			sql = query.getProperty("selectList");
			System.out.println("안눌러서 별점순");
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			int paramIndex = 1;
			
			// 검색 조건과 검색 값 있을 때
			if(s.getSearchValue() != "") {
				pstmt.setString(paramIndex++, s.getSearchValue());
				pstmt.setString(paramIndex++, s.getSearchValue());
				System.out.println("검색 값 있을때");
			}
			
			// 지역값 있을 때
			if(area != "" && !area.equals("all")) {
				pstmt.setString(paramIndex++, area);
				System.out.println("지역값있을때");
			}
			
			// 검색 조건, 검색 값 없을 때
			pstmt.setInt(paramIndex++, startRow);
			pstmt.setInt(paramIndex++, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				storeList.add(new Store(rset.getInt("s_no"),
									rset.getString("s_name"),
									rset.getString("s_tel"),
									rset.getString("menu"),
									rset.getString("s_address"),
									rset.getInt("s_count"),
									rset.getString("s_status"),
									rset.getTimestamp("s_create_date"),
									rset.getDouble("s_score"),
									rset.getInt("reply_count"),
									rset.getString("image_r_name"),
									rset.getString("route")));
			}
			
			// System.out.println("dao : " + storeList);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return storeList;
	}

	// 조회수 증가
	public int increaseCount(Connection conn, int s_no) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("increaseCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, s_no);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public Store selectStore(Connection conn, int s_no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Store store = null;
		String sql = query.getProperty("selectStore");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, s_no);
			pstmt.setInt(2, s_no);
			pstmt.setInt(3, s_no);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				store = (new Store(rset.getInt("s_no"),
							   rset.getString("s_name"),
							   rset.getString("s_tel"),
							   rset.getString("s_time"),
							   rset.getString("menu"),
							   rset.getString("s_address"),
							   rset.getInt("s_count"),
							   rset.getString("s_status"),
							   rset.getTimestamp("s_create_date"),
							   rset.getDouble("s_score"),
							   rset.getInt("reply_count")));
				store.getS_image().setImage_no(rset.getInt("image_no"));
				store.getS_image().setS_no(rset.getInt("s_no"));
				store.getS_image().setRoute(rset.getString("route"));
				store.getS_image().setImage_name(rset.getString("image_name"));
				store.getS_image().setImage_r_name(rset.getString("image_r_name"));
			}
			// System.out.println(store);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return store;
	}

	// 게시글 당 댓글 리스트 조회
	public List<S_Reply> selectReplyList(Connection conn, int s_no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<S_Reply> s_replyList = new ArrayList<>();
		String sql = query.getProperty("selectReplyList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, s_no);
			pstmt.setInt(2, s_no);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				s_replyList.add(new S_Reply(rset.getInt("comment1_no"),
											rset.getInt("user_no"),
											rset.getInt("s_no"),
											rset.getString("nickname"),
											rset.getString("gender"),
											rset.getString("comment_info"),
											rset.getTimestamp("comment_create_date"),
											rset.getInt("score"),
											rset.getString("comment_status"),
											rset.getDouble("avg_score")));
			}
			System.out.println("dao : " + s_replyList);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return s_replyList;
	}

	// 댓글 추가
	public int insertReply(Connection conn, S_Reply r) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("insertReply");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, r.getS_no());
			pstmt.setInt(2, r.getUser_no());
			pstmt.setString(3, r.getComment_info());
			pstmt.setInt(4, r.getScore());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	// 로그인 유저의 위시리스트 불러오기
	public List<S_Wish> selectStoreWishList(Connection conn, int user_no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<S_Wish> wishList = new ArrayList<>();
		String sql = query.getProperty("selectStoreWishList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, user_no);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				wishList.add(new S_Wish(rset.getInt("s_no"),
									  rset.getInt("user_no"),
									  rset.getString("s_status")));
			}
			
			// System.out.println("Dao:" + wishList);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return wishList;
	}

	// 위시리스트 삽입
	public int insertWish(Connection conn, int s_no, int user_no) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("insertWish");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, s_no);
			pstmt.setInt(2, user_no);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	// 위시리스트 삭제
	public int deleteWish(Connection conn, int s_no, int user_no) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("deleteWish");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, s_no);
			pstmt.setInt(2, user_no);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	// Store에서 삭제
	public int deleteStore(Connection conn, int s_no) {
		PreparedStatement pstmt = null; 
		int result = 0;
		String sql =query.getProperty("deleteStore");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, s_no);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int deleteImage(Connection conn, int s_no) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("deleteImage");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, s_no);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	// Store 테이블에 삽입
	public int insertStore(Connection conn, Store s) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("insertStore");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, s.getS_name());
			pstmt.setString(2, s.getS_tel());
			pstmt.setString(3, s.getS_time());
			pstmt.setString(4, s.getMenu());
			pstmt.setString(5, s.getS_address());

			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	//Image에 삽입
	public int insertImage(Connection conn, S_Image si) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("insertImage");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, si.getRoute());
			pstmt.setString(2, si.getImage_name());
			pstmt.setString(3, si.getImage_r_name());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	// Store_Image 삽입
	public int insertStore_Image(Connection conn, Store s, S_Image s_image) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("insertStore_Image");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	// Store 수정
	public int updateStore(Connection conn, Store s) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("updateStore");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			System.out.println("dao s: " + s);
			
			pstmt.setString(1, s.getS_name());
			pstmt.setString(2, s.getS_tel());
			pstmt.setString(3, s.getS_address());
			pstmt.setString(4, s.getS_time());
			pstmt.setString(5, s.getMenu());
			pstmt.setInt(6, s.getS_no());
			
			result = pstmt.executeUpdate();
			System.out.println("dao result:" + result);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	// Image 수정
	public int updateImage(Connection conn, S_Image si) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("updateImage");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, si.getImage_name());
			pstmt.setString(2, si.getImage_r_name());
			pstmt.setString(3, si.getS_deleteName());
			
			result = pstmt.executeUpdate();
			
			System.out.println("dao si: " + si);
			System.out.println("dao image result: " + result);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	// 댓글 삭제
	public int deleteReply(Connection conn, int c_no) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("deleteReply");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, c_no);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	// 댓글 수정
	public int updateReply(Connection conn, S_Reply r) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("updateReply");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, r.getComment_info());
			pstmt.setInt(2, r.getScore());
			pstmt.setInt(3, r.getComment1_no());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	// 수정시 새로 첨부된 파일 insert
	public int insertAddedImage(Connection conn, S_Image insert_si) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("insertAddedImage");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, insert_si.getRoute());
			pstmt.setString(2, insert_si.getImage_name());
			pstmt.setString(3, insert_si.getImage_r_name());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int insertAddedStoreImage(Connection conn, int s_no, S_Image s_image) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("insertAddedStoreImage");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, s_no);
			
			result = pstmt.executeUpdate();			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	
	
}






