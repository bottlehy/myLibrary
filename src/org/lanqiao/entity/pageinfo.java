package org.lanqiao.entity;

import java.util.ArrayList;
import java.util.List;

public class pageinfo<T> {
	private int pageindex;
	private int pagesize;
	private int totalnumber;
	private int totalpage;
	private boolean first;
	private boolean last;
	private List<T> data=new ArrayList<T>();
	private int currsize;
	private int currlen;
	
	public int getCurrlen() {
		return currlen;
	}
	public void setCurrlen(int currlen) {
		this.currlen = currlen;
	}
	public int getCurrsize() {
		return currsize;
	}
	public void setCurrsize(int currsize) {
		this.currsize = currsize;
	}
	public int getPageindex() {
		return pageindex;
	}
	public void setPageindex(int pageindex) {
		this.pageindex = pageindex;
	}
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	public int getTotalnumber() {
		return totalnumber;
	}
	public void setTotalnumber(int totalnumber) {
		this.totalnumber = totalnumber;
	}
	public int getTotalpage() {
		return totalpage;
	}
	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}
	public boolean getFirst() {
		return first;
	}
	public void setFirst(boolean isFirst) {
		this.first = isFirst;
	}
	public boolean getLast() {
		return last;
	}
	public void setLast(boolean isLast) {
		this.last = isLast;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	
}
