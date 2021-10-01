package notice.model.dao;

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

import notice.model.vo.Comment2;
import notice.model.vo.N_pageInfo;
import notice.model.vo.Notice;

public class NoticeDao {
	private Properties query = new Properties();
	
	public NoticeDao() {
		String fileName = NoticeDao.class.getResource("/sql/notice/notice-query.xml").getPath();
		try {
			query.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 공지사항 총 게시글 수
	public int getListCount(Connection conn, String s) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int listCount = 0;
		String sql = query.getProperty("noticeListCount");
		
		if(s != null) {
			sql = query.getProperty("selectSearchCount");
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			if(s != null) {
				pstmt.setString(1, s);
				pstmt.setString(2, s);
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
		return listCount;
	}
	// 공지사항 목록 페이징 처리 후 리턴
	public List<Notice> selectList(Connection conn, N_pageInfo npi, String s) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Notice> noticeList = new ArrayList<>();
		String sql = query.getProperty("selectList");
		int count = 1;
		
		if(s != null) {
			sql = query.getProperty("selectSearchList");
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			int startRow = (npi.getPage() - 1) * npi.getNoticeLimit() + 1;
			int endRow = startRow + npi.getNoticeLimit() - 1;
			
			if(s != null) {
				pstmt.setString(count++, s);
				pstmt.setString(count++, s);
			}
			pstmt.setInt(count++, startRow);
			pstmt.setInt(count++, endRow);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				noticeList.add(new Notice(rset.getInt("n_no"),
										  rset.getString("nickname"),
										  rset.getString("n_title"),
										  rset.getString("n_content"),
										  rset.getDate("n_date"),
										  rset.getInt("n_hit"),
										  rset.getDate("n_modify_date")
							));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		return noticeList;
	}

	public int insertNotice(Connection conn, Notice n) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("insertNotice");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, n.getUserNo());
			pstmt.setString(2, n.getnTitle());
			pstmt.setString(3, n.getnContent());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int increaseCount(Connection conn, int nno) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("increaseCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nno);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	// 공지사항 1개 조회
	public Notice selectNotice(Connection conn, int nno) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = query.getProperty("selectNotice");
		Notice n = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nno);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				n = new Notice(rset.getInt("n_no"),
									  rset.getInt("user_no"),
									  rset.getString("nickname"),
									  rset.getString("n_title"),
									  rset.getString("n_content"),
									  rset.getTimestamp("n_date"),
									  rset.getInt("n_hit"),
									  rset.getTimestamp("n_modify_date")
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		return n;
	}

	public int insertCmt(Connection conn, Comment2 c) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("insertCmt");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, c.getcWriter());
			pstmt.setString(2, c.getcContent());
			pstmt.setInt(3, c.getRefNno());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public List<Comment2> selectCmtList(Connection conn, int refNno) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Comment2> cmtList = new ArrayList<>();
		String sql = query.getProperty("selectCmtList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, refNno);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				cmtList.add(new Comment2(rset.getInt("comment2_no"),
										 rset.getInt(2),
										 rset.getString("gender"),
										 rset.getString("nickname"),
										 rset.getString("c_comment"),
										 rset.getInt("n_no"),
										 rset.getInt("origin_rid"),
										 rset.getTimestamp("c_modify_date")
										 ));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		return cmtList;
	}

	public int deleteCmt(Connection conn, int cid) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("deleteCmt");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cid);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int updateCmt(Connection conn, Comment2 cmt) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("updateCmt");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cmt.getcContent());
			pstmt.setInt(2, cmt.getCid());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}		
		return result;
	}

	public int updateNotice(Connection conn, Notice n) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("updateNotice");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, n.getnTitle());
			pstmt.setString(2, n.getnContent());
			pstmt.setInt(3, n.getNno());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteNotice(Connection conn, int nno) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("deleteNotice");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nno);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn);
		}
		return result;
	}

	public int insertReCmt(Connection conn, Comment2 c) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("insertReCmt");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, c.getcWriter());
			pstmt.setString(2, c.getcContent());
			pstmt.setInt(3, c.getRefNno());
			pstmt.setInt(4, c.getOriginCid());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

}
