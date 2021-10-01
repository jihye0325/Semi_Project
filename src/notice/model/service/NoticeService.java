package notice.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import notice.model.dao.NoticeDao;
import notice.model.vo.Comment2;
import notice.model.vo.N_pageInfo;
import notice.model.vo.Notice;

public class NoticeService {
	private NoticeDao nd = new NoticeDao();
	
	public Map<String, Object> selectList(int page, String s) {
		Connection conn = getConnection();
		// 공지사항 글 개수 리턴
		int listCount = nd.getListCount(conn, s);
		
		N_pageInfo npi = new N_pageInfo(page, listCount, 10, 20);
		
		List<Notice> noticeList = nd.selectList(conn, npi, s);
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("npi", npi);
		returnMap.put("noticeList", noticeList);
		close(conn);
		return returnMap;
	}

	public int insertNotice(Notice n) {
		Connection conn = getConnection();
		int result = nd.insertNotice(conn, n);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int increaseCount(int nno) {
		Connection conn = getConnection();
		int result = nd.increaseCount(conn, nno);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public Notice selectNotice(int nno) {
		Connection conn = getConnection();
		Notice n = nd.selectNotice(conn, nno);
		// 댓글 목록 조회
		n.setCmtList(nd.selectCmtList(conn, nno));
		close(conn);
		
		return n;
	}

	public List<Comment2> insertCmt(Comment2 c) {
		Connection conn = getConnection();
		int result = nd.insertCmt(conn, c);
		List<Comment2> cmtList = null;
		
		if(result > 0) {
			commit(conn);
			cmtList = nd.selectCmtList(conn, c.getRefNno());
		} else {
			rollback(conn);
		}
		close(conn);
		
		return cmtList;
	}

	public List<Comment2> deleteCmt(int refNno, int cid) {
		Connection conn = getConnection();
		List<Comment2> cmtList = null;
		
		int result = nd.deleteCmt(conn, cid);
		if(result > 0) {
			commit(conn);
			cmtList = nd.selectCmtList(conn, refNno);
		} else {
			rollback(conn);
		}
		close(conn);
		return cmtList;
	}

	public List<Comment2> updateCmt(Comment2 cmt, int refNno) {
		Connection conn = getConnection();
		List<Comment2> cmtList = null;
		
		int result = nd.updateCmt(conn, cmt);
		if(result > 0) {
			commit(conn);
			cmtList = nd.selectCmtList(conn, refNno);
		} else {
			rollback(conn);
		}
		close(conn);
		return cmtList;
	}

	public int updateNotice(Notice n) {
		Connection conn = getConnection();
		
		int result = nd.updateNotice(conn, n);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int deleteNotice(int nno) {
		Connection conn = getConnection();
		
		int result = nd.deleteNotice(conn, nno);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public List<Comment2> insertReCmt(Comment2 c) {
		Connection conn = getConnection();
		List<Comment2> cmtList = null;
		
		int result = nd.insertReCmt(conn, c);
		if(result > 0) {
			commit(conn);
			cmtList = nd.selectCmtList(conn, c.getRefNno());
		} else {
			rollback(conn);
		}
		return cmtList;
	}

}
