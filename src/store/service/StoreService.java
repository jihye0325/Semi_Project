package store.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import store.dao.StoreDao;
import store.model.vo.S_Image;
import store.model.vo.S_PageInfo;
import store.model.vo.S_Reply;
import store.model.vo.S_Search;
import store.model.vo.S_Wish;
import store.model.vo.Store;

public class StoreService {
	private StoreDao sd = new StoreDao();
	
	public Map<String, Object> selectList(int page, S_Search s, String area, String array, int user_no) {
		Connection conn = getConnection();
		
		// 게시글 총 개수
		int listCount = sd.getListCount(conn, s, area);
		
		// PageInfo 객체만들기
		S_PageInfo pi = new S_PageInfo(page, listCount, 10, 6);
		
		// 페이징 처리한 게시글 목록 조회
		List<Store> storeList = sd.selectList(conn, pi, s, area, array);
		// System.out.println(storeList);
		
		// 위시리스트 불러오기
		List<S_Wish> wishList = sd.selectStoreWishList(conn, user_no);
		System.out.println(wishList);
		  
		// 리턴용 Map 선언
		Map<String, Object> returnMap = new HashMap<>();
		
		returnMap.put("pi", pi);
		returnMap.put("storeList", storeList);
		returnMap.put("wishList", wishList);
		
		close(conn);
		
		return returnMap;
	}

	// 조회수 증가
	public int increaseCount(int s_no) {
		Connection conn = getConnection();
		
		int result = sd.increaseCount(conn, s_no);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	// 게시글 조회
	public Store selectStore(int s_no, int user_no) {
		Connection conn = getConnection();
		
		Store store = new Store();
		
		store = sd.selectStore(conn, s_no);
		
		// 댓글
		store.setS_replyList(sd.selectReplyList(conn, s_no));
		
		// 위시리스트
		store.setS_wishList(sd.selectStoreWishList(conn, user_no));
		
		System.out.println("Store : " + store);
		close(conn);
		
		return store;
	}
	
	// 댓글 작성 + 새로 갱신된 댓글 리스트 조회
	public List<S_Reply> insertReply(S_Reply r) {
		Connection conn = getConnection();
		
		int result = sd.insertReply(conn, r);
		
		List<S_Reply> s_replyList = null;
		
		if(result > 0) {
			commit(conn);
			s_replyList = sd.selectReplyList(conn, r.getS_no());
		} else {
			rollback(conn);
		}
		close(conn);
		
		return s_replyList;
	}
	
	// 위시리스트 삽입
	public int insertWish(int s_no, int user_no) {
		Connection conn = getConnection();
		
		int result = sd.insertWish(conn, s_no, user_no);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}

	// 위시리스트 삭제
	public int deleteWish(int s_no, int user_no) {
		Connection conn = getConnection();
		
		int result = sd.deleteWish(conn, s_no, user_no);
		
		if(result > 0){
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
				
		return result;
	}

	// 식당 정보 삭제
	public int deleteStore(int s_no) {
		Connection conn = getConnection();
		int result = 0;
		
		// Store에서 삭제
		int result1 = sd.deleteStore(conn, s_no);
		// System.out.println("result1: " + result1);
		
		// Image에서 삭제
		int result2 = sd.deleteImage(conn, s_no);
		// System.out.println("result2: " + result2);

		
		// Store_WishList에서 삭제
		//int result3 = sd.deleteStore_WishList(conn, s_no);
		
		// Store_Image에서 삭제
		// int result4 = sd.deleteStore_Image(conn, s_no);
		
		if(result1 > 0 && result2 >0) {
			result = 1;
		}
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	// 식당정보 삽입
	public int insertStore(Store s) {
		Connection conn = getConnection();
		
		int result = 0;
		
		//Store 테이블 삽입
		int result1 = sd.insertStore(conn, s);
		// System.out.println("result1 : " + result1);
		
		//Image 테이블 삽입
		int result2 = sd.insertImage(conn, s.getS_image());
		// System.out.println("result2 : " + result2);
		
		//Store_Image 테이블 삽입
		int result3 = sd.insertStore_Image(conn, s, s.getS_image());
		// System.out.println("result3 : " + result3);
		
		if(result1 > 0 && result2 > 0 && result3 > 0) 
			result = 1;
	
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}

	// 식당 정보 수정
	public int updateStore(Store s) {
		Connection conn = getConnection();
				
		// Store 수정
		int result1 = sd.updateStore(conn, s);
		System.out.println("result1: " + result1);
		
		// image 테이블 update처리할 로직, insert 처리할 로직
		S_Image update_si = new S_Image();
		S_Image insert_si = new S_Image();
		
		int result2 = 0;
		int result3 = 0;
		int result4 = 0;
		int result = 0;
		
		//이전에 첨부파일이 있었을때  update 로직
		if(s.getS_image().getS_deleteName() != null) {
			update_si = s.getS_image();
			result2 = sd.updateImage(conn, update_si);
			System.out.println("result2:" + result2);
			
			if(result1> 0 && result2 >0)
				result = 1;
		} else {
			// 이전에 첨부파일 없었을 때
			insert_si = s.getS_image();
			// 수정하면서 파일 첨부했을때만 로직 수행
			if(s.getS_image().getImage_name() != null) {
				result3 = sd.insertAddedImage(conn, insert_si);
				System.out.println("result3:" + result3);
				// store_image 테이블 update (s_no 그대로, image_no 다음 시퀀스)
				result4 = sd.insertAddedStoreImage(conn, s.getS_no(), s.getS_image());
				System.out.println("result4: " + result4);
				
				if(result1 >0 && result3 >0 && result4 >0)
					result = 1;
				System.out.println("result:" + result);
			} else {
				if(result1 >0) 
					result = 1;
					System.out.println("첨부xresult:" + result);
			}
		}
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		System.out.println("result: " + result);
		return result;
	}

	// 댓글 삭제
	public List<S_Reply> deleteReply(int s_no, int c_no) {
		Connection conn = getConnection();
		
		int result = sd.deleteReply(conn, c_no);
		
		List<S_Reply> s_replyList = null;
		
		if(result > 0) {
			commit(conn);
			s_replyList = sd.selectReplyList(conn, s_no);
			System.out.println("service1:" + s_replyList);
		} else {
			rollback(conn);
		}
		System.out.println("service2:" + s_replyList);
		return s_replyList;
	}

	// 댓글 수정
	public List<S_Reply> updateReply(S_Reply r) {
		Connection conn = getConnection();
		
		int result = sd.updateReply(conn, r);
		
		List<S_Reply> s_replyList = null;
		
		if(result > 0) {
			commit(conn);
			s_replyList = sd.selectReplyList(conn, r.getS_no());
		} else {
			rollback(conn);
		}
		
		return s_replyList;
	}

}
