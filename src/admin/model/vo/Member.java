package admin.model.vo;

import java.util.Date;

public class Member {
	private int user_no;
	private String user_name;
	private String id;
	private String password;
	private String gender;
	private String nickname;
	private String address;
	private Date sign_date;
	private Date modify_date;
	private int authority;
	private String status;
	private String phone;
	public Member() {
		
	}
	public Member(int user_no, String id, String password, String user_name, String phone, String gender,
			String nickname, Date sign_date, Date modify_date, int authority, String status, String address) {
		super();
		this.user_no = user_no;
		this.id = id;
		this.password = password;
		this.user_name = user_name;
		this.phone = phone;
		this.gender = gender;
		this.nickname = nickname;
		this.sign_date = sign_date;
		this.modify_date = modify_date;
		this.authority = authority;
		this.status = status;
		this.address = address;
	}
	
	@Override
	public String toString() {
		return "Member [user_no=" + user_no + ", user_name=" + user_name + ", id=" + id + ", password=" + password
				+ ", gender=" + gender + ", nickname=" + nickname + ", address=" + address + ", sign_date=" + sign_date
				+ ", modify_date=" + modify_date + ", authority=" + authority + ", status=" + status + ", phone="
				+ phone + "]";
	}
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getSign_date() {
		return sign_date;
	}
	public void setSign_date(Date sign_date) {
		this.sign_date = sign_date;
	}
	public Date getModify_date() {
		return modify_date;
	}
	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Member(int user_no, int authority) {
		super();
		this.user_no = user_no;
		this.authority = authority;
	}
	
	
}
//USER_NO	NUMBER
//ID	VARCHAR2(100 BYTE)
//PASSWORD	VARCHAR2(100 BYTE)
//USER_NAME	VARCHAR2(100 BYTE)
//PHONE	VARCHAR2(20 BYTE)
//GENDER	VARCHAR2(20 BYTE)
//NICKNAME	VARCHAR2(100 BYTE)
//SIGN_DATE	DATE
//MODIFY_DATE	DATE
//AUTHORITY	NUMBER
//STATUS	VARCHAR2(1 BYTE)
//ADDRESS	VARCHAR2(100 BYTE)