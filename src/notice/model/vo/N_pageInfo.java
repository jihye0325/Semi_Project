package notice.model.vo;

public class N_pageInfo {
	private int page;			// 요청 페이지
	private int listCount;		// 공지사항 게시글의 총 수
	private int pageLimit;		// 페이징 바에 표시할 페이지의 수
	private int noticeLimit;	// 한 페이지에 표시할 공지사항의 수
	private int maxPage;		// 총 페이지 중 마지막 페이지
	private int startPage;		// 페이징 바의 시작 값
	private int endPage;		// 페이징 바의 마지막 값
	
	public N_pageInfo() {}

	public N_pageInfo(int page, int listCount, int pageLimit, int noticeLimit) {
		super();
		this.page = page;
		this.listCount = listCount;
		this.pageLimit = pageLimit;
		this.noticeLimit = noticeLimit;
		
		// 계산식
		this.maxPage = (int)Math.ceil((double)listCount / noticeLimit);
		this.startPage = (page - 1) / pageLimit * pageLimit + 1;
		this.endPage = startPage + pageLimit - 1;
		this.endPage = maxPage < endPage? maxPage: endPage;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getListCount() {
		return listCount;
	}

	public void setListCount(int listCount) {
		this.listCount = listCount;
	}

	public int getPageLimit() {
		return pageLimit;
	}

	public void setPageLimit(int pageLimit) {
		this.pageLimit = pageLimit;
	}

	public int getNoticeLimit() {
		return noticeLimit;
	}

	public void setNoticeLimit(int noticeLimit) {
		this.noticeLimit = noticeLimit;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	@Override
	public String toString() {
		return "N_pageInfo [page=" + page + ", listCount=" + listCount + ", pageLimit=" + pageLimit + ", noticeLimit="
				+ noticeLimit + ", maxPage=" + maxPage + ", startPage=" + startPage + ", endPage=" + endPage + "]";
	}
}
