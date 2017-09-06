package org.lanqiao.backServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.entity.Book;
import org.lanqiao.service.BookService;
import org.lanqiao.service.Impl.BookServiceImpl;

/**
 * Servlet implementation class getGoods
 */
@WebServlet("/getgoods.do")
public class getGoods extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getGoods() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type=request.getParameter("type");
		if(type.equals("add")) {
			String gtitle=request.getParameter("uname");
			String man=request.getParameter("man");
			double sale=Double.parseDouble(request.getParameter("sale"));
			double in=Double.parseDouble(request.getParameter("in"));
			int click=Integer.parseInt(request.getParameter("click"));
			String publi=request.getParameter("public");
			String cid=request.getParameter("core");
			String desc=request.getParameter("desc");
			BookService bs=new BookServiceImpl();
			int k=bs.update(gtitle, man, sale, in, click, publi, cid, desc);
			if(k==1) {
				response.getWriter().write("1");
			}else {
				response.getWriter().write("0");
			}
		}
		else if(type.equals("delete")){
			int length=Integer.parseInt(request.getParameter("len"));
			String[] s=new String[length];
			for(int i=0;i<length;i++) {
				s[i]=request.getParameter("data["+i+"][gid]");
			}
			int k=0;
			BookService bs=new BookServiceImpl();
			for(int i=0;i<s.length;i++) {
				k=bs.delete(s[i]);
			}
			if(k==1) {
				response.getWriter().write("1");
			}else {
				response.getWriter().write("0");
			}
		}else if(type.equals("update")) {
			String uid=request.getParameter("uid");
			String gtitle=request.getParameter("uname");
			String man=request.getParameter("man");
			double sale=Double.parseDouble(request.getParameter("sale"));
			double in=Double.parseDouble(request.getParameter("in"));
			int click=Integer.parseInt(request.getParameter("click"));
			String publi=request.getParameter("public");
			String cid=request.getParameter("core");
			String desc=request.getParameter("desc");
			BookService book=new BookServiceImpl();
			int k=book.update(uid, gtitle, man, desc, sale, in, cid, publi, click);
			if(k==1) {
				response.getWriter().write("1");
			}else {
				response.getWriter().write("0");
			}
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
