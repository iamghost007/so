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
	{ id:-1, pId:0, name:"请选择角色", open:true},
	{ id:10, pId:-1, name:"角色 1", open:true},
	{ id:11, pId:10, name:"角色 1-1", open:true},
	{ id:111, pId:11, name:"角色 1-1-1"},
	{ id:112, pId:11, name:"角色 1-1-2"},
	{ id:12, pId:10, name:"角色 1-2", open:true},
	{ id:121, pId:12, name:"角色 1-2-1"},
	{ id:122, pId:12, name:"角色 1-2-2"},
	{ id:20, pId:-1, name:"角色 2", checked:true, open:true},
	{ id:21, pId:20, name:"角色 2-1"},
	{ id:22, pId:20, name:"角色 2-2", open:true},
	{ id:221, pId:22, name:"角色 2-2-1", checked:true},
	{ id:222, pId:22, name:"角色 2-2-2"},
	{ id:23, pId:20, name:"角色 2-3"}
];

jQuery.extend({
	getSelectedRoles : function() {
		var zTree = $.fn.zTree.getZTreeObj("roleTree");
		var nodes = zTree.getCheckedNodes(true);
		var rolesets = new Array();
		if(nodes){
			for(var i in nodes){
				if(nodes[i].id != 0){
					rolesets[i] = nodes[i].id;
				}
			}
		}
		$("#selectedRoles").val(rolesets);  
	},

	setRole : function(userId){
		//var mainUrl = $.getRootPath() + "/accounts/roleTree";
		//$("#roleArea").load(mainUrl);
		$.fn.zTree.init($("#roleTree"), setting, zNodes);
		$('#setRoleModal').modal('show');	
	}

	
});