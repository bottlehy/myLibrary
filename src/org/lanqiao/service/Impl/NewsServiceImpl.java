package org.lanqiao.service.Impl;

import java.util.List;

import org.lanqiao.Dao.Impl.NewsDaoImpl;
import org.lanqiao.entity.News;
import org.lanqiao.service.NewsService;

public class NewsServiceImpl implements NewsService {

	@Override
	public List<News> rtList() {
		List<News> list=null;
		NewsDaoImpl dao=new NewsDaoImpl();
		list=dao.getList();
		return list;
	}

}
