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
import admin.model.vo.Member;
import admin.model.vo.PageInfo;
import admin.model.vo.Search;
import store.model.vo.Store;

public class storeAllService {
	private AllListDao ald = new AllListDao();
	public Map<String, Object> selectStoreList(int page, Search s) {

		Connection conn = getConnection();
		
		int listCount = ald.getStoreListCount(conn,s); 
		
		//System.out.println(listCount);
		PageInfo pi = new PageInfo(page, listCount,10,10);
		//System.out.println("pi : "+pi);
		
		List<Store> storeList = ald.selecStoretList(conn,pi,s);
		
		Map<String,Object> returnMap = new HashMap<>();
		
		returnMap.put("pi", pi);
		returnMap.put("storeList", storeList);
		//System.out.println(returnMap);
		return returnMap;
	}
	public int deleteStore(int s_no) {
		
			Connection conn = getConnection();
			int result = ald.deleteStore(conn,s_no);
			if(result>0) {
				commit(conn);
			}else {
				rollback(conn);
			}
			close(conn);
			return result;
		
	}

}
