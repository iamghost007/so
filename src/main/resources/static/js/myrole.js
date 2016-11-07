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
		var reqUrl = $.getRootPath() + "/api/accounts/set_roles/"+$("#user_id").val()+"/"+ids;
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

		$('#setRoleModal').modal('show');	
		$("#user_id").val(userId);  
		//$.fn.zTree.init($("#roleTree"), setting, zNodes);  //For debug
		
		var module="role";
		var reqUrl = $.getRootPath() + "/api/"+module+"s/tree/"+userId;
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

	},
	
	setPriv : function(privs){
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

		var zNodes =[
			{ id:'root', pId:'nop', name:"请设置权限", open:true},
			{ id:'product', pId:'root', name:"产品管理",  open:false},
			{ id:'product:view', pId:'product', name:"查询"},
			{ id:'account', pId:'root', name:"用户管理", open:false},
			{ id:'account:view', pId:'account', name:"查询"},
			{ id:'account:edit', pId:'account', name:"编辑", checked:false},
			{ id:'role:define', pId:'account', name:"分配角色"},
			{ id:'role', pId:'root', name:"角色管理"},
			{ id:'role:view', pId:'role', name:"查询"},
			{ id:'role:edit', pId:'role', name:"编辑"},
			{ id:'plan', pId:'root', name:"生产计划"},
			{ id:'plan:view', pId:'plan', name:"查询所有计划"},
			{ id:'plan:create', pId:'plan', name:"创建订单"},
			{ id:'plan:audit', pId:'plan', name:"审核订单"},
			{ id:'plan:plan', pId:'plan', name:"订单转生产"},
			{ id:'stock:edit', pId:'root', name:"仓储"}
		];
		
		if(privs && privs.length >0){
			var curPrivs=privs.split(',');
			for(var j = 0;j < zNodes.length;j++) {
				for(var i = 0;i<curPrivs.length;i++){
					if(curPrivs[i] == zNodes[j].id) {
						zNodes[j].checked = true;
						for(var k=0;k<zNodes.length;k++) {
							if(zNodes[k].id == zNodes[j].pId) {
								zNodes[k].open = true;
							}
						}
					}
				}
			}
		}
			
		$.fn.zTree.init($("#privTree"), setting, zNodes);  //For debug
		
	},
	
	getSelectedPrivs : function() {
		var zTree = $.fn.zTree.getZTreeObj("privTree");
		var nodes = zTree.getCheckedNodes(true);
		var ids = "";
		if(nodes){
			for(var i in nodes){
				if(nodes[i].id.indexOf(':') != -1){
					ids += nodes[i].id +",";
				}
			}
			ids = ids.substring(0, ids.length-1);
		}
		$("#priv").val(ids);

	}
	
});