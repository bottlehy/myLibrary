package org.lanqiao.service;

import java.sql.Date;

import org.lanqiao.entity.Book;
import org.lanqiao.entity.pageinfo;

public interface BookService {
	public pageinfo<Book> rtlist(String cid,int pageindex,int pagesize);
	public Book rtbook(String gid);
	public Book rtig1(String gid,String orderid,String userid);
	public void rtig2(String gid,String orderid,String userid);
	public void updateOrder(String orderid,String person,String state);
	public int updateOrder(String orderid,String person,String state,Date data);
	public pageinfo<Book> rtlistAs(String content,int pageindex,int pagesize);
	public pageinfo<Book> rtlistAss(String content,String cid,int pageindex,int pagesize);
	public int update(String gtitle, String man, double sale, double in, int click, String publi,String cid,String desc);
	public int delete(String gid);
	public int update(String gid, String gtitle, String tauthor, String desc, double sale, double in, String cid,
			String pid, int click);
}
