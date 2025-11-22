package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import model.User;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String loginId = request.getParameter("login_id");
		String password = request.getParameter("password");

		try {
			UserDAO dao = new UserDAO();

			// Step 1: 아이디 존재 여부 확인
			if (!dao.doesUserExist(loginId)) {
				response.sendRedirect("Login.jsp?error=no_id");
				return;
			}

			// Step 2: 비밀번호 확인
			User user = dao.authenticateUser(loginId, password);
			if (user != null) {
				// 로그인 성공 - 세션에 사용자 정보 저장
				HttpSession session = request.getSession();
				session.setAttribute("userId", user.getId());
				session.setAttribute("loginId", user.getLoginId());

				// 관리자인 경우 AdminUserPage로 리다이렉트
				if ("admin".equals(loginId) && "admin".equals(password)) {
					session.setAttribute("admin", true);
					response.sendRedirect("AdminUserPage.jsp");
				} else {
					// 일반 사용자일 경우 프로필 로드 페이지로 리다이렉트
					response.sendRedirect("LoadUserProfileController");
				}
			} else {
				// 비밀번호 불일치
				response.sendRedirect("Login.jsp?error=wrong_password");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "로그인 중 오류 발생");
		}
	}
}
