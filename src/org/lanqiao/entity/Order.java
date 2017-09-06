package org.lanqiao.entity;

public class Order {
	private String orderid;
	private String gid;
	private String userid;
	private double totalprice;
	private String orderdata;
	private String orecipients;
	private String orderstate;
	private String orderdetailid;
	private String gtitle;
	private double gsaleprice;
	private int gnumber;
	private double ginprice;
	public double getGinprice() {
		return ginprice;
	}
	public Order() {
		
	}
	public void setGinprice(double ginprice) {
		this.ginprice = ginprice;
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
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public double getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(double totalprice) {
		this.totalprice = totalprice;
	}
	public String getOrderdata() {
		return orderdata;
	}
	public void setOrderdata(String orderdata) {
		this.orderdata = orderdata;
	}
	public String getOrecipients() {
		return orecipients;
	}
	public void setOrecipients(String orecipients) {
		this.orecipients = orecipients;
	}
	public String getOrderstate() {
		return orderstate;
	}
	public void setOrderstate(String orderstate) {
		this.orderstate = orderstate;
	}
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
	public Order(String orderid, String gid, String userid, double totalprice, String orderdata, String orecipients,
			String orderstate, String orderdetailid, String gtitle, double gsaleprice, int gnumber) {
		super();
		this.orderid = orderid;
		this.gid = gid;
		this.userid = userid;
		this.totalprice = totalprice;
		this.orderdata = orderdata;
		this.orecipients = orecipients;
		this.orderstate = orderstate;
		this.orderdetailid = orderdetailid;
		this.gtitle = gtitle;
		this.gsaleprice = gsaleprice;
		this.gnumber = gnumber;
	}
	@Override
	public String toString() {
		return "Order [orderid=" + orderid + ", gid=" + gid + ", userid=" + userid + ", totalprice=" + totalprice
				+ ", orderdata=" + orderdata + ", orecipients=" + orecipients + ", orderstate=" + orderstate
				+ ", orderdetailid=" + orderdetailid + ", gtitle=" + gtitle + ", gsaleprice=" + gsaleprice
				+ ", gnumber=" + gnumber + "]";
	}
}
