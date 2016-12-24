package net.bobstudio.so.dto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

public class PageVo {
	public static final String PAGE_SIZE = "2";
	
	public static final String SEARCH_PERFIX="search_";

	private static final int PAGINATION_SIZE = 5;

	private int number;

	private int totalPages;

	private boolean previous;

	private boolean next;

	private String model;;

	private String second;

	private String searchConditions;

	// private int pageSize;

	public PageVo(String model, String second, Page<?> page) {
		this.model = model;

		this.number = page.getNumber();
		this.totalPages = page.getTotalPages();
		this.previous = page.hasPrevious();
		this.next = page.hasNext();
		this.second = second;

	}

	public PageVo(String model, Page<?> page) {
		this(model, "/main", page);
	}

	public int getNumber() {
		return number;
	}

	public String getSearchConditions() {
		return searchConditions == null ? "" : searchConditions;
	}
	
	public void setSearchConditions(String searchConditions){
		this.searchConditions = searchConditions;
	}

	public String getSearchParams() {
		return getSearchConditions().length() > 0 ? "&" + searchConditions : "";
	}

	public String getSecond() {
		return second;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public boolean isPrevious() {
		return previous;
	}

	public boolean isNext() {
		return next;
	}

	public int getCurrent() {
		return number + 1;
	}

	public int getBegin() {
		int half = PAGINATION_SIZE / 2;
		int begin = Math.max(1, getCurrent() - half);
		if (getCurrent() > half && (getTotalPages() - half) < getCurrent()) {
			begin = Math.min(getTotalPages() - PAGINATION_SIZE + 1, begin);
			begin = Math.max(1, begin);
		}

		return begin;
	}

	public int getEnd() {
		return Math.min(getBegin() + (PAGINATION_SIZE - 1), getTotalPages());
	}

	public String getContent() {
		if(getTotalPages()==0) {
			return "";
		}
		
		StringBuilder sb = new StringBuilder();
		if (previous) {
			// sb.append("<li><a href='?page=1'>&lt;&lt;</a></li>");
			// sb.append("<li><a href='?page=").append(getCurrent()-1).append("'>&lt;</a></li>");
			sb.append("<li><a href='#' onclick=\"$.loadFunction('").append(model).append("','")
			        .append(second).append("','?page=1").append(getSearchParams())
			        .append("');\">&lt;&lt;</a></li>");
			sb.append("<li><a href='#' onclick=\"$.loadFunction('").append(model).append("','")
			        .append(second).append("','?page=").append(getCurrent() - 1)
			        .append(getSearchParams()).append("');\">&lt;</a></li>");
		} else {
			sb.append("<li class='disabled'><a href='#'>&lt;&lt;</a></li>");
			sb.append("<li class='disabled'><a href='#'>&lt;</a></li>");
		}

		for (int i = getBegin(); i <= getEnd(); i++) {
			if (i == getCurrent()) {
				sb.append("<li class='active'>");
			} else {
				sb.append("<li>");
			}
			sb.append("<a href='#' onclick=\"$.loadFunction('").append(model).append("','")
			        .append(second).append("','?page=").append(i).append(getSearchParams())
			        .append("');\">").append(i).append("</a></li>");
		}

		if (next) {
			sb.append("<li><a href='#' onclick=\"$.loadFunction('").append(model).append("','")
			        .append(second).append("','?page=").append(getCurrent() + 1)
			        .append(getSearchParams()).append("');\">&gt;</a></li>");
			sb.append("<li><a href='#' onclick=\"$.loadFunction('").append(model).append("','")
			        .append(second).append("','?page=").append(totalPages)
			        .append(getSearchParams()).append("');\">&gt;&gt;</a></li>");
		} else {
			sb.append("<li class='disabled'><a href='#'>&gt;</a></li>");
			sb.append("<li class='disabled'><a href='#'>&gt;&gt;</a></li>");
		}

		return sb.toString();
	}

	/**
	 * 创建分页请求.
	 */
	public static PageRequest buildPageRequest(int pageNumber, int pagzSize, String sortType) {
		Sort sort = null;
		if ("auto".equals(sortType)) {
			sort = new Sort(Direction.DESC, "id");
		} else if ("name".equals(sortType)) {
			sort = new Sort(Direction.ASC, "name");
		}

		return new PageRequest(pageNumber - 1, pagzSize, sort);
	}

}
