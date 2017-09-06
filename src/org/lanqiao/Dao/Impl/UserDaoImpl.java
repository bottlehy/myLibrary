package org.lanqiao.Dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.lanqiao.Dao.UserDao;
import org.lanqiao.entity.Order;
import org.lanqiao.entity.User;
import org.lanqiao.entity.orderdetail;
import org.lanqiao.entity.passwordAnswer;
import org.lanqiao.util.DBUtil;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

public class UserDaoImpl implements UserDao {
	private Connection conn = DBUtil.getConn();

	@Override
	public boolean addUser(String uid, String email, String uname, String upassword, String sex, String tel,
			String address) {
		boolean soe = false;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "insert into t_users values(?,?,?,?,?,?,?,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
			ps.setString(2, email);
			ps.setString(3, uname);
			ps.setString(4, upassword);
			ps.setString(5, sex);
			ps.setString(6, address);
			ps.setString(7, tel);
			ps.setString(8, "2");
			ps.setString(9, "2");
			int i = ps.executeUpdate();
			if (i != 0) {
				soe = true;
			} else {
				soe = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return soe;
	}

	@Override
	public boolean addQuestion(String question, String anser, String email, String uid) {
		boolean suc = false;
		Connection conn = DBUtil.getConn();
		PreparedStatement ps = null;
		String sql = "insert into t_passwordanswer values(qs.nextval,?,?,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, question);
			ps.setString(2, anser);
			ps.setString(3, email);
			ps.setString(4, uid);
			int i = ps.executeUpdate();
			if (i != 0) {
				suc = true;
			} else {
				suc = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return suc;
	}

	@Override
	public User getUser(String loginid, String password) {
		User user = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select t.*,s.ustatename,r.urolename from (select * from t_users where uloginid=? and upassword=?) t join t_userstate s on t.ustateid= s.ustateid join t_userrole r on t.uroleid=r.uroleid";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, loginid);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if (rs.next()) {
				user = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(10), rs.getString(9),
						rs.getString(11));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return user;
	}

	@Override
	public void update(String upassword, String usex, String tel, String address, String select, String answer,
			String email, String userid) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql1 = "update t_users set upassword=?,sex=?,utel=?,uaddress=? where userid=?";
		String sql2 = "update t_passwordanswer set question=?,answer=?,email=? where userid=?";
		try {
			ps = conn.prepareStatement(sql1);
			ps.setString(1, upassword);
			ps.setString(2, usex);
			ps.setString(3, tel);
			ps.setString(4, address);
			ps.setString(5, userid);
			ps.executeUpdate();
			ps = conn.prepareStatement(sql2);
			ps.setString(1, select);
			ps.setString(2, answer);
			ps.setString(3, email);
			ps.setString(4, userid);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public passwordAnswer getPa(String userid) {
		passwordAnswer pa = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from t_passwordanswer where userid=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, userid);
			rs = ps.executeQuery();
			if (rs.next()) {
				pa = new passwordAnswer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return pa;
	}
	
	@Override
	public List<Order> getOrder(String userid) {
		List<Order> list = new ArrayList<Order>();
		Connection con = DBUtil.getConn();
		Order order = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select t1.*,t2.* from t_order t1,t_orderdetail t2 where t1.orderid=t2.orderid and t1.userid=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, userid);
			rs = ps.executeQuery();
			while (rs.next()) {
				order = new Order(rs.getString(1), rs.getString(12), rs.getString(2), rs.getDouble(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getDouble(9),
						rs.getInt(10));
				order.setGinprice(rs.getDouble(13));
				list.add(order);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public void changeState(String userid) {
		// TODO Auto-generated method stub
		conn = DBUtil.getConn();
		PreparedStatement ps = null;
		String sql = "update t_users set ustateid='1' where userid=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, userid);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public List<Order> getOrderBys(String userid) {
		List<Order> list = new ArrayList<Order>();
		Connection con = DBUtil.getConn();
		Order order = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select t1.*,t2.* from t_order t1,t_orderdetail t2 where t1.orderid=t2.orderid and t1.userid=? and t1.orderstate='未完成'";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, userid);
			rs = ps.executeQuery();
			while (rs.next()) {
				order = new Order(rs.getString(1), rs.getString(12), rs.getString(2), rs.getDouble(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getDouble(9),
						rs.getInt(10));
				order.setGinprice(rs.getDouble(13));
				list.add(order);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public List<User> getAll() {
		List<User> list = new ArrayList();
		Connection conn = DBUtil.getConn();
		PreparedStatement ps = null;
		String sql = "select t.*,s.ustatename,r.urolename from t_users t join t_userstate s on t.ustateid= s.ustateid join t_userrole r on t.uroleid=r.uroleid";
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			User user = null;
			while (rs.next()) {
				user = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(10), rs.getString(9),
						rs.getString(11));
				list.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	public int remove(String uid) {
		int k = 0;
		List<String> list = new ArrayList<String>();
		Connection conn = DBUtil.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		list=getString(uid);
		removeOrderdetail(uid, list);
		removePass(uid);
		removeOrder(uid);
		String sql="delete from t_users where userid=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, uid);
			k=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return k;
	}
	public void removePass(String uid) {
		Connection conn = DBUtil.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql="delete from t_passwordanswer where userid=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, uid);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void removeOrder(String uid) {
		Connection conn = DBUtil.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "delete from t_order where userid=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void removeOrderdetail(String order) {
		Connection conn = DBUtil.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "delete from t_orderdetail where orderid=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, order);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void removeOrderdetail(String uid, List<String> list) {
		Connection conn = DBUtil.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "delete from t_orderdetail where orderid=?";
		try {
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < list.size(); i++) {
				ps.setString(1, list.get(i));
				ps.executeUpdate();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public List<String> getString(String uid) {
		List<String> list = new ArrayList<String>();
		Connection conn = DBUtil.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select orderid from t_order where userid=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
			rs = ps.executeQuery();
			while (rs.next()) {
				String l = rs.getString(1);
				list.add(l);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	@Override
	public String getUserid(String uname) {
		Connection conn=DBUtil.getConn();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String userid="";
		String sql="select userid from t_users where uloginid =?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, uname);
			rs=ps.executeQuery();
			if(rs.next()) {
				userid=rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return userid;
	}

	@Override
	public int update(String upassword, String usex, String tel, String address, String email, String userid,String ustatid) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		int k=0;
		ResultSet rs = null;
		String sql1 = "update t_users set upassword=?,sex=?,utel=?,uaddress=?,email=?,ustateid=? where userid=?";
		try {
			ps=conn.prepareStatement(sql1);
			ps.setString(1, upassword);
			ps.setString(2, usex);
			ps.setString(3, tel);
			ps.setString(4, address);
			ps.setString(5, email);
			ps.setString(6, ustatid);
			ps.setString(7, userid);
			k=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return k;
	}

	@Override
	public List<orderdetail> getOr(String orderid,int index,int size) {
		List<orderdetail> list=new ArrayList<orderdetail>();
		Connection conn=DBUtil.getConn();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="select t1.* from (select t.*,rownum rn from (select * from t_orderdetail where orderid=?) t) t1 where rn between ? and ?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, orderid);
			ps.setInt(2, (index-1)*size+1);
			ps.setInt(3, index*size);
			rs=ps.executeQuery();
			orderdetail od=null;
			while(rs.next()) {
				od=new orderdetail(rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getDouble(7));
				list.add(od);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	public int getNum(String userid) {
		int k=0;
		Connection conn=DBUtil.getConn();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="select count(*) from t_order where userid=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, userid);
			rs=ps.executeQuery();
			if(rs.next()) {
				k=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return k;
	}
	@Override
	public List<Order> getO(String userid,int index,int size) {
		List<Order> list=new ArrayList<Order>();
		Connection conn=DBUtil.getConn();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="select t1.* from (select t.*,rownum rn from (select * from t_order where userid=?) t) t1 where rn between ? and ?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, userid);
			ps.setInt(2, (index-1)*size+1);
			ps.setInt(3, index*size);
			rs=ps.executeQuery();
			Order order=null;
			while(rs.next()) {
				order=new Order();
				order.setOrderid(rs.getString(1));
				order.setUserid(rs.getString(2));
				order.setTotalprice(rs.getInt(3));
				order.setOrderdata(rs.getString(4));
				order.setOrecipients(rs.getString(5));
				order.setOrderstate(rs.getString(6));
				list.add(order);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public List<orderdetail> getOrtotal(String orderid) {
		List<orderdetail> list=new ArrayList<orderdetail>();
		Connection conn=DBUtil.getConn();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="select * from t_orderdetail where orderid=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, orderid);
			rs=ps.executeQuery();
			orderdetail od=null;
			while(rs.next()) {
				od=new orderdetail(rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getDouble(7));
				list.add(od);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
}
