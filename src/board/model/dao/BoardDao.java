package board.model.dao;

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

import board.model.vo.Attachment;
import board.model.vo.B_Report;
import board.model.vo.B_Search;
import board.model.vo.Board;
import board.model.vo.Comment2;
import board.model.vo.PageInfo;
import mypage.model.vo.Message;


public class BoardDao {
	private Properties query = new Properties();
	
	public BoardDao() {
		String fileName = BoardDao.class.getResource("/sql/board/board-query.xml").getPath();
		try {
			query.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// 게시글 목록 페이징 처리 후 리턴
	public List<Board> selectList(Connection conn, PageInfo pi, int[] bid, B_Search s) {
		PreparedStatement pstmt = null;		
		ResultSet rset = null;
		List<Board> boardList = new ArrayList<Board>();
		String sql = query.getProperty("selectList");
		
		if(s.getSearchCondition() == null && s.getSearchValue() != null) {
			sql = query.getProperty("selectSearchList");
		} else if (s.getSearchCondition() != null && s.getSearchValue() != null) {
			sql = query.getProperty("selectSearchReviewList");
		}

		try {
			pstmt = conn.prepareStatement(sql);
			int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			if(s.getSearchCondition() == null && s.getSearchValue() != null) {
				pstmt.setInt(1, bid[0]);
				pstmt.setInt(2, bid[bid.length-1]);
				pstmt.setString(3, s.getSearchValue());
				pstmt.setString(4, s.getSearchValue());
				pstmt.setInt(5, startRow);
				pstmt.setInt(6, endRow);
			} else if(s.getSearchCondition() != null && s.getSearchValue() != null) {
				int sc = s.getSearchCondition().equals("dining")? 20: (s.getSearchCondition().equals("room")? 30: 40);
				pstmt.setInt(1, sc);
				pstmt.setString(2, s.getSearchValue());
				pstmt.setString(3, s.getSearchValue());
				pstmt.setInt(4, startRow);
				pstmt.setInt(5, endRow);
			} else {
				pstmt.setInt(1, bid[0]);
				pstmt.setInt(2, bid[bid.length-1]);
				pstmt.setInt(3, startRow);
				pstmt.setInt(4, endRow);
			}
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				boardList.add(new Board(rset.getInt("b_no"),
										rset.getInt("b_id"),
										rset.getString("nickname"),
										rset.getString("b_title"),
										rset.getDate("b_create_date"),
										rset.getInt("b_count")
						  ));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		return boardList;
	}
	
	// 총 게시글 수 count
	public int getListCount(Connection conn, int[] bid, B_Search s) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int listCount = 0;
		String sql = query.getProperty("boardListCount");
		
		if(s.getSearchCondition() == null && s.getSearchValue() != null) {
			sql = query.getProperty("getSearchListCount");
		} else if (s.getSearchCondition() != null && s.getSearchValue() != null) {
			sql = query.getProperty("getSearchReviewCount");
		}
		try {
			pstmt = conn.prepareStatement(sql);
			if(s.getSearchCondition() == null && s.getSearchValue() != null) {
				pstmt.setInt(1, bid[0]);
				pstmt.setInt(2, bid[bid.length-1]);
				pstmt.setString(3, s.getSearchValue());
				pstmt.setString(4, s.getSearchValue());
			} else if(s.getSearchCondition() != null && s.getSearchValue() != null) {
				int sc = s.getSearchCondition().equals("dining")? 20: (s.getSearchCondition().equals("room")? 30: 40);
				pstmt.setInt(1, sc);				
				pstmt.setString(2, s.getSearchValue());
				pstmt.setString(3, s.getSearchValue());				
			} else {
				pstmt.setInt(1, bid[0]);
				pstmt.setInt(2, bid[bid.length-1]);				
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

	public Board selectBoard(Connection conn, int bno) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Board b = null;
		String sql = query.getProperty("selectBoard");
		int count = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				if(count++ < 1) {
					b = new Board(rset.getInt("b_no"),
							rset.getInt("b_id"),
							rset.getString("b_type"),
							rset.getInt("user_no"),
							rset.getString("nickname"), // bWriter
							rset.getString("b_title"),
							rset.getTimestamp("b_date"),
							rset.getString("b_content"),
							rset.getTimestamp("b_create_date"),
							rset.getTimestamp("b_modify_date"),
							rset.getInt("b_count")
							);
					if(rset.getString("b_tag") != null) {
						b.setbTag(rset.getString("b_tag"));
					}
				}

				// 첨부파일이 있는 경우에만 attachment 객체 생성
				int attachFile = rset.getInt("image_no");
				if(attachFile > 0) {
					Attachment at = new Attachment();
					at.setImgNo(rset.getInt("image_no"));
					at.setRoute(rset.getString("route"));
					at.setOriginName(rset.getString("image_name"));
					at.setReName(rset.getString("image_r_name"));
					b.getImgList().add(at);
				}					
				}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}

		return b;
	}
	public int insertBoard(Connection conn, Board b) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("insertBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b.getBid());
			pstmt.setInt(2, b.getUserNo());
			pstmt.setString(3, b.getbTitle());
			pstmt.setString(4, b.getInputDate());
			pstmt.setString(5, b.getbContent());
			pstmt.setString(6, b.getBid() == 0? "N": "Y");
			pstmt.setString(7, b.getbTag());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}
	
	public int insertAttachment(Connection conn, List<Attachment> imgList) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("insertImg");
		try {
			for(int i = 0; i < imgList.size(); i++) {
				Attachment at = imgList.get(i);
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, at.getRoute());
				pstmt.setString(2, at.getOriginName());
				pstmt.setString(3, at.getReName());
				pstmt.setInt(4, at.getImgLevel());
				
				result += pstmt.executeUpdate();
				
				insertBI(conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	private void insertBI(Connection conn) {
		PreparedStatement pstmt = null;
		String sql = query.getProperty("insertBI");
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
	}
	// 게시판 댓글 insert
	public int insertCmt(Connection conn, Comment2 c) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("insertCmt");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, c.getcWriter());
			pstmt.setString(2, c.getcContent());
			pstmt.setInt(3, c.getRefBno());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	// 게시글의 댓글 리스트
	public List<Comment2> selectCmtList(Connection conn, int refBno) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Comment2> cmtList = new ArrayList<>();
		String sql = query.getProperty("selectCmtList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, refBno);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				cmtList.add(new Comment2(rset.getInt("comment2_no"),
										 rset.getInt(2),
										 rset.getString("nickname"),
										 rset.getString("c_comment"),
										 rset.getTimestamp("c_modify_date"),
										 rset.getInt("b_no"),
										 rset.getInt("origin_rid")
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
	public int deleteBoard(Connection conn, int bno) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("deleteBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	public int increaseCount(Connection conn, int bno) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("increaseCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	public int deleteFiles(Connection conn, String[] changeNames) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("deleteAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			for(int i = 0; i < changeNames.length; i++) {
				pstmt.setString(1, changeNames[i]);
				
				result += pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	public int updateBoard(Connection conn, Board b) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("updateBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b.getbTitle());
			pstmt.setString(2, b.getbContent());
			pstmt.setInt(3, b.getBno());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	public int updateAttachment(Connection conn, List<Attachment> updateList) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("updateImg");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			for(Attachment at : updateList) {
				pstmt.setString(1, at.getOriginName());
				pstmt.setString(2, at.getReName());
				pstmt.setString(3, at.getDeletedName());
				
				result += pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	public int insertAddedAttachment(Connection conn, int bno, List<Attachment> insertList) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("insertAddedImg");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			for(Attachment at : insertList) {
				pstmt.setString(1, at.getRoute());
				pstmt.setString(2, at.getOriginName());
				pstmt.setString(3, at.getReName());
				pstmt.setInt(4, at.getImgLevel());
				
				result += pstmt.executeUpdate();
				
				updateBI(conn, bno);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	private void updateBI(Connection conn, int bno) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("insertAddedBI");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
	}
	public int insertReview(Connection conn, Board b) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("insertReview");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b.getBid());
			pstmt.setInt(2, b.getUserNo());
			pstmt.setString(3, b.getbTitle());
			pstmt.setString(4, b.getbContent());
			pstmt.setString(5, b.getbTag());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	// 사진모아보기 게시판 list
	public List<Board> selectGalleryList(Connection conn, B_Search s) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Board> boardList = new ArrayList<>();
		String sql = query.getProperty("selectGalleryList");
		
		if(s.getSearchValue() != null) {
			sql = query.getProperty("searchGalleryList");
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			if(s.getSearchValue() != null) {
				pstmt.setString(1, s.getSearchValue());
			}
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Board b = new Board(rset.getInt("b_no"),
									rset.getInt("b_id"),
									rset.getInt("user_no"),
									rset.getString("nickname"),
									rset.getString("b_title"),
									rset.getInt("b_count"),
									rset.getString("b_tag")
							 );
				List<Attachment> imgList = new ArrayList<>();
				imgList.add(new Attachment(rset.getString("image_r_name"),
											  rset.getString("route")
							));
				b.setImgList(imgList);
				boardList.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return boardList;
	}
	// 후기게시판 글 수정
	public int updateReview(Connection conn, Board b) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("updateReview");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b.getBid());
			pstmt.setString(2, b.getbTitle());
			pstmt.setString(3, b.getbContent());
			pstmt.setString(4, b.getbTag());
			pstmt.setInt(5, b.getBno());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	// 쪽지 보내기
	public int insertMsg(Connection conn, Message m) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("insertMsg");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getMsgTitle());
			pstmt.setString(2, m.getMsgContent());
			pstmt.setInt(3, m.getSender());
			pstmt.setInt(4, m.getReceiver());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	// 댓글 삭제하기
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
	// 댓글 수정하기
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
	public int selectReportList(Connection conn, B_Report bRe) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String sql = query.getProperty("selectReportList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bRe.getBno());
			pstmt.setInt(2, bRe.getUserNo());

			rset = pstmt.executeQuery();
			if(!rset.next()) {
				result = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		return result;
	}
	public int insertReport(Connection conn, B_Report bRe) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = query.getProperty("insertReport");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bRe.getBno());
			pstmt.setInt(2, bRe.getUserNo());
			pstmt.setInt(3, bRe.getReportId());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
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
			pstmt.setInt(3, c.getRefBno());
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
