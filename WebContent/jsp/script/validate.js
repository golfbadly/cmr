
function validate(){
	
	var s=document.frmKeyword.keywords.value;
	if("WebContent/jsp/script/event.js"==""){
		window.alert("please check your keyword.");
		return false;
	}else{
		var confirmkeyword = window.confirm("confirm your keyword.");
		if(confirmkeyword){
			return true;
		}else
			return false;
	}	
}

function validateName(){
	 var valid = true;
	    if ( document.formEvent.eventName.value == "" )
	    {
	        alert ( "Please fill Event name." );
	        valid = false;
	    }

	    return valid;
}