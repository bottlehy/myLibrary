<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<meta content="all" name="robots"/>
<meta name="author" content="Fisher" />
<meta name="Copyright" content="Copyright 2007-2008, 版权所有 www.reefdesign.cn" />
<meta name="description" content="reefdesign" />
<meta name="keywords" content="reefdesign" />
<title>电子书城</title>
<link rel="shortcut icon" href="favicon.ico" >
<link rel="stylesheet" rev="stylesheet" href="css/style.css" type="text/css" media="all" />
<script src="js/changeCart.js" type="text/javascript" charset="utf-8"></script>
<script src="js/jquery-2.1.4.min.js" type="text/javascript" charset="utf-8"></script>
</head>

<body class="main">

<%@include file="header.jsp" %>


<div id="divpagecontent">
  <table width="100%" border="0" cellspacing="0">
    <tr>
      <td width="25%"><table width="100%" border="0" cellspacing="0" style="margin-top:30px">
        <tr>
          <td class="listtitle">配送方式、时间及费用</td>
        </tr>
        <tr>
          <td class="listtd"><img src="images/miniicon.gif" width="9" height="6" />&nbsp;&nbsp;&nbsp;&nbsp;<a href="news.html">普通快递送货上门</a></td>
        </tr>
		
<tr>
          <td class="listtd"><img src="images/miniicon.gif" width="9" height="6" />&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="news.html">特快专递送货上门(EMS)</a></td>
        </tr>
<tr>
          <td class="listtd"><img src="images/miniicon.gif" width="9" height="6" />&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="news.html">申通快递送货上门</a></td>
        </tr>
<tr>
          <td class="listtd"><img src="images/miniicon.gif" width="9" height="6" />&nbsp;&nbsp;&nbsp;&nbsp;
<a href="news.html">普通邮寄</a></td>
        </tr>

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
      </table></td>
      <td><div style="text-align:right; margin:5px 10px 5px 0px"><a href="${pageContext.request.contextPath }/servletIndex.do">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;购物车</div>
        
		
		
		
		
        <table cellspacing="0" class="infocontent">
        <tr>
          <td><img src="${pageContext.request.contextPath }/images/ad/page_ad.jpg" width="666" height="89" />
            <table width="100%" border="0" cellspacing="0">
              <tr>
                <td><img src="${pageContext.request.contextPath }/images/buy1.gif" width="635" height="38" /></td>
              </tr>
              <tr>
                <td><table cellspacing="1" class="carttable">
                  <tr>
                    <td width="10%">序号</td>
                    <td width="40%">商品名称</td>
                    <td width="10%">市场价</td>
                    <td width="10%">优惠价</td>
                    <td width="10%">数量</td>
                    <td width="10%">小计</td>
             
                    <td width="10%">取消</td>
                  </tr>
                </table>
                <c:if test="${num!=0 }">
                <c:forEach begin="0" end="${num-1 }" var="i">
                  <table width="100%" border="0" cellspacing="0" id="t1">
                    <tr>
                      <td width="10%">${i+1 }</td>
                      <td width="40%">${orderlist[i].gtitle }</td>
                      <td width="10%">${orderlist[i].ginprice}</td>
                      <td width="10%">${orderlist[i].gsaleprice}</td>
                      <td width="10%"><input name="text" type="text" value="${orderlist[i].gnumber }" style="width:20px" oninput="inputdata(this)"/></td>
                      <td width="10%">${orderlist[i].gnumber*orderlist[i].gsaleprice }</td>
                      
                      <td width="10%"><a href="#" style="color:#FF0000; font-weight:bold" onclick="removedata(this)">X</a></td>
                    </tr>
                  </table>
				  </c:forEach>
				  </c:if>
				   <table cellspacing="1" class="carttable">
                     <tr>
                       <td style="text-align:right; padding-right:40px;">合计：&nbsp;&nbsp;<font id="f1" style="color:#FF6600; font-weight:bold">${orderlist[0].totalprice}</font>元</td>
                      </tr>
                   </table>
				   <div style="text-align:right; margin-top:10px"><a href="${pageContext.request.contextPath }/Dispatcher.do?id=${id}&type=book"><img src="images/gwc_jx.gif" border="0" /></a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath }/Dispatcher.do?type=order"><img src="images/gwc_buy.gif" border="0" /></a></div>
				  
				  </td>
              </tr>
            </table></td>
        </tr>
      </table></td>
    </tr>
  </table>
</div>


<%@include file="footer.jsp" %>



</body>
</html>
