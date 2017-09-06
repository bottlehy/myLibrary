package org.lanqiao.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.entity.User;
import org.lanqiao.service.UserService;
import org.lanqiao.service.Impl.UserServiceImpl;

/**
 * Servlet implementation class changeUser
 */
@WebServlet("/changeuser.do")
public class changeUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public changeUser() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String password=request.getParameter("upassword");
		String usex=request.getParameter("usex");
		String tel=request.getParameter("tel");
		String address=request.getParameter("address");
		String select=request.getParameter("getselect");
		String answer=request.getParameter("answer");
		String uemail=request.getParameter("uemail");
		User user=(User) request.getSession().getAttribute("user");
		String userid=user.getUid();
		UserService us=new UserServiceImpl();
		us.update(password, usex, tel, address, select, answer, uemail,userid);
		request.getRequestDispatcher("/Dispatcher.do?type=alchange").forward(request, response);;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
