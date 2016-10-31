jQuery.extend({
	getSelectedRoles : function() {
		var zTree = $.fn.zTree.getZTreeObj("roleTree");
		var nodes = zTree.getCheckedNodes(true);
		var ids = "";
		if(nodes){
			for(var i in nodes){
				if(nodes[i].id != -1){
					ids += nodes[i].id +",";
				}
			}
		}
		var reqUrl = "/api/accounts/set_roles/"+$("#user_id").val()+"/"+ids;
		$.sendAjaxReq("GET", reqUrl, "",
				function(data,textStatus){
					//var globalTip = $("#globalTip");
					//globalTip.text('用户角色设置成功!');
					//globalTip.show();
					
					$("#setRoleModal").modal('hide');
					$.loadFunction("/accounts");
				},
				function(textStatus){
					var errorRoleTip = $("#errorRoleTip");
					errorRoleTip.text('用户角色设置失败!');
					errorRoleTip.attr("class", "alert alert-danger");
					errorRoleTip.show();
				}
			);
	},

	setRole : function(userId){
		var setting = {
			check: {
				enable: true,
				chkStyle: "checkbox",
				chkboxType: { "Y": "ps", "N": "ps" }
			},
			data: {
				simpleData: {
					enable: true
				}
			}
		};

		/*var zNodes =[
			{ id:-1, pId:0, name:"请选择角色", remark:"测试数据", open:false},
			{ id:10, pId:-1, name:"角色 1",  remark:"测试数据", open:false},
			{ id:11, pId:10, name:"角色 1-1", remark:"测试数据", open:true},
			{ id:111, pId:11, name:"角色 1-1-1", remark:"测试数据"},
			{ id:112, pId:11, name:"角色 1-1-2", remark:"测试数据"},
			{ id:12, pId:10, name:"角色 1-2", remark:"测试数据", open:true},
			{ id:121, pId:12, name:"角色 1-2-1", remark:"测试数据"},
			{ id:122, pId:12, name:"角色 1-2-2", remark:"测试数据"},
			{ id:20, pId:-1, name:"角色 2", remark:"测试数据", checked:true, open:true},
			{ id:21, pId:20, name:"角色 2-1", remark:"测试数据"},
			{ id:22, pId:20, name:"角色 2-2", remark:"测试数据", open:true},
			{ id:221, pId:22, name:"角色 2-2-1", remark:"测试数据", checked:true},
			{ id:222, pId:22, name:"角色 2-2-2", remark:"测试数据"},
			{ id:23, pId:20, name:"角色 2-3", remark:"测试数据"}
		];*/

		$('#setRoleModal').modal('show');	
		$("#user_id").val(userId);  
		//$.fn.zTree.init($("#roleTree"), setting, zNodes);  //For debug
		
		var module="role";
		var reqUrl = "/api/"+module+"s/tree/"+userId;
		$.sendAjaxReq("GET", reqUrl, "",
				function(data,textStatus){
					$.fn.zTree.init($("#roleTree"), setting, data);
				},
				function(textStatus){
					var errorRoleTip = $("#errorRoleTip");
					errorRoleTip.text('加载角色资源失败!');
					errorRoleTip.attr("class", "alert alert-danger");
					errorRoleTip.show();
				}
			);

	}

	
});