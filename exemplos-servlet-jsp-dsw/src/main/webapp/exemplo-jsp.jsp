<%@page import="java.time.LocalDateTime"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Exemplo JSP</title>
</head>
<body>
  <h1>Exemplo JSP + Scriptlet</h1>
  <p>Data e hora atual: <%= LocalDateTime.now() %></p>
  <ul>
      <% for (int i = 0; i < 1000; i++) { %>
      <li>Item <%= i %></li>
      <% } %>
  </ul>
</body>
</html>
