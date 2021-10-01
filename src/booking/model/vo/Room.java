package booking.model.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import member.model.vo.Member;

public class Room {

	private int r_no; // 룸 고유번호
	private String r_name; // 룸이름
	private String r_tel;// 룸 전화번호
	private String r_info; // 룸 정보
	private int r_pay; // 룸가격
	private int sum;
	private String r_address; // 주소
	private int user_no; // 사용자 고유 번호
	private String user_name;
	private Date r_start;// 체크인
	private Date r_end; // 체크아웃
	private String r_status;// 예약상태
	private int r_head; // 숙소인원수
	private int r_r_head; // 변경된 숙소인원수
	private String hostName; // 호스트 이름
	private int booking_no; // 예약번호
	private String guest_name; // 게스트 이름
	private String host_confirm; // 호스트 예약 요청 확인 여부
	private double r_score;
	private List<R_Attachment> roomimage = new ArrayList<>();
	private List<R_comment1> replyList;
	private List<Member> member = new ArrayList<>();

	/*
	 * R_NO NUMBER R_NAME VARCHAR2(30 BYTE) R_TEL VARCHAR2(13 BYTE) R_INFO
	 * VARCHAR2(4000 BYTE) R_PAY NUMBER R_ADDRESS VARCHAR2(100 BYTE) USER_NO NUMBER
	 * R_START DATE R_END DATE R_STATUS VARCHAR2(1 BYTE)
	 */

	
	// 바뀌기 전 Room 정보
	public Room(int rno, int user_no, int r_pay) {
		super();
		this.r_no=rno;
		this.user_no=user_no;
		this.r_pay=r_pay;
	}
//selectRoomList 
	public Room(int r_no, String r_name, String r_tel, String r_info, int r_pay, String r_address, int user_no,
			String user_name, Date r_start, Date r_end, String r_status, int r_head) {
		super();
		this.r_no = r_no;
		this.r_name = r_name;
		this.r_tel = r_tel;
		this.r_info = r_info;
		this.r_pay = r_pay;
		this.r_address = r_address;
		this.user_no = user_no;
		this.user_name = user_name;
		this.r_start = r_start;
		this.r_end = r_end;
		this.r_status = r_status;
		this.r_head = r_head;
	}

//selectRoom

	/*
	 * public Room(int r_no, String r_name, String r_tel, String r_info, int r_pay,
	 * String address, int user_no, String user_name,Date r_start, Date r_end,
	 * String r_status, int r_head) { super(); this.r_no = r_no; this.r_name =
	 * r_name; this.r_tel = r_tel; this.r_info = r_info; this.r_pay = r_pay;
	 * this.address = address; this.user_no = user_no; this.user_name = user_name;
	 * this.r_start = r_start; this.r_end = r_end; this.r_status = r_status;
	 * this.r_head = r_head; // this.member.add(new Member(userName,phone));
	 * 
	 * }
	 */
//selectRoomImage _ selectRoom
	public Room(int r_no, String r_name, String r_tel, String r_info, int r_pay, String r_address, int user_no,
			String user_name, String phone, String gender, Date r_start, Date r_end, String r_status, int r_head,
			int image_no, String route, String image_name, String image_r_name, int image_level, double r_score) {
		super();
		this.r_no = r_no;
		this.r_name = r_name;
		this.r_tel = r_tel;
		this.r_info = r_info;
		this.r_pay = r_pay;
		this.r_address = r_address;
		this.user_no = user_no;
		this.user_name = user_name;
		this.member.add(new Member(user_name, phone, gender)); // member
		this.r_start = r_start;
		this.r_end = r_end;
		this.r_status = r_status;
		this.r_head = r_head;
		this.roomimage.add(new R_Attachment(image_no, route, image_name, image_r_name, image_level)); // 사진
		this.r_score = r_score;
		// this.member.add(new Member(userName,phone));

	}

// 작은 사진들
	public Room(int r_no, int image_no, String route, String image_name, String image_r_name, int image_level) {
		super();
		this.r_no = r_no;
		this.roomimage.add(new R_Attachment(image_no, route, image_name, image_r_name, image_level)); // 사진
	}

//selectRoomImage
	public Room(int r_no, String r_name, String r_info, String route, String image_r_name, String r_tel, int r_pay,
			String r_address, int user_no, String user_name, Date r_start, int r_head, Date r_end, String r_status,
			int image_no) {
		super();
		this.r_no = r_no;
		this.r_name = r_name;
		this.r_tel = r_tel;
		this.r_info = r_info;
		this.r_pay = r_pay;
		this.r_address = r_address;
		this.user_no = user_no;

		this.user_name = user_name;

		this.r_start = r_start;
		this.r_head = r_head;
		this.r_end = r_end;
		this.r_status = r_status;
		this.r_head = r_head;
		this.roomimage.add(new R_Attachment(route, image_r_name, image_no));
	}

// 숙소 등록
	public Room(String r_name, String r_tel, String r_info, int r_pay, String r_address, int user_no, Date r_start,
			Date r_end, List<R_Attachment> roomimage, int r_head) {
		super();
		this.r_name = r_name;
		this.r_tel = r_tel;
		this.r_info = r_info;
		this.r_pay = r_pay;
		this.r_address = r_address;
		this.user_no = user_no;
		this.r_start = r_start;
		this.r_end = r_end;
		this.roomimage = roomimage;
		this.r_head = r_head;
	}

	public List<R_comment1> getReplyList() {
		return replyList;
	}

	public void setReplyList(List<R_comment1> replyList) {
		this.replyList = replyList;
	}

	// 마이페이지 숙소
	public Room(int r_no, String r_name, String image_r_name, String route) {
		this.r_no = r_no;
		this.r_name = r_name;
		this.roomimage.add(new R_Attachment(image_r_name, route));
	}

	// 마이페이지 호스트가 예약 확인 처리
	public Room(int r_no, int booking_no, String r_name, String geust_name, Date r_start, Date r_end,
			String image_r_name, String route) {
		this.r_no = r_no;
		this.booking_no = booking_no;
		this.r_name = r_name;
		this.guest_name = geust_name;
		this.r_start = r_start;
		this.r_end = r_end;
		this.roomimage.add(new R_Attachment(image_r_name, route));
	}

	// 위시리스트 출력
	public Room(int r_no, String r_name, String hostName, String image_r_name, String route) {
		this.r_no = r_no;
		this.r_name = r_name;
		this.hostName = hostName;
		this.roomimage.add(new R_Attachment(image_r_name, route));
	}

	// 예약 확인 리스트
	public Room(int r_no, int booking_no, String r_status, String r_name, String hostName, Date r_start, Date r_end,
			String host_confirm, String image_r_name, String route) {
		this.r_no = r_no;
		this.booking_no = booking_no;
		this.r_status = r_status;
		this.r_name = r_name;
		this.hostName = hostName;
		this.r_start = r_start;
		this.r_end = r_end;
		this.host_confirm = host_confirm;
		this.roomimage.add(new R_Attachment(image_r_name, route));
	}

	// 예약 확인 리스트에 insert
	public Room(int r_no, int user_no, Date r_start, Date r_end, int r_r_head) {
		super();
		this.r_no = r_no;
		this.user_no = user_no;
		this.r_start = r_start;
		this.r_end = r_end;
		this.r_r_head = r_r_head;
	}
	
	
//예약요청(인원수 변경됨)
	public Room(int r_no, int user_no, Date r_start, Date r_end, /* int r_head, */ int r_r_head, int r_pay, int sum) {
		super();
		this.r_no = r_no;
		this.r_pay = r_pay;
		this.sum = sum;
		this.user_no = user_no;
		this.r_start = r_start;
		this.r_end = r_end;
	//	this.r_head = r_head;
		this.r_r_head = r_r_head;
	}
	

	public double getR_score() {
		return r_score;
	}
	public void setR_score(double r_score) {
		this.r_score = r_score;
	}
	public int getR_r_head() {
		return r_r_head;
	}

	public void setR_r_head(int r_r_head) {
		this.r_r_head = r_r_head;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = r_head*r_pay;
	}

	public List<Member> getMember() {
		return member;
	}

	public void setMember(List<Member> member) {
		this.member = member;
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

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
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

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public List<R_Attachment> getRoomimage() {
		return roomimage;
	}

	public void setRoomimage(List<R_Attachment> roomimage) {
		this.roomimage = roomimage;
	}

	public int getBooking_no() {
		return booking_no;
	}

	public void setBooking_no(int booking_no) {
		this.booking_no = booking_no;
	}

	public String getGuest_name() {
		return guest_name;
	}

	public void setGuest_name(String guest_name) {
		this.guest_name = guest_name;
	}

	public String getHost_confirm() {
		return host_confirm;
	}

	public void setHost_confirm(String host_confirm) {
		this.host_confirm = host_confirm;
	}
	@Override
	public String toString() {
		return "Room [r_no=" + r_no + ", r_name=" + r_name + ", r_tel=" + r_tel + ", r_info=" + r_info + ", r_pay="
				+ r_pay + ", sum=" + sum + ", r_address=" + r_address + ", user_no=" + user_no + ", user_name="
				+ user_name + ", r_start=" + r_start + ", r_end=" + r_end + ", r_status=" + r_status + ", r_head="
				+ r_head + ", r_r_head=" + r_r_head + ", hostName=" + hostName + ", booking_no=" + booking_no
				+ ", guest_name=" + guest_name + ", host_confirm=" + host_confirm + ", r_score=" + r_score
				+ ", roomimage=" + roomimage + ", replyList=" + replyList + ", member=" + member + "]";
	}

	

}
