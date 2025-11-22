<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원가입</title>
    <link rel="stylesheet" type="text/css" href="CSS/StyleLogin.css">
    <script>
        // 메시지를 팝업으로 표시하는 함수
        function showMessage(message) {
            if (message) {
                alert(message);
            }
        }
    </script>
</head>
<body>
    <div class="container">
    
        <h1>회원가입</h1>
        
        <form method="POST" action="SignupController">
        
        	<!-- 아이디 입력 -->
            <div class="input-group">
                <label for="login_id">아이디:</label>
                <input type="text" id="login_id" name="login_id" required>
            </div>
            
            <!-- 비밀번호 입력 -->
            <div class="input-group">
                <label for="password">비밀번호:</label>
                <input type="password" id="password" name="password" required>
            </div>
            
            <button type="submit">회원가입</button>
        </form>
        <br>
        
        <a href="Login.jsp" class="link">로그인 페이지로 돌아가기</a>
    </div>

    <%
        // 회원가입 오류 메시지 표시
        String error = request.getParameter("error");
        if ("duplicate_id".equals(error)) {
    %>
            <script>
                showMessage("이미 존재하는 아이디입니다. 다른 아이디를 사용하세요.");
            </script>
    <%
        } else if ("invalid_id".equals(error)) {
    %>
            <script>
                showMessage("아이디는 5자 이상이어야 하며, 영문자와 숫자만 사용할 수 있습니다.");
            </script>
    <%
        }
    %>
</body>
</html>