package admin.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import admin.model.dao.reportManagementDao;
import admin.model.vo.PageInfo;
import admin.model.vo.Report;
import admin.model.vo.Search;

public class reportManagementService {
	private reportManagementDao rmd = new reportManagementDao();

	public Map<String, Object> selectReportList(int page, Search s) {
		Connection conn = getConnection();

		int reportListCount = rmd.getReportListCount(conn, s);
		PageInfo pi = new PageInfo(page, reportListCount, 10, 10);
		
		List<Report> reportList = rmd.selectReportList(conn,pi,s);
		Map<String, Object> returnMap = new HashMap<>();

		returnMap.put("pi", pi);
		returnMap.put("reportList", reportList);
		// System.out.println(returnMap);
		return returnMap;
	}

	public Report selectReportDetail(int report_no) {
		Connection conn = getConnection();
		Report reportDetail = rmd.reportDetail(conn, report_no);
		
		close(conn);
		
		return reportDetail;
	}

	public int deleteBoard(int b_no) {
		
		Connection conn = getConnection();
		int result = rmd.deleteBoard(conn,b_no);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

}
