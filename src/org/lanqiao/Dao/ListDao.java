package org.lanqiao.Dao;

import java.util.List;

import org.lanqiao.entity.ListMenu;

public interface ListDao {
	public List<ListMenu> getList();
	public List<ListMenu> getList(int pageindex,int pagesize);
	public int addList(String cid,String cname);
	public int updateList(String cid,String cname);
	public int deleteList(String cid);
}
