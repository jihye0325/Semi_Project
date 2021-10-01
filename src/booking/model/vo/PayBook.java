package booking.model.vo;

public class PayBook {

	private int p_no; //승인번호
	private int bk_no; //예약요청한 방번호
	private int money;
	private String pgname; //PG사이름
	private String p_paid; //API 결제상태 
	private String p_status; // 관리자 결제상태
	
	public PayBook() {}
	
	
//예약확인쪽에 insert 되면 DB 쪽에 insert
	public PayBook(int p_no, int money, String pgname, String p_paid) {
		super();
		this.p_no = p_no;
		this.money = money;
		this.pgname = pgname;
		this.p_paid = p_paid;
	}
	


	public int getP_no() {
		return p_no;
	}



	public void setP_no(int p_no) {
		this.p_no = p_no;
	}

	public int getBk_no() {
		return bk_no;
	}

	public void setBk_no(int bk_no) {
		this.bk_no = bk_no;
	}
	
	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	

	public String getPgname() {
		return pgname;
	}

	public void setPgname(String pgname) {
		this.pgname = pgname;
	}
	

	public String getP_paid() {
		return p_paid;
	}


	public void setP_paid(String p_paid) {
		this.p_paid = p_paid;
	}


	public String getP_status() {
		return p_status;
	}

	public void setP_status(String p_status) {
		this.p_status = p_status;
	}


	@Override
	public String toString() {
		return "PayBook [p_no=" + p_no + ", bk_no=" + bk_no + ", money=" + money + ", pgname=" + pgname + ", p_paid="
				+ p_paid + ", p_status=" + p_status + "]";
	}

	

	
}
