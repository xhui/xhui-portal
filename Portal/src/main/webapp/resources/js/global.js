$.NAV = {
	_Prefix : "",
	jumpToIndex : function() {
		window.location.href = $.NAV._Prefix + "/index.xhtml";
	},
	jumpToLife : function() {
		window.location.href = $.NAV._Prefix + "/page.xhtml?pg=1";
	},
	jumpToCareer : function() {
		window.location.href = $.NAV._Prefix + "/page.xhtml?pg=2";
	},
	jumpToContact : function() {
		window.location.href = $.NAV._Prefix + "/contact.xhtml";
	},
	buildPageNav : function(pid, cid) {
		window.location.href = $.NAV._Prefix + "/page.xhtml?pg=" + pid
				+ "&cid=" + cid;
	},
	prepareNav : function() {
		var href = window.location.href;
		var navId = 'home';
		if (href.search('/page.xhtml[?]pg=1') > -1
				|| href.search('/document.xhtml[?]pg=1') > -1) {
			navId = 'life';
		} else if (href.search('/page.xhtml[?]pg=2') > -1
				|| href.search('/document.xhtml[?]pg=2') > -1) {
			navId = 'career';
		} else if (href.search('/contact.xhtml') > -1) {
			navId = 'contact';
		}
		var hel = $("#hd_" + navId).parent();
		hel.addClass("currentNav");
	}
};
$.XPT = {

};
$(document).ready(function() {
	// prepare footer
	$("#ft_cyYear").html(new Date().getFullYear());

	$.NAV.prepareNav();
});