<% 
int x = 3;
int y = 4;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
input {
	width:50px;
	height:50px;
}
.output{
	width:200px;
	height:50px;
	background: #e9e9e9;
	font-size : 24px;
	font-weight:bold;
	text-align :right;
	padding: 0px 5px;
}
</style>
</head>
<body>
	<div>
		<form action="calc3" method="POST">
			<table>
				<tr>
					<td colspan="4" class="output"><%=x*y %></td>
				</tr>
				<tr>
					<td><input type="submit" name="operator" value="CE"></td>
					<td><input type="submit" name="operator" value="C"></td>
					<td><input type="submit" name="operator" value="BS"></td>
					<td><input type="submit" name="operator" value="/"></td>
				</tr>
				<tr>
					<td><input type="submit" name="value" value="7"></td>
					<td><input type="submit" name="value" value="8"></td>
					<td><input type="submit" name="value" value="9"></td>
					<td><input type="submit" name="operator" value="*"></td>
				</tr>
				<tr>
					<td><input type="submit" name="value" value="4"></td>
					<td><input type="submit" name="value" value="5"></td>
					<td><input type="submit" name="value" value="6"></td>
					<td><input type="submit" name="operator" value="-"></td>
				</tr>
				<tr>
					<td><input type="submit" name="value" value="1"></td>
					<td><input type="submit" name="value" value="2"></td>
					<td><input type="submit" name="value" value="3"></td>
					<td><input type="submit" name="operator" value="+"></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" name="value" value="0"></td>
					<td><input type="submit" name="dot" value="."></td>
					<td><input type="submit" name="operator" value="="></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>