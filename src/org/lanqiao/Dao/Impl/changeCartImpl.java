package org.lanqiao.Dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.lanqiao.Dao.changeCart;
import org.lanqiao.util.DBUtil;

public class changeCartImpl implements changeCart {

	@Override
	public void change(String gtitle, int number) {
		Connection conn=DBUtil.getConn();
		PreparedStatement ps=null;
		ResultSet rs=null; 
		double price=0;
		int n=0;
		double s=0;
		String orderdetailid="";
		String sql="select totalprice,gnumber,gsaleprice,orderdetailid from t_order,t_orderdetail where gtitle=? and orderstate='未完成'";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1,gtitle);
			rs=ps.executeQuery();
			if(rs.next()) {
				price=rs.getDouble(1);
				n=rs.getInt(2);
				s=rs.getDouble(3);
				orderdetailid=rs.getString(4);
			}
			price+=(number-n)*s;
			sql="update t_order set totalprice=? where orderstate='未完成'";
			ps=conn.prepareStatement(sql);
			ps.setDouble(1, price);
			ps.executeUpdate();
			sql="update t_orderdetail set gnumber=? where orderdetailid=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, number);
			ps.setString(2, orderdetailid);
			ps.executeUpdate();
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
		
	}

	@Override
	public void delete(String gtitle) {
		// TODO Auto-generated method stub
		Connection conn=DBUtil.getConn();
		PreparedStatement ps=null;
		ResultSet rs=null; 
		double price=0;
		int n=0;
		double s=0;
		String orderdetailid="";
		String sql="select totalprice,gnumber,gsaleprice,orderdetailid from t_order,t_orderdetail where gtitle=? and orderstate='未完成'";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1,gtitle);
			rs=ps.executeQuery();
			if(rs.next()) {
				price=rs.getDouble(1);
				n=rs.getInt(2);
				s=rs.getDouble(3);
				orderdetailid=rs.getString(4);
			}
			price-=n*s;
			sql="update t_order set totalprice=? where orderstate='未完成'";
			ps=conn.prepareStatement(sql);
			ps.setDouble(1, price);
			ps.executeUpdate();
			sql="delete t_orderdetail where orderdetailid=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, orderdetailid);
			ps.executeUpdate();
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
		
	}

}
