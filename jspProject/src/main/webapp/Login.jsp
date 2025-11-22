<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>로그인</title>
    <link rel="stylesheet" type="text/css" href="CSS/StyleLogin.css">
    <script>
    
        // 메시지 팝업으로 표시
        function showMessage(message) {
            if (message) {
                alert(message);
            }
        }
    </script>
</head>
<body>
    <div class="container">
    
        <h1>로그인</h1>
        
        <form method="POST" action="LoginController">
        
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
            
            <button type="submit">로그인</button>
        </form>
        <br>
        
        <a href="Signup.jsp" class="link">계정 만들기</a>
    </div>

    <%
        // 회원가입 성공 메시지 표시
        String signup = request.getParameter("signup");
        if ("success".equals(signup)) {
    %>
            <script>
                showMessage("회원가입이 성공적으로 완료되었습니다. 이제 로그인하세요!");
            </script>
    <%
        }

        // 로그인 오류 메시지 표시
        String error = request.getParameter("error");
        if (error != null) {
            if ("no_id".equals(error)) {
    %>
                <script>
                    showMessage("존재하지 않는 아이디입니다.");
                </script>
    <%
            } else if ("wrong_password".equals(error)) {
    %>
                <script>
                    showMessage("비밀번호가 틀렸습니다.");
                </script>
    <%
            }
        }
    %>
</body>
</html>