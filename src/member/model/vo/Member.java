package member.model.vo;

import java.sql.Date;

public class Member {
// 회원번호 - int userNo
// 아이디(이메일) - String userId
// 비밀번호 - String userPwd
// 이름 - String userName
// 전화번호 - String phone
// 성별 - String gender
// 닉네임 - String nickName
// 주소 - String address
// 가입일 - String signUpDate
// 회원정보 수정일 - String modifyDate
// 회원 권한 - int authority
// 회원 상태 - String status
	private int userNo;
	private String userId;
	private String userPwd;
	private String userName;
	private String phone;
	private String gender;
	private String nickName;
	private Date signDate;
	private Date modifyDate;
	private int authority;
	private String status;
	private String address;
	// 이메일 인증 추가
	private boolean userEmailCheck;

	
	public Member(int userNo, String userId, String userPwd, String userName, String phone, String gender,
			String nickName, Date signDate, Date modifyDate, int authority, String status, String address) {
		super();
		this.userNo = userNo;
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.phone = phone;
		this.gender = gender;
		this.nickName = nickName;
		this.signDate = signDate;
		this.modifyDate = modifyDate;
		this.authority = authority;
		this.status = status;
		this.address = address;
	}

	public Member(String userId, String userPwd, String userName, String phone, String gender, String nickName) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.phone = phone;
		this.gender = gender;
		this.nickName = nickName;
	}

	public Member(int userNo, String userId, String userPwd, String userName, String phone, String gender,
			String nickName, String address) {
		super();
		this.userNo = userNo;
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.phone = phone;
		this.gender = gender;
		this.nickName = nickName;
		this.address = address;
	}
	public Member(int userNo, String userPwd, String phone, String nickName, String address) {
  super();
		this.userNo = userNo;
		this.userPwd = userPwd;
		this.phone = phone;
		this.nickName = nickName;
		this.address = address;
	}
	//숙소 댓글
	public Member(String phone,String gender) {
		super();
		this.phone = phone;
		this.gender = gender;
	}

	// 아이디 찾기 _ selectroom
	public Member(String userName, String phone,String gender) {
		super();
		this.userName = userName;
		this.phone = phone;
		this.gender=gender;
	}

	// 아이디 찾기
	public Member(String userId) {
		super();
		this.userId = userId;
	}
	

	public Member(int userNo, String userId, String userName, int authority) {
		super();
		this.userNo = userNo;
		this.userId = userId;
		this.userName = userName;
		this.authority = authority;
	}

	public Member() {
		super();
	}

	// 회원가입 - 주소 추가
	public Member(String userId, String userPwd, String userName, String phone, String gender, String nickName,
			String address) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.phone = phone;
		this.gender = gender;
		this.nickName = nickName;
		this.address = address;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Date getSignDate() {
		return signDate;
	}

	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public int getAuthority() {
		return authority;
	}

	public void setAuthority(int authority) {
		this.authority = authority;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public boolean isUserEmailCheck() {
		return userEmailCheck;
	}

	public void setUserEmailCheck(boolean userEmailCheck) {
		this.userEmailCheck = userEmailCheck;
	}

	@Override
	public String toString() {
		return "Member [userNo=" + userNo + ", userId=" + userId + ", userPwd=" + userPwd + ", userName=" + userName
				+ ", phone=" + phone + ", gender=" + gender + ", nickName=" + nickName + ", signDate=" + signDate
				+ ", modifyDate=" + modifyDate + ", authority=" + authority + ", status=" + status + ", address="
				+ address + "]";
	}

	

}