var lostName = '未发现'

$(function() {
	$("#greeting").html(
			$("#greeting").html() + "&nbsp;&nbsp;" + $.getGreetingTime());

	$.loadFunction("/plans");

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
			loadFunction : function(gotoWhere, second, para) {
				if(!second) {
					second = "/main";
				}
				var mainUrl = $.getRootPath() + gotoWhere
						+ second;
				
				if(para){
					mainUrl += para;
				}
				
				mainUrl += " .mainContent";
				
				$("#mainView").load(mainUrl,function(response,status,xhr){
					if(gotoWhere.indexOf("stocks")>0) {
						$.showPages();
					}
				});
//				if($("#loginForm")){
//					window.location.href=$.getRootPath()+"/logout";
//				}
			},
			
			logout : function(){
				window.location.href=$.getRootPath()+"/logout";
			},
			
			showPages : function() {
				//alert("test");
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
				var localhostPath = curWwwPath.substring(0, pos);
				
				//for tomcat
				//var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
				//return (localhostPath + projectName);
				
				//spring-boot
				return localhostPath;
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

					if (name) {
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

				var reqUrl = $.getRootPath() + "/api/" + module + "s/create";
				var errorTip = $("#errorTip");
				var modal = $("#" + module + "Modal");

				var content = form.serializeObject();  //form.serializeJSON();  
				if(module == "plan" && content.productAmount) {
					var prod = content.product;
					var planProducts = [];
					for(var i=0;i <prod.length;i++){
						var planProduct = { 
								product:0, productPrice:1.0, productAmount:1, productLength:1,productRemark:""
						}
						planProduct.product=prod[i];
						planProduct.productPrice=content.productPrice[i];
						planProduct.productAmount=content.productAmount[i];
						planProduct.productLength=content.productLength[i];
						planProduct.productRemark=content.productRemark[i];
						planProducts.push(planProduct);
					}
					content.planProducts=planProducts;
					//return;
				}
				$.sendAjaxReq("POST", reqUrl, content, function(data,
						textStatus) {
					modal.modal('hide');
					$.loadFunction("/" + module + "s");
					// var globalTip = $("#globalTip");
					// globalTip.show();
				}, function(xmlhttp) {
					var response = JSON.parse(xmlhttp.responseText);
					var ACCOUNT_CODE_DUPLICATION = 803;
					if(module=="account" && response.code == ACCOUNT_CODE_DUPLICATION){
						errorTip.text('添加失败哦! 原因：员工的工号已经存在。');
					}
					else {
						errorTip.text('添加失败哦! 可能原因：1、您不具有该操作权限； 2、数据格式不对或超长。');
					}
					errorTip.show();
				});

			},

			dataDelete : function(btn, module, id) {
				if (!window
						.confirm("警告：您即将删除一条记录，该操作不可撤销。\n单击“确定”继续删除动作。单击“取消”不删除。")) {
					return false;
				}

				var globalTip = $("#globalTip");
				var reqUrl = $.getRootPath() + "/api/" + module + "s/" + id + "/delete";

				$.sendAjaxReq("GET", reqUrl, "", function(data, textStatus) {
					// $.loadFunction("/" + module + "s"); //删除多行时需要重新读取服务端数据
					$(btn).parent().parent().remove();
					globalTip.text('删除记录成功!');
					globalTip.show();
				}, function(textStatus) {
					globalTip.text('删除失败! 可能原因：您不具有该操作权限。');
					globalTip.attr("class", "alert alert-danger");
					globalTip.show();
				});
			},

			sendAjaxReq : function(requestType, restUrl, content, tCallBack,
					fCallBack) {
				$.ajax({
					type : requestType,
					url : restUrl,
					data : JSON.stringify(content), 	//data : JSON.stringify(content),
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
			},

			newData : function(module,title){
				$.showModule(module,title);
				
				var values = $('#' + module + 'Form').serializeArray();  	
				for (index = 0; index < values.length; ++index) {  
					var id = values[index].name;
					if(id.indexOf("[") == -1) {
						$("#"+id).attr("readonly", false);
					}
				}  

			},
//================For Order Begin==========================			
			linkageCodeToStandard : function(){
				if($(".productItemIndex").length == 1) {	//仅当只有一个产品时才取数据
					if($("#product_code option").length == 1) {  	//仅当未进行过split操作
						var item = {
						        id:0,
						        code:"", 
						        standard:"", 
						    };
						var i=0,j=0,data = new Array(),code=new Array();
						$("#product_data option").each(function() { 
							var values= $(this).text().split(',');
							item.id=values[0];
							item.code=values[1];
							item.standard=values[2];
							data[i++] = item;
							if(j==0){
								code[j++] = values[1];
							}
							if(j>0 && code[j-1] != values[1]) {
								code[j++] = values[1];
							}
		
						});
						for(var i=0;i<code.length;i++){
							$("#product_code").append("<option>"+code[i]+"</option>"); 
						}
					}
				}
				
				$(".productItemIndex").each(function() {
					var standard = $(this).parent().parent().find("#product");
					$.emptyProductStandardInOrder(standard);
				});
				
			},
			
			linkageCodeSelected : function(self){
				var currentItem = $(self).parent().parent();
				var standard = currentItem.find("#product");
				$.emptyProductStandardInOrder(standard);

				var selectedCode = currentItem.find("#product_code").val();
				$("#product_data option").each(function() { 
					var values= $(this).text().split(',');
					if(values[1] == selectedCode) {
						standard.append("<option value='"+values[0]+"'>"+values[2]+"</option>"); 
					}
				});
			},
			
			emptyProductStandardInOrder : function(self){
				self.empty(); 
				self.append("<option value=''>规格</option>"); 
			},
			
			cloneProductItemInOrder : function() {
				var todoItemNum = $('#productNumInOrder').val();
				var currentItemNum = $(".productItemIndex").length;

				if(todoItemNum < 1){
					alert('亲，订单中至少需要包含一款产品。');
					$('#productNumInOrder').val(currentItemNum);
					return;
				}
				
				if(todoItemNum>currentItemNum){	//clone
					for(var i=currentItemNum;i<todoItemNum;i++){
						$.cloneOneProductItemInOrder();
					};
				}
				else if(todoItemNum<currentItemNum){//remove
					for(var i=currentItemNum;i>todoItemNum;i--){
						$('#productItem').remove();
					}
				}
				
				
				$.numberingProductItemInOrder();
			},
			
			cloneOneProductItemInOrder : function() {
				var srcItem = $('#productItem');
				var newItem = srcItem.clone(true);
				srcItem.before(newItem);

				newItem.find("#productPrice").val("");
				newItem.find("#productAmount").val("");
				newItem.find("#productLength").val("");
				newItem.find("#productRemark").val("");
				$.emptyProductStandardInOrder(newItem.find("#product"));
				
			},
			
			removeProductItemInOrder : function(self){
				if($(".productItemIndex").length == 1) {
					alert('亲，不能删除了，订单中至少需要包含一款产品。');
					return;
				}
				$(self).parent().parent().remove();
				
				var currentItemNum = $(".productItemIndex").length;
				$('#productNumInOrder').val(currentItemNum);
				
				$.numberingProductItemInOrder();
			},
			
			numberingProductItemInOrder : function(){
				var index = 1;
				$(".productItemIndex").each(function() {
					$(this).html(index ++);
				});
			},
			
			openNewOrder : function() {
				$.linkageCodeToStandard();
				$.newData('plan','新增订单');
				$.setPlanDisabled(false);
				var currentItemNum = $(".productItemIndex").length;
				$('#productNumInOrder').val(currentItemNum);
				
				$('#productNumInOrder').blur(function(){
					$.cloneProductItemInOrder();
				});
			},
			
			createNewOrder : function(){
				var isSave = true;
				$(".productItemIndex").each(function() {
					var standard = $(this).parent().parent().find("#product");
					if(standard.val() == '') {
						isSave = false;
						return false;	//break:return false; continue:return true;
					}
				});
				if(isSave){
					$.dataSave('plan');
				}
				else{
					alert('请选择规格。所有产品的代码和规格都要选择，不能为空');
				}
			},
//================For Order End==========================			
			
			showModule : function(module, title) {
				$("#status1").attr("checked", false);
				$("#status2").attr("checked", false);

				$('#errorTip').hide();
				$('#dataSave').show();
				$('#currentTitle').html(title);
				$('#' + module + "Form")[0].reset();
				$('#id').val("");

				$('#' + module + 'Modal').modal('show');
				
				if(module == 'account'){
					$('#pwdLine').show();
				}
				else if(module == 'plan'){
					$('#workflow').hide();
					$('#wf_node').html("");
					$('#content').val("DRIFTING");
				}
				
			},

			editData : function(btn, module, moduleId, readOnly) {
				var title = $(btn).html();

				$.showModule(module, "当前操作--" + title);

				
				var tip = $("#errorTip");
				var reqUrl = $.getRootPath() + "/api/" + module + "s/" + moduleId;

				$.sendAjaxReq("GET", reqUrl, "", function(data, textStatus) {
					if(data.length<1){
						tip.text('对不起，不能发现对应的记录!');
						tip.show();
						$('#dataSave').hide();
					}
					
					$.bindFormValue(module,data,readOnly);
					
				}, function(textStatus) {
					tip.text('对不起，不能发现对应的记录!');
					tip.show();
					$('#dataSave').hide();
				});

			},
			
			editSelf : function() {
				$('#selfSave').show();
				$('#selfForm')[0].reset();
				$('#selfModal').modal('show');
				
				var tip = $("#selfErrorTip");
				tip.hide();
				var reqUrl = $.getRootPath() + "/api/accounts/-8";
				$.sendAjaxReq("GET", reqUrl, "", function(data, textStatus) {
					if(data.length<1){
						tip.text('对不起，不能发现对应的记录!');
						tip.show();
						$('#selfSave').hide();
					}
					
					$.bindSelfValue('account',data,false);
					
				}, function(textStatus) {
					tip.text('对不起，不能发现对应的记录!');
					tip.show();
					$('#selfSave').hide();
				});				
			},
			
			selfSave : function() {
				var form = $('#selfForm');
				if (!form.valid()) {
					return false;
				}

				var reqUrl = $.getRootPath() + "/api/accounts/create";
				var errorTip = $("#selfErrorTip");
				var modal = $("#selfModal");

				var content = form.serializeObject();
				$.sendAjaxReq("POST", reqUrl, content, function(data,
						textStatus) {
					alert("修改个人信息成功!")
					modal.modal('hide');					
				}, function(xmlhttp) {
					errorTip.text('添加失败哦! 可能原因：数据格式不对或超长。');
					
					errorTip.show();
				});

			},
			
			search : function(module, second){
				if(!second) {
					second = "/main";
				}
				
				var form = $('#searchForm');
				var value1 = $.trim($("#search_1").val());
				var value2 = $.trim($("#search_2").val());
				if(value1.length == 0 && value2.length == 0) {
					alert('您没有输入要查询的数据');
					return false;
				}
				var name1= $("#search_1").attr("name");
				var name2= $("#search_2").attr("name");
				var para = "?"+name1+"="+value1+"&"+name2+"="+value2;
				//alert('module:'+module+'; para:'+para);
				$.loadFunction(module, second, para);
			},
			
			editPwd : function() {
				var form = $('#editPwdForm');
				if (!form.valid()) {
					return false;
				}
				var reqUrl = $.getRootPath() + "/api/accounts/edit_password";
				var errorTip = $("#pwdErrorTip");

				var content = form.serializeObject();
				$.sendAjaxReq("POST", reqUrl, content, function(data,
						textStatus) {
					alert("修改密码成功!需要重新登陆才能生效!")
					$("#editPwdModal").modal('hide');
				}, function(xmlhttp) {
					var response = JSON.parse(xmlhttp.responseText);
					var ACCOUNT_PASSWORD_INCORRECT = 903;
					if(response.code == ACCOUNT_PASSWORD_INCORRECT){
						errorTip.text('修改失败哦! 原因：旧密码输入错误。');
					}
					else {
						errorTip.text('修改失败哦! 可能原因：1、您不具有该操作权限； 2、数据格式不对或超长。');
					}
					errorTip.show();
				});
			},
			
			resetPassword : function(accountId){
				if(!confirm("重置后，该员工的密码会更新为默认的‘12345678’，您确认要继续吗？")){
					return;
				}
				
				var reqUrl = $.getRootPath() + "/api/accounts/edit_password";

				var tip = $("#globalTip");

				var content = {id:accountId, newPassword:"12345678"};
				$.sendAjaxReq("POST", reqUrl, content, function(data,
						textStatus) {
					tip.text("重置密码成功!")
					tip.show();
				}, function(xmlhttp) {
					tip.text('修改失败哦! 可能原因：1、您不具有该操作权限； ');
					tip.show();
				});

			},
			
			
			bindSelfValue : function(module,data,readOnly){
				names =["#self_id","#self_code","#self_name","#self_duty","#self_phone","#self_email","#self_family_addr","#self_remark","#self_password"];
				values =[data.id, data.code, data.name, data.duty, data.phone, data.email, data.family_addr, data.remark,data.password];
				
				$("#self_update").val(true);
				
				var rList = data.roleList;
				if(rList.length > 0){	//角色不修改，值要回传
					var roles = "<input type='hidden' id='self_roleList' name='roleList' value='-1'/> "; 
					for(var i=0;i<rList.length;i++) {
						roles += "<input type='hidden' id='self_roleList"+i+"' name='roleList' value='"+rList[i].id+"'/> ";
					}
					$("#self_roles").html(roles);
				}
				for(var i=0;i<names.length;i++) {
					$(names[i]).val(values[i]);
				}
			},
			
			bindFormValue : function(module,data,readOnly){
				var names,values;
				
				var salesman = data.salesman ? data.salesman.name : lostName;
				if(module == 'role'){
					names =["#id","#name","#priv","#remark"];
					values =[data.id, data.name,data.priv,data.remark];
				}
				else if(module == 'account'){
					names =["#id","#code","#name","#duty","#phone","#email","#family_addr","#remark","#password"];
					values =[data.id, data.code, data.name, data.duty, data.phone, data.email, data.family_addr, data.remark,data.password];

					$('#pwdLine').hide();
					$("#update").val(true);
					
					var rList = data.roleList;
					if(rList.length > 0){	//角色不修改，值要回传
						var roles = "<input type='hidden' id='roleList' name='roleList' value='-1'/> "; 
						for(var i=0;i<rList.length;i++) {
							roles += "<input type='hidden' id='roleList"+i+"' name='roleList' value='"+rList[i].id+"'/> ";
						}
						$("#roles").html(roles);
					}
					
				}
				else if(module == 'plan'){
					names = ["#id","#productName","#productAmount","#productType","#customer"];
					values = [data.id,data.productName,data.productAmount,data.productType,data.customer];
					
					var messages = data.messages;
					if(messages.length > 0){
						var wf = "";
						for(var i = 0;i < data.messages.length;i++){
							wf += "<li>"+messages[i].workflow+"</li> ";
						}
						$("#workflow").html(wf);
						$('#workflow').show();
					}
					
				}
				else if(module == 'customer') {
					names=["#id","#name","#address","#contacter","#phone","#remark","#salesman"];
					values=[data.id, data.name, data.address,data.contacter,data.phone,data.remark,salesman];
				}
				else if(module == 'supplier') {
					names=["#id","#code","#name","#address","#contacter","#phone","#remark","#salesman"];
					values=[data.id, data.code, data.name, data.address,data.contacter,data.phone,data.remark,salesman];
				}
				else if(module == 'product') {
					names=["#id","#code","#name","#standard","#type","#numStock","#numAlarm","#remark"];
					values=[data.id, data.code, data.name, data.standard,data.type,data.numStock,data.numAlarm, data.remark];
				}
				else if(module == 'material') {
					names=["#id","#code","#name","#standard","#gbCode","#type","#numStock","#numAlarm","#remark"];
					values=[data.id, data.code, data.name, data.standard,data.gbCode,data.type,data.numStock,data.numAlarm,data.remark];
				}
				
				var status = (data.status == '有效') ? "#status1" :"#status2";
				$(status).attr("checked","checked");

				for(var i=0;i<names.length;i++) {
					$(names[i]).val(values[i]);
					if(i>0){
						$(names[i]).attr("readonly", readOnly);
					}
				}
				
				if(readOnly){
					$('#dataSave').hide();
				}
				
				if(module == "account") {
					$("#code").attr("readonly", true);
				}

			},
			
			showDrawingImage : function(drawingId) {
				var module = "drawing";
				$('#' + module + 'Modal').modal('show');
				var reqUrl = $.getRootPath() + "/api/" + module + "s/" + drawingId;

				$.sendAjaxReq("GET", reqUrl, "", function(data, textStatus) {
					if(data.length<1){
						tip.text('对不起，不能发现对应的记录!');
						tip.show();
						$('#dataSave').hide();
					}
					
					$("#name").html(data.name);
					$("#designer").html(data.designer ? data.designer.name : lostName);
					$("#date").html(new Date(data.date).Format("yy年MM月dd日hh:mm"));
					$("#remark").html(data.remark);
					
			        var result = document.getElementById("drwImg");
			        var type="image/jpeg";
			        var src = "data:" + type + ";base64," + data.drwImg;
			        result.innerHTML = '<img src ="'+src+'"/>';
				
				}, function(textStatus) {
					tip.text('对不起，不能发现对应的记录!');
					tip.show();
					$('#dataSave').hide();
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

Date.prototype.Format = function (fmt) { 
    var o = {
        "M+": this.getMonth() + 1, // 月份
        "d+": this.getDate(), // 日
        "h+": this.getHours(), // 小时
        "m+": this.getMinutes(), // 分
        "s+": this.getSeconds(), // 秒
        "q+": Math.floor((this.getMonth() + 3) / 3), // 季度
        "S": this.getMilliseconds() // 毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};