<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "https://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="jsp/css/keyword.css"></link>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Note Paper</title>
</head>
<script type="text/javascript" src="jsp/script/jquery-ui-1.9.2.custom.js"></script>
<script type="text/javascript" src="jsp/script/ajax.js"></script>
 <script type="text/javascript" src="jsp/script/locale.js"></script>
 	 <script type="text/javascript" src="jsp/script/prototype.js" ></script>
<script src="jsp/script/keyword.js"></script>


<body>
<div style="margin:0 auto;background: url('pics/BGMain.png');width:800px;min-height:599px;"></div>

<div id="noteLayer">
		<div id="noteTitle">Note title ( Event name )</div>
		<div id="noteContent">
			<span id="noteText">A basic introduction to jQuery and the concepts that you need to know to use it.</span>
		</div>
		<div id="editNoteContent">
			<textarea maxlength="250" id="textareaNote"></textarea>
			<div id="doneButton" onclick="showEditNote()">done.</div>
		</div>
		<div id="editButton" onclick="showEditNote()">edit</div>
		<img src="pics/QuillPen.png" id="quillPen"/>
</div>



</body>
</html>
