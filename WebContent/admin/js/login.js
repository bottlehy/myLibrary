/**
 * 
 */
$(function(){
	$("#login").dialog({
		title:"登陆",
		width:350,
		height:210,
		minimizable:false,
		maximizable:false,
		iconCls:'icon-man',
		buttons:[{text:"登陆",
			iconCls:"icon-ok",
			handler:function(){
				var isValid=$("form").form("validate");
				if(isValid){
					$.ajax({
						type:"post",
						url:"/WebDyanmic/loginservlet.do",
						data:$("form").serialize(),
						success:function(data){
							if(data=="1"){
								window.location="index.html";
							}else{
								$.messager.alert('登陆失败','请检查账号与密码',"info");    
							}
						}
					})
				}else{
					$.messager.alert("验证失败","账号或密码输入错误","info");
				}
			}
		},{
			text:"取消",
			iconCls:"icon-no",
			handler:function(){
				window.close();
			}
		}
			
		]
	})
})