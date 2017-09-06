package org.lanqiao.backServlet;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.Dao.UserDao;
import org.lanqiao.Dao.Impl.UserDaoImpl;
import org.lanqiao.entity.Order;
import org.lanqiao.entity.orderdetail;
import org.lanqiao.service.BookService;
import org.lanqiao.service.UserService;
import org.lanqiao.service.Impl.BookServiceImpl;
import org.lanqiao.service.Impl.UserServiceImpl;

import com.google.gson.Gson;

/**
 * Servlet implementation class getOrder
 */
@WebServlet("/getorder.do")
public class getOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type=request.getParameter("type");
		UserService us=new UserServiceImpl();
		if(type.equals("detatil")) {
			String orderid=request.getParameter("data");
			int page=Integer.parseInt(request.getParameter("page"));
			int size=Integer.parseInt(request.getParameter("rows"));
			UserDao ut=new UserDaoImpl();
			List<orderdetail> list=ut.getOr(orderid,page,size);
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("total", ut.getOrtotal(orderid).size());
			map.put("rows", list);
			Gson gson=new Gson();
			response.getWriter().write(gson.toJson(map));
		}
		if(type.equals("list")) {
			String uid=request.getParameter("data");
			int index=Integer.parseInt(request.getParameter("page"));
			int size=Integer.parseInt(request.getParameter("rows"));
			UserDao ud=new UserDaoImpl();
			List<Order> list=ud.getO(uid, index, size);
			int k=ud.getNum(uid);
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("total", k);
			map.put("rows", list);
			response.getWriter().write(new Gson().toJson(map));
		}else if(type.equals("update")){
			String orderid=request.getParameter("orderid");
			String man=request.getParameter("man");
			String state=request.getParameter("state");
			String odate=request.getParameter("odate");
			int year=Integer.parseInt(odate.substring(0, 4))-1900;
			int month=Integer.parseInt(odate.substring(5, 7))-1;
			int dd=Integer.parseInt(odate.substring(8));
			Date date=new Date(year, month, dd);
			BookService bs=new BookServiceImpl();
			System.out.println(man);
			int k=bs.updateOrder(orderid, man, state, date);
			if(k==1) {
				response.getWriter().write("1");
			}else {
				response.getWriter().write("0");
			}
		}else if(type.equals("delete")) {
			int total=Integer.parseInt(request.getParameter("total"));
			String[] order=new String[total];
			for(int i=0;i<total;i++) {
				order[i]=request.getParameter("data["+i+"][orderid]");
			}
			UserDaoImpl ud=new UserDaoImpl();
			List<String> list=new ArrayList<String>();
			for(int i=0;i<order.length;i++) {
				ud.removeOrderdetail(order[i]);
				ud.removeOrder(order[i]);
				response.getWriter().write("1");
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
