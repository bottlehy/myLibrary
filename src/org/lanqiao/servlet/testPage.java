package org.lanqiao.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.entity.Book;
import org.lanqiao.entity.pageinfo;
import org.lanqiao.service.BookService;
import org.lanqiao.service.Impl.BookServiceImpl;

import com.google.gson.Gson;

/**
 * Servlet implementation class testPage
 */
@WebServlet("/testpage.do")
public class testPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public testPage() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pageindex = request.getParameter("pageIndex");
		String pagesize = request.getParameter("pageSize");
		BookService bs = new BookServiceImpl();
		pageinfo<Book> pageinfo = bs.rtlist("25",Integer.parseInt(pageindex)+1, Integer.parseInt(pagesize));
		Gson gson=new Gson();
		String page=gson.toJson(pageinfo);
		response.getWriter().write(page);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
