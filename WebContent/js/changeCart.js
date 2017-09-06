function inputdata(data){
	var pa=$(data).parent();
	if($(data).val()==""){
		$(data).val(0);
	}
	var nodeAll=$(pa).prevAll();
	var nextpa=$(pa).next();
	var pre=$(nextpa).html();
	var lastpa=$(pa).prev();
	var num=$(data).val();
	var price=$(lastpa).html();
	$(nextpa).html(parseFloat(num)*parseFloat(price));
	var total=$("#f1").html();
	$("#f1").html(parseFloat(total)-parseFloat(pre)+parseFloat(num)*parseFloat(price));
	$.ajax({
		type:"get",
		url:"/WebDyanmic/change.do",
		data:{"gtitle":$(nodeAll[2]).html(),"number":$(data).val()}
	});
}
function removedata(data){
	var pa=$(data).parent().parent();
	var pt=$(data).parent();
	var pr=$(pt).prev();
	var nodeAll=$(pt).prevAll();
	var total=$("#f1").html();
	var price=parseFloat(total)-parseFloat($(pr).html());
	$("#f1").html(parseFloat(total)-parseFloat($(pr).html()));
	$.ajax({
		type:"get",
		url:"/WebDyanmic/remove.do",
		data:{"gtitle":$(nodeAll[4]).html()}
	});
	$(pa).remove();
}