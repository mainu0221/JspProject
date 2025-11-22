<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.List" %>
<%@ page import="dao.UserProfileDAO" %>
<%@ page import="dao.UserDAO" %>
<%@ page import="model.UserProfile" %>
<%@ page import="model.User" %>
<%
    if (session == null || session.getAttribute("admin") == null) {
        response.sendRedirect("Login.jsp"); // 관리자가 아니면 로그인 페이지로 리다이렉트
        return;
    }

    // 사용자 프로필 조회
    UserProfileDAO profileDao = new UserProfileDAO();
    List<UserProfile> profiles = profileDao.getAllProfiles();
    
 	// 사용자 정보 조회
    UserDAO userDao = new UserDAO();
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>관리자용 사용자 계정 관리</title>
	<link rel="stylesheet" type="text/css" href="CSS/StyleAdmin.css">
</head>
<body>
    <div class="container">
        <h1 class="title">사용자 계정 관리</h1>

        <div class="table-container">
            <table class="admin-table">
                <thead>
                    <tr>
                        <th>사용자 아이디</th>
                        <th>이름</th>
                        <th>성별</th>
                        <th>생년월일</th>
                        <th>달력 유형</th>
                        <th>출생 시간</th>
                        <th>수정</th>
                        <th>삭제</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (UserProfile profile : profiles) {
                        User user = userDao.getUserById(profile.getUserId());
                    %>
                        <tr>
                            <td><%= user.getLoginId() %></td>
                            <td><%= profile.getName() %></td>
                            <td><%= profile.getGender() %></td>
                            <td><%= profile.getBirthDate() %></td>
                            <td><%= profile.getCalendarType() %></td>
                            <td><%= profile.getBirthTime() %></td>
                            <td>
                                <!-- 각각 수정 버튼 -->
                                <form action="AdminEditProfile.jsp" method="GET" style="display:inline;">
                                    <input type="hidden" name="userId" value="<%= profile.getUserId() %>">
                                    <button type="submit" class="btn-primary">수정</button>
                                </form>
                            </td>
                            <td>
                                <!-- 각각 삭제 버튼 -->
                                <form action="DeleteUserController" method="GET" style="display:inline;">
                                    <input type="hidden" name="userId" value="<%= profile.getUserId() %>">
                                    <button type="submit" class="btn-delete">삭제</button>
                                </form>
                            </td>
                        </tr>
                    <% } %>
                </tbody>
            </table>
        </div>

        <div class="footer-buttons">
            <!-- 운세 기록 관리 넘어가기 버튼 -->
            <a href="AdminFortunePage.jsp" class="link">운세 기록 관리</a>

            <!-- 로그아웃 버튼 -->
            <a href="LogoutController" class="link right" style="margin-left: 20px;">로그아웃</a>
        </div>
    </div>
</body>
</html>