

jQuery(document).ready(function() {
	jQuery("#displayDateSlide").dynDateTime({
		showsTime: true,
		ifFormat: "%d/%m/%Y %H:%M",
		daFormat: "%l;%M %p, %e %m,  %Y",
		align: "TL",
		electric: false,
		singleClick: false,
		displayArea: ".siblings('.dtcDisplayArea')",
		button: ".next()" //next sibling
	});
});
jQuery(document).ready(function() {
	jQuery("#expireDateSlide").dynDateTime({
		showsTime: true,
		ifFormat: "%d/%m/%Y %H:%M",
		daFormat: "%l;%M %p, %e %m,  %Y",
		align: "TL",
		electric: false,
		singleClick: false,
		displayArea: ".siblings('.dtcDisplayArea')",
		button: ".next()" //next sibling
	});
});
