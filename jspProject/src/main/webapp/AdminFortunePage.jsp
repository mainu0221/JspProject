<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="dao.FortuneDAO" %>
<%@ page import="dao.UserDAO" %>
<%@ page import="model.FortuneRecord" %>
<%@ page import="model.User" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%
    if (session == null || session.getAttribute("admin") == null) {
        response.sendRedirect("Login.jsp"); // 관리자가 아니면 로그인 페이지로 리다이렉트
        return;
    }

	//운세 기록 조회
    FortuneDAO fortuneDao = new FortuneDAO();
    List<FortuneRecord> records = fortuneDao.getAllRecords();
    
 	// 사용자 정보 조회
    UserDAO userDao = new UserDAO();
 	
    DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH시 mm분");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>관리자용 운세 기록 관리</title>
	<link rel="stylesheet" type="text/css" href="CSS/StyleAdmin.css">
</head>
<body>
    <div class="container">
        <h1 class="title">운세 기록 관리</h1>

        <div class="table-container">
            <table class="admin-table">
                <thead>
                    <tr>
                        <th class="col-user-id">사용자<br>아이디</th>
                        <th class="col-name">이름</th>
                        <th>운세 내용</th>
                        <th class="col-record-time">기록<br>시간</th>
                        <th class="col-delete">삭제</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (FortuneRecord record : records) {
                        User user = userDao.getUserById(record.getUserProfile().getUserId());
                        LocalDateTime createdAt = record.getCreatedAt();
                        String formattedDate = createdAt.format(formatterDate);
                        String formattedTime = createdAt.format(formatterTime);
                    %>
                        <tr>
                            <td><%= user.getLoginId() %></td>
                            <td><%= record.getUserProfile().getName() %></td>
                            <td class="fortune-content"><%= record.getFortune() %></td>
                            <td><%= formattedDate %><br><%= formattedTime %></td>
                            <td>
                            	<!-- 각각 삭제 버튼 -->
                                <form action="DeleteFortuneController" method="GET" class="inline-form">
                                    <input type="hidden" name="recordId" value="<%= record.getId() %>">
                                    <button type="submit" class="btn-delete">삭제</button>
                                </form>
                            </td>
                        </tr>
                    <% } %>
                </tbody>
            </table>
        </div>

        <div class="footer-buttons">
        
        	<!-- 사용자 관리 넘어가기 버튼 -->
            <a href="AdminUserPage.jsp" class="link">사용자 관리</a>
            
            <!-- 로그아웃 버튼 -->
            <a href="LogoutController" class="link">로그아웃</a>
        </div>
    </div>
</body>
</html>