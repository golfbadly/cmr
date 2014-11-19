var imgCount = 1;
var docCount = 1;
var linkCount = 1;

$j = jQuery.noConflict();

$j(document).ready(function() {
	//add image script
	$j('#btnAddImg').click(function() {
		var lastElem = $j("[id*='image-input-']").last();
		var num = getRowFormId(lastElem.attr('id'));
		var newNum = new Number(imgCount + 1);		// the numeric ID of the new input field being added
		
		// create the new element via clone(), and manipulate it's ID using newNum value
		var newElem = lastElem.clone().attr('id', 'image-input-' + newNum);

		// manipulate the name/id values of the input inside the new element
		newElem.find('#image-detail-' + num).attr('id', 'image-detail-' + newNum).attr('name', 'image-detail-' + newNum).val('');
		
		// manipulate the name/id values of the input inside the new element
		newElem.find('#image-' + num).attr('id', 'image-' + newNum).attr('name', 'image-' + newNum).val('');

		newElem.find('#image-delete-' + num).attr('id', 'image-delete-' + newNum);

		// insert the new element after the last "duplicatable" input field
		lastElem.after(newElem);

		// enable the "remove" button
		$j("[id*='image-delete-']").attr('disabled','');

		imgCount++;
	});

	//add document script
	$j('#btnAddDoc').click(function() {
		var lastElem = $j("[id*='document-input-']").last();
		var num = getRowFormId(lastElem.attr('id'));
		var newNum = new Number(docCount + 1);		// the numeric ID of the new input field being added
		
		// create the new element via clone(), and manipulate it's ID using newNum value
		var newElem = lastElem.clone().attr('id', 'document-input-' + newNum);

		// manipulate the name/id values of the input inside the new element
		newElem.find('#document-' + num).attr('id', 'document-' + newNum).attr('name', 'document-' + newNum).val('');
		
		newElem.find('#document-detail-' + num).attr('id', 'document-detail-' + newNum).attr('name', 'document-detail-' + newNum).val('');

		newElem.find('#document-delete-' + num).attr('id', 'document-delete-' + newNum);

		// insert the new element after the last "duplicatable" input field
		lastElem.after(newElem);

		// enable the "remove" button
		$j("[id*='document-delete-']").attr('disabled','');

		docCount++;
	});

	//add link script
	$j('#btnAddLink').click(function() {
		var lastElem = $j("[id*='link-input-']").last();
		var num = getRowFormId(lastElem.attr('id'));
		var newNum = new Number(linkCount + 1);		// the numeric ID of the new input field being added
		
		// create the new element via clone(), and manipulate it's ID using newNum value
		var newElem = lastElem.clone().attr('id', 'link-input-' + newNum);

		// manipulate the name/id values of the input inside the new element
		newElem.find('#link-' + num).attr('id', 'link-' + newNum).attr('name', 'link-' + newNum).val('http://');
		newElem.find('#link-detail-' + num).attr('id', 'link-detail-' + newNum).attr('name', 'link-detail-' + newNum).val('');

		newElem.find('#link-delete-' + num).attr('id', 'link-delete-' + newNum);

		// insert the new element after the last "duplicatable" input field
		lastElem.after(newElem);

		// enable the "remove" button
		$j("[id*='link-delete-']").attr('disabled','');

		linkCount++;
	});

	//disable all delete button
	$j("[id*='-delete-']").attr('disabled','disabled');
});

function deleteCurrentRow(type, element) {

	var num = getRowFormId(element.id);
	$j('#' + type + '-input-' + num).remove();

	// if only one element remains, disable the "remove" button
	if ($j("[id*='" + type + "-input-']").length == 1)
		$j("[id*='" + type + "-delete-']").attr('disabled','disabled');	
}

function getRowFormId(id) {
	return id.split("-").pop();
}
