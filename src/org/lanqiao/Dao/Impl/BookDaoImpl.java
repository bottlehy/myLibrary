package org.lanqiao.Dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.lanqiao.Dao.BookDao;
import org.lanqiao.entity.Book;
import org.lanqiao.entity.pageinfo;
import org.lanqiao.util.DBUtil;

public class BookDaoImpl implements BookDao {
	pageinfo<Book> pageinfo = new pageinfo<Book>();

	@Override
	public pageinfo<Book> getList(String cid, int pageindex, int pagesize) {
		List<Book> list = new ArrayList<Book>();
		Connection conn = DBUtil.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Book book = null;
		String sql = null;
		int totalnumber = 0;
		try {
			if (Integer.parseInt(cid) != 25) {
				sql = "select t2.* from (select t1.*,rownum rn from (select * from t_goods where cid=?) t1) t2 where rn between ? and ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, cid);
				ps.setInt(2, (pageindex - 1) * pagesize + 1);
				ps.setInt(3, pageindex * pagesize);
				
				totalnumber = this.getTotal(cid);
			} else {
				sql = "select t2.* from (select t1.*,rownum rn from (select * from t_goods) t1) t2 where rn between ? and ?";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, (pageindex - 1) * pagesize + 1);
				ps.setInt(2, pageindex * pagesize);
				totalnumber = this.getTotal();
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				book = new Book(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getDouble(5),
						rs.getString(6), rs.getString(7), rs.getInt(8), rs.getString(9), rs.getString(10),
						rs.getString(11));
				list.add(book);
			}
			pageinfo.setData(list);
			pageinfo.setPageindex(pageindex);
			pageinfo.setPagesize(pagesize);
			pageinfo.setCurrsize(list.size());
			int len = 0;
			if (list.size() % 4 == 0) {
				len = list.size() / 4;
			} else {
				len = list.size() / 4 + 1;
			}
			pageinfo.setCurrlen(len);
			int totalpage = 0;
			if (totalnumber % pagesize == 0) {
				totalpage = totalnumber / pagesize;
			} else {
				totalpage = totalnumber / pagesize + 1;
			}
			pageinfo.setFirst(pageindex == 1);
			pageinfo.setLast(pageindex == totalpage);
			pageinfo.setTotalnumber(totalnumber);
			pageinfo.setTotalpage(totalpage);
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
		return pageinfo;
	}

	@Override
	public int getTotal(String cid) {
		Connection conn = DBUtil.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		int num = 0;
		String sql = "select count(*) from t_goods where cid=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, cid);
			rs = ps.executeQuery();
			if (rs.next()) {
				num = rs.getInt(1);
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
		return num;
	}

	public int getTotal() {
		Connection conn = DBUtil.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		int num = 0;
		String sql = "select count(*) from t_goods";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				num = rs.getInt(1);
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
		return num;
	}

	public Book getBook(String gid) {
		Book book = null;
		Connection conn = DBUtil.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select t1.*,t2.pname from t_goods t1 join t_publicsher t2 on gid=? and t1.pid=t2.pid";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, gid);
			rs = ps.executeQuery();
			if (rs.next()) {
				book = new Book(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getDouble(5),
						rs.getString(6), rs.getString(7), rs.getInt(8), rs.getString(9), rs.getString(10),
						rs.getString(11));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return book;
	}

	@Override
	public Book insertAndGet1(String gid, String orderid, String userid) {
		Book book = null;
		Connection conn = DBUtil.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select t1.*,t2.pname from t_goods t1 join t_publicsher t2 on gid=? and t1.pid=t2.pid";
		BookDao bd = new BookDaoImpl();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, gid);
			rs = ps.executeQuery();
			if (rs.next()) {
				book = new Book(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getDouble(5),
						rs.getString(6), rs.getString(7), rs.getInt(8), rs.getString(9), rs.getString(10),
						rs.getString(11));
				System.out.println(book);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		bd.insertOrder1(orderid, book, userid);
		bd.orderDetail(orderid, book);
		return book;
	}

	public void insertOrder1(String orderid, Book book, String userid) {
		Connection conn = DBUtil.getConn();
		PreparedStatement ps = null;
		String sql = "insert into t_order values(?,?,?,sysdate,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, orderid);
			ps.setString(2, userid);
			ps.setDouble(3, book.getGsaleprice());
			ps.setString(4, "");
			ps.setString(5, "未完成");
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void orderDetail(String orderid, Book book) {
		Connection conn = DBUtil.getConn();
		PreparedStatement ps = null;
		String sql = "insert into t_orderdetail values(od.nextval,?,?,?,?,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, book.getGtitle());
			ps.setDouble(2, book.getGsaleprice());
			ps.setInt(3, 1);
			System.out.println(orderid);
			ps.setString(4, orderid);
			ps.setString(5, book.getGid());
			ps.setDouble(6, book.getGinprice());
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
	public void insertAndGet2(String gid, String orderid, String userid) {
		Book book = null;
		Connection conn = DBUtil.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select t1.*,t2.pname from t_goods t1 join t_publicsher t2 on gid=? and t1.pid=t2.pid";
		BookDao bd = new BookDaoImpl();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, gid);
			rs = ps.executeQuery();
			if (rs.next()) {
				book = new Book(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getDouble(5),
						rs.getString(6), rs.getString(7), rs.getInt(8), rs.getString(9), rs.getString(10),
						rs.getString(11));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		bd.insertOrder2(orderid, book, userid);
		bd.orderDetail(orderid, book);
	}

	@Override
	public void insertOrder2(String orderid, Book book, String userid) {
		Connection conn = DBUtil.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		double total = 0;
		String sql = "select * from t_order where orderid=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, orderid);
			rs = ps.executeQuery();
			if (rs.next()) {
				total = rs.getDouble(3);
			}
			sql = "update t_order set totalprice=? where orderid=?";
			ps = conn.prepareStatement(sql);
			ps.setDouble(1, total + book.getGsaleprice());
			ps.setString(2, orderid);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void updateOrder(String orderid, String person, String state) {
		// TODO Auto-generated method stub
		Connection conn = DBUtil.getConn();
		PreparedStatement ps = null;
		String sql = "update t_order set ORECIPIENTS=?,orderstate=? where orderid=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, person);
			ps.setString(2, state);
			ps.setString(3, orderid);
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
	public org.lanqiao.entity.pageinfo<Book> getBookAs(String content, int pageindex, int pagesize) {
		List<Book> list = new ArrayList<Book>();
		Connection conn = DBUtil.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Book book = null;
		int totalnumber = 0;
		content="%"+content+"%";
		String sql = "select t2.* from (select t1.*,rownum rn from (select * from t_goods where gtitle like ? or gdesc like ?) t1) t2 where rn between ? and ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, content);
			ps.setString(2, content);
			ps.setInt(3, (pageindex-1)*pagesize+1);
			ps.setInt(4, pageindex*pagesize);
			rs = ps.executeQuery();
			while (rs.next()) {
				book = new Book(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getDouble(5),
						rs.getString(6), rs.getString(7), rs.getInt(8), rs.getString(9), rs.getString(10),
						rs.getString(11));
				list.add(book);
			}
			totalnumber = new BookDaoImpl().getTotalAs(content);
			pageinfo.setData(list);
			pageinfo.setPageindex(pageindex);
			pageinfo.setPagesize(pagesize);
			pageinfo.setCurrsize(list.size());
			int len = 0;
			if (list.size() % 4 == 0) {
				len = list.size() / 4;
			} else {
				len = list.size() / 4 + 1;
			}
			pageinfo.setCurrlen(len);
			int totalpage = 0;
			if (totalnumber % pagesize == 0) {
				totalpage = totalnumber / pagesize;
			} else {
				totalpage = totalnumber / pagesize + 1;
			}
			pageinfo.setFirst(pageindex == 1);
			pageinfo.setLast(pageindex == totalpage);
			pageinfo.setTotalnumber(totalnumber);
			pageinfo.setTotalpage(totalpage);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pageinfo;
	}

	@Override
	public int getTotalAs(String context) {
		Connection conn = DBUtil.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		int num = 0;
		String sql = "select count(*) from t_goods where gtitle like ? or gdesc like ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, context);
			ps.setString(2, context);
			rs = ps.executeQuery();
			if (rs.next()) {
				num = rs.getInt(1);
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
		return num;
	}

	@Override
	public pageinfo<Book> getBookAss(String content, String cid,int pageindex,int pagesize) {
		List<Book> list = new ArrayList<Book>();
		Connection conn = DBUtil.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Book book = null;
		int totalnumber = 0;
		content="%"+content+"%";
		String sql;
		try {
			if(!cid.equals("0")) {
				sql = "select t2.* from (select t1.*,rownum rn from (select t.* from (select * from t_goods where cid=?) t where gtitle like ? or gdesc like ?) t1) t2 where rn between ? and ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, cid);
				ps.setString(2, content);
				ps.setString(3, content);
				ps.setInt(4, (pageindex-1)*pagesize+1);
				ps.setInt(5, pageindex*pagesize);
			}
			else {
				sql ="select t2.* from (select t1.*,rownum rn from (select * from t_goods where gtitle like ? or gdesc like ?) t1) t2 where rn between ? and ?";
				ps=conn.prepareStatement(sql);
				ps.setString(1, content);
				ps.setString(2, content);
				ps.setInt(3, (pageindex-1)*pagesize+1);
				ps.setInt(4, pageindex*pagesize);
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				book = new Book(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getDouble(5),
						rs.getString(6), rs.getString(7), rs.getInt(8), rs.getString(9), rs.getString(10),
						rs.getString(11));
				list.add(book);
			}
			System.out.println(list.size());
			totalnumber = new BookDaoImpl().getTotalAss(content,cid);
			pageinfo.setData(list);
			pageinfo.setPageindex(pageindex);
			pageinfo.setPagesize(pagesize);
			pageinfo.setCurrsize(list.size());
			int len = 0;
			if (list.size() % 4 == 0) {
				len = list.size() / 4;
			} else {
				len = list.size() / 4 + 1;
			}
			pageinfo.setCurrlen(len);
			int totalpage = 0;
			if (totalnumber % pagesize == 0) {
				totalpage = totalnumber / pagesize;
			} else {
				totalpage = totalnumber / pagesize + 1;
			}
			pageinfo.setFirst(pageindex == 1);
			pageinfo.setLast(pageindex == totalpage);
			pageinfo.setTotalnumber(totalnumber);
			pageinfo.setTotalpage(totalpage);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pageinfo;
	}

	@Override
	public int getTotalAss(String context, String cid) {
		Connection conn = DBUtil.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		int num = 0;
		String sql;
		try {
			if(!cid.equals("0")) {
				sql = "select count(*) from (select * from t_goods where cid=? ) where gtitle like ? or gdesc like ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, cid);
				ps.setString(2, context);
				ps.setString(3, context);
			}else {
				sql="select count(*) from t_goods where gtitle like ? or gdesc like ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, context);
				ps.setString(2, context);
			}
			rs = ps.executeQuery();
			if (rs.next()) {
				num = rs.getInt(1);
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
		return num;
	}

	@Override
	public int addBook(String gtitle, String man, double sale, double in, int click, String publi,String cid,String desc) {
		int k=0;
		Connection conn=DBUtil.getConn();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="insert into t_goods values(sq.nextval,?,?,?,?,?,?,?,?,?)";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, gtitle);
			ps.setString(2, man);
			ps.setDouble(3, sale);
			ps.setDouble(4, in);
			ps.setString(5, desc);
			ps.setString(6, "1");
			ps.setInt(7, click);
			ps.setString(8, cid);
			ps.setString(9, publi);
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
	public int delete(String gid) {
		int k=0;
		Connection conn=DBUtil.getConn();
		PreparedStatement ps=null;
		String sql="delete t_goods where gid=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, gid);
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
	public int updateBook(String gid, String gtitle, String tauthor, String desc, double sale, double in, String cid,
			String pid, int click) {
		int k=0;
		Connection conn=DBUtil.getConn();
		PreparedStatement ps=null;
		String sql="update t_goods set gtitle=?,gauthor=?,gsaleprice=?,ginprice=?,gdesc=?,gimage='1',gclicks=?,cid=?,pid=? where gid=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, gtitle);
			ps.setString(2, tauthor);
			ps.setDouble(3, sale);
			ps.setDouble(4, in);
			ps.setString(5, desc);
			ps.setInt(6, click);
			ps.setString(7, cid);
			ps.setString(8, pid);
			ps.setString(9, gid);
			k=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return k;
	}

	@Override
	public int updateOrder(String orderid, String person, String state, java.sql.Date data) {
		// TODO Auto-generated method stub
		int k=0;
		Connection conn = DBUtil.getConn();
		PreparedStatement ps = null;
		String sql = "update t_order set ORECIPIENTS=?,orderstate=?,orderdata=? where orderid=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, person);
			ps.setString(2, state);
			ps.setDate(3, data);
			ps.setString(4, orderid);
			k=ps.executeUpdate();
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
		return k;
	}
}
