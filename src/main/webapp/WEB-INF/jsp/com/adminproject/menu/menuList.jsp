<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/com/adminproject/cmmn/taglib.jsp" %>
<script>
	function menulist() {
		location.reload();
	}

	function menuRegistView(url, menuName){

		var param = {
			upprMenuCd	: $("#upprMenuCd").val(),
			menuLv	: $("#menuLv").val()
		}

		$.ajax({
			url			: "${pageContext.request.contextPath}/layer/"+url,
			type		: "post",
			data		: param,
			dataType	: "html",
			beforeSend	: function() {
				$(".loading").show();
				
			},
			success		: function(html) {
				$(".loading").hide();
				$("#layerView").empty();
				$(html).appendTo("#layerView");
				openModalPopWindow("layerView", menuName, 550, 520, true, false, true);				
			}
		});
	}

	function menuModifyView(url, menuName, menuCd, menuLv){

		var param = {};
		param.menuCd = menuCd;
		param.menuLv = menuLv;

		$.ajax({
			url			: "${pageContext.request.contextPath}/layer/"+url,
			type		: "post",
			data		: param,
			dataType	: "html",
			beforeSend	: function() {
				$(".loading").show();
			},
			success		: function(html) {
				$(".loading").hide();
				$('#layerView').empty();
				$(html).appendTo('#layerView');
				openModalPopWindow("layerView", menuName, 550, 520, true, false, true);
			}
		});
	}

	function secondMenuList(upprMenuCd, menuLv){

		$("#secondRegistBtn").show();
		$("#upprMenuCd").val(upprMenuCd);
		$("#menuLv").val(menuLv);

		var param = {
				upprMenuCd : upprMenuCd,
				menuLv : menuLv
		}

		$.ajax({
			url			: "${pageContext.request.contextPath}/selectMenuList.do",
			type		: "post",
			data		: param,
			beforeSend	: function() {
				$(".loading").show();
			},
			success		: function(data) {
				$(".loading").hide();
				$("#SECOND_TABLE").empty();

				var htmlStr = "";

				if(data.menuList.length > 0) {
					$.each(data.menuList, function(idx, menuList) {
						console.log(menuList);
						htmlStr += "<tr style=\"cursor:pointer\" ";
						htmlStr += "ondblclick=\"javascript:menuModifyView('secondMenuModify.do', '2차 메뉴 수정', '";
						htmlStr += menuList.menuCd;
						htmlStr += "', 2);\" >";

						htmlStr += "<td class=\"txt-center\" >";
						htmlStr += (idx+1);
						htmlStr += "</td>";
						htmlStr += "<td class=\"txt-center\" >";
						htmlStr += menuList.menuNm;
						htmlStr += "</td>";
						htmlStr += "<td class=\"txt-center\" >";

						if (menuList.useYn === 'Y') {
							htmlStr += "사용";
						} else {
							htmlStr += "사용안함";
						}

						htmlStr += "</td>";
						htmlStr += "<td class=\"txt-center\" >";

						if (menuList.dpYn === 'Y') {
							htmlStr += "전시";
						} else {
							htmlStr += "전시안함";
						}

						htmlStr += "</td>";
						htmlStr += "</tr>";
					});
				} else {
					htmlStr += "<tr><td colspan=\"4\" align=\"center\">데이터가 없습니다.</td></tr>";
				}


				$("#SECOND_TABLE").append(htmlStr);
				$("#SECOND_TABLE_TOTAL").html(data.menuList.length);
			}
		});
	}
</script>
<form id="frm" name="frm">
	<input type="hidden" id="upprMenuCd" value=""/>
	<input type="hidden" id="menuLv" value=""/>
</form>
<!-- container_start -->
<div id="container">
	<!-- left_menu_start -->
	<div id="left-navi">
		<h2><span>메뉴관리</span></h2>
		<ul id="lnb">
			<li class="lnb-on"><a href="#" class="color-white bold" title="메뉴관리">메뉴관리</a></li>
		</ul>
	</div>
	<hr />
	<!-- left_menu_end -->
	<!-- contents_start -->
	<div id="contents">
		<!-- contents_title_start -->
		<div class="title-area">
			<h3>메뉴관리</h3>
			<!-- navigation_start -->
			<div class="top_menu_nav">
				HOME > 메뉴관리 > 메뉴관리
			</div>
			<!-- navigation_end -->
		</div>
		<!-- contents_title_end -->
		<!-- meunu_manager_start -->
		<div class="con-detail">
			<div class="sub-left" style="width:600;">
				<div class="txt-bold">1차메뉴</div>
				<div class="sub-tit">
					<span class="color_wine" style="float:left">TOTAL : <span>${fn:length(menuList) }</span> 건 </span>
					<div class="com_table_board-list">
						<span><a href="javascript:menuRegistView('firstMenuRegist.do', '1차 메뉴 등록');" class="btn-wine-12">등록</a></span>
					</div>
				</div>
				<div class="sub-table">
					<div class="sub-list-style1-th">
						<table style="width:309px;" summary="1차메뉴">
							<caption>1차메뉴</caption>
							<colgroup>
								<col width="15%" /><col width="45%" /><col width="15%" /><col width="15%" />
							</colgroup>
							<thead>
								<tr>
									<td class="txt-center">번호</td>
									<td class="txt-center">메뉴명</td>
									<td class="txt-center">사용여부</td>
									<td class="txt-center">전시여부</td>
								</tr>
							</thead>
						</table>
					</div>

					<div class="sub-list-style1-td" style="height:500px;">
						<table style="width:309px;" summary="1차메뉴">
							<caption>1차메뉴</caption>
							<colgroup>
								<col width="15%" /><col width="45%" /><col width="15%" /><col width="15%" />
							</colgroup>
							<c:choose>
								<c:when test="${fn:length(menuList) > 0 }">
									<c:forEach var="menuList" items="${menuList }" varStatus="status">
										<tr style="cursor:pointer" onclick="javascript:secondMenuList('${menuList.menuCd}', 2);"
												ondblClick="javascript:menuModifyView('firstMenuModify.do', '1차 메뉴 수정', '${menuList.menuCd}', 1);">
											<td class="txt-center">${status.count }</td>
											<td class="txt-center">${menuList.menuNm }</td>
											<td class="txt-center">
												${menuList.useYn eq 'Y' ? '사용' : '사용안함' }
											</td>
											<td class="txt-center">
												${menuList.dpYn eq 'Y' ? '전시' : '전시안함' }

											</td>
										</tr>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<tr>
										<td colspan="4" align="center">
											데이터가 없습니다.
										</td>
									</tr>
								</c:otherwise>
							</c:choose>
						</table>
					</div>
				</div>
			</div>

			<div class="sub-left" style="width:600; padding-left:20px;">
				<div class="txt-bold">2차메뉴</div>
				<div class="sub-tit">
					<span class="color_wine" style="float:left">TOTAL : <span id="SECOND_TABLE_TOTAL">0</span> 건 </span>
					<div class="com_table_board-list">
						<span id="secondRegistBtn" style="display: none;"><a href="javascript:menuRegistView('secondMenuRegist.do', '2차 메뉴 등록');" class="btn-wine-12">등록</a></span>
					</div>
				</div>
				<div class="sub-table">
					<div class="sub-list-style1-th">
						<table style="width:309px;" summary="2차메뉴">
							<caption>2차메뉴</caption>
							<colgroup>
								<col width="15%" /><col width="45%" /><col width="15%" /><col width="15%" />
							</colgroup>
							<thead>
								<tr>
									<td class="txt-center">번호</td>
									<td class="txt-center">메뉴명</td>
									<td class="txt-center">사용여부</td>
									<td class="txt-center">전시여부</td>
								</tr>
							</thead>
						</table>
					</div>

					<div class="sub-list-style1-td" style="height:500px;">
						<table style="width:309px;" summary="2차메뉴">
							<caption>2차메뉴</caption>
							<colgroup>
								<col width="15%" /><col width="45%" /><col width="15%" /><col width="15%" />
							</colgroup>
							<tbody id="SECOND_TABLE">

							</tbody>
						</table>
					</div>
				</div>
			</div>

		</div>
		<!-- menu_manager_end -->
	</div>
	<!-- contents_end -->

	<hr />
</div>
<!-- container_end -->