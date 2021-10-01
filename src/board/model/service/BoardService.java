package board.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import admin.model.vo.Search;
import board.model.dao.BoardDao;
import board.model.vo.Attachment;
import board.model.vo.B_Report;
import board.model.vo.B_Search;
import board.model.vo.Board;
import board.model.vo.Comment2;
import board.model.vo.PageInfo;
import mypage.model.vo.Message;

public class BoardService {
	private BoardDao bd = new BoardDao();

	// 게시글 목록 조회
	public Map<String, Object> selectList(int page, int[] bid, B_Search s) {
		Connection conn = getConnection();
		
		int listCount = bd.getListCount(conn, bid, s);

		PageInfo pi = new PageInfo(page, listCount, 10, 20);
		
		List<Board> boardList = bd.selectList(conn, pi, bid, s);

		Map<String, Object> returnMap = new HashMap<>();
		
		returnMap.put("pi", pi);
		returnMap.put("boardList", boardList);
		
		return returnMap;
	}
	// 게시글 1개 조회
	public Board selectBoard(int bno) {
		Connection conn = getConnection();
		// System.out.println(bno);
		
		Board b = bd.selectBoard(conn, bno);
		b.setCmtList(bd.selectCmtList(conn, bno));
		
		close(conn);
		
		return b;
	}
	// 게시글 작성
	public int insertBoard(Board b) {
		Connection conn = getConnection();
		
		int result1 = bd.insertBoard(conn, b);
		int result2 = bd.insertAttachment(conn, b.getImgList());
		int result = 0;
		
		if(result1 > 0 && result2 == b.getImgList().size()) {
			commit(conn);
			result = 1;
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	// 댓글 조회
	public List<Comment2> insertCmt(Comment2 c) {
		Connection conn = getConnection();
		int result = bd.insertCmt(conn, c);
		List<Comment2> cmtList = null;
		
		if(result > 0) {
			commit(conn);
			cmtList = bd.selectCmtList(conn, c.getRefBno());
		} else {
			rollback(conn);
		}
		close(conn);
		return cmtList;
	}
	// 게시글 삭제
	public int deleteBoard(int bno) {
		Connection conn = getConnection();
		int result = bd.deleteBoard(conn, bno);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	// 조회수 증가
	public int increaseCount(int bno) {
		Connection conn = getConnection();
		int result = bd.increaseCount(conn, bno);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	// 첨부파일 삭제
	public int deleteFiles(int bno, String[] changeNames) {
		Connection conn = getConnection();
		
		int result1 = bd.deleteBoard(conn, bno);
		int result2 = bd.deleteFiles(conn, changeNames);
		int result = 0;
		
		if(result1 > 0 && result2 == changeNames.length) {
			commit(conn);
			result = 1;
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	// 함께해요 게시글 수정
	public int updateBoard(Board b) {
		Connection conn = getConnection();
		
		int result1 = bd.updateBoard(conn, b);
		
		// Attachment 테이블에 update/insert 처리할 로직
		List<Attachment> updateList = new ArrayList<>();
		List<Attachment> insertList = new ArrayList<>();
		
		for(Attachment at : b.getImgList()) {
			if(at.getDeletedName() != null) {
				updateList.add(at);
			} else {
				insertList.add(at);
			}
		}
		
		int result2 = bd.updateAttachment(conn, updateList);
		int result3 = bd.insertAddedAttachment(conn, b.getBno(), insertList);
		int result = 0;
		
		if(result1 > 0 && result2 == updateList.size() && result3 == insertList.size()) {
			commit(conn);
			result = 1;
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	// 후기 게시글 작성
	public int insertReview(Board b) {
		Connection conn = getConnection();
		
		int result1 = bd.insertReview(conn, b);
		int result2 = bd.insertAttachment(conn, b.getImgList());
		int result = 0;
		
		if(result1 > 0 && result2 == b.getImgList().size()) {
			commit(conn);
			result = 1;
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	// 사진 모아보기 List
	public List<Board> selectGalleryList(B_Search s) {
		Connection conn = getConnection();
		
		List<Board> boardList  = bd.selectGalleryList(conn, s);
		
		close(conn);
		
		return boardList;
	}
	// 후기 게시글 수정
	public int updateReview(Board b) {
		Connection conn = getConnection();
		
		int result1 = bd.updateReview(conn, b); 

		// Attachment 테이블에 update/insert 처리할 로직
		List<Attachment> updateList = new ArrayList<>();
		List<Attachment> insertList = new ArrayList<>();
		
		for(Attachment at : b.getImgList()) {
			if(at.getDeletedName() != null) {
				updateList.add(at);
			} else {
				insertList.add(at);
			}
		}
		
		int result2 = bd.updateAttachment(conn, updateList);
		int result3 = bd.insertAddedAttachment(conn, b.getBno(), insertList);
		int result = 0;
		
		if(result1 > 0 && result2 == updateList.size() && result3 == insertList.size()) {
			commit(conn);
			result = 1;
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	// 쪽지 보내기
	public int insertMsg(Message m) {
		Connection conn = getConnection();
		
		int result = bd.insertMsg(conn, m);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	// 댓글 삭제하기
	public List<Comment2> deleteCmt(int refBno, int cid) {
		Connection conn = getConnection();
		List<Comment2> cmtList = null;
		
		int result = bd.deleteCmt(conn, cid);
		
		if(result > 0 ) {
			commit(conn);
			cmtList = bd.selectCmtList(conn, refBno);
		} else {
			rollback(conn);
		}
		close(conn);
		return cmtList;
	}
	// 댓글 수정
	public List<Comment2> updateCmt(Comment2 cmt, int refBno) {
		Connection conn = getConnection();
		List<Comment2> cmtList = null;
		
		int result = bd.updateCmt(conn, cmt);
		if(result > 0) {
			commit(conn);
			cmtList = bd.selectCmtList(conn, refBno);
		} else {
			rollback(conn);
		}
		close(conn);
		return cmtList;
	}
	public int insertReport(B_Report bRe) {
		Connection conn = getConnection();
		int result = bd.selectReportList(conn, bRe);

		if(result > 0) {
			result = bd.insertReport(conn, bRe);
		}
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}

	public List<Comment2> insertReCmt(Comment2 c) {
		Connection conn = getConnection();
		List<Comment2> cmtList = null;
		
		int result = bd.insertReCmt(conn, c);
		if(result > 0) {
			commit(conn);
			cmtList = bd.selectCmtList(conn, c.getRefBno());
		} else {
			rollback(conn);
		}
		return cmtList;
	}

}