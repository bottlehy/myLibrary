package org.lanqiao.entity;

public class User {
	private String uid;
	private String email;
	private String uname;
	private String upassword;
	private String sex;
	private String uaddress;
	private String utel;
	private String ustaid;
	private String ustaname;
	private String uroleid;
	private String urolename;
	
	@Override
	public String toString() {
		return "User [uid=" + uid + ", email=" + email + ", uname=" + uname + ", upassword=" + upassword + ", sex="
				+ sex + ", uaddress=" + uaddress + ", utel=" + utel + ", ustaid=" + ustaid + ", ustaname=" + ustaname
				+ ", uroleid=" + uroleid + ", urolename=" + urolename + "]";
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUpassword() {
		return upassword;
	}
	public void setUpassword(String upassword) {
		this.upassword = upassword;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getUaddress() {
		return uaddress;
	}
	public void setUaddress(String uaddress) {
		this.uaddress = uaddress;
	}
	public String getUtel() {
		return utel;
	}
	public void setUtel(String utel) {
		this.utel = utel;
	}
	public String getUstaid() {
		return ustaid;
	}
	public void setUstaid(String ustaid) {
		this.ustaid = ustaid;
	}
	public String getUstaname() {
		return ustaname;
	}
	public void setUstaname(String ustaname) {
		this.ustaname = ustaname;
	}
	public String getUroleid() {
		return uroleid;
	}
	public void setUroleid(String uroleid) {
		this.uroleid = uroleid;
	}
	public String getUrolename() {
		return urolename;
	}
	public void setUrolename(String urolename) {
		this.urolename = urolename;
	}
	public User(String uid, String email, String uname, String upassword, String sex, String uaddress, String utel,
			String ustaid, String ustaname, String uroleid, String urolename) {
		super();
		this.uid = uid;
		this.email = email;
		this.uname = uname;
		this.upassword = upassword;
		this.sex = sex;
		this.uaddress = uaddress;
		this.utel = utel;
		this.ustaid = ustaid;
		this.ustaname = ustaname;
		this.uroleid = uroleid;
		this.urolename = urolename;
	}
}
