package org.lanqiao.backServlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.entity.Book;
import org.lanqiao.entity.ListMenu;
import org.lanqiao.entity.User;
import org.lanqiao.entity.pageinfo;
import org.lanqiao.service.BookService;
import org.lanqiao.service.ListService;
import org.lanqiao.service.UserService;
import org.lanqiao.service.Impl.BookServiceImpl;
import org.lanqiao.service.Impl.ListServiceImpl;
import org.lanqiao.service.Impl.UserServiceImpl;

import com.google.gson.Gson;

/**
 * Servlet implementation class getUser
 */
@WebServlet("/getuser.do")
public class getUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public getUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String type = request.getParameter("type");
		UserService us = new UserServiceImpl();
		if(type.equals("goods")) {
			String cid=request.getParameter("cid");
			String text=request.getParameter("text");
			int pageindex=Integer.parseInt(request.getParameter("page"));
			int pagesize=Integer.parseInt(request.getParameter("rows"));
			BookService bs=new BookServiceImpl();
			pageinfo<Book> list=new pageinfo<Book>();
			if(text.equals("0")) {
				list=bs.rtlist(cid, pageindex, pagesize);
			}else {
				list=bs.rtlistAss(text, cid, pageindex, pagesize);
			}
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("total", list.getTotalnumber());
			map.put("rows", list.getData());
			Gson gson=new Gson();
			response.getWriter().write(gson.toJson(map));
		}
		if(type.equals("getlist")) {
			ListService list=new ListServiceImpl();
			List<ListMenu> getlist=list.rtList();
			Gson gson=new Gson();
			response.getWriter().write(gson.toJson(getlist));
		}
		if (type.equals("list")) {
			List<User> list = us.getAll();
			if (list != null) {
				Gson gson = new Gson();
				String li = gson.toJson(list);
				response.getWriter().write(li);
			} else {
				response.getWriter().write("0");
			}
		}
		else if(type.equals("remove")) {
			String userid=request.getParameter("uid");
			int i=us.remove(userid);
			if(i==1) {
				response.getWriter().write("1");
			}else {
				response.getWriter().write("0");
			}
		}else if(type.equals("add")) {
			String uname=request.getParameter("uname");
			String upassword=request.getParameter("upassword");
			String sex=request.getParameter("sex");
			String address=request.getParameter("uaddress");
			String utel=request.getParameter("utel");
			String email=request.getParameter("email");
			String uid=UUID.randomUUID().toString();
			boolean al=us.rtAdd(uid, email, uname, upassword, sex, utel, address);
			if(al) {
				response.getWriter().write("1");
			}else {
				response.getWriter().write("0");
			}
		}else if(type.equals("update")) {
			String uname=request.getParameter("uname");
			String upassword=request.getParameter("upassword");
			String sex=request.getParameter("sex");
			String address=request.getParameter("uaddress");
			String utel=request.getParameter("utel");
			String email=request.getParameter("email");
			String ustateid=request.getParameter("ustate");
			System.out.println(ustateid);
			String userid=us.rtuserid(uname);
			int k=us.updatebyweb(upassword, sex, utel, address, email, userid,ustateid);
			if(k==1) {
				response.getWriter().write("1");
			}else {
				response.getWriter().write("0");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
