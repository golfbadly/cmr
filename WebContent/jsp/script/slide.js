function goToURL(url) {
	document.location =url; 
	}

function deleteSlideShowUpload(uploadId){

	var alertDelete = jQuery("#alertDelete").val();
	var r = confirm(alertDelete);

	if(r==true){
		setDeleteImg(uploadId);
	}else{
		return false;
	}
}

function deleteSlide(url){
	var alertDelete = jQuery("#alertDelete").val();
	var r = confirm(alertDelete);

	if(r==true){
		document.location =url;
	}else{
		return false;
	}
	
}