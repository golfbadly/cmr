<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="org.springframework.web.servlet.i18n.SessionLocaleResolver"%>

<html>
<head>
<link rel="stylesheet" type="text/css" href="jsp/css/style.css" media="screen" />
<link rel="stylesheet" type="text/css" href="jsp/css/style_slide.css"  media="screen" />
<link rel="stylesheet" type="text/css" href="jsp/script/slide/css/slideshow.css"  media="screen" />

<script type="text/javascript" src="jsp/script/jquery-1.4.2.js"></script>
<script type="text/javascript" src="jsp/script/prototype.js"></script>
<script type="text/javascript" src="jsp/script/ajax.js"></script>
<script type="text/javascript" src="jsp/script/locale.js"></script>
<script type="text/javascript" src="jsp/script/jquery.blockUI.js"></script>
<script type="text/javascript" src="jsp/script/function.js"></script>

<title>List Ads</title>
</head>
<body class="body">

<div id="page">
<%@ include file="language.jsp" %>
<div id="content">

<c:set var="listSlide" value="${listSlide}" scope="request" />
<c:set var="imageDir" value="${imageDir}" scope="request" />
<c:set var="adsDir" value="${adsDir}" scope="request" />
<c:set var="viewAdstImg" value="${viewAdsImg}" scope="request"/>


<div id="slideAds">

<h3 class="slide-title"><fmt:message key="slide.randomcm.manage" /></h3>
<p><fmt:message key="manage.back" /> : <a href="menu.html"><fmt:message
	key="manage.back.home" /></a></p>
<div class="margin-button"><a href="goToAddAdsSlide.html"><img
	src="<%=request.getContextPath()%>/pics/slideMenu/AddButton.gif"
	class="setcursor" alt="add" /></a> <a href="#" onClick="deleteSelect();"><img
	src="<%=request.getContextPath()%>/pics/slideMenu/DeleteButton.gif"
	class="setcursor" alt="delete" /></a></div>
<div id="checkboxes">
<div class="list">
<table>
	<thead>
		<tr>
			<th class="head-style checkbox-width">
			<center><input type="checkbox"
				onclick="toggleChecked(this.checked)"></center>
			</th>
			<th class="head-style"><fmt:message key="slide.show.image" /></th>
			<th class="head-style"><fmt:message key="slide.show.title" /></th>
			<th class="head-style"><fmt:message key="date.start" /></th>
			<th class="head-style"><fmt:message key="date.finish" /></th>
			<th></th>
		</tr>
	</thead>
	<tbody>

		<c:if test="${! empty(listSlide) }">

			<c:forEach var="item" items="${listSlide}" varStatus="numItem">
				<c:choose>
					<c:when test="${numItem.count % 2 == 0}">
						<tr class="odd" id="${item.slideId}">
					</c:when>
					<c:otherwise>
						<tr id="${item.slideId}">
					</c:otherwise>
				</c:choose>
				<td>
				<center><input type="checkbox" value="${item.slideId}"
					id='selectItem' name='selectItem'></center>
				</td>
				<td>
				<img class="thumbnail" src="${viewAdsImg}/${item.slideId}/${item.image}"
					style="width: 130xp; height: 100px;" alt="edit"></td>
				<td><a href="detailAdsSlide.html?slideId=${item.slideId}">
				${item.url}</a></td>
				<td class="text-center">${item.displayDate}</td>
				<td class="text-center">${item.expireDate}</td>
				<td class="tools"><a
					href="goToEditAdsSlide.html?slideId=${item.slideId}"><img
					src="<%=request.getContextPath()%>/pics/slideMenu/EditButton.gif"
					alt="edit" /></td>
				</tr>
			</c:forEach>

		</c:if>
		<c:if test="${empty(listSlide)}">
			<tr>
				<td colspan="7" align="center">no data</td>
			</tr>
		</c:if>

	</tbody>
</table>
</div>


<input type="hidden" id="currentPage" name="currentPage"
	value="${currentPage}">
<input type="hidden" id="pageNumber" name="pageNumber"
	value="${pageNumber}">
<c:if test="${pageNumber!=null&&pageNumber>1}">
	<c:if test="${currentPage!=1}">
		<a href="listAdsSlide.html?newPage=1"><c:out value="${i}" />[<fmt:message
			key="page.first" />]</a>&nbsp;
<a href="listAdsSlide.html?newPage=${currentPage-1}"><c:out
			value="${i}" />[<fmt:message key="page.previous" />]</a>&nbsp;
</c:if>
	<c:if test="${currentPage==1}">
[<fmt:message key="page.first" />]&nbsp;
[<fmt:message key="page.previous" />]&nbsp;
</c:if>
	<c:forEach var="i" begin="1" end="${pageNumber}" varStatus="status">
		<c:if test="${currentPage!=status.count}">
			<a href="listAdsSlide.html?newPage=${i}"><c:out value="${i}" />&nbsp;</a>
		</c:if>
		<c:if test="${currentPage==status.count}">
			<c:out value="${i}" />&nbsp;
</c:if>
	</c:forEach>

	<c:if test="${currentPage==pageNumber}">
[<fmt:message key="page.next" />]&nbsp;
[<fmt:message key="page.last" />]&nbsp;
</c:if>
	<c:if test="${currentPage!=pageNumber}">
		<a href="listAdsSlide.html?newPage=${currentPage+1}"><c:out
			value="${i}" />[<fmt:message key="page.next" />]</a>&nbsp;
<a href="listAdsSlide.html?newPage=${pageNumber}"><c:out
			value="${i}" />[<fmt:message key="page.last" />]</a>&nbsp;
</c:if>
</c:if>

<input type="hidden" id="firstPage" name="firstPage"
	value="listAdsSlide.html" />
<input id="alertDelete" type="hidden"
	value="<fmt:message key='delete.confirm'/>" />
<input type="hidden" id="deletedMessage" name="deletedMessage"
	value="<fmt:message key='deleted.message'/>" />
<input type="hidden" id="deletedMessageFail" name="deletedMessageFail"
	value="<fmt:message key='deleted.message.fail'/>" />
</div> <!-- end div check bok -->
</div> <!-- end div slideAds -->

</div>	 <!-- end div content -->
<div id="copyright">
		Copyright&copy;2011
		<a class="brownLink" href="http://www.xp-link.com">XPLink</a>
		. All rights reserved.
	</div>
	
</div>   <!-- end div page -->
</body>
</html>