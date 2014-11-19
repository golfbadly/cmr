jQuery.noConflict()

jQuery(document).ready(DatePicker());
    
function DatePicker() {
	jQuery("#dateStart").datepicker({
		showOn : "button",
		buttonImage : "pics/calendar.gif",
		buttonImageOnly : true,
		dateFormat : "yy-mm-dd"
	});
	jQuery("#dateEnd").datepicker({
		showOn : "button",
		buttonImage : "pics/calendar.gif",
		buttonImageOnly : true,
		dateFormat : "yy-mm-dd"
	});
	jQuery("#receiveGift").css("margin-left","366px");
	jQuery("#receiveGift").css("margin-top","70px");
	jQuery("#receiveGift").attr('display','none');
	
	var keywordReceive = jQuery("#keywordReceive").val();
	
	if(keywordReceive != ""){
		jQuery("#receiveGift").attr('display','block');
	}
	
	jQuery("#receiveGift").click(function(){
		console.log("receiveGift click");	
		showDialog();
	});

	jQuery('.file-image').hide();	
	jQuery('#backToEvent').click(function() {
		if( validateEvent()==true){
			window.location = "event-list.html";
		}
	});	
	jQuery('#backEvent').click(function() {
			window.location = "event-list.html";
	});	
	jQuery('.input-text-keyword:first').focus();

	
}

function validateEvent(){
	  var valid = true;
		    if ( document.formImage.eventName.value == "" || document.formImage.dateStart.value == "" || document.formImage.dateEnd.value == "")
		    {
		        alert ( "Please fill Data for complete." );
		        valid = false;
		    }

		    return valid;
	}

function showUpdateImage(){
	jQuery('.file-image').show();	
}

function listMember(eventDetailId){

	window.location="Getlist.html?eventDetailId="+ eventDetailId;
	
}

function showNoPic(){
	document.getElementById('eventImg').innerHTML="<img id='imageView' src='pics/eventImg/noPhoto.gif' /> ";		
}

function deleteEvent(){
	jQuery('#deleteFrm').submit();
}

function isImage(inputDiv){
    
    extension = getFileType(inputDiv.value);
 	if(extension == "gif" || extension == "jpeg" || extension == "png" || extension == "jpg" || extension == "" ){
		console.log("File uploaded is image file");
		return true;
	} 
	else{
		alert("à¸�à¸£à¸¸à¸“à¸²à¸•à¸£à¸§à¸ˆà¸ªà¸­à¸šà¸£à¸¹à¸›à¹�à¸šà¸šà¹„à¸Ÿà¸¥à¹Œà¹�à¸™à¸šà¸›à¸£à¸°à¹€à¸ à¸—à¸£à¸¹à¸›à¸ à¸²à¸ž");
		jQuery("#"+ inputDiv.id).replaceWith("<input type='file' name='" + inputDiv.id + "' id='" + inputDiv.id +"' onChange='isImage(this)' />");
		return false;
	}
 } 

function getFileType(filename) {
	filename = filename.toLowerCase();
	var beginIndex = filename.indexOf(".");
	var endIndex = filename.length;
	var type = filename.substring(beginIndex + 1, endIndex);
	return type;
}

function alertKeyPress(inputDiv) {
	alert("à¹„à¸¡à¹ˆà¸ªà¸²à¸¡à¸²à¸£à¸–à¸žà¸´à¸¡à¸žà¹Œà¸‚à¹‰à¸­à¸„à¸§à¸²à¸¡à¸¥à¸‡à¹ƒà¸™ upload file à¹„à¸”à¹‰");
	jQuery("#"+ inputDiv.id).replaceWith("<input type='file' name='" + inputDiv.id + "' id='" + inputDiv.id +"' onChange='isImage(this)' onkeypress='alertKeyPress(this)'/>");
}

function updateImage(){
	jQuery('#formImage').submit();
}

function cancelUpdateImage(){
	jQuery('.file-image').hide();
}

function confirmDeleteImage(eventId,type) {
	 if(confirm('à¸„à¸¸à¸“à¸•à¹‰à¸­à¸‡à¸�à¸²à¸£à¸¥à¸šà¸£à¸¹à¸›à¸™à¸µà¹‰à¹ƒà¸Šà¹ˆà¸«à¸£à¸·à¸­à¹„à¸¡à¹ˆ')) {
	 	ajaxDeleteImage(eventId,type);
	 }
}

function ajaxDeleteImage(eventId,type) {
 	new Ajax.Request('deleteEventImg.html', {
		method: 'post',
		parameters: {eventId:eventId, type:type},
		onSuccess: function(transport) {
			var data = transport.responseText;
			jQuery('.file-image').hide();
			document.getElementById('eventImg').innerHTML="<img id='imageView' src='pics/eventImg/noPhoto.gif' /> ";
 			console.info('in success');
	    }
	});
  }

function textLimit(field, maxlen) {
  if (field.value.length > maxlen + 1)
     alert('à¸‚à¹‰à¸­à¸„à¸§à¸²à¸¡à¹€à¸�à¸´à¸™à¸ˆà¸³à¸™à¸§à¸™à¸—à¸µà¹ˆà¸ˆà¸³à¸�à¸±à¸”');
  if (field.value.length > maxlen)
     field.value = field.value.substring(0, maxlen);
} 



function showDialog() {
	//$("#keywordReceiveModal").modal('show');
	 jQuery("#dialog-modal").dialog({
		resizable : true,
		width : 340,
		height : 400,
		modal : true,
		buttons : {
			"OK" : function() {
				jQuery(this).dialog("close");
			}
		}
	});
	
}
function shakeBox(){
	jQuery( "#rafflebox" ).effect( "shake" );
}
