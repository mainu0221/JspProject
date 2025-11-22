<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>운세 결과</title>
    <link rel="stylesheet" type="text/css" href="CSS/StyleFortune.css">
</head>
<body>
    <div class="container">
        <%
            String name = (String) request.getAttribute("name");
            String fortune = (String) request.getAttribute("fortune");
            Integer recordId = (Integer) request.getAttribute("recordId");
        %>

        <h1 class="title">오늘의 운세 결과</h1>
        <p class="fortune-text"><strong><%= name %> 님의 운세는...</strong></p>
        <p class="fortune-result"><%= fortune %></p>

        <div class="link-group">
        
            <!-- 기록 보기 버튼 -->
            <a href="Records.jsp" class="link">기록 보기</a>
            
            <!-- 다시 입력하기 버튼 -->
            <a href="LoadUserProfileController" class="link">다시 입력하기</a>
        </div>
    </div>
</body>
</html>
