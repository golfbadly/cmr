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
<script type="text/javascript" src="jsp/script/function.js"></script>
	
<title>Detail Ads</title>
</head>
<body class="body">

<div id="page">
<div id="content">

<c:set var="isPreview" value="${isPreview}" scope="request" />
<c:set var="slideDetail" value="${slideDetail}" scope="request" />
<c:set var="slideShowUploadList" value="${slideShowUploadList}" scope="request" />
<c:set var="viewAdsImg" value="${viewAdsImg}" scope="request" />
	<div id="news">
	
	<p>Back to : <a href="adsSlide.html">Home page.</a>
	 | <a href="listAdsSlide.html">List page</a>

	</p>
	
	<div class="margin-button">

		<input type="submit" name="ClosePreview" value="Close Preview" onclick="CloseWindow1()" /> 
		</br>

	<a href="goToEditAdsSlide.html?slideId=${slideDetail.slideId}"><img src="<%=request.getContextPath()%>/pics/EditButton.gif" alt="edit" /></a> 
	<a href="deleteAdsSlide.html?slideId=${slideDetail.slideId}"><img src="<%=request.getContextPath()%>/pics/DeleteButton.gif" class="setcursor" alt="delete" /></a>
	</div>

	<h3>${slideDetail.url}</h3>
	<center><img  class="news-image" src="${viewAdsImg}/${slideDetail.slideId}/${slideDetail.image}" alt="detail" /></center>

	</div>
	<input id="linkRemove" type="hidden" value="<portlet:actionURL portletMode='view' windowState='maximized'><portlet:param name='action'  value='DeleteSlidePreviewServlet' ></portlet:param></portlet:actionURL>"/>
	<input id="previewId" type="hidden" value="${previewId}"/>
	<input id="alertDelete" type="hidden" value="<portal-fmt:text key="marquees.delete.confirm" bundle="nls.customs"/>"/>

	
			</div>	 <!-- end div content -->
<div id="copyright">
		Copyright&copy;2011
		<a class="brownLink" href="http://www.xp-link.com">XPLink</a>
		. All rights reserved.
	</div>
	
</div>   <!-- end div page -->
</body>
</html>
