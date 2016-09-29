$(function() {
	$("#greeting").html($("#greeting").html() + "&nbsp;&nbsp;" + $.getGreetingTime()) ;

	$.loadFunction("/accounts");
/*
	var href = window.location.href;
	var pathname = window.location.pathname;
	var listPage = href.substring(0, href.length - pathname.length)
			+ "/accounts/main .accountMain";
	$("#mainView").load(listPage);
*/

});

jQuery.extend({
	loadFunction:function(gotoWhere) {
		var href = window.location.href;
		var pathname = window.location.pathname;
		var listPage = href.substring(0, href.length - pathname.length)
				+ gotoWhere+"/main .mainContent";
		$("#mainView").load(listPage);
	},

	getGreetingTime:function () {
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
	}
});

