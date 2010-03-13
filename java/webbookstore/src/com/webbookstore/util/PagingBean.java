package com.webbookstore.util;

@SuppressWarnings("unused")
public class PagingBean {
	
	// 当前页
	private int currentPage;
	// 每页大小
	private int pageSize = 3;
	// 总记录数
	private int totalSize;
	// 总页数
	private int totalPage;
	
	// 是否是第一页
	private boolean hasFirst;
	// 是否有上一页
	private boolean hasPrevious;
	// 是否有下一页
	private boolean hasNext;
	// 是否是最后一页
	private boolean hasLast;
	
	/**
	 * 传入当前页码和总记录数
	 */
	public PagingBean(int currentPage,int totalSize){
		this.currentPage = currentPage;
		this.totalSize = totalSize;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}
	/**
	 * 获取总页数
	 */
	public int getTotalPage() {
		totalPage = totalSize / pageSize;
		if(totalSize % pageSize != 0 ){
			totalPage = totalPage + 1;
		}
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	/**
	 * 是否是第一页
	 */
	public boolean isHasFirst() {
		if(currentPage == 1){
			return false;
		}
		return true;
	}
	public void setHasFirst(boolean hasFirst) {
		this.hasFirst = hasFirst;
	}
	/**
	 * 是否有上一页
	 */
	public boolean isHasPrevious() {
		if(this.isHasFirst()){
			return true;
		}
		return false;
	}
	public void setHasPrevious(boolean hasPrevious) {
		this.hasPrevious = hasPrevious;
	}
	/**
	 * 是否有下一页
	 */
	public boolean isHasNext() {
		if(this.isHasLast()){
			return true;
		}
		return false;
	}
	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}
	/**
	 * 是否是最后一页
	 */
	public boolean isHasLast() {
		if(currentPage == this.getTotalPage()){
			return false;
		}
		return true;
	}
	public void setHasLast(boolean hasLast) {
		this.hasLast = hasLast;
	}
	
}
