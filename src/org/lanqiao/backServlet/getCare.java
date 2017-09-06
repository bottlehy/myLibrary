package org.lanqiao.backServlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.entity.ListMenu;
import org.lanqiao.service.ListService;
import org.lanqiao.service.Impl.ListServiceImpl;

import com.google.gson.Gson;

/**
 * Servlet implementation class getCare
 */
@WebServlet("/getcare.do")
public class getCare extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getCare() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String type=request.getParameter("type");
		ListService ls=new ListServiceImpl();
		int k=0;
		if(type.equals("list")) {
			int pageindex=Integer.parseInt(request.getParameter("page"));
			int pagesize=Integer.parseInt(request.getParameter("rows"));
			List<ListMenu> list=ls.rtList(pageindex,pagesize);
			List<ListMenu> ano=ls.rtList();
			Map<String, Object> map=new HashMap<String,Object>();
			map.put("total", ano.size());
			map.put("rows", list);
			response.getWriter().write(new Gson().toJson(map));
		}else if(type.equals("add")) {
			String cid=request.getParameter("cid");
			String cname=request.getParameter("cname");
			List<ListMenu> ano=ls.rtList();
			int j=0;
			for(int i=0;i<ano.size();i++) {
				if(ano.get(i).getCid().equals(cid)&&ano.get(i).getCname().equals(cname)) {
					response.getWriter().write("0");
					break;
				}else {
					j++;
				}
			}
			if(j==ano.size()) {
				k=ls.addList(cid, cname);
				if(k==1) {
					response.getWriter().write("1");
				}else {
					response.getWriter().write("0");
				}
			}
		}else if(type.equals("update")) {
			String cid=request.getParameter("cid");
			String cname=request.getParameter("cname");
			List<ListMenu> ano=ls.rtList();
			int j=0;
			for(int i=0;i<ano.size();i++) {
				if(ano.get(i).getCid().equals(cid)&&ano.get(i).getCname().equals(cname)) {
					response.getWriter().write("0");
					break;
				}else {
					j++;
				}
			}
			if(j==ano.size()) {
				k=ls.updateList(cid, cname);
				if(k==1) {
					response.getWriter().write("1");
				}else {
					response.getWriter().write("0");
				}
			}
		}else if(type.equals("delete")) {
			int total=Integer.parseInt(request.getParameter("total"));
			String cid="";
			for(int i=0;i<total;i++) {
				cid=request.getParameter("data["+i+"][cid]");
				k=ls.deleteList(cid);
			}
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
