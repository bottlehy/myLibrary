/**
 * 
 */
$(function() {
	$("#tab").datagrid({
		title : "商品分类",
		url : "/WebDyanmic/getcare.do",
		queryParams : {
			type : "list"
		},
		pagination : true,
		rownumbers : true,
		toolbar : [ {
			text : "添加",
			iconCls : "icon-add",
			handler : function() {
				$("#d1").dialog({
					closed:false,
					title:"添加",
					buttons:[{
						text:"保存",
						iconCls:"icon-save",
						handler:function(){
							var is=$("form").form("validate");
							if(is){
								$.ajax({
									data:$("form").serialize(),
									url:"/WebDyanmic/getcare.do?type=add",
									type:"post",
									success:function(data){
										if(data=="1"){
											$.messager.alert("成功","添加操作成功");
											$("form").form("reset");
											$("#d1").dialog("close");
											$("#tab").datagrid("reload");
										}else{
											$.messager.alert("失败","添加操作失败","info");
										}
									}
								})
							}else{
								$.messager.alert("失败","你自己造成的，还不快填对了","info");
							}
						}
					},{
						text:"取消",
						iconCls:"icon-cancel",
						hancler:function(){
							$("form").form("reset");
							$("#d1").dialog("close");
						}
					}]
				})
			}
		}, "-", {
			text : "修改",
			iconCls : "icon-edit",
			handler : function() {
				var select =$("#tab").datagrid("getSelected");
				if(select!=null){
					$("#d1").dialog({
						title:"修改",
						iconCls:"icon-edit",
						closed:false,
						buttons:[{
							text:"写入",
							iconCls:"icon-save",
							handler:function(){
								var is=$("form").form("validate");
								if(is){
									$.ajax({
										data:$("form").serialize(),
										url:"/WebDyanmic/getcare.do?type=update",
										type:"post",
										success:function(data){
											if(data=="1"){
												$.messager.alert("恭喜","你终于成功了");
												$("form").form("reset");
												$("#d1").dialog("close");
												$("#tab").datagrid("reload");
											}else{
												$.messager.alert("未知的失败","数据库写入失败","info");
											}
										}
									})
								}else{
									$.messager.alert("失误","数据没有填写正确","info");
								}
							}
						},{
							text:"取消",
							iconCls:"icon-cancel",
							handler:function(){
								$("form").form("reset");
								$("#d1").dialog("close");
							}
						}]
					})
					$("#cid").textbox("setValue",select.cid);
					$("#cname").textbox("setValue",select.cname);
				}else{
					$.messager.alert("失误","还没有选择要修改的行","info");
				}
			}
		}, "-",{
			text : "删除",
			iconCls : "icon-cancel",
			handler : function() {
				var select =$("#tab").datagrid("getChecked");
				console.log(select[0].cid);
				if(select!=null){
					$.messager.confirm("Are you sure?","你确定要执行删除操作",function(r){
						if(r){
							$.ajax({
								data:{total:select.length,data:select},
								url:"/WebDyanmic/getcare.do?type=delete",
								type:"post",
								success:function(data){
									if(data=="1"){
										$.messager.alert("删除成功","请欢呼");
										$("#tab").datagrid("reload");
									}else{
										$.messager.alert("未知错误","导致删除失败","info");
									}
								}
							})
						}
					})
				}
				else{
					$.messager.alert("操作失误","没有选择要删除的行","info");
				}
			}
		} ],
		columns : [ [ {
			field : "chk",
			checkbox : true
		}, {
			field : "cid",
			title : "分类",
			width : 100
		}, {
			field : "cname",
			title : "分类名",
			width : 100
		} ] ]
	})
	$("#d1").dialog({
		width:300,
		height:200,
		closed:true,
	})
})