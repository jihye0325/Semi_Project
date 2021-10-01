package admin.model.vo;

import java.util.Date;

public class Room {
	private int r_no;
	private String r_name;
	private String r_tel;
	private String r_info;
	private int r_pay;
	private String r_address;
	private int user_no;
	private Date r_start;
	private Date r_end;
	private String r_status;
	private int r_head;
	private int money;
	private String host_name;
	private String p_status;
	private String booking_status;
	private Date book_start;
	private Date book_end;
	
	public Room() {
		super();
	}
	
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public String getHost_name() {
		return host_name;
	}
	public void setHost_name(String host_name) {
		this.host_name = host_name;
	}
	public String getP_status() {
		return p_status;
	}
	public void setP_status(String p_status) {
		this.p_status = p_status;
	}
	public String getBooking_status() {
		return booking_status;
	}
	public void setBooking_status(String booking_status) {
		this.booking_status = booking_status;
	}
	public Date getBook_start() {
		return book_start;
	}
	public void setBook_start(Date book_start) {
		this.book_start = book_start;
	}
	public Date getBook_end() {
		return book_end;
	}
	public void setBook_end(Date book_end) {
		this.book_end = book_end;
	}

	
	
	public int getR_no() {
		return r_no;
	}
	public void setR_no(int r_no) {
		this.r_no = r_no;
	}
	public String getR_name() {
		return r_name;
	}
	public void setR_name(String r_name) {
		this.r_name = r_name;
	}
	public String getR_tel() {
		return r_tel;
	}
	public void setR_tel(String r_tel) {
		this.r_tel = r_tel;
	}
	public String getR_info() {
		return r_info;
	}
	public void setR_info(String r_info) {
		this.r_info = r_info;
	}
	public int getR_pay() {
		return r_pay;
	}
	public void setR_pay(int r_pay) {
		this.r_pay = r_pay;
	}
	public String getR_address() {
		return r_address;
	}
	public void setR_address(String r_address) {
		this.r_address = r_address;
	}
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	public Date getR_start() {
		return r_start;
	}
	public void setR_start(Date r_start) {
		this.r_start = r_start;
	}
	public Date getR_end() {
		return r_end;
	}
	public void setR_end(Date r_end) {
		this.r_end = r_end;
	}
	public String getR_status() {
		return r_status;
	}
	public void setR_status(String r_status) {
		this.r_status = r_status;
	}
	public int getR_head() {
		return r_head;
	}
	public void setR_head(int r_head) {
		this.r_head = r_head;
	}
	public Room(int r_no, String r_name, String r_tel, String r_info, int r_pay, String r_address, int user_no,
			Date r_start, Date r_end, String r_status, int r_head, int money, String host_name, String p_status,
			String booking_status, Date book_start, Date book_end) {
		super();
		this.r_no = r_no;
		this.r_name = r_name;
		this.r_tel = r_tel;
		this.r_info = r_info;
		this.r_pay = r_pay;
		this.r_address = r_address;
		this.user_no = user_no;
		this.r_start = r_start;
		this.r_end = r_end;
		this.r_status = r_status;
		this.r_head = r_head;
		this.money = money;
		this.host_name = host_name;
		this.p_status = p_status;
		this.booking_status = booking_status;
		this.book_start = book_start;
		this.book_end = book_end;
	}
	@Override
	public String toString() {
		return "Room [r_no=" + r_no + ", r_name=" + r_name + ", r_tel=" + r_tel + ", r_info=" + r_info + ", r_pay="
				+ r_pay + ", r_address=" + r_address + ", user_no=" + user_no + ", r_start=" + r_start + ", r_end="
				+ r_end + ", r_status=" + r_status + ", r_head=" + r_head + ", money=" + money + ", host_name="
				+ host_name + ", p_status=" + p_status + ", booking_status=" + booking_status + ", book_start="
				+ book_start + ", book_end=" + book_end + "]";
	}

	public Room(String r_name, String r_tel, int r_pay, String r_address, String host_name,int money) {
		super();
		this.r_name = r_name;
		this.r_tel = r_tel;
		this.r_pay = r_pay;
		this.r_address = r_address;
		this.host_name = host_name;
		this.money = money;
	}

	public Room(int r_no, String r_name, String r_tel, String r_info, int r_pay, String r_address, int user_no,
			Date r_start, Date r_end, String r_status, int r_head) {
		super();
		this.r_no = r_no;
		this.r_name = r_name;
		this.r_tel = r_tel;
		this.r_info = r_info;
		this.r_pay = r_pay;
		this.r_address = r_address;
		this.user_no = user_no;
		this.r_start = r_start;
		this.r_end = r_end;
		this.r_status = r_status;
		this.r_head = r_head;
	}

	
}
