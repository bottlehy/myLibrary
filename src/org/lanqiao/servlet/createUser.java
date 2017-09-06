package org.lanqiao.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.lanqiao.entity.Order;
import org.lanqiao.entity.User;
import org.lanqiao.service.UserService;
import org.lanqiao.service.Impl.UserServiceImpl;

/**
 * Servlet implementation class createUser
 */
@WebServlet("/createuser.do")
public class createUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public createUser() {

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String userpassword = request.getParameter("userpassword");
		if (username != null && userpassword != null) {
			UserService us = new UserServiceImpl();
			User result = us.rtuser(username, userpassword);
			if (result.getUstaid().equals("1")) {
				if (result != null) {
					HttpSession session = request.getSession();
					String chk = request.getParameter("checkbox");
					if (chk != null) {
						Cookie cookie = new Cookie("userid", username);
						cookie.setMaxAge(60 * 60);
						response.addCookie(cookie);
						cookie = new Cookie("userpassword", userpassword);
						cookie.setMaxAge(60 * 60);
						response.addCookie(cookie);
					}
					List<Order> list=us.relistby(result.getUid());
					session.setAttribute("num", list.size());
					session.setAttribute("user", result);
					request.getRequestDispatcher("WEB-INF/my.jsp").forward(request, response);
				} else {
					request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
				}
			}else {
				request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
			}
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
