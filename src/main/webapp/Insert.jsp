<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*, com.ispan.wu.bean.AtmBean"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>新增郵局ATM資料</title>
<link rel="stylesheet" href="styles/index.css">
<style>
.main {
	flex-direction: column;
}

input[type=file], #back {
	margin-top: 30px;
	margin-left: 50px;
	padding: 0;
}

input[type=file] {
	padding: 2px 5px;
	border: 1px solid black;
	box-shadow: 0px 0px 2px black;
}

input[type=checkbox]{
margin:10px 10px;
padding: 2px 5px;
}

input[type=text]{
width:250px;
}

form {
	margin: 20px 0;
}

table {
	width: min(50vw, 500px);
}

.td1 {
	padding-right: 5px;
	text-align: right;
}

label {
	font-weight: bold;
}

.inlineBlock {
	displat: inline-block;
}

.none {
	display: none;
}
</style>
</head>

<body>
	<%
	request.getRequestDispatcher("QueryAllCity").include(request, response);
	%>
	<div class="main">
		<h2>新增郵局ATM資料</h2>
		
		<h3 style="color: red;">${result.msg}</h3>

		<form method="post" action="Insert">
			<table>
				<tr>
					<td class="td1"><label>資料編號</label>
					<td><input type="text" name="key" disabled value="(由系統產生)" /><br>
				<tr>
					<td class="td1"><label>縣市</label>
					<td><select id="cities" name="city" onchange="changeCity()">
							<c:forEach items="${cities}" var="city" varStatus="s">
								<option value="${city.cityNo},${city.city}">${city.city}</option>
							</c:forEach>
					</select><br>
				<tr>
					<td class="td1"><label>鄉鎮市區</label>
					<td><input type="text" name="district" maxlength="4" placeholder="請輸入鄉鎮市區名稱(4字以內)" /><br>
				<tr>
					<td class="td1"><label>儲匯局號</label>
					<td><input type="text" name="postNo" maxlength="9" placeholder="請輸入儲匯局號(8位數字包含'-')" /><br>
				<tr>
					<td class="td1"><label>局名</label>
					<td><input type="text" name="postName" maxlength="30" placeholder="請輸入郵局名稱或ATM位置(30字內)" /><br>
				<tr>
					<td class="td1"><label>聯絡電話</label>
					<td><input type="text" name="phone" maxlength="20" placeholder="請輸入電話號碼，可包含分機(20字以內)" /><br>
				<tr>
					<td class="td1"><label>郵局地址</label>
					<td><input type="text" name="address" maxlength="50" placeholder="請輸入地址(50字內)" /><br>
				<tr>
					<td class="td1"><label>經度</label>
					<td><input type="text" name="longitude" maxlength="10"placeholder="請輸入位置經度(小數下6位)" /><br>
				<tr>
					<td class="td1"><label>緯度</label>
					<td><input type="text" name="latitude" maxlength="10" placeholder="請輸入位置緯度(小數下6位)" /><br>
				<tr>
					<td class="td1"><label>ATM</label>
					<td><input type="checkbox" name="hasAtm" value="true" /><br>
				<tr>
					<td class="td1"><label>具存款</label>
					<td><input type="checkbox" name="hasDeposit" value="true" /><br>
				<tr>
					<td class="td1"><label>具補摺</label>
					<td><input type="checkbox" name="hasPassbookUpdate" value="true" /><br>
				<tr>
					<td class="td1"><label>自動補摺機</label>
					<td><input type="checkbox" name="hasPassbookUpdateMachine" value="true" /><br>
				<tr>
					<td class="td1"><label>提領200元</label>
					<td><input type="checkbox" name="has200cash" value="true" /><br>
				<tr>
					<td class="td1"><label>具視障語音</label>
					<td><input type="checkbox" name="hasVoiceAssistant" value="true" /><br>
				<tr>
					<td class="td1"><label>是否為局外ATM</label>
					<td><input type="checkbox" name="isOutside" value="true" /><br>
				<tr>
					<td class="td1"><label>縣市排序代號</label>
					<td><input type="text" style="display: none;" id="cityNo999" name="cityNo" value="0" /> 
					<input class="cityNo" type="text" id="cityNo-1" name="cityNo" disabled value="0" />
					<c:forEach items="${cities}" var="city" varStatus="s">
					<input class="cityNo none" type="text" id="cityNo${city.cityNo}" name="cityNo" disabled value="${city.cityNo}" />
					</c:forEach><br>
				<tr>
					<td class="td1"><label>資料建立時間</label>
					<td><input type="text" name="insertTime" disabled value="(由系統產生)" /><br>
				<tr>
					<td colspan="2"><input type="submit" value="確定新增" />
			</table>
		</form>

		<form method="post" enctype="multipart/form-data" action="InsertFile">
			<input type="file" name="file" id="file" value="Upload"
				accept="text/csv" onchange="checkFileEmpty()" /> <input
				type="submit" id="upload" value="上傳" disabled />
		</form>
		<div id="back">
			<input type="submit" value="回上頁" onclick="history.back()">
		</div>
	</div>
	<div style="height:100px;"></div>
</body>
<script>
	function checkFileEmpty() {
		const UPLOAD_BUTTON = document.getElementById("upload");
		UPLOAD_BUTTON.disabled = !document.getElementById("file").files.length;
		console.log(document.getElementById("file").files.length);
		console.log(UPLOAD_BUTTON.disabled);
	}

	function changeCity() {
		let currentCityNo = document.getElementById("cities").value.split(",")[0];

		let cityNos = document.getElementsByClassName("cityNo");
		for (let i = 0; i < cityNos.length; i++) {
			const cityNo = cityNos[i];
			cityNo.className = "cityNo none";
		}
		document.getElementById("cityNo999").value = document
				.getElementById("cityNo" + currentCityNo).value;
		document.getElementById("cityNo" + currentCityNo).className = "cityNo inlineBlock";
	}
</script>
</html>