<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.UserProfileDAO" %>
<%@ page import="model.UserProfile" %>
<%
    if (session == null || session.getAttribute("admin") == null) {
        response.sendRedirect("Login.jsp");
        return;
    }

    int userId = Integer.parseInt(request.getParameter("userId"));
    UserProfileDAO profileDao = new UserProfileDAO();
    UserProfile profile = profileDao.getProfileById(userId);
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <title>사용자 수정</title>
    <link rel="stylesheet" type="text/css" href="CSS/StyleAdmin.css">
</head>
<body>
    <div class="container">
        <h1 class="title">사용자 수정</h1>
        
        <!-- 사용자 수정 폼 -->
        <form action="UpdateUserController" method="POST" class="form-box">
            <!-- 사용자 ID -->
            <input type="hidden" name="userId" value="<%= profile.getUserId() %>">

            <!-- 이름 입력 -->
            <div class="input-group">
                <label for="name">이름:</label>
                <input type="text" id="name" name="name" value="<%= profile.getName() %>" required>
            </div>

            <!-- 성별 선택 -->
            <div class="input-group">
                <label for="gender">성별:</label>
                <select id="gender" name="gender" required>
                    <option value="male" <%= "male".equals(profile.getGender()) ? "selected" : "" %>>남성</option>
                    <option value="female" <%= "female".equals(profile.getGender()) ? "selected" : "" %>>여성</option>
                </select>
            </div>

            <!-- 생년월일 입력 -->
            <div class="input-group">
                <label for="birthDate">생년월일:</label>
                <input type="date" id="birthDate" name="birthDate" value="<%= profile.getBirthDate() %>" required>
            </div>

            <!-- 달력 유형 선택 -->
            <div class="input-group">
                <label for="calendarType">달력 유형:</label>
                <select id="calendarType" name="calendarType" required>
                    <option value="solar" <%= "solar".equals(profile.getCalendarType()) ? "selected" : "" %>>양력</option>
                    <option value="lunar" <%= "lunar".equals(profile.getCalendarType()) ? "selected" : "" %>>음력</option>
                </select>
            </div>

            <!-- 태어난 시간 입력 -->
            <div class="input-group">
                <label for="birthTime">태어난 시간:</label>
                <input type="time" id="birthTime" name="birthTime" value="<%= profile.getBirthTime() %>" required>
            </div>

            <!-- 저장 버튼 -->
            <button type="submit" class="btn-primary">저장</button>
        </form>

        <!-- 뒤로 가기 버튼 -->
        <div class="footer-buttons">
            <a href="AdminUserPage.jsp" class="link">뒤로 가기</a>
        </div>
    </div>
</body>
</html>