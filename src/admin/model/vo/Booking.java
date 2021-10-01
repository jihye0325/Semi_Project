package admin.model.vo;

import java.util.Date;

public class Booking {
	private int booking_no;
	private int user_no;
	private int r_no;
	private String booking_status;
	private Date book_start;
	private Date book_end;
	private String host_confirm;
	@Override
	public String toString() {
		return "Booking [booking_no=" + booking_no + ", user_no=" + user_no + ", r_no=" + r_no + ", booking_status="
				+ booking_status + ", book_start=" + book_start + ", book_end=" + book_end + ", host_confirm="
				+ host_confirm + "]";
	}
	public int getBooking_no() {
		return booking_no;
	}
	public void setBooking_no(int booking_no) {
		this.booking_no = booking_no;
	}
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	public int getR_no() {
		return r_no;
	}
	public void setR_no(int r_no) {
		this.r_no = r_no;
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
	public String getHost_confirm() {
		return host_confirm;
	}
	public void setHost_confirm(String host_confirm) {
		this.host_confirm = host_confirm;
	}
	public Booking() {
		super();
	}
	public Booking(int booking_no, int user_no, int r_no, String booking_status, Date book_start, Date book_end,
			String host_confirm) {
		super();
		this.booking_no = booking_no;
		this.user_no = user_no;
		this.r_no = r_no;
		this.booking_status = booking_status;
		this.book_start = book_start;
		this.book_end = book_end;
		this.host_confirm = host_confirm;
	}
	
	
	

}
