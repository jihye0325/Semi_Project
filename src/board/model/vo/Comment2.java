package board.model.vo;

import java.util.Date;

public class Comment2 {
	private int cid;			// 댓글 고유번호
	private int cWriter;		// 댓글 작성자 userNo
	private String nickname;	// 작성자 닉네임
	private String cContent;	// 내용
	private Date createDate;	// 작성일
	private Date modifyDate;	// 수정일
	private int RefBno;			// 게시글 번호
	private int originCid;		// 원댓
	private String status;		// 상태
	
	/*
	 	COMMENT2_NO	NUMBER
		USER_NO	NUMBER 
		C_CREATE_DATE	DATE
		C_MODIFY_DATE	DATE
		C_CONTENT	VARCHAR2(600 BYTE)
		B_NO	NUMBER
		N_NO	NUMBER
		A_NO	NUMBER
		ORIGIN_RID	NUMBER
		C_STATUS	VARCHAR2(1 BYTE)
	*/
	
	public Comment2() {}
	/* 기존 selectCmtList
	public Comment2(int cid, int cWriter, String nickname, String cContent, Date modifyDate, int refBno) {
		super();
		this.cid = cid;
		this.cWriter = cWriter;
		this.nickname = nickname;
		this.cContent = cContent;
		this.modifyDate = modifyDate;
		RefBno = refBno;
	} */
	
	public Comment2(int cWriter, String cContent, int refBno, int originCid) {
		super();
		this.cWriter = cWriter;
		this.cContent = cContent;
		RefBno = refBno;
		this.originCid = originCid;
	}

	public Comment2(int cid, int cWriter, String nickname, String cContent, Date modifyDate, int refBno,
			int originCid) {
		super();
		this.cid = cid;
		this.cWriter = cWriter;
		this.nickname = nickname;
		this.cContent = cContent;
		this.modifyDate = modifyDate;
		RefBno = refBno;
		this.originCid = originCid;
	}
	public Comment2(int cid, String cContent) {
		super();
		this.cid = cid;
		this.cContent = cContent;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public int getRefBno() {
		return RefBno;
	}

	public void setRefBno(int refBno) {
		RefBno = refBno;
	}

	public String getcContent() {
		return cContent;
	}

	public void setcContent(String cContent) {
		this.cContent = cContent;
	}

	public int getcWriter() {
		return cWriter;
	}

	public void setcWriter(int cWriter) {
		this.cWriter = cWriter;
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

	public int getOriginCid() {
		return originCid;
	}

	public void setOriginCid(int originCid) {
		this.originCid = originCid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Override
	public String toString() {
		return "Comment2 [cid=" + cid + ", cWriter=" + cWriter + ", nickname=" + nickname + ", cContent=" + cContent
				+ ", createDate=" + createDate + ", modifyDate=" + modifyDate + ", RefBno=" + RefBno + ", originCid="
				+ originCid + ", status=" + status + "]";
	}
}
