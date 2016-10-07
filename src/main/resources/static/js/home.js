$(function() {
	$("#greeting").html($("#greeting").html() + "&nbsp;&nbsp;" + $.getGreetingTime()) ;

	$.loadFunction("/products");

    $("#tabs a").click(function () {  
        $(this).tab('show');  
    });  
    
    //ajax submit::http://www.cnblogs.com/nangong/archive/2013/07/23/3208302.html

});

jQuery.extend({
	loadFunction:function(gotoWhere) {
		var mainUrl = $.getRootPath() + gotoWhere+"/main .mainContent";
		$("#mainView").load(mainUrl);
	},
	
	load2:function(gototab){
		var mainUrl = $.getRootPath() + "/materials"+"/main .mainContent";
		$("#mainView").load(mainUrl);
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
	},
	
	getRootPath:function() {
        var curWwwPath = window.document.location.href;
        var pathName = window.document.location.pathname;
        var pos = curWwwPath.indexOf(pathName);
        var localhostPaht = curWwwPath.substring(0, pos);
        var projectName = pathName.substring(0, pathName.substr(1).lastIndexOf('/') + 1);
        return (localhostPaht + projectName);
    },
    
    gotoTAB:function(name) {
    	$("#tabs a").click(function (e) {  
            $(this).tab('show'); 
			
			var current = this.hash;
			var pos = current.indexOf("Page");
			var flag = current.substring(1,pos);
			current = "#add"+ flag.replace(/(\w)/,function(v){return v.toUpperCase()});
			$("#_add").attr("href",current);

			name = $("#_add").text().substr(0,2) + name;
			$("#_add").text(name);
			
			var isLoad = $(flag +"Flag").val();
			if(isLoad == 0){
				//alert("Loading data");
				$("#"+flag +"Flag").val("1")
				$.loadFunction("/"+flag+"s");
			}
			else {
				//alert("don't Load data");
			}
        }); 
    }
    
});

