package org.lanqiao.Dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.lanqiao.Dao.ListDao;
import org.lanqiao.entity.ListMenu;
import org.lanqiao.util.DBUtil;

public class ListDaoImpl implements ListDao {

	@Override
	public List<ListMenu> getList() {
		List<ListMenu> list=new ArrayList<ListMenu>();
		Connection conn=DBUtil.getConn();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="select * from t_category";
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			ListMenu lm=null;
			while(rs.next()) {
				lm=new ListMenu(rs.getString(1), rs.getString(2));
				list.add(lm);
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
	public List<ListMenu> getList(int pageindex, int pagesize) {
		List<ListMenu> list=new ArrayList<ListMenu>();
		Connection conn=DBUtil.getConn();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="select t1.* from (select t.*,rownum rn from t_category t) t1 where rn between ? and ?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, (pageindex-1)*pagesize+1);
			ps.setInt(2, pagesize*pageindex);
			rs=ps.executeQuery();
			ListMenu lm=null;
			while(rs.next()) {
				lm=new ListMenu(rs.getString(1), rs.getString(2));
				list.add(lm);
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
	public int addList(String cid, String cname) {
		int k=0;
		Connection conn=DBUtil.getConn();
		PreparedStatement ps=null;
		String sql="insert into t_category values(?,?)";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, cid);
			ps.setString(2, cname);
			k=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return k;
	}

	@Override
	public int updateList(String cid, String cname) {
		int k=0;
		Connection conn=DBUtil.getConn();
		PreparedStatement ps=null;
		String sql="update t_category set cname =? where cid=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, cname);
			ps.setString(2, cid);
			k=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return k;
	}

	@Override
	public int deleteList(String cid) {
		int k=0;
		Connection conn=DBUtil.getConn();
		PreparedStatement ps=null;
		String sql="delete t_category where cid=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, cid);
			k=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return k;
	}

}
