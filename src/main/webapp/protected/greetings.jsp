<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%

Object slackName = session.getAttribute(com.poc.AuthFilter.SLACK_USER_NAME);
String message = "";
String userPersistentId = "";
String userAvatar = "";
if(slackName == null) {
	message = "FORBIDDEN";
} else {
	message = "Vous &ecirc;tes identifi&eacute;(e) " + slackName.toString();
	userPersistentId = session.getAttribute(com.poc.AuthFilter.SLACK_USER_ID).toString();
	userAvatar = session.getAttribute(com.poc.AuthFilter.SLACK_USER_AVATAR).toString();
}

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Protected</title>
</head>
<body>

<h1><%= message %> </h1>
<img src="<%= userAvatar %>" />
<h2>Id slack pour g&eacute;rer la persistence <%= userPersistentId %></h2>
<h3>Ici on pourra d&eacute;velopper nos applications...</h3>
<a href="https://crypto-trading-bot.slack.com/messages">acc&egrave;s au slack crypto-trading-bot</a>

</body>
</html>