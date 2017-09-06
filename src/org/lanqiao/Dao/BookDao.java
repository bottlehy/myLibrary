package org.lanqiao.Dao;

import java.sql.Date;

import org.lanqiao.entity.Book;
import org.lanqiao.entity.pageinfo;

public interface BookDao {
	public pageinfo<Book> getList(String cid,int pageindex,int pagesize);
	public int getTotal(String cid);
	public Book getBook(String gid);
	public Book insertAndGet1(String gid,String orderid,String userid);
	public void insertAndGet2(String gid,String orderid,String userid);
	public void insertOrder1(String orderid,Book book,String userid);
	public void insertOrder2(String orderid,Book book,String userid);
	public void orderDetail(String orderid,Book book);
	public void updateOrder(String orderid,String person,String state);
	public int updateOrder(String orderid,String person,String state,Date data);
	public pageinfo<Book> getBookAs(String context,int pageindex,int pagesize);
	public int getTotalAs(String context);
	public pageinfo<Book> getBookAss(String context,String cid,int pageindex,int pagesize);
	public int getTotalAss(String context,String cid);
	public int addBook(String gtitle,String man,double sale,double in,int click,String publi,String cid,String desc);
	public int delete(String gid);
	public int updateBook(String gid,String gtitle,String tauthor,String desc,double sale,double in,String cid,String pid,int click);
}
