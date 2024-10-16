<%@ page import="java.util.Calendar" %>
<% Calendar today = Calendar.getInstance();
int year = today.get(Calendar.YEAR);%>
<div style="
	position:fixed;
	left:0;
	bottom:0;
	display:flex;
	justify-content:left;
	height:min(10vh,100px);
	width:100vw;
	background-color:navy;">
		<div style="
			height:min(10vh,100px);
			width:min(100px,20vw);
			background-color:green;
			font-size:80px;
			font-weight:bold;
			text-align:center;
			text-shadow:0 0 5px white;">
				&copy;
		</div>
		<h3 style="
			margin:20px;
			color:white;
			font-size:40px;
			text-shadow:8px 4px 10px white;">
				www.wu.com
		</h3>
		<p style="
			margin:20px;
			color:lightblue;">
				Copyright <%=year%> 
		</p>
</div>