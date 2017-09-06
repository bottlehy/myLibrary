<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	<link rel="stylesheet" rev="stylesheet"
		href="${pageContext.request.contextPath }/css/style.css"
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
				<td>
					<div style="text-align: right; margin: 5px 10px 5px 0px">
						<a href="${pageContext.request.contextPath }/servletIndex.do">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;${listmenu[id-1].cname }&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;图书列表
					</div>





					<table cellspacing="0" class="listcontent">
						<tr>
							<td>
								<h1>商品目录</h1>
								<hr />
								<h1>${listmenu[id-1].cname }</h1>&nbsp;&nbsp;&nbsp;&nbsp;${Book.totalnumber }
								<hr /> <img src="images/miniicon2.gif" />&nbsp;&nbsp;&nbsp;&nbsp;计算机类的子分类&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;JAVA&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;ASP.NET&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;网站设计&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;图形处理

								<div style="margin-top: 20px; margin-bottom: 5px">
									<img src="images/productlist.gif" width="631" height="38" />
								</div>



								<table cellspacing="0" class="booklist">
									<c:set var="i" value="0"></c:set>
									<c:set var="k" value="${i+3 }"></c:set>
									<c:set var="c" value="${Book.currsize%4==0?true:false }"></c:set>
									<c:set var="s" value="${Book.currlen }"></c:set>					
									<c:forEach begin="1" end="${s }" var="l">
										<tr>
										<c:forEach begin='${ i}' end="${k }" var="j">
												<td>
													<div class="divbookpic">
														<p>
															<a href="${pageContext.request.contextPath }/Dispatcher.do?id=${Book.data[0].cid }&gid=${Book.data[j].gid}&type=goods"><img
																src="${pageContext.request.contextPath }/images/bookcover/dayongxiaohua.jpg" width="115"
																height="129" border="0" /></a>
														</p>
													</div>
													<div class="divlisttitle">
														<a href="${pageContext.request.contextPath }/Dispatcher.do?id=${Book.data[0].cid  }&gid=${Book.data[j].gid}&type=goods">${Book.data[j].gtitle } <br /> 售价： ${Book.data[j].gsaleprice }
														</a>
													</div>
												</td>
											</c:forEach>
										</tr>
										<c:if test="${c }">
											<c:set var="i" value="${i+4 }"></c:set>
											<c:set var="k" value="${i+3 }"></c:set>
										</c:if>
										<c:if test="${!c }">
											<c:if test="${(l+1)!=s}">
												<c:set var="i" value="${i+4 }"></c:set>
												<c:set var="k" value="${i+3 }"></c:set>	
											</c:if>
											<c:if test="${(l+1)==s }">
												<c:set var="i" value="${i+4 }"></c:set>
												<c:set var="k" value="${i+Book.currsize-(l*4)-1 }"></c:set>
											</c:if>
										</c:if>
									</c:forEach>
								</table>
								<div class="pagination">
									<ul>
										<c:if test="${Book.first}">
											<li class="disablepage"><< 上一页</li>
										</c:if>
										<c:if test="${id!=25 }">
											<c:if test="${!Book.first }">
												<li class="nextpage"><a
													href="${pageContext.request.contextPath }/Dispatcher.do?id=${Book.data[0].cid}&pageindex=${Book.pageindex-1}&type=book"><< 上一页</a></li>
											</c:if>
											<c:if test="${Book.totalpage<10 }">
												<c:forEach begin="1" end="${Book.totalpage }" var="x">
													<c:if test="${x==Book.pageindex }">
														<li class="currentpage">${x }</li>
													</c:if>
													<c:if test="${x!=Book.pageindex }">
														<li><a href="${pageContext.request.contextPath }/Dispatcher.do?id=${Book.data[0].cid }&pageindex=${x }&type=book">${x }</a></li>
													</c:if>
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
												<c:set var="endindex" value="${Book.totalpage}"></c:set>
												<c:set var="startindex" value="${endindex-9 }"></c:set>
											</c:if>
												<c:forEach begin="1" end="${endindex }" var="x">
													<c:if test="${x==Book.pageindex }">
														<li class="currentpage">${x }</li>
													</c:if>
													<c:if test="${x!=Book.pageindex }">
														<li><a href="${pageContext.request.contextPath }/Dispatcher.do?id=${Book.data[0].cid }&pageindex=${x }&type=book">${x }</a></li>
													</c:if>
												</c:forEach>
											</c:if>
										</c:if>
										<c:if test="${id==25 }">
											<c:if test="${!Book.first }">
												<li class="nextpage"><a
													href="${pageContext.request.contextPath }/Dispatcher.do?id=25&pageindex=${Book.pageindex-1}&type=book"><< 上一页</a></li>
													</c:if>
												<c:if test="${Book.totalpage<10 }">
											<c:forEach begin="1" end="${Book.totalpage }" var="x">
												<c:if test="${x==Book.pageindex }">
													<li class="currentpage">${x }</li>
												</c:if>
												<c:if test="${x!=Book.pageindex }">
													<li><a href="${pageContext.request.contextPath }/Dispatcher.do?id=25&pageindex=${x }&type=book">${x }</a></li>
												</c:if>
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
											<c:set var="endindex" value="${Book.totalpage}"></c:set>
											<c:set var="startindex" value="${endindex-9 }"></c:set>
										</c:if>
											<c:forEach begin="${startindex }" end="${endindex }" var="x">
												<c:if test="${x==Book.pageindex }">
													<li class="currentpage">${x }</li>
												</c:if>
												<c:if test="${x!=Book.pageindex }">
													<li><a href="${pageContext.request.contextPath }/Dispatcher.do?id=25&pageindex=${x }&type=book">${x }</a></li>
												</c:if>
											</c:forEach>
										</c:if>
										</c:if>
										<c:if test="${Book.last}">
											<li class="disablepage">下一页 >></li>
										</c:if>
										<c:if test="${id!=25 }">
											<c:if test="${!Book.last}">
												<li class="nextpage"><a
													href="${pageContext.request.contextPath }/Dispatcher.do?id=${Book.data[0].cid}&pageindex=${Book.pageindex+1}&type=book">下一页 >></a></li>
											</c:if>
										</c:if>
										<c:if test="${id==25 }">
											<c:if test="${!Book.last}">
												<li class="nextpage"><a
													href="${pageContext.request.contextPath }/Dispatcher.do?id=25&pageindex=${Book.pageindex+1}&type=book">下一页 >></a></li>
											</c:if>
										</c:if>
									</ul>
								</div>


							</td>
						</tr>
					</table>



				</td>
			</tr>
		</table>
	</div>



	<%@include file="footer.jsp"%>


</body>
</html>
