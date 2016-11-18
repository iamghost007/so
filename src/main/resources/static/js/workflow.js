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
				// var mainUrl = $.getRootPath() + "/plans/" + planId + "/bpmn
				// .mainContent";
				// $("#mainView").load(mainUrl);
				// window.location.href = $.getRootPath() + "/plans/" + planId +
				// "/bpmn";
				
                 showBpmn(window.BpmnJS,planId);
                 $("#bpmnModal").modal("show");
                // $('#bpmnModal').modal({backdrop: 'static', keyboard: false});
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
				$.readPlan(btn,module, moduleId, false);

				var next = "<label class='col-md-2 control-label' id='wf_node_lable'>重校订单</label>";
				next += "<div class='col-md-10'>";
				next += "<input type='radio' id=status1 name=status value='APPROVE_ORDER' required='required'/>修改后，重新提交 ";
				next += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				next += "<input type='radio' id=status2 name=status value='PLAN_OVER'/>不提交，订单作废";
				next += "</div>";
				$('#wf_node').html(next);
				$('#wf_node').show();
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
				names = ["#id","#salesman","#product","#productAmount","#productLength","#customer","#remark","#content"];
				values = [data.id,data.salesman.id,data.product.id,data.productAmount,data.productLength,data.customer,data.remark, data.status];
				
				var messages = data.messages;
				if(messages.length > 0){
					var wf = "";
					for(var i = 0;i < data.messages.length;i++){
						wf += "<li>"+messages[i].workflow+"</li> ";
					}
					$("#workflow").html(wf);
					$('#workflow').show();
				}
					

				var orderType = (data.orderType == 'SALE') ? "#orderType1" :"#orderType2";
				$(orderType).attr("checked","checked");
				for(var i=0;i<names.length;i++) {
					$(names[i]).val(values[i]);
					if(i>0){
						$(names[i]).attr("readonly", readOnly);
					}
				}
				if(readOnly){
					$("#orderType1").attr("disabled","disabled");
					$("#orderType2").attr("disabled","disabled");
					$("#salesman").attr("disabled","disabled");
					$("#product").attr("disabled","disabled");
				}
				
			},
			
			removePlanDisabled : function(){
				$("#orderType1").attr("disabled",false);
				$("#orderType2").attr("disabled",false);
				$("#salesman").attr("disabled",false);
				$("#product").attr("disabled",false);
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
        // return console.error('could not import BPMN 2.0 diagram', err);
    	  return;
      }

      var canvas = bpmnViewer.get('canvas'),
          overlays = bpmnViewer.get('overlays');


      // zoom to fit full viewport
      canvas.zoom('fit-viewport');

	  // ///////////////////////////////
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
						  var rd = new Date(data[i].receiveDate).Format("yy年MM月dd日hh:mm"); 
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
			
	  // //////////////////////////////

    });
  }


  // load external diagram file via AJAX and import it
  // $.get('diagram.bpmn', importXML, 'text');
  $.get($.getRootPath()+'/simple/product-orders.v1.bpmn', importXML, 'text');

};

