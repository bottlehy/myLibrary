package org.lanqiao.service;

import java.util.List;

import org.lanqiao.entity.ListMenu;

public interface ListService {
	public List<ListMenu> rtList();
	public List<ListMenu> rtList(int pageindex,int pagesize);
	public int addList(String cid,String cname);
	public int updateList(String cid,String cname);
	public int deleteList(String cid);
}
