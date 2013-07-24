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
	$("#nav_icon_career").on("click", function(event) {
		$("#gbNav\\:career").trigger("click");
	});
	$("#nav_icon_life").on("click", function(event) {
		$("#gbNav\\:life").trigger("click");
	});
	$("#nav_icon_contact").on("click", function(event) {
		$("#gbNav\\:contact").trigger("click");
	});
});