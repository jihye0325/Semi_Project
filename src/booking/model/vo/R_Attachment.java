package booking.model.vo;

public class R_Attachment {
	private int image_no;
	private String route;
	private String image_name;
	private String image_r_name;
	private int image_level;
	private String i_status;
	private int r_no;
	
	/*
	 *  IMAGE_NO	NUMBER
		ROUTE	VARCHAR2(1000 BYTE)
		IMAGE_NAME	VARCHAR2(255 BYTE)
		IMAGE_R_NAME	VARCHAR2(255 BYTE)
		IMAGE_LEVEL	NUMBER
		I_STATUS	VARCHAR2(1 BYTE)
	 * 
	 * */
	
	public R_Attachment() {}

	public R_Attachment(int image_no, String route, String image_name, String image_r_name, int image_level,
			String i_status) {
		super();
		this.image_no = image_no;
		this.route = route;
		this.image_name = image_name;
		this.image_r_name = image_r_name;
		this.image_level = image_level;
		this.i_status = i_status;
	}
	
	// 마이페이지 - 숙소 관리 리스트 출력
	public R_Attachment(String route, String image_r_name) {
		this.route = route;
		this.image_r_name = image_r_name;
	}
	

	

	public R_Attachment(String route, String image_r_name,int image_no) {
		super();
		this.route = route;
		this.image_r_name = image_r_name;
		this.image_no = image_no;
	}

//selectroom
	public R_Attachment(int image_no, String route, String image_name, String image_r_name, int image_level) {
		super();
		this.image_no = image_no;
		this.route = route;
		this.image_name=image_name;
		this.image_r_name = image_r_name;
		this.image_level = image_level;
	}
//작은사진들
	public R_Attachment(int r_no,int image_no,String route,String image_name, String image_r_name, int image_level) {
		super();
		this.r_no = r_no;
		this.image_no = image_no;
		this.route=route;
		this.image_name=image_name;
		this.image_r_name=image_r_name;
		this.image_level=image_level;
				
	}

	public int getImage_no() {
		return image_no;
	}


	public void setImage_no(int image_no) {
		this.image_no = image_no;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public String getImage_name() {
		return image_name;
	}

	public void setImage_name(String image_name) {
		this.image_name = image_name;
	}

	public String getImage_r_name() {
		return image_r_name;
	}

	public void setImage_r_name(String image_r_name) {
		this.image_r_name = image_r_name;
	}

	public int getImage_level() {
		return image_level;
	}

	public void setImage_level(int image_level) {
		this.image_level = image_level;
	}

	public String getI_status() {
		return i_status;
	}

	public void setI_status(String i_status) {
		this.i_status = i_status;
	}

	@Override
	public String toString() {
		return "R_Attachment [image_no=" + image_no + ", route=" + route + ", image_name=" + image_name
				+ ", image_r_name=" + image_r_name + ", image_level=" + image_level + ", i_status=" + i_status + "]" ;
	}
	
	
	

}
