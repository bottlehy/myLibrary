package org.lanqiao.service.Impl;

import java.util.List;

import org.lanqiao.Dao.UserDao;
import org.lanqiao.Dao.Impl.UserDaoImpl;
import org.lanqiao.entity.Order;
import org.lanqiao.entity.User;
import org.lanqiao.entity.passwordAnswer;
import org.lanqiao.service.UserService;

public class UserServiceImpl implements UserService {
	private UserDao ud=new UserDaoImpl();

	@Override
	public boolean rtAdd(String uid,String email, String uname, String upassword, String sex, String tel, String address) {
		return ud.addUser(uid,email, uname, upassword, sex, tel, address);
	}

	@Override
	public boolean rtquestion(String question, String anser, String email,String uid) {
		return ud.addQuestion(question, anser,email, uid);
	}

	@Override
	public User rtuser(String userid,String password) {
		return ud.getUser(userid,password);
	}

	@Override
	public void update(String upassword, String usex, String tel, String address, String select, String answer,
			String email,String userid) {
		ud.update(upassword, usex, tel, address, select, answer, email,userid);
	}

	@Override
	public passwordAnswer rtpass(String userid) {
		// TODO Auto-generated method stub
		return ud.getPa(userid);
	}

	@Override
	public List<Order> relist(String userid) {
		// TODO Auto-generated method stub
		return ud.getOrder(userid);
	}

	@Override
	public void change(String userid) {
		// TODO Auto-generated method stub
		ud.changeState(userid);
	}

	@Override
	public List<Order> relistby(String userid) {
		// TODO Auto-generated method stub
		return ud.getOrderBys(userid);
	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return ud.getAll();
	}

	@Override
	public int remove(String uid) {
		// TODO Auto-generated method stub
		return ud.remove(uid);
	}

	public String rtuserid(String login) {
		return ud.getUserid(login);
	}
	public int updatebyweb(String upassword,String usex,String tel,String address,String email,String userid,String statid) {
		return ud.update(upassword, usex, tel, address, email, userid,statid);
	}
}
