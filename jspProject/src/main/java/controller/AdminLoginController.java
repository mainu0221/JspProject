package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/AdminLoginController")
public class AdminLoginController extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String loginId = request.getParameter("login_id");
		String password = request.getParameter("password");

		// Admin 계정 확인
		if ("admin".equals(loginId) && "admin".equals(password)) {
			HttpSession session = request.getSession();
			session.setAttribute("admin", true); // 관리자 세션 설정
			response.sendRedirect("AdminPage.jsp");
		} else {
			response.sendRedirect("AdminLogin.jsp?error=invalid");
		}
	}
}
