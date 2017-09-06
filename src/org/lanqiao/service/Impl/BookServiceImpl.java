package org.lanqiao.service.Impl;

import java.sql.Date;

import org.lanqiao.Dao.BookDao;
import org.lanqiao.Dao.Impl.BookDaoImpl;
import org.lanqiao.entity.Book;
import org.lanqiao.entity.pageinfo;
import org.lanqiao.service.BookService;

public class BookServiceImpl implements BookService {
	private BookDao bd=new BookDaoImpl();
	@Override
	public pageinfo<Book> rtlist(String cid, int pageindex, int pagesize) {
		pageinfo<Book> pageinfo=bd.getList(cid, pageindex, pagesize);
		return pageinfo;
	}

	@Override
	public Book rtbook(String gid) {
		Book book=bd.getBook(gid);
		return book;
	}

	@Override
	public Book rtig1(String gid, String orderid, String userid) {
		// TODO Auto-generated method stub
		return bd.insertAndGet1(gid, orderid, userid);
	}

	@Override
	public void rtig2(String gid, String orderid, String userid) {
		// TODO Auto-generated method stub
		bd.insertAndGet2(gid, orderid, userid);
	}

	@Override
	public void updateOrder(String orderid, String person, String state) {
		// TODO Auto-generated method stub
		bd.updateOrder(orderid, person, state);
	}

	@Override
	public pageinfo<Book> rtlistAs(String content, int pageindex, int pagesize) {
		// TODO Auto-generated method stub
		return bd.getBookAs(content, pageindex, pagesize);
	}
	public pageinfo<Book> rtlistAss(String content,String cid, int pageindex, int pagesize) {
		// TODO Auto-generated method stub
		return bd.getBookAss(content,cid, pageindex, pagesize);
	}

	@Override
	public int update(String gtitle, String man, double sale, double in, int click, String publi, String cid,
			String desc) {
		// TODO Auto-generated method stub
		return bd.addBook(gtitle, man, sale, in, click, publi, cid, desc);
	}
	public int delete(String gid) {
		return bd.delete(gid);
	}
	public int update(String gid, String gtitle, String tauthor, String desc, double sale, double in, String cid,
			String pid, int click) {
		return bd.updateBook(gid, gtitle, tauthor, desc, sale, in, cid, pid, click);
	}

	@Override
	public int updateOrder(String orderid, String person, String state, Date data) {
		// TODO Auto-generated method stub
		return bd.updateOrder(orderid, person, state, data);
	}
}
