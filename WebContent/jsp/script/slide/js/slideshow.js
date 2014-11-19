
jQueryslideshow = {
    context: false,
    tabs: false,
    timeout: 8000,      // time before next slide appears (in ms)
    slideSpeed: 1000,   // time it takes to slide in each slide (in ms)
    tabSpeed: 500,      // time it takes to slide in each slide (in ms) when clicking through tabs
    fx: 'scrollLeft',   // the slide effect to use
    
    init: function() {
        // set the context to help speed up selectors/improve performance
        this.context = jQuery('#slideshow');
        
        // set tabs to current hard coded navigation items
        this.tabs = jQuery('ul.slides-nav li', this.context);
        
        // remove hard coded navigation items from DOM 
        // because they aren't hooked up to jQuery cycle
        this.tabs.remove();
        
        // prepare slideshow and jQuery cycle tabs
        this.prepareSlideshow();
    },
    
    prepareSlideshow: function() {
        // initialise the jquery cycle plugin -
        // for information on the options set below go to: 
        // http://malsup.com/jquery/cycle/options.html
    	if (jQuery('div.slides > ul') && (jQuery('div.slides > ul').children().length == 1)) {
    		jQuery('div.slides > ul :first').show();
    	}
    	
        jQuery('div.slides > ul', jQueryslideshow.context).cycle({
            fx: jQueryslideshow.fx,
            timeout: jQueryslideshow.timeout,
            speed: jQueryslideshow.slideSpeed,
            fastOnEvent: jQueryslideshow.tabSpeed,
            pager: jQuery('ul.slides-nav', jQueryslideshow.context),
            pagerAnchorBuilder: jQueryslideshow.prepareTabs,
            before: jQueryslideshow.activateTab,
            pauseOnPagerHover: true,
            pause: true
        });            
    },
    
    prepareTabs: function(i, slide) {
        // return markup from hardcoded tabs for use as jQuery cycle tabs
        // (attaches necessary jQuery cycle events to tabs)
        return jQueryslideshow.tabs.eq(i);
    },

    activateTab: function(currentSlide, nextSlide) {
        // get the active tab
        var activeTab = jQuery('a[href="#' + nextSlide.id + '"]', jQueryslideshow.context);
        
        // if there is an active tab
        if(activeTab.length) {
            // remove active styling from all other tabs
            jQueryslideshow.tabs.removeClass('on');
            
            // add active styling to active button
            activeTab.parent().addClass('on');
        }            
    }            
};


jQuery(function() {
    // add a 'js' class to the body
    jQuery('body').addClass('js');
    
    // initialise the slideshow when the DOM is ready
    jQueryslideshow.init();
});  