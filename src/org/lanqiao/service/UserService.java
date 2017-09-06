package org.lanqiao.service;

import java.util.List;

import org.lanqiao.entity.Order;
import org.lanqiao.entity.User;
import org.lanqiao.entity.passwordAnswer;

public interface UserService {
	public boolean rtAdd(String uid,String email,String uname,String upassword,String sex,String tel,String address);
	public boolean rtquestion(String question,String anser,String email,String uid);
	public User rtuser(String userid,String password);
	public void update(String upassword,String usex,String tel,String address,String select,String answer,String email,String userid);
	public passwordAnswer rtpass(String userid);
	public List<Order> relist(String userid);
	public List<Order> relistby(String userid);
	public void change(String userid);
	public List<User> getAll();
	public int remove(String uid);
	public String rtuserid(String login);
	public int updatebyweb(String upassword,String usex,String tel,String address,String email,String userid, String ustateid);
}
