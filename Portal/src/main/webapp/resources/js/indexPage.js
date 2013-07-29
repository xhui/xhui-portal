(function($) {
	// $.fn.nav = $.fn.Nav = function(options) {
	//
	// $.jump = function(param) {
	// $(param).click();
	// };
	// };
})(jQuery);

$(document).ready(function() {
	// prepare slider.
	$('#coin-slider').coinslider({
		width : 880,
		height : 320
	});

	// prepare navigation
	$("#nav_icon_life").on("click", $.NAV.jumpToLife);
	$("#nav_icon_career").on("click", $.NAV.jumpToCareer);
	$("#nav_icon_contact").on("click", $.NAV.jumpToContact);
});