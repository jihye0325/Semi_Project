package board.model.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Board {
	private int bno;
	private int bid;
	private String bType;
	private int userNo;
	private String bWriter;
	private String bTitle;
	private Date bDate;
	private String inputDate;
	private String bContent;
	private Date createDate;
	private Date modifyDate;
	private int bCount;
	private String status;
	private String bTag;
	private List<Attachment> imgList = new ArrayList<>();
	private List<Comment2> cmtList;
	
	/*
	B_NO	NUMBER
	B_ID	NUMBER
	USER_NO	NUMBER
	B_TITLE	VARCHAR2(100 BYTE)
	B_DATE	DATE
	B_CONTENT	VARCHAR2(4000 BYTE)
	B_CREATE_DATE	DATE
	B_MODIFY_DATE	DATE
	B_COUNT	NUMBER
	COMPANION	VARCHAR2(1 BYTE)
	STATUS	VARCHAR2(1 BYTE)
	B_TAG	VARCHAR2(100 BYTE)
	 */
	
	public Board() {}

	public Board(int bno, int bid, String bWriter, String bTitle, Date createDate , int bCount) {
		super();
		this.bno = bno;
		this.bid = bid;
		this.bWriter = bWriter;
		this.bTitle = bTitle;
		this.createDate = createDate;
		this.bCount = bCount;
	}

	public Board(int bno, int bid, String bType, int userNo, String bWriter, String bTitle, Date bDate, String bContent,
			Date createDate, Date modifyDate, int bCount) {
		super();
		this.bno = bno;
		this.bid = bid;
		this.bType = bType;
		this.userNo = userNo;
		this.bWriter = bWriter;
		this.bTitle = bTitle;
		this.bDate = bDate;
		this.bContent = bContent;		
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.bCount = bCount;
	}

	public Board(int bid, int userNo, String bTitle, Date bDate, String bContent, List<Attachment> imgList) {
		super();
		this.bid = bid;
		this.userNo = userNo;
		this.bTitle = bTitle;
		this.bDate = bDate;
		this.bContent = bContent;
		this.imgList = imgList;
	}
	
	public Board(int bid, int userNo, String bTitle, String inputDate, String bContent, List<Attachment> imgList, String bTag) {
		super();
		this.bid = bid;
		this.userNo = userNo;
		this.bTitle = bTitle;
		this.inputDate = inputDate;
		this.bContent = bContent;
		this.imgList = imgList;
		this.bTag = bTag;
	}

	public Board(int bno, String bTitle, String bContent, List<Attachment> imgList) {
		super();
		this.bno = bno;
		this.bTitle = bTitle;
		this.bContent = bContent;
		this.imgList = imgList;
	}

	public Board(int bid, int userNo, String bTitle, String bContent, List<Attachment> imgList, String bTag) {
		super();
		this.bid = bid;
		this.userNo = userNo;
		this.bTitle = bTitle;
		this.bContent = bContent;
		this.bTag = bTag;
		this.imgList = imgList;
	}

	public Board(int bno, int bid, int userNo, String bWriter, String bTitle, int bCount, String bTag) {
		super();
		this.bno = bno;
		this.bid = bid;
		this.userNo = userNo;
		this.bWriter = bWriter;
		this.bTitle = bTitle;
		this.bCount = bCount;
		this.bTag = bTag;
	}

	public Board(int bno, String bTitle, int bid, String bContent, List<Attachment> imgList, String bTag) {
		super();
		this.bno = bno;
		this.bid = bid;
		this.bTitle = bTitle;
		this.bContent = bContent;
		this.bTag = bTag;
		this.imgList = imgList;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public String getbType() {
		return bType;
	}

	public void setbType(String bType) {
		this.bType = bType;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getbWriter() {
		return bWriter;
	}

	public void setbWriter(String bWriter) {
		this.bWriter = bWriter;
	}

	public String getbTitle() {
		return bTitle;
	}

	public void setbTitle(String bTitle) {
		this.bTitle = bTitle;
	}

	public Date getbDate() {
		return bDate;
	}

	public void setbDate(Date bDate) {
		this.bDate = bDate;
	}

	public String getbContent() {
		return bContent;
	}

	public void setbContent(String bContent) {
		this.bContent = bContent;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public int getbCount() {
		return bCount;
	}

	public void setbCount(int bCount) {
		this.bCount = bCount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getbTag() {
		return bTag;
	}

	public void setbTag(String bTag) {
		this.bTag = bTag;
	}

	public List<Attachment> getImgList() {
		return imgList;
	}

	public void setImgList(List<Attachment> imgList) {
		this.imgList = imgList;
	}

	public String getInputDate() {
		return inputDate;
	}

	public void setInputDate(String inputDate) {
		this.inputDate = inputDate;
	}

	
	public List<Comment2> getCmtList() {
		return cmtList;
	}

	public void setCmtList(List<Comment2> cmtList) {
		this.cmtList = cmtList;
	}

	@Override
	public String toString() {
		return "Board [bno=" + bno + ", bid=" + bid + ", bType=" + bType + ", userNo=" + userNo + ", bWriter=" + bWriter
				+ ", bTitle=" + bTitle + ", bDate=" + bDate + ", inputDate=" + inputDate + ", bContent=" + bContent
				+ ", createDate=" + createDate + ", modifyDate=" + modifyDate + ", bCount=" + bCount + ", status="
				+ status + ", bTag=" + bTag + ", imgList=" + imgList + ", cmtList=" + cmtList + "]";
	}


}
