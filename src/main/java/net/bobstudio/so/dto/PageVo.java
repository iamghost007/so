package net.bobstudio.so.dto;

public class PageVo {
	private int number;
	
	private int totalPages;
	
	private boolean previous;
	
	private boolean next;
	
	private int pageSize;
	
	public PageVo(int number,int totalPages, boolean previous,boolean next, int pageSize){
		this.number = number;
		this.totalPages = totalPages;
		this.previous = previous;
		this.next = next;
		this.pageSize = pageSize;
	}
	
	public int getNumber() {
		return number;
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
		return Math.max(1, getCurrent() - pageSize/2);
	} 
	
	public int getEnd() {
		return Math.min(getBegin() + (pageSize - 1), getTotalPages());
	}

	public String getContent(){
		StringBuilder sb = new StringBuilder();
		if(previous) {
			//sb.append("<li><a href='?page=1&sortType=${sortType}&${searchParams}'>&lt;&lt;</a></li>");
            //sb.append("<li><a href='?page=${current-1}&sortType=${sortType}&${searchParams}'>&lt;</a></li>");
			sb.append("<li><a href='?page=1'>&lt;&lt;</a></li>");
            sb.append("<li><a href='?page=").append(getCurrent()-1).append("'>&lt;</a></li>");
		}
		else {
			sb.append("<li class='disabled'><a href='#'>&lt;&lt;</a></li>");
            sb.append("<li class='disabled'><a href='#'>&lt;</a></li>");
		}
		
		for(int i = getBegin();i<getEnd();i++){
			if(i == getCurrent()) {
				//sb.append("<li class='active'><a href='?page=${i}&sortType=${sortType}&${searchParams}'>${i}</a></li>");
				sb.append("<li class='active'><a href='?page=").append(i).append("'>").append(i).append("</a></li>");
			}
			else {
				//sb.append("<li><a href='?page=${i}&sortType=${sortType}&${searchParams}'>${i}</a></li>");
				sb.append("<li><a href='?page=").append(i).append("'>").append(i).append("</a></li>");
			}
		}
		
		if(next) { 
			//sb.append("<li><a href='?page=${current+1}&sortType=${sortType}&${searchParams}'>&gt;</a></li>");
			//sb.append("<li><a href='?page=${page.totalPages}&sortType=${sortType}&${searchParams}'>&gt;&gt;</a></li>");
			sb.append("<li><a href='?page=").append(getCurrent()+1).append("'>&gt;</a></li>");
			sb.append("<li><a href='?page=").append(totalPages).append("'>&gt;&gt;</a></li>");
		}
		else{
			sb.append("<li class='disabled'><a href='#'>&gt;</a></li>");
			sb.append("<li class='disabled'><a href='#'>&gt;&gt;</a></li>");
		}
		
		return sb.toString();
 	}

}
