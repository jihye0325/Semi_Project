package store.model.vo;

public class S_Search {
	private String searchValue;
	
	public S_Search() {}

	public S_Search(String searchValue) {
		super();
		this.searchValue = searchValue;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	@Override
	public String toString() {
		return "S_Search [searchValue=" + searchValue + "]";
	}
}
