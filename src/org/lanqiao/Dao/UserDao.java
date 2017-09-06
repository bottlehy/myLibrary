package org.lanqiao.Dao;

import java.util.List;

import org.lanqiao.entity.Order;
import org.lanqiao.entity.User;
import org.lanqiao.entity.orderdetail;
import org.lanqiao.entity.passwordAnswer;

public interface UserDao {
	public boolean addUser(String uid,String email,String uname,String upassword,String sex,String tel,String address);
	public boolean addQuestion(String question,String anser,String email,String uid);
	public User getUser(String userid,String password);
	public passwordAnswer getPa(String userid);
	public void update(String upassword,String usex,String tel,String address,String select,String answer,String email,String userid);
	public List<Order> getOrder(String userid);
	public List<Order> getOrderBys(String userid);
	public void changeState(String userid);
	public List<Order> getO(String userid,int index,int size);
	public List<orderdetail> getOr(String orderid,int index,int size);
	public List<orderdetail> getOrtotal(String orderid);
	public List<User> getAll();
	public int remove(String uid);
	public String getUserid(String uname);
	public int getNum(String userid);
	public int update(String upassword,String usex,String tel,String address,String email,String userid,String statid);
}
