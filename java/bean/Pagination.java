package bean;

public class Pagination {
	private int pageSize = 6;

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getEndPage(int list_size) {
		int endPage = 1;
		if (list_size > pageSize) {
			endPage = list_size / pageSize;
			if (list_size % pageSize != 0) {
				endPage++;
			}
		}

		return endPage;
	}
}
