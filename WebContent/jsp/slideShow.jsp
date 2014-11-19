<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="jsp/script/jquery-1.4.2.js"></script>
<script type="text/javascript" src="jsp/script/slideScript.js"></script>
<link rel="stylesheet" href="jsp/script/slide/css/slideshow.css" type="text/css" media="screen" />
<script type="text/javascript" src="jsp/script/slide/js/jquery.cycle.js"></script>
<script type="text/javascript" src="jsp/script/slide/js/slideshow.js"></script>

<c:set var="listSlideShow" value="${listSlideShow}" scope="request" />
<c:set var="imageDir" value="${imageDir}" scope="request" />
<c:set var="adsDir" value="${adsDir}" scope="request" />
<c:set var="viewAdstImg" value="${viewAdsImg}" scope="request"/>

<div class="slide-show">
<div id="slideshow">
<div class="slides">

<ul>
	<c:forEach var="item" items="${listSlideShow}" varStatus="numItem">
		<li id="${numItem.count}">

		<div>
		<a href="http://${item.url}" target="blank"> 
		<img src="${viewAdsImg}/${item.slideId}/${item.image}" alt="ads" /> </a>
		</div>
		</li>
	</c:forEach>
</ul>
</div>

<ul class="slides-nav">

	<c:forEach var="item" items="${listSlideShow}" varStatus="numItem">
		<c:if test="${numItem.count==1}">
			<li class="on"><a href="#${numItem.count}">${numItem.count}</a></li>
		</c:if>
		<c:if test="${numItem.count!=1}">
			<li><a href="#${numItem.count}">${numItem.count}</a></li>
		</c:if>
	</c:forEach>
</ul>
</div>
</div>



