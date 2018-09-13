package bkhn.att.pagination;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.query.Query;

public class PaginationResult<E> {
	private int totalRecords;	//tổng số record trên tất cả các trang
	private int currentPage;
	private List<E> listResult;		//các record của trang hiện tại
	private int maxResult;	//số lượng record mỗi trang
	private int totalPages;

	private int maxNavigationPage;

	private List<Integer> navigationPages;

	// @page: 1, 2, ..
	/**
	 * Thực hiện phân trang
	 * @param query câu sql lấy data từ database
	 * @param page vị trí của trang cần lấy data
	 * @param maxResult quy định số lượng record mỗi trang
	 * @param maxNavigationPage Đéo hiển tham số này trên o7planning dùng để làm gì.
	 * Nên sẽ không dùng nó!
	 */
	public PaginationResult(Query<E> query, int page, int maxResult, int maxNavigationPage) {
		final int pageIndex = page - 1 < 0 ? 0 : page - 1;

		int fromRecordIndex = pageIndex * maxResult;
		int maxRecordIndex = fromRecordIndex + maxResult;

		// Using ScrollableResults to implement pagination has the potential to 
		// reduce database calls. This approach streams the result set as the 
		// program scrolls through it, therefore eliminating the need to repeat 
		// the query to fill each page:
		ScrollableResults resultScroll = query.scroll(ScrollMode.SCROLL_INSENSITIVE);

		List<E> results = new ArrayList<E>();

		boolean hasResult = resultScroll.first();

		if (hasResult) {
			// Scroll to position to start getting record from database:
			hasResult = resultScroll.scroll(fromRecordIndex);

			if (hasResult) {
				do {
					@SuppressWarnings("unchecked")
					E record = (E) resultScroll.get(0);
					results.add(record);
				} while (resultScroll.next()//
						&& resultScroll.getRowNumber() >= fromRecordIndex
						&& resultScroll.getRowNumber() < maxRecordIndex);
			}

			// Go to Last record.
			resultScroll.last();
		}

		// Total Records
		this.totalRecords = resultScroll.getRowNumber() + 1;
		this.currentPage = pageIndex + 1;
		this.listResult = results;
		this.maxResult = maxResult;

		if (this.totalRecords % this.maxResult == 0) {
			this.totalPages = this.totalRecords / this.maxResult;
		} else {
			this.totalPages = (this.totalRecords / this.maxResult) + 1;
		}

		/******** WHAT THE FUCK IS THIS? ***********/
		this.maxNavigationPage = maxNavigationPage;
		if (maxNavigationPage < totalPages) {
			this.maxNavigationPage = maxNavigationPage;
		}
		/******** WHAT THE FUCK IS THAT? ***********/
		
		resultScroll.close();

		//cái hàm này tính toán như cc!
		//this.calcNavigationPages();
		this.pagination();
	}

	/**
	 * Cái hàm này bọn o7planning viết như cc ý, đọc đéo hiểu, mà dùng nó
	 * cũng vẫn bị lỗi!
	 */
	@SuppressWarnings("unused")
	private void calcNavigationPages() {

		this.navigationPages = new ArrayList<Integer>();

		// current page
		int current = this.currentPage > this.totalPages ? this.totalPages : this.currentPage;

		int begin = current - this.maxNavigationPage / 2;
		int end = current + this.maxNavigationPage / 2;

		// The first page
		navigationPages.add(1);
		if (begin > 2) {

			// Using for '...'
			navigationPages.add(-1);
		}

		for (int i = begin; i < end; i++) {
			if (i > 1 && i < this.totalPages) {
				navigationPages.add(i);
			}
		}

		if (end < this.totalPages - 2) {

			// Using for '...'
			navigationPages.add(-1);
		}

		// The last page.
		navigationPages.add(this.totalPages);
	}

	/**
	 * Tính toán giá trị cho Navigation (cái thanh hiển thị số trang, trang hiện tại) <br>
	 * VD: Có tất cả 40 trang, mà người dùng đang ở trang 24 thì cái thanh này như sau: <br>
	 * 1 2 3 ... 23 24 25 ... 39 40
	 */
	private void pagination() {
		this.navigationPages = new ArrayList<Integer>();

		// current page
		int page = this.currentPage > this.totalPages ? this.totalPages : this.currentPage;

        //============= algorithm in here ==================//
        int amountOfPages = this.totalPages;

        if (amountOfPages < 15) {
            for (int i = 1; i <= amountOfPages; i++) {
            	navigationPages.add(i);
            }
        } else {
            if (page <= 5) {        // là 1 trong 5 trang đầu tiên
                for (int i = 1; i <= 6; i++) {
                	navigationPages.add(i);
                }
                navigationPages.add(-1);	//để hiển thị dấu ...
                for (int i = amountOfPages - 2; i <= amountOfPages; i++) {
                	navigationPages.add(i);
                }
            } else if (page >= amountOfPages - 4) {        // là 1 trong 5 trang cuối
                for (int i = 1; i <= 3; i++) {
                	navigationPages.add(i);
                }
                navigationPages.add(-1);	//để hiển thị dấu ...
                for (int i = amountOfPages - 5; i <= amountOfPages; i++) {
                	navigationPages.add(i);
                }
            } else {        // là 1 trang ở giữa
                for (int i = 1; i <= 3; i++) {
                	navigationPages.add(i);
                }
            	navigationPages.add(-1);
                for (int i = -1; i <= 1; i++) {
                	int temp = page + i;
                	navigationPages.add(temp);
                }
            	navigationPages.add(-1);
                for (int i = amountOfPages - 2; i <= amountOfPages; i++) {
                	navigationPages.add(i);
                }
            }
        }
        //============= algorithm end here ==================//
    }
    
	public int getTotalPages() {
		return totalPages;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public List<E> getListResult() {
		return listResult;
	}

	public int getMaxResult() {
		return maxResult;
	}

	public List<Integer> getNavigationPages() {
		return navigationPages;
	}

}
