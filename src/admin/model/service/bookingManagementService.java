package admin.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import admin.model.dao.bookingManagementDao;
import admin.model.vo.PageInfo;
import admin.model.vo.Room;
import admin.model.vo.Search;

public class bookingManagementService {
	private bookingManagementDao bmd = new bookingManagementDao();

	

	public Map<String, Object> selectRoomList(int page, Search s) {
		Connection conn = getConnection();
		
		int listCount = bmd.getRoomCount(conn,s); 
		
		
		PageInfo pi = new PageInfo(page, listCount,10,10);
		//System.out.println("pi : "+pi);
		
		List<Room> roomList = bmd.selectRoomList(conn,pi,s);
		
		Map<String,Object> returnMap = new HashMap<>();
		//System.out.println(bookingList);
		returnMap.put("pi", pi);
		returnMap.put("roomList", roomList);
		//System.out.println(returnMap);
		return returnMap;
	}



	public Room getSalseInfo(int r_no) {
		Connection conn = getConnection();
		Room room = bmd.selectSalseInfo(conn,r_no);
		
		
		
		close(conn);
				
		return room;
	}

}
