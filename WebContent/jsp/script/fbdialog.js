function showInvite(eventId) {
	 FB.init({
		 appId:'1503306796600671', cookie:true,
		  //appId:'230570663783676', cookie:true, 
		  //ppId:'574643435941098', cookie:true, 
	      //appId:'105035129663321', cookie:true, 
	      status:true, xfbml:true 
	    });
	FB.ui({
        method: 'apprequests',
        message: 'Let play random keyword with me.',
        title: 'Let play random keyword with me.',
        data: eventId
    },
	function (response) {
        if (response && response.request_ids) {
           // run when invite user
        } else {
            // run when reject invite
        }
    });
    return false;
}

function validateName(eventName){

	if(eventName.value==""){
		alert("please fill your data.");
		return false;
	}else{
		var confirmdata = window.confirm("confirm your data.");
		if(confirmdata){
			return true;
		}else
			return false;
	}
}		