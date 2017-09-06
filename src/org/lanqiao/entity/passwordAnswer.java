package org.lanqiao.entity;

public class passwordAnswer {
	private String answerid;
	private String question;
	private String answer;
	private String email;
	private String userid;
	public String getAnswerid() {
		return answerid;
	}
	public void setAnswerid(String answerid) {
		this.answerid = answerid;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public passwordAnswer(String answerid, String question, String answer, String email, String userid) {
		super();
		this.answerid = answerid;
		this.question = question;
		this.answer = answer;
		this.email = email;
		this.userid = userid;
	}
}
