package org.lanqiao.Dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.lanqiao.Dao.NewsDao;
import org.lanqiao.entity.News;
import org.lanqiao.util.DBUtil;

public class NewsDaoImpl implements NewsDao {

	@Override
	public List<News> getList() {
		List<News> list=new ArrayList<News>();
		Connection conn=DBUtil.getConn();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="select * from t_news";
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			News news=null;
			while(rs.next()) {
				news=new News(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4));
				list.add(news);
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
