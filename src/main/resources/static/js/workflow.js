jQuery
		.extend({
			planSave : function(module) {
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

		});

