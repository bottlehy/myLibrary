/**
 * 
 */
var sign;
$(function() {
	$("#s").combobox({
		url : "/WebDyanmic/getuser.do?type=list",
		valueField : "uid",
		textField : "uname",
		onSelect : function(data) {
			showdata(data.uid);
		},
		onLoadSuccess : function() {
			var data = $("#s").combobox("getData");
			$("#s").combobox("setValue", data[0].uid);
			showdata(data[0].uid);
		}
	})
	$("#d1").dialog({
		width : 450,
		height : 300,
		closed : true
	})
	$("#d2").dialog({
		width : 700,
		height : 300,
		title : "订单详情",
		closed : true
	})
})
function showdata(uid) {
	$("#tab").datagrid(
					{
						url : "/WebDyanmic/getorder.do",
						pagination : true,
						title : "用户订单",
						iconCls : "icon-man",
						rownumbers : true,
						queryParams : {
							type : "list",
							data : uid
						},
						onClickRow : function() {
							var is = $("#tab").datagrid("getSelected");
							sign = is.orderid;
							$("#d2").dialog({
								closed : false
							});
							$("#t3").datagrid({
								title : "订单详情",
								url : "/WebDyanmic/getorder.do",
								queryParams : {
									type : "detatil",
									data : sign
								},
								pagination : true,
								iconCls : "icon-man",
								rownumbers : true,
								columns : [ [ {
											field : 'orderdetailid',
											title : '详情编号',
											width : 70
										}, {
											field : 'gtitle',
											title : '商品名称',
											width : 100
										}, {
											field : 'gsaleprice',
											title : '售价',
											width : 50
										}, {
											field : 'gnumber',
											title : '数量',
											width : 50
										}, {
											field : 'gid',
											title : '商品编号',
											width : 70
										}, {
											field : 'ginprice',
											title : '进价',
											width : 50
										}, {
											field : 'orderid',
											title : '订单编号',
											width : 240
										}
								 ] ]
							})
						},
						toolbar : [
								{
									text : "修改",
									iconCls : "icon-edit",
									handler : function() {
										var select = $("#tab").datagrid(
												"getSelected");
										if (select != null) {
											$("#d1")
													.dialog(
															{
																closed : false,
																title : "修改",
																iconCls : "icon-add",
																buttons : [
																		{
																			text : "保存",
																			iconCls : "icon-save",
																			handler : function() {
																				var is = $(
																						"form")
																						.form(
																								"validate");
																				if (is) {
																					$
																							.ajax({
																								url : "/WebDyanmic/getorder.do?type=update",
																								data : $(
																										"form")
																										.serialize(),
																								type : "post",
																								success : function(
																										data) {
																									if (data == "1") {
																										$.messager
																												.alert(
																														"撒花",
																														"修改订单成功");
																										$(
																												"form")
																												.form(
																														"reset");
																										$(
																												"#d1")
																												.dialog(
																														"close");
																										$(
																												"#tab")
																												.datagrid(
																														"reload");
																									} else {
																										$.messager
																												.alert(
																														"抱歉",
																														"我的勇士,我没能完成任务",
																														"info");
																									}
																								}
																							})
																				} else {
																					$.messager
																							.alert(
																									"漏了",
																									"你还有信息没有正确填写",
																									"info");
																				}
																			}
																		},
																		{
																			text : "取消",
																			iconCls : "icon-cancel",
																			handler : function() {
																				$(
																						"form")
																						.form(
																								"reset");
																				$(
																						"#d1")
																						.dialog(
																								"close");
																			}
																		} ]
															})
											$("#orderid").textbox("setValue",
													select.orderid);
											$("#orderid").textbox("readonly");
											$("#total").textbox("setValue",
													select.totalprice);
											$("#total").textbox("readonly");
											$("#man").textbox("setValue",
													select.orecipients);
											$("#state").textbox("setValue",
													select.orderstate);
											$("#userid").textbox("setValue",
													select.userid);
											$("#userid").textbox("readonly");
											$("#odate").datebox("setValue",
													select.orderdata);
										} else {
											$.messager.alert("抱歉",
													"不知道你要修改哪条数据", "info");
										}
									}
								},
								"-",
								{
									text : "删除",
									iconCls : "icon-cancel",
									handler : function() {
										var is = $("#tab").datagrid(
												"getChecked");
										if (is.length > 0) {
											$.messager
													.confirm(
															"确认",
															"你真的要删除订单信息吗",
															function(r) {
																if (r) {
																	$
																			.ajax({
																				data : {
																					total : is.length,
																					data : is
																				},
																				url : "/WebDyanmic/getorder.do?type=delete",
																				type : "post",
																				success : function(
																						data) {
																					if (data == "1") {
																						$.messager
																								.alert(
																										"厉害",
																										"顽固污渍成功清楚");
																						$(
																								"#tab")
																								.datagrid(
																										"reload");
																					} else {
																						$.messager
																								.alert(
																										"抱歉",
																										"删不了",
																										"info");
																					}
																				}
																			})
																}
															})
										} else {
											$.messager.alert("出错",
													"还没有选择删除的信息", "info")
										}
									}
								} ],
						columns : [ [ {
							field : "chk",
							checkbox : true
						}, {
							field : 'orderid',
							title : '订单编号',
							width : 250
						}, {
							field : 'totalprice',
							title : '总价',
							width : 50
						}, {
							field : 'orecipients',
							title : '收件人',
							width : 50
						}, {
							field : 'orderstate',
							title : '订单状态',
							width : 60
						}, {
							field : 'orderdata',
							title : '订单时间',
							width : 70
						}, {
							field : 'userid',
							title : '用户ID',
							hidden : true,
						}, {
							field : 'orderdetailid',
							title : '订单号ID',
							hidden : true,
						} ] ]
					})
}