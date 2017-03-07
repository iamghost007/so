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
			
			readPlan : function(btn, module, moduleId, readOnly, bindToInput) {
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
							
							$.bindPlanValue(module,data,readOnly, bindToInput);
							
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
				next += "<input type='radio' id='status1' name='status' value='TO_PRODUCT' required='required'/>审核通过 ";
				next += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				next += "<input type='radio' id='status2' name='status' value='REVIEW_ORDER'/>订单退回";
				next += "</div>";
				$('#wf_node').html(next);
				$('#wf_node').show();
			},
			
			reviewOrder : function(btn, module, moduleId, readOnly){
				$.openNewOrder();
				$.readPlan(btn,module, moduleId, false, true);

				var next = "<label class='col-md-2 control-label' id='wf_node_lable'>重校订单</label>";
				next += "<div class='col-md-10'>";
				next += "<input type='radio' id='status1' name='status' value='APPROVE_ORDER' required='required'/>修改后，重新提交 ";
				next += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				next += "<input type='radio' id='status2' name='status' value='PLAN_OVER'/>不提交，订单作废";
				next += "</div>";
				$('#wf_node').html(next);
				$('#wf_node').show();
			},
			
			planOrder : function(btn, module, moduleId, readOnly){
				$.readPlan(btn,module, moduleId, readOnly);
				
				var next = "<label class='col-md-2 control-label' id='wf_node_lable'>计划生产</label>";
				next += "<div class='col-md-10'>";
				next += "<input type='radio' id='status1' name='status' value='PULL_MATERIAL' required='required'/>组织生产，生成原料出库单 ";
				next += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				next += "<input type='radio' id='status2' name='status' value='PULL_PRODUCT'/>现货供应，生成产品出库单";
				next += "</div>";
				$('#wf_node').html(next);
				$('#wf_node').show();
			},
			
			overOrder : function(btn, module, moduleId, readOnly){
				$.readPlan(btn,module, moduleId, readOnly);
				
				var next = "<label class='col-md-2 control-label' id='wf_node_lable'>关闭订单</label>";
				next += "<div class='col-md-10'>";
				next += "<input type='radio' id='status1' name='status' value='PLAN_OVER' required='required'/>关闭订单，即使没有全部生产完成。选中表示确认关闭。";
				next += "</div>";
				$('#wf_node').html(next);
				$('#wf_node').show();
			},
			
			batchToProduct : function() {
				var tip = $("#errorBatchTip");
				var module = 'plan';
				var reqUrl = $.getRootPath() + "/api/" + module + "s/batch_to_product";

				$.sendAjaxReq("GET", reqUrl, "", 
						function(data, textStatus) {
							$('#orderList').html('');
							if(data.length<1){
								tip.text('对不起，不能发现对应的记录!');
								tip.show();
								$('#batchSave').hide();
							}
							
							if(data.length > 0){
			                    var values = "";
								for(var i = 0; i<data.length; i++) {
			                    	values += "<label><input name='planIds' type='checkbox' value='"+data[i].id+","+data[i].sponsor.id+"'";
			                    	if(i==0){
			                    		values += " required='required' ";
			                    	}
			                    	values += ">"+ data[i].name+ " , " + data[i].orderType +" , "+ new Date(data[i].orderDate).Format("yy年MM月dd日hh:mm") +"</label><br/>";
								}
								$('#orderList').html(values);
							}
							
						}, function(textStatus) {
							tip.text('对不起，不能发现对应的记录!');
							tip.show();
							$('#dataSave').hide();
						});
				
				$('#batchModal').modal('show');

			},
			
			batchSave : function() {
				var module = "plan";
				var form = $('#batchForm');
				if (!form.valid()) {
					return false;
				}

				var reqUrl = $.getRootPath() + "/api/" + module + "s/save/batch_to_product";
				var errorTip = $("#errorTip");
				var modal = $("#" + module + "Modal");

				var content = form.serializeObject();
				$.sendAjaxReq("POST", reqUrl, content, function(data,
						textStatus) {
					modal.modal('hide');
					$.loadFunction("/" + module + "s");
				}, function(xmlhttp) {
					errorTip.text('添加失败哦! 可能原因：1、您不具有该操作权限； 2、数据格式不对或超长。');
					errorTip.show();
				});

			},
			
			bindPlanValue : function(module,data,readOnly, bindToInput){
				var names,values;
				var salesmanid = data.salesman ? data.salesman.id : '';
				var customerid = data.customer ? data.customer.id : '';
				names = ["#id","#name","#salesman","#customer","#productNumInOrder","#remark","#content"];
				values = [data.id,data.name,salesmanid,customerid,data.productNumInOrder, data.remark, data.status];
				
				var messages = data.messages;
				if(messages.length > 0){
					var wf = "";
					for(var i = 0;i < data.messages.length;i++){
						wf += "<li>"+messages[i].workflow+"</li> ";
					}
					$("#workflow").html(wf);
					$('#workflow').show();
				}
				
				var currentItemNum = $(".productItemIndex").length;
				if(currentItemNum > 1) {	//only remain one
					for(var i=0;i<currentItemNum-1;i++){
						$('#productItem').remove();
					}
				}

				for(var i=0;i<data.productNumInOrder;i++){
					var productItem = $("#productItem");

					if(i>0){
						var newItem = productItem.clone(true);
						productItem.before(newItem);
						productItem = newItem;
					}
					var planProduct = data.planProducts[i];
					if(bindToInput){	//
						var productCode = productItem.find("#product_code");
						productCode.val(planProduct.product.code);
						$.linkageCodeSelected(productCode);
						productItem.find("#product").val(planProduct.product.id);
						productItem.find("#productPrice").val(planProduct.productPrice);
						productItem.find("#productAmount").val(planProduct.productAmount);
						productItem.find("#productLength").val(planProduct.productLength);
						productItem.find("#productRemark").val(planProduct.productRemark);
						
					}
					else {
						productItem.find("#product").html("代号:"+planProduct.product.code+"，规格:"+planProduct.product.standard+",");
						productItem.find("#productPrice").html("价格:"+planProduct.productPrice+",");
						productItem.find("#productAmount").html("数量:"+planProduct.productAmount+",");
						productItem.find("#productLength").html("长度:"+planProduct.productLength+",");
						productItem.find("#productRemark").val(planProduct.productRemark);
						productItem.find("#productRemark").attr("readonly",true);
					}

				}
				
				$.numberingProductItemInOrder();
					

				var orderType = (data.orderType == 'SALE') ? "#orderType1" :"#orderType2";
				$(orderType).attr("checked","checked");
				
				for(var i=0;i<names.length;i++) {
					$(names[i]).val(values[i]);
					if(i>0){
						$(names[i]).attr("readonly", readOnly);
					}
				}
				if(readOnly){
					$.setPlanDisabled("disabled");
				}
				
			},
			
			setPlanDisabled : function(isDisabled){
				$("#orderType1").attr("disabled",isDisabled);
				$("#orderType2").attr("disabled",isDisabled);
				$("#salesman").attr("disabled",isDisabled);
				$("#customer").attr("disabled",isDisabled);
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
					canvas.addMarker(data[0].status, 'currentNode');
					
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

