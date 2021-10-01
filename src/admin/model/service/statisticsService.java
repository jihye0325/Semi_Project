package admin.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;

import admin.model.dao.siteStatisticsDao;

public class statisticsService {
	private siteStatisticsDao ssd = new siteStatisticsDao();

	public int memberListcount() {
		Connection conn = getConnection();

		int memberListCount = ssd.getMemberListCount(conn);
		//System.out.println(memberListCount);
		return memberListCount;

	}

	public int boardListCount() {
		Connection conn = getConnection();

		int boardListcount = ssd.getBoardListcount(conn);
		System.out.println(boardListcount);
		return boardListcount;
	}

	public int noticeListCount() {
		Connection conn = getConnection();

		int noticeListCount = ssd.getNoticeListCount(conn);
		//System.out.println(noticeListCount);
		return noticeListCount;
	}

	public int storeListCount() {
		Connection conn = getConnection();

		int storeListCount = ssd.getStoreListCount(conn);
		//System.out.println(storeListCount);
		return storeListCount;
	}

	public int roomListCount() {
		Connection conn = getConnection();

		int roomListCount = ssd.getRoomListCount(conn);
		//System.out.println(roomListCount);
		return roomListCount;
	}

}
