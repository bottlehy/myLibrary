package org.lanqiao.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.lanqiao.entity.Book;
import org.lanqiao.entity.News;
import org.lanqiao.entity.Order;
import org.lanqiao.entity.User;
import org.lanqiao.entity.pageinfo;
import org.lanqiao.entity.passwordAnswer;
import org.lanqiao.service.BookService;
import org.lanqiao.service.ListService;
import org.lanqiao.service.UserService;
import org.lanqiao.service.Impl.BookServiceImpl;
import org.lanqiao.service.Impl.ListServiceImpl;
import org.lanqiao.service.Impl.NewsServiceImpl;
import org.lanqiao.service.Impl.UserServiceImpl;

import com.sun.deploy.uitoolkit.impl.fx.Utils;

/**
 * Servlet implementation class Dispatcher
 */
@WebServlet("/Dispatcher.do")
public class Dispatcher extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Dispatcher() {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String type = request.getParameter("type");
		String id = request.getParameter("id");
		ListService ls = new ListServiceImpl();
		request.setAttribute("listmenu", ls.rtList());
		request.setAttribute("id", id);
		if(type.equals("sbook")) {
			String pageindex = request.getParameter("pageindex");
			String pagesize = request.getParameter("pagesize");
			String context=request.getParameter("attach");
			String cid=request.getParameter("select");
			BookService bs = new BookServiceImpl();
			if (pageindex == null) {
				pageindex = "1";
			}
			pagesize = "5";
			pageinfo<Book> pageinfo = bs.rtlistAss(context,cid, Integer.parseInt(pageindex), Integer.parseInt(pagesize));
			request.setAttribute("Book", pageinfo);
			request.getSession().setAttribute("context", context);
			request.getSession().setAttribute("selectcid", cid);
			request.getRequestDispatcher("WEB-INF/search.jsp").forward(request, response);
		}
		if (type.equals("search1")) {
			String pageindex = request.getParameter("pageindex");
			String pagesize = request.getParameter("pagesize");
			String context=request.getParameter("textsearch");
			request.getSession().removeAttribute("selectcid");
			BookService bs = new BookServiceImpl();
			if (pageindex == null) {
				pageindex = "1";
			}
			pagesize = "5";
			pageinfo<Book> pageinfo = bs.rtlistAs(context, Integer.parseInt(pageindex), Integer.parseInt(pagesize));
			request.setAttribute("Book", pageinfo);
			request.getSession().setAttribute("context", context);
			request.getRequestDispatcher("WEB-INF/search.jsp").forward(request, response);
		}
		if (type.equals("search2")) {
			String pageindex = request.getParameter("pageindex");
			String pagesize = request.getParameter("pagesize");
			String context=request.getSession().getAttribute("context").toString();
			String selectcid;
			if(request.getSession().getAttribute("selectcid")==null) {
				selectcid=null;
			}else {
				selectcid=request.getSession().getAttribute("selectcid").toString();
			}
			BookService bs = new BookServiceImpl();
			if (pageindex == null) {
				pageindex = "1";
			}
			pagesize = "5";
			pageinfo<Book> pageinfo =null;
			if(selectcid==null) {
				pageinfo = bs.rtlistAs(context, Integer.parseInt(pageindex), Integer.parseInt(pagesize));
				request.setAttribute("Book", pageinfo);
				request.getSession().setAttribute("context", context);
			}else {
				pageinfo = bs.rtlistAss(context,selectcid, Integer.parseInt(pageindex), Integer.parseInt(pagesize));
				request.setAttribute("Book", pageinfo);
				request.getSession().setAttribute("context", context);
				request.getSession().setAttribute("selectcid", selectcid);
			}
			request.getRequestDispatcher("WEB-INF/search.jsp").forward(request, response);
		}
		if (type.equals("success")) {
			String add = request.getSession().getAttribute("add").toString();
			String person = request.getSession().getAttribute("person").toString();
			String tel = request.getSession().getAttribute("rectel").toString();
			User user = (User) request.getSession().getAttribute("user");
			String userid = user.getUid();
			List<Order> list = (List<Order>) request.getSession().getAttribute("orderlist");
			String orderid = list.get(0).getOrderid();
			BookService bs = new BookServiceImpl();
			bs.updateOrder(orderid, person, "已提交");
			request.getSession().setAttribute("num", 0);
			request.getRequestDispatcher("WEB-INF/success2.jsp").forward(request, response);
			;
		}
		if (type.equals("orderfinal")) {
			String add = request.getParameter("address");
			String person = request.getParameter("person");
			String tel = request.getParameter("tel");
			request.getSession().setAttribute("add", add);
			request.getSession().setAttribute("person", person);
			request.getSession().setAttribute("rectel", tel);
			request.getRequestDispatcher("/WEB-INF/orderfinal.jsp").forward(request, response);
		}
		if (type.equals("order")) {
			request.getRequestDispatcher("/WEB-INF/order.jsp").forward(request, response);
		}
		if (type.equals("update")) {
			User user = (User) request.getSession().getAttribute("user");
			String userid = user.getUid();
			UserService us = new UserServiceImpl();
			passwordAnswer pa = us.rtpass(userid);
			request.setAttribute("ue", user);
			request.setAttribute("pass", pa);
			request.getRequestDispatcher("/WEB-INF/modifyuserinfo.jsp").forward(request, response);
		}
		if (type.equals("selectorder")) {
			User user = (User) request.getSession().getAttribute("user");
			String userid = user.getUid();
			UserService us = new UserServiceImpl();
			List<Order> list = us.relist(userid);
			request.setAttribute("orderlistAll", list);
			request.setAttribute("numAll", list.size());
			request.getRequestDispatcher("/WEB-INF/orderlist.jsp").forward(request, response);
		}
		if (type.equals("alchange")) {
			request.getRequestDispatcher("/WEB-INF/success.jsp").forward(request, response);
		}
		if (type.equals("aluser")) {
			request.getRequestDispatcher("/WEB-INF/my.jsp").forward(request, response);
		}
		if (type.equals("adduser")) {
			request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
		}
		if (type.equals("user")) {
			Cookie[] cook = request.getCookies();
			Cookie usercook = null;
			Cookie userpassword = null;
			for (Cookie c : cook) {
				if (c.getName().equals("userid")) {
					usercook = c;
				} else if (c.getName().equals("userpassword")) {
					userpassword = c;
				}
			}
			if (request.getSession().getAttribute("user") == null) {
				if (usercook != null && userpassword != null) {
					UserService us = new UserServiceImpl();
					User user = us.rtuser(usercook.getValue(), userpassword.getValue());
					if (user != null) {
						request.getSession().setAttribute("user", user);
						request.getRequestDispatcher("/WEB-INF/my.jsp").forward(request, response);
					} else {
						request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
					}
				} else {
					request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
				}
			} else {
				request.getRequestDispatcher("/WEB-INF/my.jsp").forward(request, response);
			}
		}
		if (type.equals("buy")) {
			String gid = request.getParameter("gid");
			User user = (User) request.getSession().getAttribute("user");
			UserService us = new UserServiceImpl();
			String userid = user.getUid();
			List<Order> list = us.relistby(userid);
			if (gid != null) {
				String orderid = "";
				if (list.size() == 0) {
					orderid = UUID.randomUUID().toString();
					BookService bs = new BookServiceImpl();
					bs.rtig1(gid, orderid, userid);
					list = us.relistby(userid);
					request.getSession().setAttribute("orderlist", list);
					request.getSession().setAttribute("num", list.size());
				} else {
					orderid = list.get(0).getOrderid();
					BookService bs = new BookServiceImpl();
					bs.rtig2(gid, orderid, userid);
					list = us.relistby(userid);
					System.out.println(list.size());
					request.getSession().setAttribute("orderlist", list);
					request.getSession().setAttribute("num", list.size());
				}
				request.getRequestDispatcher("/Dispatcher.do?type=goods").forward(request, response);
			} else {
				request.getSession().setAttribute("orderlist", list);
				request.getSession().setAttribute("num", list.size());
				request.getRequestDispatcher("/WEB-INF/cart.jsp").forward(request, response);
			}
		}
		if (type.equals("goods")) {
			String gid = request.getParameter("gid");
			BookService bs = new BookServiceImpl();
			Book good = bs.rtbook(gid);
			request.setAttribute("good", good);
			request.getRequestDispatcher("/WEB-INF/info.jsp").forward(request, response);
		}
		if (type.equals("news") && id != null) {
			NewsServiceImpl nsi = new NewsServiceImpl();
			List<News> list = nsi.rtList();
			request.setAttribute("News", list);
			request.setAttribute("id", id);
			request.getRequestDispatcher("/WEB-INF/news.jsp").forward(request, response);
		}
		if (type.equals("book") && id != null) {
			String pageindex = request.getParameter("pageindex");
			String pagesize = request.getParameter("pagesize");
			BookService bs = new BookServiceImpl();
			if (pageindex == null) {
				pageindex = "1";
			}
			pagesize = "16";
			pageinfo<Book> pageinfo = bs.rtlist(id, Integer.parseInt(pageindex), Integer.parseInt(pagesize));
			request.setAttribute("Book", pageinfo);
			request.getRequestDispatcher("/WEB-INF/product_list.jsp").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
