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
	prepareNav : function(eName) {
		var hel = $("#hd_" + eName).parent();
		hel.addClass("selected");
	},
	navHome : function() {
		$.NAV.prepareNav("home");
	},
	navLife : function() {
		$.NAV.prepareNav("life");
	},
	navCareer : function() {
		$.NAV.prepareNav("career");
	},
	navContact : function() {
		$.NAV.prepareNav("contact");
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

	// prepare footer
	$("#ft_cyYear").html(new Date().getFullYear());
});