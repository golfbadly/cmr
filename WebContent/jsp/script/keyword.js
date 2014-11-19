function showEditNote(){
	    if(jQuery("#noteText").attr('disabled')){
	    	jQuery("#noteText").removeAttr('disabled');
		    jQuery("#noteText").focus();
		    jQuery("#editButton").text("save");
	    }
	    else{
	    	jQuery("#editButton").text("edit");
	    	addKeyword();
	    	jQuery("#statusText").html("Saving...");
	    	jQuery("#noteText").attr('disabled',true);
	    }
	    
	}
	function addKeyword() {
		var eventDetailId = jQuery('#eventDetailId').val();
		var memberId = jQuery('#memberId').val();
		var eventId = jQuery('#eventId').val();
		var eventName = jQuery('#eventName').val();
		var keyword = jQuery("#noteText").val();
		
		console.log('keyword:'+keyword);
		
		
		 	new Ajax.Request('Inputkeyword.html', {
				method: 'post',
				parameters: {keyword:keyword, eventDetailId:eventDetailId, memberId:memberId, eventId:eventId, eventName:eventName},
				onSuccess: function(transport) {
					var data = transport.responseText;
					jQuery("#statusText").html("");
			    }
			});
		  
	}