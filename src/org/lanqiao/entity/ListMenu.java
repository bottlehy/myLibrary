package org.lanqiao.entity;

public class ListMenu {
	private String cid;
	private String cname;
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public ListMenu(String cid, String cname) {
		this.cid = cid;
		this.cname = cname;
	}
}
