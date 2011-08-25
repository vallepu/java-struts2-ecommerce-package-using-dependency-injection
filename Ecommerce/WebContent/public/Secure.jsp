



<%@ page language="java" contentType="text/html" import="java.util.*"%>

<html>
<head>

<script type='text/javascript' src='./includes/verticalnavigation.js'></script>
<link rel="stylesheet" href="./includes/testverticalnavigation.css" type="text/css" media="screen" />

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Secure Page</title>

<script type="text/javascript">
if (document.images) {
	a_1 = new Image;
	a_1.src = "http://www.jsmadeeasy.com/javascripts/Images/picture_rollover/a_1.gif";
	a_2 = new Image;
	a_2.src = "http://www.jsmadeeasy.com/javascripts/Images/picture_rollover/a_2.gif";
	a_3 = new Image;
	a_3.src = "http://www.jsmadeeasy.com/javascripts/Images/picture_rollover/a_3.gif";
	a_4 = new Image;
	a_4.src = "http://www.jsmadeeasy.com/javascripts/Images/picture_rollover/a_4.gif";

	b_1 = new Image;
	b_1.src = "http://www.jsmadeeasy.com/javascripts/Images/picture_rollover/b_1.gif";
	b_2 = new Image;
	b_2.src = "http://www.jsmadeeasy.com/javascripts/Images/picture_rollover/b_2.gif";
	b_3 = new Image;
	b_3.src = "http://www.jsmadeeasy.com/javascripts/Images/picture_rollover/b_3.gif";
	b_4 = new Image;
	b_4.src = "http://www.jsmadeeasy.com/javascripts/Images/picture_rollover/b_4.gif";
}

function showImg(imgname, rollname)
{
	if (document.images) {
		window.onerror = null;
		document.getElementById(rollname).src=eval(imgname + ".src");
	}
}

</script>

 
</head>
<body>


<div align="center">
	<table border="0" cellpadding="0" cellspacing="0" width="85%">
		<tr>
			<td valign="top" width="74">
				<table border="0" cellpadding="3" cellspacing="0" width="100%" id="rolllist">
					<tr>
						<td><a href="#" onmouseover="showImg('a_1','roll_a');showImg('b_1','roll_b')"><img src="http://www.jsmadeeasy.com/javascripts/Images/picture_rollover/m1.gif" width="74" height="18" border="0"></a></td>
					</tr>
					<tr>
						<td><a href="#" onmouseover="showImg('a_2','roll_a');showImg('b_2','roll_b')"><img src="http://www.jsmadeeasy.com/javascripts/Images/picture_rollover/m2.gif" width="74" height="18" border="0"></a></td>
					</tr>
					<tr>
						<td><a href="#" onmouseover="showImg('a_3','roll_a');showImg('b_3','roll_b')"><img src="http://www.jsmadeeasy.com/javascripts/Images/picture_rollover/m3.gif" width="74" height="18" border="0"></a></td>
					</tr>
					<tr>
						<td><a href="#" onmouseover="showImg('a_4','roll_a');showImg('b_4','roll_b')"><img src="http://www.jsmadeeasy.com/javascripts/Images/picture_rollover/m4.gif" width="74" height="18" border="0"></a></td>
					</tr>
				</table>
			</td>
			<td align="center" width="426" valign="center" align="center">
				<img src="http://www.jsmadeeasy.com/javascripts/Images/picture_rollover/a_2.gif" id="roll_a">
			</td>
			<td valign="top" valign="center" align="left">
				<br><img src="http://www.jsmadeeasy.com/javascripts/Images/picture_rollover/b_blank.gif" id="roll_b">
			</td>
		</tr>
	</table>
</div>


</body>
</html>