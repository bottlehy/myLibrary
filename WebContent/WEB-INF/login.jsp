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
	<div id="divcontent">
		<table width="900px" border="0" cellspacing="0">
			<tr>
				<td style="padding: 30px"><div style="height: 470px">
						<b>&nbsp;&nbsp;首页&nbsp;&raquo;&nbsp;个人用户登录</b>
						<div>
							<form method="post" action="${pageContext.request.contextPath }/createuser.do">
							<table width="85%" border="0" cellspacing="0">
								<tr>
									<td><div id="logindiv">
											<table width="100%" border="0" cellspacing="0">
												<tr>
													<td style="text-align: center; padding-top: 20px"><img
														src="images/logintitle.gif" width="150" height="30" /></td>
												</tr>
												<tr>

													<td style="text-align: center"><table width="80%"
															border="0" cellspacing="0"
															style="margin-top: 15px; margin-left: auto; margin-right: auto">
															<tr>
																<td
																	style="text-align: right; padding-top: 5px; width: 25%">用户名：</td>
																<td style="text-align: left"><input name="username"
																	type="text" class="textinput" /></td>
															</tr>
															<tr>
																<td style="text-align: right; padding-top: 5px">密&nbsp;&nbsp;&nbsp;&nbsp;码：</td>
																<td style="text-align: left"><input name="userpassword"
																	type="password" class="textinput" /></td>
															</tr>
															<tr>
																<td colspan="2" style="text-align: center"><input
																	type="checkbox" name="checkbox" value="checkbox" />
																	&nbsp;&nbsp;记住我的登录状态</td>
															</tr>
															<tr>
																<td colspan="2"
																	style="padding-top: 10px; text-align: center">
																	<input name="image" type="image" type="submit"
																	src="images/loginbutton.gif" width="83" height="22" /></td>
															</tr>
															<tr>
																<td colspan="2"
																	style="padding-top: 10px; text-align: center">登录帮助&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;帮助中心&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;忘记密码</td>
															</tr>
															<tr>
																<td colspan="2" style="padding-top: 10px"><img
																	src="images/loginline.gif" width="241" height="10" /></td>
															</tr>
															<tr>
																<td colspan="2"
																	style="padding-top: 10px; text-align: center"><a href="${pageContext.request.contextPath }/Dispatcher.do?type=adduser"><img src="${pageContext.request.contextPath }/images/signupbutton.gif"></a></td>
															</tr>
														</table>
														</form>
														</td>
												</tr>
											</table>
										</div></td>
									<td style="text-align: left; padding-top: 30px; width: 60%"><h1>您还没有注册？</h1>
										<p>注册新会员，享受更优惠价格!</p>
										<p>千种图书，供你挑选！注册即享受丰富折扣和优惠，便宜有好货！超过万本图书任您选。</p>
										<p>超人气社区！精彩活动每一天。买卖更安心！支付宝交易超安全。</p>
										<p style="text-align: right">
											<a href="${pageContext.request.contextPath }/Dispatcher.do?type=adduser"><img src="${pageContext.request.contextPath }/images/signupbutton.gif"></a>
										</p></td>
								</tr>
							</table>
						</div>
					</div></td>
			</tr>
		</table>
	</div>



	<%@include file="footer.jsp"%>


</body>
</html>
