(function($) {
	// $.fn.nav = $.fn.Nav = function(options) {
	//
	// $.jump = function(param) {
	// $(param).click();
	// };
	// };
})(jQuery);

$(document).ready(function() {
	// prepare navigation
	$("#nav_icon_life").on("click", $.NAV.jumpToLife);
	$("#nav_icon_career").on("click", $.NAV.jumpToCareer);
	$("#nav_icon_contact").on("click", $.NAV.jumpToContact);

	$.NAV.navHome();
});