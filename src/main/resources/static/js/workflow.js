var bpmnViewer;

jQuery
		.extend({
			camundaSave : function(module) {
				var form = $('#' + module + 'Form');
				if (!form.valid()) {
					return false;
				}

				var reqUrl = "http://localhost:8080/engine-rest/process-definition/key/planOrders/submit-form";
				var errorTip = $("#errorTip");
				var modal = $("#" + module + "Modal");

				var productId = $("#productId").val();
				var content = 
					{"variables":
						{"productId" : {"value" : productId, "type": "Long"},
						 "productName" : {"value" : $("#productName").val(), "type": "String"},
						 "productAmount" : {"value" : $("#productAmount").val(), "type": "Double"},
						 "productType" : {"value" : $("#productType").val(), "type": "String"},
						 "customer" : {"value" : $("#customer").val(), "type": "String"},
						},
				     "businessKey" : "rest-so-order"
                    };
				$.sendAjaxReq("POST", reqUrl, content, function(data,
						textStatus) {
					modal.modal('hide');
					$.loadFunction("/" + module + "s");
				}, function() {
					errorTip.text('添加失败哦');
					errorTip.show();
				});

			},
			
			readPlan : function(btn, module, moduleId, readOnly) {
				var title = $(btn).html();

				$.showModule(module, "当前操作--" + title);

				
				var tip = $("#errorTip");
				var reqUrl = $.getRootPath() + "/api/" + module + "s/" + moduleId;

				$.sendAjaxReq("GET", reqUrl, "", 
						function(data, textStatus) {
							if(data.length<1){
								tip.text('对不起，不能发现对应的记录!');
								tip.show();
								$('#dataSave').hide();
							}
							
							$.bindPlanValue(module,data,readOnly);
							
						}, function(textStatus) {
							tip.text('对不起，不能发现对应的记录!');
							tip.show();
							$('#dataSave').hide();
						});
			},
			
            viewBpmn : function(planId) {
				//var mainUrl = $.getRootPath() + "/plans/" + planId + "/bpmn  .mainContent";
				//$("#mainView").load(mainUrl);
				//window.location.href = $.getRootPath() + "/plans/" + planId + "/bpmn"; 
				
                 showBpmn(window.BpmnJS,planId);
                 $("#bpmnModal").modal("show");
                //$('#bpmnModal').modal({backdrop: 'static', keyboard: false});
			},
			
			removeBpmn : function() {
				if(bpmnViewer){
					bpmnViewer.destroy();
				}
                $("#canvas").val("");
                $("#bpmnModal").modal("hide");
			},
			
			auditOrder : function(btn, module, moduleId, readOnly) {
				$.readPlan(btn,module, moduleId, readOnly);
				
				var next = "<label class='col-md-2 control-label' id='wf_node_lable'>审核订单</label>";
				next += "<div class='col-md-10'>";
				next += "<input type='radio' id=status1 name=status value='TO_PRODUCT' required='required'/>审核通过 ";
				next += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				next += "<input type='radio' id=status2 name=status value='REVIEW_ORDER'/>订单退回";
				next += "</div>";
				$('#wf_node').html(next);
				$('#wf_node').show();
			},
			
			reviewOrder : function(btn, module, moduleId, readOnly){
				alert('亲，回退订单暂时不能重新提交，功能正在开发中。。。');
			},
			
			planOrder : function(btn, module, moduleId, readOnly){
				$.readPlan(btn,module, moduleId, readOnly);
				
				var next = "<label class='col-md-2 control-label' id='wf_node_lable'>计划生产</label>";
				next += "<div class='col-md-10'>";
				next += "<input type='radio' id=status1 name=status value='PULL_MATERIAL' required='required'/>组织生产，生成原料出库单 ";
				next += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				next += "<input type='radio' id=status2 name=status value='PULL_PRODUCT'/>现货供应，生成产品出库单";
				next += "</div>";
				$('#wf_node').html(next);
				$('#wf_node').show();
			},
			
			bindPlanValue : function(module,data,readOnly){
				var names,values;
				names = ["#id","#productName","#productAmount","#productType","#customer","#content"];
				values = [data.id,data.productName,data.productAmount,data.productType,data.customer,data.status];
				
				var messages = data.messages;
				if(messages.length > 0){
					var wf = "";
					for(var i = 0;i < data.messages.length;i++){
						wf += "<li>"+messages[i].workflow+"</li> ";
					}
					$("#workflow").html(wf);
					$('#workflow').show();
				}
					

				for(var i=0;i<names.length;i++) {
					$(names[i]).val(values[i]);
					if(i>0){
						$(names[i]).attr("readonly", readOnly);
					}
				}
				
			}

		});

function showBpmn(BpmnViewer,planId){
  // create viewer
  bpmnViewer = new BpmnViewer({
    container: '#canvas'
  });


  // import function
  function importXML(xml) {

    // import diagram
    bpmnViewer.importXML(xml, function(err) {

      if (err) {
        //return console.error('could not import BPMN 2.0 diagram', err);
    	  return;
      }

      var canvas = bpmnViewer.get('canvas'),
          overlays = bpmnViewer.get('overlays');


      // zoom to fit full viewport
      canvas.zoom('fit-viewport');

	  /////////////////////////////////
		var tip = $("#errorTip");
		var reqUrl = $.getRootPath() + "/api/plans/"+planId+"/bpmn";
		
		$.sendAjaxReq("GET", reqUrl, "", 
				function(data, textStatus) {
					if(data.length<1){
						tip.text('对不起，不能发现对应的记录!');
						tip.show();
						return;
					}
		
					for(var i=0;i<data.length;i++){
						  var rd = new Date(data[i].receiveDate).Format("yyyy-MM-dd hh:mm:ss"); 
						  overlays.add(data[i].content, 'note', {
							position: {
							  bottom: 0,
							  right: 0
							},
							html: '<div class="diagram-note">操作人：'+data[i].sender.name+'</br>时间：'+rd+'</div>'
						  });
		
						  // add marker
						  canvas.addMarker(data[i].content, 'needs-discussion');
					}
					
				}, function(textStatus) {
					tip.text('对不起，不能发现对应的记录!');
					tip.show();
				});
			
	  ////////////////////////////////

    });
  }


  // load external diagram file via AJAX and import it
  //$.get('diagram.bpmn', importXML, 'text');
  $.get($.getRootPath()+'/simple/product-orders.v1.bpmn', importXML, 'text');

};

// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
// 例子： 
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18 
Date.prototype.Format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};