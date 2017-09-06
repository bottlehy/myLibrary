package org.lanqiao.entity;

import java.util.Date;

public class News {
	private String nid;
	private String ntitle;
	private String ncontent;
	private Date npubdate;
	public String getNid() {
		return nid;
	}
	public void setNid(String nid) {
		this.nid = nid;
	}
	public String getNtitle() {
		return ntitle;
	}
	public void setNtitle(String ntitle) {
		this.ntitle = ntitle;
	}
	public String getNcontent() {
		return ncontent;
	}
	public void setNcontent(String ncontent) {
		this.ncontent = ncontent;
	}
	public Date getNpubdate() {
		return npubdate;
	}
	public void setNpubdate(Date npubdate) {
		this.npubdate = npubdate;
	}
	public News(String nid, String ntitle, String ncontent, Date npubdate) {
		this.nid = nid;
		this.ntitle = ntitle;
		this.ncontent = ncontent;
		this.npubdate = npubdate;
	}
	
	
}
