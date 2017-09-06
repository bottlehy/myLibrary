package org.lanqiao.entity;

public class Book {
	private String gid;
	private String gtitle;
	private String gauthor;
	private double gsaleprice;
	private double ginprice;
	private String gdesc;
	private String gimage;
	private int gclicks;
	private String cid;
	private String pid;
	private String pname;
	
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getGid() {
		return gid;
	}
	public void setGid(String gid) {
		this.gid = gid;
	}
	public String getGtitle() {
		return gtitle;
	}
	public void setGtitle(String gtitle) {
		this.gtitle = gtitle;
	}
	public String getGauthor() {
		return gauthor;
	}
	public void setGauthor(String gauthor) {
		this.gauthor = gauthor;
	}
	public double getGsaleprice() {
		return gsaleprice;
	}
	public void setGsaleprice(double gsaleprice) {
		this.gsaleprice = gsaleprice;
	}
	public double getGinprice() {
		return ginprice;
	}
	public void setGinprice(double ginprice) {
		this.ginprice = ginprice;
	}
	public String getGdesc() {
		return gdesc;
	}
	public void setGdesc(String gdesc) {
		this.gdesc = gdesc;
	}
	public String getGimage() {
		return gimage;
	}
	public void setGimage(String gimage) {
		this.gimage = gimage;
	}
	public int getGclicks() {
		return gclicks;
	}
	public void setGclicks(int gclicks) {
		this.gclicks = gclicks;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public Book(String gid, String gtitle, String gauthor, double gsaleprice, double ginprice, String gdesc, String gimage,
			int gclicks, String cid, String pid,String pname) {
		this.gid = gid;
		this.gtitle = gtitle;
		this.gauthor = gauthor;
		this.gsaleprice = gsaleprice;
		this.ginprice = ginprice;
		this.gdesc = gdesc;
		this.gimage = gimage;
		this.gclicks = gclicks;
		this.cid = cid;
		this.pid = pid;
		this.pname =pname;
	}
	
}
