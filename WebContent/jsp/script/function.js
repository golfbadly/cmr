
//Open query window
function openQuery(theURL,winName,features) { //v2.0
 features =	"scrollbars=yes,width=650,height=500";
  window.open(theURL,winName,features);
}

//Open dialog
function opendialog(val){
		var URL = "";
		var windowStyle = "scrollbars=no,width=300,height=100,status=no,menubar=no,location=no,toolbar=no,directories=no";
		if(val == "confirm"){
			URL="dialog/confirmDialog.html";
		}else if (val == "warning"){
			URL="dialog/warningDialog.html";
		}else if (val == "error"){
			URL="dialog/errorDialog.html";
		}else{
			URL="dialog/infoDialog.html";
		}
		var val = window.open(URL,"",windowStyle);
		val.moveTo(350,200);
	}

//Display date
Today = new Date();
TodayDay = Today.getDate();
TodayMon = Today.getMonth();
TodayYear = Today.getYear();
if (TodayYear < 2000) TodayYear += 1900;

if (TodayMon == 0) { TodayMonth = "January"; } 
else if (TodayMon == 1) { TodayMonth = "February"; }
else if (TodayMon == 2) { TodayMonth = "March"; }
else if (TodayMon == 3) { TodayMonth = "April"; }
else if (TodayMon == 4) { TodayMonth = "May"; }
else if (TodayMon == 5) { TodayMonth = "June"; }
else if (TodayMon == 6) { TodayMonth = "July"; }
else if (TodayMon == 7) { TodayMonth = "August"; }
else if (TodayMon == 8) { TodayMonth = "September"; }
else if (TodayMon == 9) { TodayMonth = "October"; }
else if (TodayMon == 10) { TodayMonth = "November"; }
else if (TodayMon == 11) { TodayMonth = "December"; }
else { TodayMonth = TodayMon; }

//elem = document.getElementById("displayDate");
//elem.textContent = TodayMonth + " " + TodayDay + ", " + TodayYear;

/*startList = function() {
if (document.all&&document.getElementById) {
navRoot = document.getElementById("nav");
for (i=0; i<navRoot.childNodes.length; i++) {
node = navRoot.childNodes[i];
if (node.nodeName=="LI") {
node.onmouseover=function() {
this.className+=" over";
  }
  node.onmouseout=function() {
  this.className=this.className.replace(" over", "");
   }
   }
  }
 }
}
window.onload=startList;*/



//Menu drop down
function addLoadEvent(func) {
    var oldonload = window.onload;
    if (typeof window.onload != 'function') {
        window.onload = func;
    } else {
        window.onload = function() {
            oldonload();
            func();
        }
    }
}

function prepareMenu() {
    // first lets make sure the browser understands the DOM methods we will be using
  	if (!document.getElementsByTagName) return false;
  	if (!document.getElementById) return false;
  	
  	// lets make sure the element exists
  	if (!document.getElementById("menu")) return false;
  	var menu = document.getElementById("menu");
  	
  	// for each of the li on the root level check if the element has any children
  	// if so append a function that makes the element appear when hovered over
  	var root_li = menu.getElementsByTagName("li");
  	for (var i = 0; i < root_li.length; i++) {
  	    var li = root_li[i];
  	    // search for children
  	    var child_ul = li.getElementsByTagName("ul");
  	    if (child_ul.length >= 1) {
  	        // we have children - append hover function to the parent
  	        li.onmouseover = function () {
  	            if (!this.getElementsByTagName("ul")) return false;
  	            var ul = this.getElementsByTagName("ul");
  	            ul[0].style.display = "block";
				
  	            return true;
  	        }
  	        li.onmouseout = function () {
  	            if (!this.getElementsByTagName("ul")) return false;
  	            var ul = this.getElementsByTagName("ul");
				var t=setTimeout('menuclose()',5000);
  	            ul[0].style.display = "none";

				//window.setTimeout('test(ul)', 500);
  	            return true;
				
  	        }
  	    }
  	}
  	
  	return true;

}

addLoadEvent(prepareMenu);

function menuclose()
{
	 ul[0].style.display = "block";
}

function setMaxLength() {
	var x = document.getElementsByTagName('textarea');
	var counter = document.createElement('div');
	counter.className = 'counter';
	for (var i=0;i<x.length;i++) {
		if (x[i].getAttribute('maxlength')) {
			var counterClone = counter.cloneNode(true);
			counterClone.relatedElement = x[i];
			counterClone.innerHTML = '<span>0</span>/'+x[i].getAttribute('maxlength');
			x[i].parentNode.insertBefore(counterClone,x[i].nextSibling);
			x[i].relatedElement = counterClone.getElementsByTagName('span')[0];

			x[i].onkeyup = x[i].onchange = checkMaxLength;
			x[i].onkeyup();
		}
	}
}

function checkMaxLength() {
	var maxLength = this.getAttribute('maxlength');
	var currentLength = this.value.length;
	if (currentLength > maxLength)
		this.relatedElement.className = 'toomuch';
	else
		this.relatedElement.className = '';
	this.relatedElement.firstChild.nodeValue = currentLength;
	// not innerHTML
}


// Timeout menu

var timeout	= 500;
var closetimer	= 0;
var ddmenuitem	= 0;

// open hidden layer
function mopen(id)
{	
	// cancel close timer
	mcancelclosetime();

	// close old layer
	if(ddmenuitem) ddmenuitem.style.visibility = 'hidden';

	// get new layer and show it
	ddmenuitem = document.getElementById(id);
	ddmenuitem.style.visibility = 'visible';

}
// close showed layer
function mclose()
{
	if(ddmenuitem) ddmenuitem.style.visibility = 'hidden';
}

// go close timer
function mclosetime()
{
	closetimer = window.setTimeout(mclose, timeout);
}

// cancel close timer
function mcancelclosetime()
{
	if(closetimer)
	{
		window.clearTimeout(closetimer);
		closetimer = null;
	}
}

// close layer when click-out
document.onclick = mclose; 


function HideContent(d) {
if(d.length < 1) { return; }
document.getElementById(d).style.display = "none";
}
function ShowContent(d) { 
document.getElementById(d).style.display = "block";
}
function ReverseContentDisplay(d) {
if(d.length < 1) { return; }
if(document.getElementById(d).style.display == "none") { document.getElementById(d).style.display = "block"; }
else { document.getElementById(d).style.display = "none"; }
}

function confirmApproval() {
	confirm("ยืนยันการอนุมัติข่าวสาร/ประกาศ");
}

function confirmDelete() {
	confirm("ยืนยันการลบข่าวสาร/ประกาศ");
}

function toggleChecked(status) {
	jQuery("#checkboxes input").each( function() {
		jQuery(this).attr("checked",status);


	});
	}
function deleteSelect(){ 
	var deletedMessage = jQuery("#deletedMessage").val();
	var deletedMessageFail = jQuery("#deletedMessageFail").val();
	var alertDelete = jQuery("#alertDelete").val();
	var homePage = jQuery("#firstPage").val();
	var numbers = jQuery("input[name='selectItem']").serializeArray();
	if(numbers.length>0){
	var ok = confirm(alertDelete);

	if(ok == true){
		jQuery.blockUI({ message: "<img src='pics/busy.gif' />" }); 
		 jQuery.each(numbers, function(i, field){ 
			 jQuery.ajax({
				 	method: "post",
					url: "DeleteSlideServlet",
					data: "selectItem=" + field.value,
					success: function(msg){
				 		// unblock when remote call returns 
					jQuery.unblockUI(); 
						if(msg == "success"){
							alert(deletedMessage);
							//console.log("isTrue");
							 window.location = homePage;
						}else{
							alert(deletedMessageFail);
							//console.log("isFalse");
						}
		  		}
				});
		      });
	}else{
		return false;
	}
	}
	
}





