package admin.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import admin.model.dao.AllListDao;
import admin.model.vo.Notice;
import admin.model.vo.PageInfo;
import admin.model.vo.Search;



public class noticeAllService {
	private AllListDao ald = new AllListDao();
	public Map<String, Object> selectNoticeList(int page, Search s) {

		Connection conn = getConnection();
		
		int listCount = ald.getNoticeListCount(conn,s); 
		
		//System.out.println(listCount);
		PageInfo pi = new PageInfo(page, listCount,10,10);
		//System.out.println("pi : "+pi);
		
		List<Notice> noticeList = ald.selecNoticeList(conn,pi,s);
		
		Map<String,Object> returnMap = new HashMap<>();
		
		returnMap.put("pi", pi);
		returnMap.put("noticeList", noticeList);
		//System.out.println(returnMap);
		return returnMap;
	}
	public int deleteNotice(int n_no) {
		
			Connection conn = getConnection();
			int result = ald.deleteNotice(conn,n_no);
			if(result>0) {
				commit(conn);
			}else {
				rollback(conn);
			}
			close(conn);
			return result;
		
	}

}
