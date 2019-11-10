package com.tool;

public class Page {
	public static final int pageSize=5;
	public int pageCount;
	public int totalCount;
	public int first,last;
	public int next,before;
	public int totalPage,curentPage;
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getFirst() {
		return first;
	}
	public void setFirst(int first) {
		this.first = first;
	}
	public int getLast() {
		return last;
	}
	public void setLast(int last) {
		this.last = last;
	}
	public int getNext() {
		return next;
	}
	public void setNext(int next) {
		this.next = next;
	}
	public int getBefore() {
		return before;
	}
	public void setBefore(int before) {
		this.before = before;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getCurentPage() {
		return curentPage;
	}
	public void setCurentPage(int curentPage) {
		this.curentPage = curentPage;
	}


}
