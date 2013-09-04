$(document).ready(function() {
	$(".MENU_LINK > a:first-child").click(function() {
		var cid = $(this).parent()[0].id;
		var r = new RegExp("subMenu_p([0-9]*)c([0-9]*)").exec(cid);
		if (r.length > 2) {
			$.NAV.buildPageNav(r[1], r[2]);
		}
	});
});