$.NAV = {
	_Prefix : "",
	jumpToIndex : function() {
		window.location.href = $.NAV._Prefix + "/index.xhtml?n=home";
	},
	jumpToLife : function() {
		window.location.href = $.NAV._Prefix + "/life.xhtml?n=life";
	},
	jumpToCareer : function() {
		window.location.href = $.NAV._Prefix + "/career.xhtml?n=career";
	},
	jumpToContact : function() {
		window.location.href = $.NAV._Prefix + "/contact.xhtml?n=contact";
	},
	prepareNav : function() {
		if (window.location.search.length <= 0) {
			return;
		}
		var _p = window.location.search.substring(1,
				window.location.search.length).split("&");
		for ( var _i = 0; _i < _p.length; _i++) {
			var _s = _p[_i].split("=");
			if (_s.length > 1 && _s[0] == "n") {
				var hid = "#hd_" + _s[1];
				var hel = $(hid);
				if (hel.length > 0) {
					hel.addClass("selected");
				}
				break;
			}
		}
	}
};
$.G = {

};
$(document).ready(function() {
	// prepare header navigation
	$("#hd_index").on("click", $.NAV.jumpToIndex);
	$("#hd_home").on("click", $.NAV.jumpToIndex);
	$("#hd_life").on("click", $.NAV.jumpToLife);
	$("#hd_career").on("click", $.NAV.jumpToCareer);
	$("#hd_contact").on("click", $.NAV.jumpToContact);

	$.NAV.prepareNav();
});