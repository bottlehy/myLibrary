package org.lanqiao.service.Impl;

import java.util.List;

import org.lanqiao.Dao.ListDao;
import org.lanqiao.Dao.Impl.ListDaoImpl;
import org.lanqiao.entity.ListMenu;
import org.lanqiao.service.ListService;

public class ListServiceImpl implements ListService {
	@Override
	public List<ListMenu> rtList() {
		ListDao ld=new ListDaoImpl();
		return ld.getList();
	}
	public List<ListMenu> rtList(int pageindex,int pagesize) {
		ListDao ld=new ListDaoImpl();
		return ld.getList(pageindex,pagesize);
	}
	@Override
	public int addList(String cid, String cname) {
		ListDao ld=new ListDaoImpl();
		return ld.addList(cid, cname);
	}
	@Override
	public int updateList(String cid, String cname) {
		ListDao ld=new ListDaoImpl();
		return ld.updateList(cid, cname);
	}
	@Override
	public int deleteList(String cid) {
		ListDao ld=new ListDaoImpl();
		return ld.deleteList(cid);
	}
}
