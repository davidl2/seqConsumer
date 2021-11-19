<!DOCTYPE HTML><%@page language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<title>Sequence Consumer demo</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
   
   
   %>
</head>
<body background="bg.jpg" style="background-image:url(bg.jpg)" >
  <h1>Sequence Consumer demo</h1>

  <p>
  Initial value of sequence: <span style="font-family: 'Courier New', monospace" ><%= (config.getServletContext().getAttribute("com.lmert.seqConsumer.initialValue")) %></span>

  <p>
    <form action="seq0" method="POST">
      <input type="submit" value="Get New Value" />
    </form>
</body>
</html>
