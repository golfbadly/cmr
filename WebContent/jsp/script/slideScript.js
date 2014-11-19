
var extension;

function submitForm1(button) {

	var status = true;
	var fileElement = document.getElementById("image").value;
	var url = document.getElementById("url").value;

	var expire = jQuery("#expireDateSlide").val();
	var display = jQuery("#displayDateSlide").val();
	var displayNow = jQuery("#displayCurrent").val();
	var pathImage = jQuery("#pathImage").val();
	var type = jQuery("#type").val();
	
	var actionSave = jQuery("#actionSave").val();
	var actionPreview = jQuery("#actionPreview").val();
	var form_url = jQuery("#frmAddSlide").attr("action");

	
	var currentTime = Date.parseString(displayNow, 'dd/MM/yyyy HH:mm');
	var startDisplayDate = Date.parseString(display, 'dd/MM/yyyy HH:mm');
	var expireDisplayDate = Date.parseString(expire, 'dd/MM/yyyy HH:mm');
	
	if (type == "") {
		if (pathImage == "") {
			if (fileElement == "") {
				var alertSelectImage = jQuery("#alertSelectImage").val();
				jQuery("#imageMessage").html(alertSelectImage);
				status = false;
			} else {
				jQuery("#imageMessage").html("");
			}
		}
	}
	
	// check url
	if (url == "" || url == null) {
		var errorMessage = jQuery("#emptyTitle").val();
		jQuery("#newstitle").html(errorMessage);
		status = false;
	} else {
		if (url.length > 1000) {
			var errorMessage = jQuery("#oversizeTitle").val();
			jQuery("#newstitle").html(errorMessage);
			status = false;
		} else {
			jQuery("#newstitle").html("");
		}
	}


	// check date
	if (startDisplayDate >= expireDisplayDate) {
		jQuery("#dateMessage").show();
		status = false;
	} else {
		jQuery("#dateMessage").hide();
	}
	if (status) {
		if(button == "preview"){
			jQuery("#frmAddSlide").attr("action", actionPreview);
			jQuery("#frmAddSlide").attr("target", "_blank");
			jQuery("#frmAddSlide").submit();
		}else{
			jQuery("#frmAddSlide").attr("action", actionSave);
			jQuery("#frmAddSlide").attr("target", "_top");
			jQuery("#frmAddSlide").submit();
		}
		
		
	}
}

function isMainImage1(inputDiv){
    extension = getFileType(inputDiv.value);
 	if(extension == "gif" || extension == "png" || extension == "jpg"){
 		var errorElement = "";
 		errorElement = GetFileSize(inputDiv.id);//console.log("File uploaded is image file");
 		if(errorElement != ""){
 			alert(errorElement);
			jQuery("#"+ inputDiv.id).replaceWith("<input type='file' name='" + inputDiv.id + "' id='" + inputDiv.id +"' onChange='isMainImage1(this)' />");
			return false;
 	 	 }else{
 	 		jQuery("#imageMessage").html("");
 	 	 }
	} 
	else{
		var errWarning = jQuery("#alertImageType").val();
		alert(errWarning);
		jQuery("#"+ inputDiv.id).replaceWith("<input type='file' name='" + inputDiv.id + "' id='" + inputDiv.id +"' onChange='isMainImage1(this)' />");
		return false;
	}
 } 


function isDocument(inputDiv) {

	var errorElement = "";
	errorElement = GetFileSizeDocument(inputDiv.id);// console.log("File uploaded is
											// image file");
	if (errorElement != "") {
		alert(errorElement);
		jQuery("#" + inputDiv.id).replaceWith("<input type='file' name='" + inputDiv.id + "' id='"+ inputDiv.id + "' onChange='isDocument(this)' />");
		return false;
	} else {
		jQuery("#docMessage").html("");
	}

} 


function GetFileSizeDocument(file) {
	var fileInput = document.getElementById(file);
	var errorElement = "";
	var file = fileInput.files[0];
	if (file.size > (1024 * 1024 * 30)) {
		errorElement = jQuery("#alertDocSize").val();
		return errorElement;
	} else {
		return errorElement;
	}
}

/*
 * 
 * function submitEditForm() { checkEditImage(); checkTitle(); checkDESC();
 * checkDate();
 * 
 * if(status){ jQuery("#frmAddSlide").submit(); } }
 * 
 * function checkDESC() { //var detail = jQuery("#detail").val(); var detail =
 * CKEDITOR.instances.detail.getData(); if (detail == "" || detail == null) {
 * var errorMessage = jQuery("#emptyDESC").val();
 * jQuery("#imageDESCMessage").html(errorMessage); status = false; } else {
 * jQuery("#imageDESCMessage").html(""); // return true; } } function
 * checkTitle() { //var title = jQuery("#title").val();
 * 
 * var title = CKEDITOR.instances.title.getData();
 * 
 * if (title == "" || title == null) { var errorMessage =
 * jQuery("#emptyTitle").val(); jQuery("#newstitle").html(errorMessage); status =
 * false; } else { if(title.length>1000){ var errorMessage =
 * jQuery("#oversizeTitle").val(); jQuery("#newstitle").html(errorMessage);
 * status = false; }else{ jQuery("#newstitle").html(""); } } } function
 * checkImage() {
 * 
 * var fileElement = document.getElementById("image").value; if (fileElement !=
 * "") { var errorElement = checkImageType(fileElement); if (errorElement != "") {
 * jQuery("#imageMessage").html(errorElement); status = false; } else {
 * jQuery("#imageMessage").html(""); errorElement = GetFileInfo(); if
 * (errorElement != "") { jQuery("#imageMessage").html(errorElement); status=
 * false; } else { var partOfPath=fileElement.split('\\');
 * 
 * var lengthOfPath=partOfPath.length; var filename=partOfPath[lengthOfPath-1];
 * if(filename.length>200){ var imgNameOver200 = jQuery("#imageName200").val();
 * jQuery("#imageMessage").html(imgNameOver200); status =false; } else {
 * jQuery("#imageMessage").html(""); }
 *  } } } else { var alertSelectImage = jQuery("#alertSelectImage").val();
 * jQuery("#imageMessage").html(alertSelectImage); status =false; }
 *  } function checkEditImage() {
 * 
 * var fileElement = document.getElementById("image").value; if (fileElement !=
 * "") { var errorElement = checkImageType(fileElement); if (errorElement != "") {
 * jQuery("#imageMessage").html(errorElement); status = false; } else {
 * jQuery("#imageMessage").html(""); errorElement = GetFileInfo(); if
 * (errorElement != "") { jQuery("#imageMessage").html(errorElement); status =
 * false; } else { var partOfPath=fileElement.split('\\');
 * 
 * var lengthOfPath=partOfPath.length; var filename=partOfPath[lengthOfPath-1];
 * if(filename.length>200){ var imgNameOver200 = jQuery("#imageName200").val();
 * jQuery("#imageMessage").html(imgNameOver200); status = false; } else {
 * jQuery("#imageMessage").html(""); // return true; } } } } else { // return
 * true; }
 *  }
 *  // function checkotherImage(){ // jQuery('.inputImgFile').each(function() { //
 * var txt = jQuery(this).find(".otherimg").val(); // alert(txt); // }); // }
 * 
 * function checkDate() { var expire = jQuery("#expireDateSlide").val(); var
 * display = jQuery("#displayDateSlide").val(); var displayNow =
 * jQuery("#displayCurrent").val();
 * 
 * var currentTime = Date.parseString(displayNow, 'dd/MM/yyyy HH:mm'); var
 * startDisplayDate = Date.parseString(display, 'dd/MM/yyyy HH:mm'); var
 * expireDisplayDate = Date.parseString(expire, 'dd/MM/yyyy HH:mm');
 * 
 * if (startDisplayDate >= expireDisplayDate) { jQuery("#dateMessage").show();
 * status =false; } else { jQuery("#dateMessage").hide(); // return true; } }
 * 
 * function GetFileInfo() { var fileInput = document.getElementById("image");
 * var errorElement = ""; var file = fileInput.files[0]; if (file.size > (1024 *
 * 1024 * 2)) { errorElement = jQuery("#alertImageSize").val(); return
 * errorElement; } else { return errorElement; } }
 * 
 * function checkImageType(file) { extArray = new Array("gif", "jpg", "png");
 * var errWarning; // 0002013: upload image png file but alert must upload png
 * file type. var mySplitResult = file.split("."); var length =
 * mySplitResult.length; var index = length - 1; var type =
 * mySplitResult[index].toLowerCase();
 * 
 * for ( var i = 0; i < extArray.length; i++) { if (extArray[i] == type) {
 * errWarning = ""; break; } else { errWarning =
 * jQuery("#alertImageType").val(); } } return errWarning; }
 */

function showTitleUpload() {
	var uploadTag = "<input id='image' name='image' type='file' /><span id='imageMessage' style='color: red;'></span>";
	$("#uploadImage").html(uploadTag);
	$("#editimageTitle").hide();
	$("#cancelimageTitle").show();
}
function hideTitleUpload() {
	$("#uploadImage").html("");
	$("#editimageTitle").show();
	$("#cancelimageTitle").hide();
}



function deleteSlideImg(uploadId ,url) {
	var alertDelete = jQuery("#alertDelete").val();
	var r = confirm(alertDelete);

	if(r==true){
 	jQuery.ajax({
			type: "POST",
			url: url,
			data: "uploadId="+uploadId,
			success: function(msg){
 		jQuery("#"+uploadId).remove();
 		alert(jQuery("#removeAlert").val());
  		}
	});
 	}
 }

function deleteSlideDoc(uploadId,url){

	var alertDelete = jQuery("#alertDelete").val();
	var r = confirm(alertDelete);

	if(r==true){
	 	jQuery.ajax({
			type: "POST",
			url: url,
			data: "uploadId="+uploadId,
			success: function(msg){
 		jQuery("#"+uploadId).remove();
 		alert(jQuery("#removeAlert").val());
  		}
	});
	}else{
		return false;
	}
}

function isImage1(inputDiv){
    extension = getFileType(inputDiv.value);
 	if(extension == "gif" || extension == "png" || extension == "jpg"){
 		var errorElement = "";
 		errorElement = GetFileSize(inputDiv.id);//console.log("File uploaded is image file");
 		if(errorElement != ""){
 			alert(errorElement);
			jQuery("#"+ inputDiv.id).replaceWith("<input type='file' name='" + inputDiv.id + "' id='" + inputDiv.id +"' onChange='isImage1(this)' />");
			return false;
 	 	 }
	} 
	else{
		var errWarning = jQuery("#alertImageType").val();
		alert(errWarning);
		jQuery("#"+ inputDiv.id).replaceWith("<input type='file' name='" + inputDiv.id + "' id='" + inputDiv.id +"' onChange='isImage1(this)' />");
		return false;
	}
 } 

function GetFileSize(file) {
	var fileInput = document.getElementById(file);
	var errorElement = "";
	var file = fileInput.files[0];
	if (file.size > (1024 * 1024 * 2)) {
		errorElement = jQuery("#alertImageSize").val();
		return errorElement;
	} else {
		return errorElement;
	}
}


function getFileType(filename) {
	filename = filename.toLowerCase();
	var beginIndex = filename.lastIndexOf(".");
	var endIndex = filename.length;
	var type = filename.substring(beginIndex + 1, endIndex);
	return type;
}


function CloseWindow1()
{
	var previewId = jQuery("#previewId").val();
	var linkUrl = jQuery("#linkRemove").val();
	 jQuery.ajax({
		 	method: "post",
			url: '/wps/PA_SlideShow/DeleteSlidePreviewServlet',
			data: "previewId=" +previewId,
			success: function(msg){
		 		window.close();
			}
	});
}
