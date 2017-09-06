<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="UTF-8">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta content="all" name="robots" />
    <meta name="author" content="Fisher" />
    <meta name="Copyright" content="Copyright 2007-2008, 版权所有 www.reefdesign.cn" />
    <meta name="description" content="reefdesign" />
    <meta name="keywords" content="reefdesign" />
    <title>电子书城</title>
    <link rel="shortcut icon" href="favicon.ico">
    <link rel="stylesheet" rev="stylesheet" href="css/style.css" type="text/css" media="all" />
</head>
<body class="main">
    <%@include file="header.jsp" %>
    <div id="divpagecontent">
        <table width="100%" border="0" cellspacing="0">
            <tr>
                <td width="25%">
                <form action="${pageContext.request.contextPath }/Dispatcher.do?type=sbook" method="post">
                    <table width="100%" border="0" cellspacing="0" style="margin-top: 30px">
                        <tr>
                            <td class="listtitle">
                                缩小搜索范围
                            </td>
                        </tr>
                        <tr>
                            <td class="listtd">
                                <br />
                                <p>
                                    关键字：<input type="text" name="attach" class="inputtable" /></p>
                                <p>
                                    类&nbsp;&nbsp;&nbsp;&nbsp;别：<select name="select">
                                    <option value="0">请选择</option>
                                    <c:forEach items="${listmenu }" var="item">
                                    	<c:if test="${selectcid==item.cid }">
                                       		 <option value="${item.cid }" selected="selected">${item.cname }</option>
                                        </c:if>
                                        <c:if test="${selectcid!=item.cid }">
                                        	<option value="${item.cid }">${item.cname }</option>
                                        </c:if>
                                    </c:forEach>
                                    </select>
                                </p>
                                <p style="text-align: center">
                                    <input name="确定" type="submit" class="inputbutton" value="提交" />
                                </p>
                            </td>
                        </tr>
                    </table>
                    </form>
                </td>
                <td>
                    <div style="text-align: right; margin: 5px 10px 5px 0px">
                        <a href="${pageContext.request.contextPath }/servletIndex.do">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;查找</div>
                    <table cellspacing="0" class="infocontent">
                        <tr>
                            <td>
                                <table width="100%" border="0" cellspacing="0">
                                    <tr>
                                        <td style="padding: 10px">
                                            以下 <strong>${Book.totalnumber }</strong> 条结果按 <strong>销量</strong> 排列 每页显示<strong>${Book.pagesize }</strong>条<hr />
										<c:forEach items="${Book.data }" var="item">
                                            <table border="0" cellspacing="0" class="searchtable">
                                                <tr>
                                                    <td width="20%" rowspan="2">
                                                        <div class="divbookpic">
                                                            <p>
                                                                <a href="${pageContext.request.contextPath }/Dispatcher.do?type=goods&gid=${item.gid}">
                                                                    <img src="${pageContext.request.contextPath }/images/bookcover/dayongxiaohua.jpg" width="115" height="129" border="0" /></a></p>
                                                        </div>
                                                    </td>
                                                    <td colspan="2">
                                                       <a href="${pageContext.request.contextPath }/Dispatcher.do?type=goods&gid=${item.gid}"><font class="bookname">${item.gtitle }</font></a><br />
                                                        作者：${item.gauthor } 著<br />
					  &nbsp;&nbsp; ${item.gdesc }
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        售价：<font color="#FF0000">￥${item.gsaleprice }</font>&nbsp;&nbsp;&nbsp;&nbsp;原价：<s>￥${item.ginprice }</s>
                                                    </td>
                                                    <td style="text-align: right">
                                                        <a href="${pageContext.request.contextPath }/Dispatcher.do?gid=${item.gid}&type=goods&id=${id}">
                                                            <img src="images/buy.gif" width="91" height="27" border="0" style="margin-bottom: -8px" /></a>
                                                    </td>
                                                </tr>
                                            </table>
                                            </c:forEach>
                                           
                                            <div class="pagination">
                                                <ul>
                                                <c:if test="${Book.first}">
                                                    <li class="disablepage"><< 上一页 </li>
                                                 </c:if>
                                                  <c:if test="${!Book.first }">
                                                    <li class="nextpage"><a href="${pageContext.request.contextPath }/Dispatcher.do?type=search2&pageindex=${Book.pageindex-1 }"><< 上一页 </a></li>
                                                 </c:if>
                                                 <c:if test="${Book.totalpage<10 }">
                                                 	<c:forEach begin="1" end="${Book.totalpage }" var="i"> 
                                                 		<c:if test="${i==Book.pageindex }"><li class="currentpage">${i }</li></c:if>
                                                   		<c:if test="${i!=Book.pageindex }"><li><a href="${pageContext.request.contextPath }/Dispatcher.do?type=search2&pageindex=${i}">${i }</a></li></c:if>
                                                    </c:forEach>
                                                  </c:if>
                                                  <c:if test="${Book.totalpage>=10 }">
                                                  		<c:set var="startindex" value="${Book.pageindex-5 }"></c:set>
                                                  		<c:set var="endindex" value="${startindex+9 }"></c:set>
                                                  		<c:if test="${startindex<=0 }">
                                                  			<c:set var="startindex" value="1"></c:set>
                                                  			<c:set var="endindex" value="${startindex+9 }"></c:set>
                                                  		</c:if>
                                                  		<c:if test="${endindex>Book.totalpage }">
                                                  			<c:set var="endindex" value="${Book.totalpage }"></c:set>
                                                  			<c:set var="startindex" value="${endindex-9 }"></c:set>
                                                  		</c:if>
                                                  		<c:forEach begin="${startindex }" end="${endindex }" var="i">
                                                  			<c:if test="${i==Book.pageindex }"><li class="currentpage">${i }</li></c:if>
                                                   			<c:if test="${i!=Book.pageindex }"><li><a href="${pageContext.request.contextPath }/Dispatcher.do?type=search2&pageindex=${i}">${i }</a></li></c:if>
                                                  		 </c:forEach>
                                                  	
                                                  </c:if>
                                                    <c:if test="${Book.last }">
                                                    	<li class="disablepage">下一页 >></li>
                                                    </c:if>
                                                    <c:if test="${!Book.last }">
                                                    	<li class="nextpage"><a href="${pageContext.request.contextPath }/Dispatcher.do?type=search2&pageindex=${Book.pageindex+1 }">下一页 >></a></li>
                                                    </c:if>
                                                </ul>
                                            </div>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
    </div>
   <%@include file="footer.jsp" %>
</body>
</html>
