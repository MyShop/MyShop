function submitForm(action, formId, flag) {
	$.post(
					action,
					$("#" + formId).serialize(),
					function(json) {
						var array = new Array();
						if (json != null && json.length > 0) {
							for ( var i = 0; i < json.length; i++) {
								var o = new Object();
								o.filedName = json[i].filedName;
								o.errorMessage = json[i].errorMessage;
								array[i] = o;
							}
						}
						// clear errorMessage
						$("span").hide();

						if (array.length > 0) {
							for ( var i = 0; i < array.length; i++) {
								$("#" + array[i].filedName.replace(".", "")
										+ "_error")[0].innerHTML = array[i].errorMessage;
								$("#" + array[i].filedName.replace(".", "")
										+ "_error")[0].style.display = "block";
							}
						} else {
							$("#" + formId).submit();
						}
					}, "json");
}

function loadMenu() {
	rand = Math.random();
	$.ajax({
		type : "POST",
		url : "/IBS/loadMenu.xhtml?jsonid="+rand,// 提交页面
		beforeSend : function() { // 设置表单提交前方法
		},
		error : function(request) { // 设置表单提交出错
		},
		success : function(data) // 设置表单提交成功后的方法
		{
			list = data.menuList;
			if (list.length > 0) {
				function analysis(temp) {
					var tempHtml = "<ul>"

					for ( var i = 0; i < temp.length; i++) {
						tempHtml = tempHtml + "<li><a href='"+temp[i].url+"'>" + temp[i].name
								+ "</a>"
						if (temp[i].child != null && temp[i].child.length > 0) {
							tempHtml = tempHtml + analysis(temp[i].child);
						}
						tempHtml = tempHtml + "</li>";
					}
					tempHtml = tempHtml + "</ul>";
					return tempHtml;
				}
				innerhtml = analysis(list);

			} else {
				alert("对不起，加载数据失败");
			}
			$("#menu")[0].innerHTML = "<ul id='nav'>"
					+ innerhtml.substr(4, innerhtml.length - 4);
		}
	});
}