package store.model.vo;

public class S_Wish {
	private int s_no;
	private int use_no;
	private String s_status;
	
	public S_Wish() {}

	public S_Wish(int s_no, int use_no, String s_status) {
		super();
		this.s_no = s_no;
		this.use_no = use_no;
		this.s_status = s_status;
	}

	public int getS_no() {
		return s_no;
	}

	public void setS_no(int s_no) {
		this.s_no = s_no;
	}

	public int getUse_no() {
		return use_no;
	}

	public void setUse_no(int use_no) {
		this.use_no = use_no;
	}

	public String getS_status() {
		return s_status;
	}

	public void setS_status(String s_status) {
		this.s_status = s_status;
	}

	@Override
	public String toString() {
		return "S_Wish [s_no=" + s_no + ", use_no=" + use_no + ", s_status=" + s_status + "]";
	}
	
}
