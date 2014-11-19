
<div id="language">
<%
	String local = (String) session.getAttribute("LOCALE");

 	if (local != null) {
	 if (local.equals("th")) {
%> <a id="localelink_th"
	onclick="switchLang('th','listAdsSlide.html')" class="image-border"><img
	src="pics/icons/thai-flag.png" alt="Thai" title="Thai" /></a> : <a
	id="localelink_en" onclick="switchLang('en','listAdsSlide.html')"><img
	src="pics/icons/eng-flag.png" alt="English" title="English" /></a> <%
 	} else {
 %>
<a id="localelink_th" onclick="switchLang('th','listAdsSlide.html')"><img
	src="pics/icons/thai-flag.png" alt="Thai" title="Thai" /></a> : <a
	id="localelink_en" onclick="switchLang('en','listAdsSlide.html')"
	class="image-border"><img src="pics/icons/eng-flag.png"
	alt="English" title="English" /></a> <%
 	}
 %> <%
 	} else {
 %> <a id="localelink_th"
	onclick="switchLang('th','listAdsSlide.html')"><img
	src="pics/icons/thai-flag.png" alt="Thai" title="Thai"
	class="image-border" /></a> : <a id="localelink_en"
	onclick="switchLang('en','listAdsSlide.html')"><img
	src="pics/icons/eng-flag.png" alt="English" title="English" /></a> <%
 	}
 %>
</div>

































