<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<meta content="all" name="robots" />
<meta name="author" content="Fisher" />
<meta name="Copyright"
	content="Copyright 2007-2008, 版权所有 www.reefdesign.cn" />
<meta name="description" content="reefdesign" />
<meta name="keywords" content="reefdesign" />
<title>电子书城</title>
<link rel="shortcut icon" href="favicon.ico">
<link rel="stylesheet" rev="stylesheet" href="css/style.css"
	type="text/css" media="all" />
</head>

<body class="main">


	<%@include file="header.jsp"%>

	<div id="divpagecontent">
		<table width="100%" border="0" cellspacing="0">
			<tr>
				<td width="25%"><table width="100%" border="0" cellspacing="0"
						style="margin-top: 30px">
						<tr>
							<td class="listtitle">分类</td>
						</tr>
						<c:forEach items="${listmenu }" var="item">
							<tr>
								<td class="listtd"><img src="images/miniicon.gif" width="9"
									height="6" />&nbsp;&nbsp;&nbsp;&nbsp;<a
									href="${pageContext.request.contextPath}/Dispatcher.do?id=${item.cid}&type=book">${item.cname }</a></td>
							</tr>
						</c:forEach>
					</table></td>
				<td><div style="text-align: right; margin: 5px 10px 5px 0px">
						<a href="${pageContext.request.contextPath }/servletIndex.do">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;<a
							href="${pageContext.request.contextPath }/Dispatcher.do?id=${listmenu[id-1].cid}&type=book">&nbsp;${listmenu[id-1].cname }</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;${good.gtitle }
					</div>
					<table cellspacing="0" class="infocontent">
						<tr>
							<td><img src="${pageContext.request.contextPath }/images/ad/page_ad.jpg" width="645" height="84" />

								<table width="100%%" border="0" cellspacing="0">
									<tr>
										<td width="30%">

											<div class="divbookcover">
												
												<p>
													<img src="${pageContext.request.contextPath }/images/bookcover/bookcover.jpg" />
												</p>
												
												
											</div>

											<div style="text-align: center; margin-top: 25px">
												<c:if test="${ user!=null}">
												<a href="${pageContext.request.contextPath }/Dispatcher.do?gid=${good.gid}&type=buy&id=${id}"><img src="${pageContext.request.contextPath }/images/buybutton.gif"
													border="0" /></a>
													</c:if>
													<c:if test="${user==null }">>
												<a href="${pageContext.request.contextPath }/Dispatcher.do?type=user&id=1"><img src="${pageContext.request.contextPath }/images/buybutton.gif"
													border="0" /></a>
												</c:if>
											</div>
										</td>
										<td style="padding: 20px 5px 5px 5px"><img
											src="images/miniicon3.gif" width="16" height="13" /><font
											class="bookname">&nbsp;&nbsp;${good.gtitle }</font>
										<hr />作者：${good.gauthor } 著
											<hr />售价：<font color="#FF0000">￥${good.gsaleprice }</font>&nbsp;&nbsp;&nbsp;&nbsp;原价：<s>￥${good.ginprice }</s>
										<hr />出版社：${good.pname }
											<hr />
											<p>
												<b>内容简介：</b>
											</p>
											&nbsp&nbsp ${  good.gdesc }
										</td>
									</tr>
								</table></td>
						</tr>
					</table></td>
			</tr>
		</table>
	</div>



	<%@include file="footer.jsp"%>


</body>
</html>
