package board.model.vo;

public class Attachment {
	private int bno;	// 게시글 번호
	private int imgNo;	// 이미지 번호
	private String originName;
	private String reName;
	private String deletedName;
	private String route;
	private int imgLevel;
	private String status;
	
	public Attachment() {}

	public Attachment(String reName, String route) {
		super();
		this.reName = reName;
		this.route = route;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public int getImgNo() {
		return imgNo;
	}

	public void setImgNo(int imgNo) {
		this.imgNo = imgNo;
	}

	public String getOriginName() {
		return originName;
	}

	public void setOriginName(String originName) {
		this.originName = originName;
	}

	public String getReName() {
		return reName;
	}

	public void setReName(String reName) {
		this.reName = reName;
	}

	public int getImgLevel() {
		return imgLevel;
	}

	public void setImgLevel(int imgLevel) {
		this.imgLevel = imgLevel;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDeletedName() {
		return deletedName;
	}

	public void setDeletedName(String deletedName) {
		this.deletedName = deletedName;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	@Override
	public String toString() {
		return "Attachment [bno=" + bno + ", imgNo=" + imgNo + ", originName=" + originName + ", reName=" + reName
				+ ", deletedName=" + deletedName + ", route=" + route + ", imgLevel=" + imgLevel + ", status=" + status
				+ "]";
	}
	
}
