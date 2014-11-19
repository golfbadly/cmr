<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="org.springframework.web.servlet.i18n.SessionLocaleResolver"%>

<html>
<head>
<script type="text/javascript" src="jsp/script/jquery-1.4.2.js"></script>
<script type="text/javascript"	src="jsp/script/function.js"></script>
<script type="text/javascript" src="jsp/script/date.js"></script>
<script type="text/javascript" src="jsp/script/dynDateTime/jquery.dynDateTime.js"></script>
<script type="text/javascript" src="jsp/script/dynDateTime/lang/calendar-en.js"></script>
<link rel="stylesheet" type="text/css" media="all" href="jsp/script/dynDateTime/css/calendar-win2k-cold-1.css"  />
<script src="jsp/script/dateSeleted.js"></script>
<script type="text/javascript" src="jsp/script/slideScript.js"></script>
<script type="text/javascript" src="jsp/script/multiple-file-upload.js"></script>

<link rel="stylesheet" type="text/css" href="jsp/css/style.css" media="screen" />
<link rel="stylesheet" type="text/css" href="jsp/css/style_slide.css"  media="screen" />
<link rel="stylesheet" type="text/css" href="jsp/script/slide/css/slideshow.css"  media="screen" />

<script type="text/javascript" src="jsp/script/prototype.js"></script>
<script type="text/javascript" src="jsp/script/ajax.js"></script>
<script type="text/javascript" src="jsp/script/locale.js"></script>
<script type="text/javascript" src="jsp/script/jquery.blockUI.js"></script>
	
 <c:set var="model" value="${requestScope.slideShowModel}" scope="request" />
 <c:set var="slideShowUploadList" value="${slideShowUploadList}" scope="request" />
 <c:set var="viewAdsImg" value="${viewAdsImg}" scope="request"/>
 <c:set var="imageDir" value="${imageDir}" scope="request" />
 <c:set var="path" value="${path}" scope="request" />
 
 
 <title>Add Ads</title>
</head>
<body class="body">
<%@ include file="language.jsp" %>
<div id="page">

<div id="content">
<div id="slideAds">
 
<h3 class="slide-title"><fmt:message key="slide.show.manage" /></h3>
<p><fmt:message key="manage.back" /> : <a href="menu.html"><fmt:message
	key="manage.back.home" /></a></p>
	<div class="input-form">
	<form id="frmAddSlide" name="frmAddSlide" action="editAdsSlide.html" method="post" enctype="multipart/form-data" charset=UTF-8"> 
	<input id="slideId" name="slideId" type="hidden" value="${model.slideId}"/>
	<table>
			<tr>
				<th><fmt:message key="slide.show.image" /><span class="red">*</span></th>
				<td>
				<img class="news-image" src="${viewAdsImg}/${model.slideId}/${model.image}" alt="detail" width="500px"/>
				<br/>
				<input id="image" name="image" type="file" value="" onChange="isMainImage1(this)" onkeypress="alertKeyPress(this)"/><span id="imageMessage" style="color: red;" ></span>
				</td>
			</tr>
			<tr class="multi-link">
				<th><fmt:message key="slide.show.title" /><span class="red">*</span></th>
				<td>
				<textarea   class="long" rows="3" cols="1"  id="url" name="url" >${model.url}</textarea>
			<span id="newstitle" style="color: red;" ></span>	
			</td>
			</tr>
			
			<tr>
				<th><fmt:message key="date.start" /></th>
				<td>
				<input id="displayDateSlide" name="displayDateSlide" type="text" value="${model.displayDate}"  readonly="readonly" />
				<button type="button" >
    				<img src="<%=request.getContextPath()%>/pics/CalendarButton.gif" alt="calendar"/>
				</button>
			</tr> 
			<tr>
				<th><fmt:message key="date.finish"/></th>
				<td>
				<input id="expireDateSlide" name="expireDateSlide" type="text" value="${model.expireDate}"  readonly="readonly"  />
				<button type="button" >
    				<img src="images/CalendarButton.gif" alt="calendar"/>
				</button>
				<span id="dateMessage" class="red" style="display: none;"><portal-fmt:text key="marquees.date.expire.length" /></span>
				</td>	
			</tr>
			</table>
			
			<div class="margin-button">
			<input id="actionSave" type="hidden" value="editAdsSlide.html"/>
			<input id="actionPreview" type="hidden" value="<portlet:actionURL portletMode='view' windowState='maximized'><portlet:param name='action'  value='addPreviewSlideController' ></portlet:param></portlet:actionURL>"/>
			<input type="hidden" name="previewId" value="${previewId}">
			<input class="ButtonText" type="button" value='<fmt:message key="common.button.save"/>' id="submitButton" onclick="submitForm1('save')"/>
			<input class="ButtonText" type="button" name="Cancel" value="<fmt:message key='common.button.cancel'/>" onClick="goToURL('<portlet:renderURL portletMode='view' windowState='maximized'><portlet:param name='action'  value='closeSlidePreviewController' ></portlet:param><portlet:param name='previewId'  value='${previewId}' ></portlet:param></portlet:renderURL>')"/>
			<input class="ButtonText" type="button" name="preview" value="<fmt:message key='common.button.preview'/>" onclick="submitForm1('preview')"/>
			</div>
			</form>
			
			<input id="type" type="hidden" value="edit"/>
			<input id="emptyURL" type="hidden" value="<fmt:message key='slide.show.select.url'/>"/>
			<input id="oversizeTitle" type="hidden" value="<fmt:message key='text.over.1000'/>"/>
		
			<input id="emptyDESC" type="hidden" value="<fmt:message key='slide.show.select.text'/>"/>
			<input id="pathImage" type="hidden" value="${model.image}"/>
			<input id="removeAlert" type="hidden" value="<fmt:message key='remove.complete'/>"/>
			<input id="imageName200" type="hidden" value="<fmt:message key='image.name.limit.200' />"/>
			<input id="deletedMessage" type="hidden" value="<fmt:message key="deleted.message"/>"/>
			<input id="alertDelete" type="hidden" value="<fmt:message key="delete.confirm"/>"/>	
			<input id="alertExpire" type="hidden" value="<fmt:message key="date.expire.length" />"/>
			<input id="alertSelectImage" type="hidden" value="<fmt:message key="slide.show.select.banner" />"/>
			<input id="alertImageType" type="hidden" value="<fmt:message key="select.image.type" />"/>
			<input id="alertImageSize" type="hidden" value="<fmt:message key="select.image.size" />"/>
			</div>
			
			</div>
			</div>	 <!-- end div content -->
<div id="copyright">
		Copyright&copy;2011
		<a class="brownLink" href="http://www.xp-link.com">XPLink</a>
		. All rights reserved.
	</div>
	
</div>   <!-- end div page -->
</body>
</html>
			