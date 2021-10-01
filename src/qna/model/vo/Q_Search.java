package qna.model.vo;

public class Q_Search {
	private String searchCondition;
	private String searchValue;
	     
	
	public Q_Search() {}

	public Q_Search(String searchCondition, String searchValue) {
		super();
		this.searchCondition = searchCondition;
		this.searchValue = searchValue;
	}

	public String getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	@Override
	public String toString() {
		return "Q_Search [searchCondition=" + searchCondition + ", searchValue=" + searchValue + "]";
	}
}
