package admin.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import admin.model.dao.boardListDao;
import admin.model.vo.Board;
import admin.model.vo.PageInfo;
import admin.model.vo.Search;

public class BoardService {
	private boardListDao bld = new boardListDao();

	public Map<String, Object> selectBoardList(int page, Search s) {
		Connection conn = getConnection();
		
		int listCount = bld.getBoardListCount(conn,s); 
		System.out.println(listCount);
		
		
		PageInfo pi = new PageInfo(page, listCount,10,10);
		System.out.println("pi : "+pi);
		
		List<Board> boardList = bld.selectBoardList(conn,pi,s);
		
		Map<String,Object> returnMap = new HashMap<>();
		
		returnMap.put("pi", pi);
		returnMap.put("boardList", boardList);
		System.out.println(returnMap);
		return returnMap;
	
	}

	
	public int deleteBoard(int b_no) {
		Connection conn = getConnection();
		int result = bld.deleteBoard(conn,b_no);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	
	}

}
