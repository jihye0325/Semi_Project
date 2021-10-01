package qna.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import qna.model.dao.QnaDao;
import qna.model.vo.Q_Image;
import qna.model.vo.Q_PageInfo;
import qna.model.vo.Q_Reply;
import qna.model.vo.Q_Search;
import qna.model.vo.Qna;

public class QnaService {
	private QnaDao qd = new QnaDao();
	
	public Map<String, Object> selectList(int page, Q_Search s) {
		Connection conn = getConnection();
		
		// 게시글 총 개수
		int listCount = qd.getListCount(conn, s);
		
		// PageInfo 객체만들기
		Q_PageInfo pi = new Q_PageInfo(page, listCount, 10, 10);
		
		// 페이징 처리한 게시글 목록 조회
		List<Qna> qnaList = qd.selectList(conn, pi, s);
		System.out.println(qnaList);
		  
		// 리턴용 Map 선언
		Map<String, Object> returnMap = new HashMap<>();
		
		returnMap.put("pi", pi);
		returnMap.put("qnaList", qnaList);
		
		close(conn);
		
		return returnMap;
	}

	// 게시글 작성
	public int insertQna(Qna q) {
		Connection conn = getConnection();
		
		// Qna 테이블 insert
		int result1 = qd.insertQna(conn, q);
		System.out.println("result1 : " + result1);
		
		int result2 = 0;
		int result3 = 0;
		
		if(q.getQ_image().getImage_name() != null) {
			// Image 테이블 insert
			result2 = qd.insertImage(conn, q.getQ_image());
			System.out.println("result2 : " + result2);
			
			// qna_image 테이블 insert
			result3 = qd.insertQnaImage(conn, q, q.getQ_image());
			System.out.println("result3 : " + result3);
		}
		
		int result = 0;
		
		if(q.getQ_image().getImage_name() != null) {
			if(result1 > 0 && result2 > 0 && result3 > 0)
				result = 1;
		} else {
			if(result1 > 0)
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

	// 조회수 증가
	public int increaseCount(int q_no) {
		Connection conn = getConnection();
		
		int result = qd.increaseCount(conn, q_no);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	
	// 게시글 조회
	public Qna selectQna(int q_no) {
		Connection conn = getConnection();
		
		// 사진 첨부여부 확인
		int result = qd.selectQnaCount(conn, q_no);
		// System.out.println("첨부: " + result);
		
		Qna qna = new Qna();
		
		if(result > 0) {
			qna = qd.selectQna(conn, q_no);
			qna.setQ_replyList(qd.selectQ_ReplyList(conn, q_no));
			
		} else {
			// 사진 첨부하지 않은 글일 때 조회
			qna = qd.selectQnaNoImage(conn, q_no);
			qna.setQ_replyList(qd.selectQ_ReplyList(conn, q_no));
			// System.out.println("확인: " + qna);
		}
		
		close(conn);
		
		return qna;
	}

	// 게시글 수정
	public int updateQna(Qna q) {
		Connection conn = getConnection();
		
		// Qna 테이블 update
		int result1 = qd.updateQna(conn, q);
		 System.out.println("result1 : " + result1);
		
		// Image 테이블 update처리할 로직, insert 처리할 로직
		Q_Image update_qi = new Q_Image();
		Q_Image insert_qi = new Q_Image();
			
		int result2 = 0;
		int result3 = 0;
		int result4 = 0;
		int result = 0;
		
		// 이전에 첨부파일이 있었을 때만 update로직 
		if(q.getQ_image().getQ_deleteName() != null) {
			update_qi = q.getQ_image();
			result2 = qd.updateImage(conn, update_qi);
			System.out.println("result2 : " + result2);
			
			if(result1 > 0 && result2 > 0) 
					result = 1;	
		// 이전에 첨부파일이 없었을 때 	
		} else {
			insert_qi = q.getQ_image();
			// 수정하면서 파일을 첨부했을때만 로직 수행
			if(q.getQ_image().getImage_name() != null) {
				result3 = qd.insertAddedImage(conn, insert_qi);
				 System.out.println("result3 : " + result3);
				// qna_image 테이블 update (q_no 그대로, image_no 다음시퀀스)
				result4 = qd.insertAddedQnaImage(conn, q.getQ_no(), q.getQ_image());
				 System.out.println("result4 : " + result4);
				
				if(result1 > 0 && result3 > 0 && result4 > 0) 
					result = 1;
				
				// System.out.println("result : " +result);
			
			} else {
				if(result1 > 0) 
					result = 1;
				System.out.println("? : " + result);
			}
		} 
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	// 나의 문의글 보기
	public Map<String, Object> selectMyList(int page, int user_no, String id) {
		Connection conn = getConnection();
		
		// 게시글 총 개수
		int listCount = qd.getMyListCount(conn, id);
		
		// PageInfo 객체만들기
		Q_PageInfo pi = new Q_PageInfo(page, listCount, 10, 10);
		
		// 페이징 처리한 게시글 목록 조회
		List<Qna> qnaList = qd.selectMyList(conn, pi, id);
		  
		// 리턴용 Map 선언
		Map<String, Object> returnMap = new HashMap<>();
		
		returnMap.put("pi", pi);
		returnMap.put("qnaList", qnaList);
		
		close(conn);
		
		return returnMap;
	}

	// 게시글 삭제
	public int deleteQna(int q_no) {
		Connection conn = getConnection();
		
		// Qna에서 삭제
		int result1 = qd.deleteQna(conn, q_no);
		// System.out.println("result1: " + result1);
		int result = 0;
		
		// 조회해서 첨부파일이 있는경우
		Qna q = qd.selectQna(conn, q_no);
		// System.out.println(q);
		
		if(q != null) {
			int result2 = qd.deleteImage(conn, q_no);
			// System.out.println("result2: " + result2);
			
			if(result1 > 0 && result2 > 0) {
				result = 1;
			}
		} else {
			if(result1 > 0)
				result = 1;
		}
		
		// Qna_Image에서 삭제
		// int result3 = qd.deleteQna_Image(conn, q_no);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	// 댓글 작성 + 새로 갱신된 댓글 리스트 조회
	public List<Q_Reply> insertReply(Q_Reply r) {
		Connection conn = getConnection();
		
		int result = qd.insertReply(conn, r);
		
		List<Q_Reply> q_replyList = null;
		
		if(result > 0) {
			commit(conn);
			q_replyList = qd.selectQ_ReplyList(conn, r.getQ_no());
		} else {
			rollback(conn);
		}
		return q_replyList;
	}

	// 댓글 삭제
	public List<Q_Reply> deleteReply(int q_no, int c_no) {
		Connection conn = getConnection();
		
		int result = qd.deleteReply(conn, c_no);
		
		List<Q_Reply> q_replyList = null;
		
		if(result > 0) {
			commit(conn);
			q_replyList = qd.selectQ_ReplyList(conn, q_no);
		} else {
			rollback(conn);
		}
		
		return q_replyList;
	}

	// 수정할 댓글 조회
	public Q_Reply selectReply(int c_no) {
		Connection conn = getConnection();
		Q_Reply reply = new Q_Reply();
		
		reply = qd.selectQ_Reply(conn, c_no);
		
		close(conn);
		
		return reply;
	}

	// 댓글 수정
	public List<Q_Reply> updateReply(Q_Reply reply, int q_no) {
		Connection conn = getConnection();
		List<Q_Reply> q_replyList = null;
		
		int result = qd.updateReply(conn, reply);
		
		if(result > 0) {
			commit(conn);
			q_replyList = qd.selectQ_ReplyList(conn, q_no);
		} else {
			rollback(conn);
		}
		close(conn);
		return q_replyList;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

