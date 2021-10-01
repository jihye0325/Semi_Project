package booking.model.vo;

public class Booking {

	private int booking_no;
	private String r_start;
	private String r_end;
	private int head;
	private int userno;
	
	public Booking() {}
	
	// bookinsert_Roombooking DB에 저장
	public Booking(int booking_no, String r_start, String r_end, int head, int userno) {
		super();
		this.booking_no = booking_no;
		this.r_start = r_start;
		this.r_end = r_end;
		this.head = head;
		this.userno = userno;
	}




	public int getBooking_no() {
		return booking_no;
	}

	public void setBooking_no(int booking_no) {
		this.booking_no = booking_no;
	}

	public String getR_start() {
		return r_start;
	}

	public void setR_start(String r_start) {
		this.r_start = r_start;
	}

	public String getR_end() {
		return r_end;
	}

	public void setR_end(String r_end) {
		this.r_end = r_end;
	}

	public int getHead() {
		return head;
	}

	public void setHead(int head) {
		this.head = head;
	}

	public int getUserno() {
		return userno;
	}

	public void setUserno(int userno) {
		this.userno = userno;
	}

	@Override
	public String toString() {
		return "Booking [booking_no=" + booking_no + ", r_start=" + r_start + ", r_end=" + r_end + ", head=" + head
				+ ", userno=" + userno + "]";
	}
	
	
	
	
}
