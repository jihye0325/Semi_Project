package booking.model.vo;


public class Wish {
	private int rno;
	private int userno;

	
	public Wish() {}

	public Wish(int rno, int userno) {
		super();
		this.rno = rno;
		this.userno = userno;
	}

	
	public int getRno() {
		return rno;
	}

	public void setRno(int rno) {
		this.rno = rno;
	}

	public int getUserno() {
		return userno;
	}

	public void setUserno(int userno) {
		this.userno = userno;
	}
	
	


	@Override
	public String toString() {
		return   "Wish [rno=" + rno + ", userno=" + userno + "]";
	}

}
