$(function() {
	$("#greeting").html(
			$("#greeting").html() + "&nbsp;&nbsp;" + $.getGreetingTime());

	$.loadFunction("/products");

	$("#tabs a").click(function() {
		$(this).tab('show');
	});

});

$.fn.serializeObject = function() {
	var o = {};
	var a = this.serializeArray();
	$.each(a, function() {
		if (o[this.name]) {
			if (!o[this.name].push) {
				o[this.name] = [ o[this.name] ];
			}
			o[this.name].push(this.value || '');
		} else {
			o[this.name] = this.value || '';
		}
	});
	return o;
};

jQuery
		.extend({
			loadFunction : function(gotoWhere) {
				var mainUrl = $.getRootPath() + gotoWhere
						+ "/main .mainContent";
				$("#mainView").load(mainUrl);
			},

			getGreetingTime : function() {
				var hour = new Date().getHours();
				var greeting = "";
				if (hour <= 6) {
					greeting = "现在是凌晨，注意休息。";
				} else if (hour <= 9) {
					greeting = "早上好！";
				} else if (hour <= 12) {
					greeting = "上午好！";
				} else if (hour <= 18) {
					greeting = "下午好！";
				} else if (hour <= 24) {
					greeting = "晚上好！";
				}
				return greeting;
			},

			getRootPath : function() {
				var curWwwPath = window.document.location.href;
				var pathName = window.document.location.pathname;
				var pos = curWwwPath.indexOf(pathName);
				var localhostPaht = curWwwPath.substring(0, pos);
				var projectName = pathName.substring(0, pathName.substr(1)
						.lastIndexOf('/') + 1);
				return (localhostPaht + projectName);
			},

			gotoTAB : function(name) {
				$("#tabs a").click(function(e) {
					$(this).tab('show');

					var current = this.hash;
					var pos = current.indexOf("Page");
					var flag = current.substring(1, pos);
					current = "#add" + flag.replace(/(\w)/, function(v) {
						return v.toUpperCase();
					});
					$("#_add").attr("href", current);

					if(name){
						name = $("#_add").text().substr(0, 2) + name;
						$("#_add").text(name);
					}

					var isLoad = $(flag + "Flag").val();
					if (isLoad == 0) {
						// alert("Loading data");
						$("#" + flag + "Flag").val("1");
						$.loadFunction("/" + flag + "s");
					} else {
						// alert("don't Load data");
					}
				});
			},

			dataSave : function(module) {
				var form = $('#' + module + 'Form');
				if (!form.valid()) {
					return false;
				}

				var reqUrl = "/api/" + module + "s/create";
				var errorTip = $("#errorTip");
				var modal = $("#" + module + "Modal");

				var content = form.serializeObject();
				$.sendAjaxReq("POST", reqUrl, content, 
						function(data,textStatus) {
							modal.modal('hide');
							$.loadFunction("/" + module + "s");
							//var globalTip = $("#globalTip");
							//globalTip.show();
						}, function() {
							errorTip.text('添加失败哦');
							errorTip.show();
				});

			},
			
			dataDelete : function(btn,module, id){
				if(!window.confirm("警告：您即将删除一条记录，该操作不可撤销。\n单击“确定”继续删除动作。单击“取消”不删除。")){
					return false;
				}
				
				var globalTip = $("#globalTip");
				var reqUrl = "/api/"+module+"s/"+id+"/delete";
				
				$.sendAjaxReq("GET", reqUrl, "",
						function(data,textStatus){
							//$.loadFunction("/" + module + "s");	//删除多行时需要重新读取服务端数据
							$(btn).parent().parent().remove();
							globalTip.text('删除记录成功!');
							globalTip.show();
						},
						function(textStatus){
							globalTip.text('删除失败!');
							globalTip.attr("class", "alert alert-danger");
							globalTip.show();
						}
					);
			},

			sendAjaxReq : function(requestType, restUrl, content, tCallBack,
					fCallBack) {
				$.ajax({
					type : requestType,
					url : restUrl,
					data : JSON.stringify(content),
					contentType : "application/json",
					success : function(data, textStatus) {
						if (tCallBack) {
							tCallBack(data, textStatus);
						}
					},
					error : function(xmlhttp, textStatus, errorThrown) {
						if (fCallBack) {
							fCallBack(xmlhttp, textStatus, errorThrown);
						}
					}
				});
			}

		});

function insertTable(module, content) {
	var tab = module + "Table";
	var trHtml = "<tr >";

	if (module == 'drawing') {
		trHtml += "<td>" + $('#name').val() + "</td>";
		trHtml += "<td>" + $('#designer').val() + "</td>";
		trHtml += "<td>2016-10-10</td>";
		trHtml += "<td>双门冰箱</td>";
		trHtml += "<td>" + $('#remark').val() + "</td>";
		trHtml += "<td><button type='button' class='btn btn-sm btn-info'>查看</button>";
		trHtml += " <button type='button' class='btn btn-sm btn-warning'>修改</button>";
		trHtml += " <button type='button' class='btn btn-sm btn-danger'>删除</button></td>";
	}
	trHtml += "</tr>"

	addTr(tab, 0, trHtml);
}