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

				var content = 
					{"variables":
						{"productName" : {"value" : "冰箱", "type": "String"},
						 "productAmount" : {"value" : 300, "type": "Double"},
						 "productType" : {"value" : "家用", "type": "String"},
						 "customer" : {"value" : "zte", "type": "String"},
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

