package store.model.vo;

import java.util.Date;
import java.util.List;

public class Store {
	private int s_no;
	private String s_name;
	private String s_tel;
	private String s_time;
	private String menu;
	private String s_address;
	private int s_count;
	private String s_status;
	private Date s_create_date;
	private double s_score;
	private int reply_count;
	private S_Image s_image = new S_Image();
	private List<S_Reply> s_replyList;
	private List<S_Wish> s_wishList;

	/*
	 * S_NO S_NAME S_TEL S_TIME MENU S_ADDRESS S_COUNT S_STATUS S_CREATE_DATE
	 */

	public Store() {
	}

	public Store(int s_no, String s_name, String s_tel, String menu, String s_address, int s_count, String s_status,
			Date s_create_date, double s_score, int reply_count, String image_r_name, String route) {
		super();
		this.s_no = s_no;
		this.s_name = s_name;
		this.s_tel = s_tel;
		this.menu = menu;
		this.s_address = s_address;
		this.s_count = s_count;
		this.s_status = s_status;
		this.s_create_date = s_create_date;
		this.s_score = s_score;
		this.reply_count = reply_count;
		this.s_image = new S_Image(image_r_name, route);
	}

	public Store(int s_no, String s_name, String s_tel, String s_time, String menu, String s_address, int s_count,
			String s_status, Date s_create_date, double s_score, int reply_count) {
		super();
		this.s_no = s_no;
		this.s_name = s_name;
		this.s_tel = s_tel;
		this.s_time = s_time;
		this.menu = menu;
		this.s_address = s_address;
		this.s_count = s_count;
		this.s_status = s_status;
		this.s_create_date = s_create_date;
		this.s_score = s_score;
		this.reply_count = reply_count;
	}

	public Store(int s_no, String s_name, String s_tel, String menu, String s_address, int s_count, String s_status,
			Date s_create_date) {
		super();
		this.s_no = s_no;
		this.s_name = s_name;
		this.s_tel = s_tel;
		this.menu = menu;
		this.s_address = s_address;
		this.s_count = s_count;
		this.s_status = s_status;
		this.s_create_date = s_create_date;
	}

	public Store(int s_no, String s_name, String s_tel, String s_address, String s_time, String menu, S_Image s_image) {
		super();
		this.s_no = s_no;
		this.s_name = s_name;
		this.s_tel = s_tel;
		this.s_time = s_time;
		this.menu = menu;
		this.s_address = s_address;
		this.s_image = s_image;
	}

	// 마이페이지 식당 위시리스트
	public Store(int s_no, String s_name, String s_tel, String route, String image_r_name) {
		this.s_no = s_no;
		this.s_name = s_name;
		this.s_tel = s_tel;
		this.s_image = new S_Image(image_r_name, route);
	}

	public int getS_no() {
		return s_no;
	}

	public void setS_no(int s_no) {
		this.s_no = s_no;
	}

	public String getS_name() {
		return s_name;
	}

	public void setS_name(String s_name) {
		this.s_name = s_name;
	}

	public String getS_tel() {
		return s_tel;
	}

	public void setS_tel(String s_tel) {
		this.s_tel = s_tel;
	}

	public String getS_time() {
		return s_time;
	}

	public void setS_time(String s_time) {
		this.s_time = s_time;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public String getS_address() {
		return s_address;
	}

	public void setS_address(String s_address) {
		this.s_address = s_address;
	}

	public int getS_count() {
		return s_count;
	}

	public void setS_count(int s_count) {
		this.s_count = s_count;
	}

	public String getS_status() {
		return s_status;
	}

	public void setS_status(String s_status) {
		this.s_status = s_status;
	}

	public Date getS_create_date() {
		return s_create_date;
	}

	public void setS_create_date(Date s_create_date) {
		this.s_create_date = s_create_date;
	}

	public S_Image getS_image() {
		return s_image;
	}

	public void setS_image(S_Image s_image) {
		this.s_image = s_image;
	}

	public List<S_Reply> getS_replyList() {
		return s_replyList;
	}

	public void setS_replyList(List<S_Reply> s_replyList) {
		this.s_replyList = s_replyList;
	}

	public double getS_score() {
		return s_score;
	}

	public void setS_score(double s_score) {
		this.s_score = s_score;
	}

	public int getReply_count() {
		return reply_count;
	}

	public void setReply_count(int reply_count) {
		this.reply_count = reply_count;
	}

	public List<S_Wish> getS_wishList() {
		return s_wishList;
	}

	public void setS_wishList(List<S_Wish> s_wishList) {
		this.s_wishList = s_wishList;
	}

	@Override
	public String toString() {
		return "Store [s_no=" + s_no + ", s_name=" + s_name + ", s_tel=" + s_tel + ", s_time=" + s_time + ", menu="
				+ menu + ", s_address=" + s_address + ", s_count=" + s_count + ", s_status=" + s_status
				+ ", s_create_date=" + s_create_date + ", s_score=" + s_score + ", reply_count=" + reply_count
				+ ", s_image=" + s_image + ", s_replyList=" + s_replyList + ", s_wishList=" + s_wishList + "]";
	}

	public Store(int s_no, String s_name, String s_tel, String s_time, String menu, String s_address, int s_count,
			String s_status, Date s_create_date) {
		super();
		this.s_no = s_no;
		this.s_name = s_name;
		this.s_tel = s_tel;
		this.s_time = s_time;
		this.menu = menu;
		this.s_address = s_address;
		this.s_count = s_count;
		this.s_status = s_status;
		this.s_create_date = s_create_date;
	}

}
