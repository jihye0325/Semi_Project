package qna.model.dao;

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

import qna.model.vo.Q_Image;
import qna.model.vo.Q_PageInfo;
import qna.model.vo.Q_Reply;
import qna.model.vo.Q_Search;
import qna.model.vo.Qna;

public class QnaDao {
	private Properties query = new Properties();
	
	// 파일 읽어오기
	public QnaDao () {
		String fileName = QnaDao.class.getResource("/sql/qna/qna-query.xml").getPath();
		try {
			query.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	    
	// 게시글 총 개수
	public int getListCount(Connection conn, Q_Search s) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int listCount = 0;
		String sql = query.getProperty("getListCount");
		
		if(s.getSearchCondition() != null && s.getSearchValue() != null) {
			if(s.getSearchCondition().equals("title")) {
				sql = query.getProperty("getTitleListCount");
			} else if(s.getSearchCondition().equals("content")) {
				sql = query.getProperty("getContentListCount");
			} else if(s.getSearchCondition().equals("writer")) {
				sql = query.getProperty("getWriterListCount");
			}
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			if(s.getSearchCondition() != null && s.getSearchValue() != null) {
				pstmt.setString(1, s.getSearchValue());
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
	
	// 페이징 처리한 qnaList 조회
	public List<Qna> selectList(Connection conn, Q_PageInfo pi, Q_Search s) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Qna> qnaList = new ArrayList<>();
		String sql = query.getProperty("selectList");
		
		// 검색조건, 검색 값 있을 경우
		if(s.getSearchCondition() != null && s.getSearchValue() != null) {
			if(s.getSearchCondition().equals("title")) {
				sql = query.getProperty("selectTitleList");
			} else if(s.getSearchCondition().equals("content")) {
				sql = query.getProperty("selectContentList");
			} else if(s.getSearchCondition().equals("writer")) {
				sql = query.getProperty("selectWriterList");
			}
		}
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			int paramIndex = 1;
			
			// 검색 조건과 검색 값 있을 때
			if(s.getSearchCondition() != null && s.getSearchValue() != null) {
				pstmt.setString(paramIndex++, s.getSearchValue());
			}
			
			// 검색 조건, 검색 값 없을 때
			pstmt.setInt(paramIndex++, startRow);
			pstmt.setInt(paramIndex++, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				qnaList.add(new Qna(rset.getInt("q_no"),
									rset.getString("q_cname"),
									rset.getString("q_title"),
									rset.getInt("user_no"),
									rset.getString("id"),
									rset.getInt("q_count"),
									rset.getDate("q_create_date"),
									rset.getString("open_status"),
									rset.getInt("reply_count")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return qnaList;
	}
	
	// Qna 테이블 삽입
	public int insertQna(Connection conn, Qna q) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("insertQna");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, q.getQ_cid());
			pstmt.setInt(2, q.getUser_no());
			pstmt.setString(3, q.getQ_title());
			pstmt.setString(4, q.getQ_content());
			pstmt.setString(5, q.getOpen_status());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	// Image 테이블 삽입
	public int insertImage(Connection conn, Q_Image qi) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("insertImage");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, qi.getRoute());
			pstmt.setString(2, qi.getImage_name());
			pstmt.setString(3, qi.getImage_r_name());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	// Qna_Image 테이블 삽입
	public int insertQnaImage(Connection conn, Qna q, Q_Image qi) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("insertQnaImage");
		
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

	// 조회수 증가
	public int increaseCount(Connection conn, int q_no) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("increaseCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, q_no);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public Qna selectQna(Connection conn, int q_no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Qna qna = null;
		String sql = query.getProperty("selectQna");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, q_no);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				qna = (new Qna(rset.getInt("q_no"),
							   rset.getInt("q_cid"),
							   rset.getString("q_cname"),
							   rset.getInt("user_no"),
							   rset.getString("id"),
							   rset.getString("q_title"),
							   rset.getString("q_content"),
							   rset.getInt("q_count"),
							   rset.getTimestamp("q_create_date"),
							   rset.getTimestamp("q_modify_date"),
							   rset.getString("open_status"),
							   rset.getString("q_status")));
						qna.getQ_image().setImage_no(rset.getInt("image_no"));
						qna.getQ_image().setQ_no(rset.getInt("q_no"));
						qna.getQ_image().setRoute(rset.getString("route"));
						qna.getQ_image().setImage_name(rset.getString("image_name"));
						qna.getQ_image().setImage_r_name(rset.getString("image_r_name"));
			}
			// System.out.println("사진있는 qna: " + qna);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return qna;
	}

	// Qna테이블 update
	public int updateQna(Connection conn, Qna q) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("updateQna");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, q.getQ_cid());
			pstmt.setString(2, q.getQ_title());
			pstmt.setString(3, q.getQ_content());
			pstmt.setString(4, q.getOpen_status());
			pstmt.setInt(5, q.getQ_no());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	// 기존 파일 변경으로 인한 Image 테이블 update
	public int updateImage(Connection conn, Q_Image u_qi) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("updateImage");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, u_qi.getImage_name());
			pstmt.setString(2, u_qi.getImage_r_name());
			pstmt.setString(3, u_qi.getQ_deleteName());
			
			result = pstmt.executeUpdate();
			System.out.println("dao qi: " + u_qi);
			System.out.println("dao result: " + result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	// 수정시 새로 첨부된 파일 insert
	public int insertAddedImage(Connection conn, Q_Image i_qi) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("insertAddedImage");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, i_qi.getRoute());
			pstmt.setString(2, i_qi.getImage_name());
			pstmt.setString(3, i_qi.getImage_r_name());

			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int insertAddedQnaImage(Connection conn, int q_no, Q_Image q_image) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("insertAddedQnaImage");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, q_no);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	// 나의 문의글 개수 
	public int getMyListCount(Connection conn, String id) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int listCount = 0;
		String sql = query.getProperty("getMyListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			// ** 로그인한 유저의 아이디 입력하기**
			pstmt.setString(1, id);
			
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

	// 페이징 처리한 나의 게시글 목록 조회
	public List<Qna> selectMyList(Connection conn, Q_PageInfo pi, String id) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Qna> qnaList = new ArrayList<>();
		String sql = query.getProperty("selectMyList");
				
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			// 로그인한 유저의 아이디 입력하기
			pstmt.setString(1, id);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				qnaList.add(new Qna(rset.getInt("q_no"),
									rset.getString("q_cname"),
									rset.getString("q_title"),
									rset.getInt("user_no"),
									rset.getString("id"),
									rset.getInt("q_count"),
									rset.getDate("q_create_date"),
									rset.getString("open_status"),
									rset.getInt("reply_count")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return qnaList;
	}
	
	// 사진 첨부 확인
	public int selectQnaCount(Connection conn, int q_no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String sql = query.getProperty("selectQnaCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, q_no);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result =rset.getInt(1);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	// 사진 첨부하지 않은 글일 때 조회
	public Qna selectQnaNoImage(Connection conn, int q_no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Qna qna = null;
		String sql = query.getProperty("selectQnaNoImage");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, q_no);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				qna = (new Qna(rset.getInt("q_no"),
							rset.getInt("q_cid"),
							rset.getString("q_cname"),
							rset.getInt("user_no"),
							rset.getString("id"),
							rset.getString("q_title"),
							rset.getString("q_content"),
							rset.getInt("q_count"),
							rset.getTimestamp("q_create_date"),
							rset.getTimestamp("q_modify_date"),
							rset.getString("open_status"),
							rset.getString("q_status")));
				//  System.out.println("qna: " + qna);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return qna;
	}

	// Qna에서 삭제
	public int deleteQna(Connection conn, int q_no) {
		PreparedStatement pstmt = null; 
		int result = 0;
		String sql =query.getProperty("deleteQna");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, q_no);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	// Image에서 삭제
	public int deleteImage(Connection conn, int q_no) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("deleteImage");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, q_no);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	// 게시글 당 댓글 리스트 조회
	public List<Q_Reply> selectQ_ReplyList(Connection conn, int q_no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Q_Reply> q_replyList = new ArrayList<>();
		String sql = query.getProperty("selectQ_ReplyList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, q_no);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				q_replyList.add(new Q_Reply(rset.getInt("comment2_no"),
										  rset.getString("id"),
										  rset.getTimestamp("c_create_date"),
										  rset.getTimestamp("c_modify_date"),
										  rset.getString("c_comment"),
										  rset.getInt("q_no"),
										  rset.getString("c_status")));
			}
			System.out.println("q_replyList : " + q_replyList);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return q_replyList;
	}

	// 댓글 추가
	public int insertReply(Connection conn, Q_Reply r) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("insertReply");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, r.getUser_no());
			pstmt.setString(2, r.getC_comment());
			pstmt.setInt(3, r.getQ_no());
			
			result = pstmt.executeUpdate();
			
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

	// 수정할 댓글 조회
	public Q_Reply selectQ_Reply(Connection conn, int c_no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Q_Reply reply = null;
		String sql = query.getProperty("selectQ_Reply");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, c_no);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				reply = new Q_Reply(rset.getInt("comment2_no"),
								    rset.getString("id"),
								    rset.getTimestamp("c_create_date"),
								    rset.getString("c_comment"),
								    rset.getInt("q_no"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return reply;
	}

	// 댓글 수정
	public int updateReply(Connection conn, Q_Reply reply) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("updateReply");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reply.getC_comment());
			pstmt.setInt(2, reply.getComment2_no());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

}





