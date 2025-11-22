<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>오늘의 운세</title>
	<link rel="stylesheet" type="text/css" href="CSS/StyleFortune.css">
</head>
<body>
    <div class="container">
        <h1 class="title">오늘의 운세 입력</h1>

        <form method="POST" action="FortuneController" class="form-box">
            <!-- 이름 입력 -->
            <div class="input-group">
                <label for="name">이름:</label>
                <input type="text" id="name" name="name" 
                       value="<%= request.getAttribute("name") != null ? request.getAttribute("name") : "" %>" required>
            </div>

            <!-- 성별 선택 -->
            <div class="input-group">
                <label for="gender">성별:</label>
                <select id="gender" name="gender" required>
                    <option value="male" <%= "male".equals(request.getAttribute("gender")) ? "selected" : "" %>>남성</option>
                    <option value="female" <%= "female".equals(request.getAttribute("gender")) ? "selected" : "" %>>여성</option>
                </select>
            </div>

            <!-- 생년월일 입력 -->
            <div class="input-group">
                <label for="birthDate">생년월일:</label>
                <input type="date" id="birthDate" name="birthDate" 
                       value="<%= request.getAttribute("birthDate") != null ? request.getAttribute("birthDate") : "" %>" required>
            </div>

            <!-- 달력 유형 선택 -->
            <div class="input-group">
                <label for="calendarType">음력, 양력:</label>
                <select id="calendarType" name="calendarType" required>
                    <option value="solar" <%= "solar".equals(request.getAttribute("calendarType")) ? "selected" : "" %>>양력</option>
                    <option value="lunar" <%= "lunar".equals(request.getAttribute("calendarType")) ? "selected" : "" %>>음력</option>
                </select>
            </div>

            <!-- 태어난 시간 입력 -->
            <div class="input-group">
                <label for="birthTime">태어난 시간:</label>
                <input type="time" id="birthTime" name="birthTime" 
                       value="<%= request.getAttribute("birthTime") != null ? request.getAttribute("birthTime") : "" %>" required>
            </div>

            <!-- 운세 보기 버튼 -->
            <button type="submit" class="btn-primary">운세 보기</button>
        </form>

        <div class="link-group">
        
            <!-- 기록 보기 버튼 -->
            <a href="Records.jsp" class="link">기록 보기</a>
            
            <!-- 로그아웃 버튼 -->
            <a href="LogoutController" class="link">로그아웃</a>
        </div>
    </div>
</body>
</html>