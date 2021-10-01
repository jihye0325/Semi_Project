package store.model.vo;

public class S_Image {
	private int image_no;	// 파일 pk
	private int s_no;	// 참조한 게시글 번호
	private String image_name;	// 파일 업로드 시 이름
	private String image_r_name;	// 서버에 저장된 파일 이름
	private String s_deleteName; 	// 지워진 이름
	private String route;	// 파일 저장 경로
	private int fileLevel;	// 대표사진: 0, 내용 사진: 1
	private String i_status;	// 삭제 여부
	/*IMAGE_NO
	ROUTE
	IMAGE_NAME
	IMAGE_R_NAME
	IMAGE_LEVEL
	I_STATUS*/
	
	public S_Image() {}

	public S_Image(String image_r_name, String route) {
		super();
		this.image_r_name = image_r_name;
		this.route = route;
	}	

	public S_Image(int image_no, String route, String image_name, String image_r_name) {
		super();
		this.image_no = image_no;
		this.route = route;
		this.image_name = image_name;
		this.image_r_name = image_r_name;
	}

	public S_Image(int image_no, int s_no, String image_name, String image_r_name, String route) {
		super();
		this.image_no = image_no;
		this.s_no = s_no;
		this.image_name = image_name;
		this.image_r_name = image_r_name;
		this.route = route;
	}

	public int getImage_no() {
		return image_no;
	}

	public void setImage_no(int image_no) {
		this.image_no = image_no;
	}

	public int getS_no() {
		return s_no;
	}

	public void setS_no(int s_no) {
		this.s_no = s_no;
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

	public String getS_deleteName() {
		return s_deleteName;
	}

	public void setS_deleteName(String s_deleteName) {
		this.s_deleteName = s_deleteName;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public int getFileLevel() {
		return fileLevel;
	}

	public void setFileLevel(int fileLevel) {
		this.fileLevel = fileLevel;
	}

	public String getI_status() {
		return i_status;
	}

	public void setI_status(String i_status) {
		this.i_status = i_status;
	}

	@Override
	public String toString() {
		return "S_Image [image_no=" + image_no + ", s_no=" + s_no + ", image_name=" + image_name + ", image_r_name="
				+ image_r_name + ", s_deleteName=" + s_deleteName + ", route=" + route + ", fileLevel=" + fileLevel
				+ ", i_status=" + i_status + "]";
	}
	
	
}
 