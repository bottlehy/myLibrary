window.onload=function(){
		document.querySelector("#imgchange").onclick=function(){
			this.setAttribute("src","");
			this.setAttribute("src","/WebDyanmic/imageServlet.do?tm="+Math.random());
		}
		document.querySelector("#check_user").onclick=function(){
			var uname=document.querySelector("#uname").value;
			var xmlhttp=new XMLHttpRequest();
			xmlhttp.open("POST","/WebDyanmic/check.do",true);
			xmlhttp.scriptCharset="UTF-8";
			var data="uname="+uname;
			console.log(data);
			xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
			xmlhttp.send(data);
			xmlhttp.onreadystatechange=function(){
			if(xmlhttp.readyState==4){
				if(xmlhttp.status==200){
					var data=xmlhttp.responseText;
					var sp=document.querySelector("#sp");
					if(data=="1")
					{
						sp.innerHTML="用户已存在";
						sp.style.color="red";
					}else{
						sp.innerHTML="用户不存在";
						sp.style.color="green";
					}
				}
			}
		}
	}
}