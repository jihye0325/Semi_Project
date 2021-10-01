package board.model.vo;

public class B_Report {

	private int reportNo;
	private int bno;
	private int userNo;
	private int reportId;
	/*
		REPORT_NO	NUMBER
		B_NO	NUMBER
		USER_NO	NUMBER
		REPORT_ID	NUMBER
	 */
	
	public B_Report() {}

	public B_Report(int bno, int userNo, int reportId) {
		super();
		this.bno = bno;
		this.userNo = userNo;
		this.reportId = reportId;
	}

	public int getReportNo() {
		return reportNo;
	}

	public void setReportNo(int reportNo) {
		this.reportNo = reportNo;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public int getReportId() {
		return reportId;
	}

	public void setReportId(int reportId) {
		this.reportId = reportId;
	}

	@Override
	public String toString() {
		return "B_Report [reportNo=" + reportNo + ", bno=" + bno + ", userNo=" + userNo + ", reportId=" + reportId
				+ "]";
	}
}
