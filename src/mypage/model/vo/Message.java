package mypage.model.vo;

import java.util.Date;

public class Message {
	private int msgNo;
	private String senderName;
	private String msgTitle;
	private String msgContent;
	private Date msgDate;
	private int sender;
	private int receiver;
	private Date msgCheck;
	private String msgStatus;

	public Message() {
	}

	public Message(int msgNo, String msgTitle, String msgContent, Date msgDate, int sender, int receiver, Date msgCheck,
			String msgStatus) {
		this.msgNo = msgNo;
		this.msgTitle = msgTitle;
		this.msgContent = msgContent;
		this.msgDate = msgDate;
		this.sender = sender;
		this.receiver = receiver;
		this.msgCheck = msgCheck;
		this.msgStatus = msgStatus;
	}

	public Message(String senderName, int msgNo, String msgTitle, String msgContent, Date msgDate) {
		this.msgNo = msgNo;
		this.senderName = senderName;
		this.msgTitle = msgTitle;
		this.msgContent = msgContent;
		this.msgDate = msgDate;
	}
	
	public Message(String msgTitle, String msgContent, int sender, int receiver) {
		super();
		this.msgTitle = msgTitle;
		this.msgContent = msgContent;
		this.sender = sender;
		this.receiver = receiver;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public int getMsgNo() {
		return msgNo;
	}

	public void setMsgNo(int msgNo) {
		this.msgNo = msgNo;
	}

	public String getMsgTitle() {
		return msgTitle;
	}

	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	public Date getMsgDate() {
		return msgDate;
	}

	public void setMsgDate(Date msgDate) {
		this.msgDate = msgDate;
	}

	public int getSender() {
		return sender;
	}

	public void setSender(int sender) {
		this.sender = sender;
	}

	public int getReceiver() {
		return receiver;
	}

	public void setReceiver(int receiver) {
		this.receiver = receiver;
	}

	public Date getMsgCheck() {
		return msgCheck;
	}

	public void setMsgCheck(Date msgCheck) {
		this.msgCheck = msgCheck;
	}

	public String getMsgStatus() {
		return msgStatus;
	}

	public void setMsgStatus(String msgStatus) {
		this.msgStatus = msgStatus;
	}

	@Override
	public String toString() {
		return "Message [msgNo=" + msgNo + ", senderName=" + senderName + ", msgTitle=" + msgTitle + ", msgContent="
				+ msgContent + ", msgDate=" + msgDate + ", sender=" + sender + ", receiver=" + receiver + ", msgCheck="
				+ msgCheck + ", msgStatus=" + msgStatus + "]";
	}

}
