package org.lanqiao.servlet;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.lanqiao.service.UserService;
import org.lanqiao.service.Impl.UserServiceImpl;
import org.lanqiao.util.MailUtil;

/**
 * Servlet implementation class addUser
 */
@WebServlet("/adduser.do")
public class addUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addUser() {
   
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("uemail");
		String uname=request.getParameter("uname");
		String upassword=request.getParameter("upassword");
		String sex=request.getParameter("radiobutton");
		String tel=request.getParameter("tel");
		String address=request.getParameter("address");
		String question=request.getParameter("getselect");
		String anser=request.getParameter("anser");
		String anotherEmail=request.getParameter("debug");
		if(uname!=null&&upassword!=null) {
			String uid=UUID.randomUUID().toString();
			UserService us=new UserServiceImpl();
			boolean b=us.rtAdd(uid,email, uname, upassword, sex, tel, address);
			boolean c=us.rtquestion(question, anser, anotherEmail,uid);
			if(b&&c) {
				request.getSession().setAttribute("userid", uid);
				MailUtil.sendMail(email, "<a href='localhost:8080/WebDyanmic/IHaveUser.do?uid="+uid+"'>localhost:8080/WebDyanmic/IHaveUser.do?uid="+uid+"</a>", "这有一份您的快递");
				response.setHeader("refresh" , "5;URL=/WebDyanmic/my.do" );
				request.getRequestDispatcher("/WEB-INF/registersuccess.jsp").forward(request, response);;
			}else {
				request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);;
			}
		}else {
			request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
