var loadBpmn = true;

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
                if(loadBpmn) {
                    showBpmn(window.BpmnJS);
                    loadBpmn = false;
                }
                $("#bpmnModal").modal("show");
                //$('#bpmnModal').modal({backdrop: 'static', keyboard: false});
			},
			
			removeBpmn : function() {
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

function showBpmn(BpmnViewer){
  // create viewer
  var bpmnViewer = new BpmnViewer({
    container: '#canvas'
  });


  // import function
  function importXML(xml) {

    // import diagram
    bpmnViewer.importXML(xml, function(err) {

      if (err) {
        return console.error('could not import BPMN 2.0 diagram', err);
      }

      var canvas = bpmnViewer.get('canvas'),
          overlays = bpmnViewer.get('overlays');


      // zoom to fit full viewport
      canvas.zoom('fit-viewport');

      // attach an overlay to a node
      overlays.add('approveOrder', 'note', {
        position: {
          bottom: 0,
          right: 0
        },
        html: '<div class="diagram-note">审核人：马飞 <br/>时间：9-10</div>'
      });
      overlays.add('StartDraftOrderEvent', 'note', {
        position: {
          bottom: 0,
          right: 0
        },
        html: '<div class="diagram-note">拟稿人：小丁</div>'
      });

      // add marker
      canvas.addMarker('approveOrder', 'needs-discussion');
      canvas.addMarker('StartDraftOrderEvent', 'needs-discussion');
    });
  }


  // load external diagram file via AJAX and import it
  //$.get('diagram.bpmn', importXML, 'text');
  $.get('/simple/product-orders.v1.bpmn', importXML, 'text');


};
