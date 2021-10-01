package board.model.vo;

public class B_Search {
	private String searchCondition;
	private String searchValue;
	
	public B_Search() {	}

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
		return "B_Search [searchCondition=" + searchCondition + ", searchValue=" + searchValue + "]";
	}
}
