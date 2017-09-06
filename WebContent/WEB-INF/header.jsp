<%@page import="org.lanqiao.entity.ListMenu"%>
<%@page import="java.util.List"%>
<%@page import="org.lanqiao.service.Impl.ListServiceImpl"%>
<%@page import="org.lanqiao.service.ListService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- Logo -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<div id="divhead">
  <table cellspacing="0" class="headtable">
    <tr>
      <td><a href="${pageContext.request.contextPath }/servletIndex.do"><img src="${pageContext.request.contextPath }/images/logo.gif" width="95" height="30" border="0" /></a></td>
 	 <c:if test="${user==null }">
      <td style="text-align:right"><img src="${pageContext.request.contextPath }/images/cart.gif" width="26" height="23" style="margin-bottom:-4px"/>&nbsp;<a href="${pageContext.request.contextPath }/Dispatcher.do?type=user&id=1">购物车<c:if test="${num!=null }"><font color="red">(${num })</font></c:if><c:if test="${num==null }"><font color="red">(0)</font></c:if></a>　|　<a href="#">帮助中心</a>　|　<a href="${pageContext.request.contextPath }/Dispatcher.do?type=user&id=1">我的帐户</a>　|　<a href="${pageContext.request.contextPath }/Dispatcher.do?type=adduser&id=1">新用户注册</a></td>
    </c:if>
    <c:if test="${user!=null }">
      <td style="text-align:right"><img src="${pageContext.request.contextPath }/images/cart.gif" width="26" height="23" style="margin-bottom:-4px"/>&nbsp;<a href="${pageContext.request.contextPath }/Dispatcher.do?type=buy">购物车<c:if test="${num!=null }"><font color="red">(${num })</font></c:if><c:if test="${num==null }"><font color="red">(0)</font></c:if></a>　|　<a href="#">帮助中心</a>　|　<a href="${pageContext.request.contextPath }/Dispatcher.do?type=aluser&id=1">我的帐户</a>　|　<a href="${pageContext.request.contextPath }/Dispatcher.do?type=adduser&id=1">新用户注册</a></td>
    </c:if>
    </tr>
  </table>
</div>
<% ListService ls=new ListServiceImpl();
   List<ListMenu> list=ls.rtList();
   request.setAttribute("list", list);
%>
<!-- Logo end -->
<!-- menu -->
<div id="divmenu">
<c:forEach items="${list}" var="item" begin="0" end="13">
    <a href="${pageContext.request.contextPath }/Dispatcher.do?id=${item.cid}&type=book">${item.cname }</a>　　
 </c:forEach>
  <a href="${pageContext.request.contextPath }/Dispatcher.do?id=25&type=book" style="color:#FFFF00">全部商品目录</a>
</div>
<!-- menu end -->
<!-- search -->
<div id="divsearch"><table width="100%" border="0" cellspacing="0">
   <form action="${pageContext.request.contextPath }/Dispatcher.do?type=search1" method="post">
  <tr>
    <td style="text-align:right; padding-right:220px">Search
  <input type="text" name="textsearch" class="inputtable"/>
<!--<input name="searchbutton" type="image" src="images/serchbutton.gif" style=" margin-bottom:-4px"/>-->
<input type="image" src="${pageContext.request.contextPath }/images/serchbutton.gif" border="0" style="margin-bottom:-4px" /></td>
  </tr>
  </form>
</table>

</div>
<!-- search end -->