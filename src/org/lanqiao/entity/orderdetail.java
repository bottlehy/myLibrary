package org.lanqiao.entity;

public class orderdetail {
	private String orderdetailid;
	private String gtitle;
	private double gsaleprice;
	private int gnumber;
	private String orderid;
	private String gid;
	private double ginprice;
	public String getOrderdetailid() {
		return orderdetailid;
	}
	public void setOrderdetailid(String orderdetailid) {
		this.orderdetailid = orderdetailid;
	}
	public String getGtitle() {
		return gtitle;
	}
	public void setGtitle(String gtitle) {
		this.gtitle = gtitle;
	}
	public double getGsaleprice() {
		return gsaleprice;
	}
	public void setGsaleprice(double gsaleprice) {
		this.gsaleprice = gsaleprice;
	}
	public int getGnumber() {
		return gnumber;
	}
	public void setGnumber(int gnumber) {
		this.gnumber = gnumber;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getGid() {
		return gid;
	}
	public void setGid(String gid) {
		this.gid = gid;
	}
	public double getGinprice() {
		return ginprice;
	}
	public void setGinprice(double ginprice) {
		this.ginprice = ginprice;
	}
	public orderdetail(String orderdetailid, String gtitle, double gsaleprice, int gnumber, String orderid, String gid,
			double ginprice) {
		super();
		this.orderdetailid = orderdetailid;
		this.gtitle = gtitle;
		this.gsaleprice = gsaleprice;
		this.gnumber = gnumber;
		this.orderid = orderid;
		this.gid = gid;
		this.ginprice = ginprice;
	}
	@Override
	public String toString() {
		return "orderdetail [orderdetailid=" + orderdetailid + ", gtitle=" + gtitle + ", gsaleprice=" + gsaleprice
				+ ", gnumber=" + gnumber + ", orderid=" + orderid + ", gid=" + gid + ", ginprice=" + ginprice + "]";
	}
	
}
