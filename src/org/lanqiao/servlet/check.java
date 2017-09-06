package org.lanqiao.servlet;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.entity.User;
import org.lanqiao.service.UserService;
import org.lanqiao.service.Impl.UserServiceImpl;

/**
 * Servlet implementation class check
 */
@WebServlet("/check.do")
public class check extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public check() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uname=URLDecoder.decode(request.getParameter("uname"),"UTF-8");
		uname=new String(uname.getBytes("iso-8859-1"),"UTF-8");
		UserService us=new UserServiceImpl();
		User user=us.rtuser(uname, "123456");
		response.setContentType("text/html;charset=UTF-8");
		if(user!=null) {
			response.getWriter().write("1");
		}else {
			response.getWriter().write("0");
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
