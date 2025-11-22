<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="dao.FortuneDAO, model.FortuneRecord, java.util.List, java.time.format.DateTimeFormatter" %>
<%
    if (session == null || session.getAttribute("userId") == null) {
        response.sendRedirect("Login.jsp");
        return;
    }

    int userId = (int) session.getAttribute("userId");
    FortuneDAO dao = new FortuneDAO();
    List<FortuneRecord> records = dao.getRecordsByUserId(userId);
    
    DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH시 mm분");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>운세 기록</title>
	<link rel="stylesheet" type="text/css" href="CSS/StyleFortune.css">
</head>
<body>
    <div class="container">
        <h1 class="title">운세 기록</h1>

        <table class="record-table">
            <thead>
                <tr>
                    <th class="col-fortune">운세</th>
                    <th class="col-date">생성 날짜</th>
                    <th class="col-action">삭제</th>
                </tr>
            </thead>
            <tbody>
                <% for (FortuneRecord record : records) { 
                    String formattedDate = record.getCreatedAt().format(formatterDate);
                    String formattedTime = record.getCreatedAt().format(formatterTime);
                %>
                    <tr>
                        <td class="col-fortune"><%= record.getFortune() %></td>
                        <td class="col-date"><%= formattedDate %><br><%= formattedTime %></td>
                        <td class="col-action">
                        
                        	<!-- 각각 삭제 버튼 -->
                            <form action="DeleteRecordController" method="POST" class="inline-form">
                                <input type="hidden" name="id" value="<%= record.getId() %>">
                                <button type="submit" class="btn-delete">삭제</button>
                            </form>
                        </td>
                    </tr>
                <% } %>
            </tbody>
        </table>

        <div class="link-group">
            <a href="LoadUserProfileController" class="link">돌아가기</a>
            <a href="LogoutController" class="link">로그아웃</a>
        </div>
    </div>
</body>
</html>