package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;

@WebServlet("/SignupController")
public class SignupController extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String loginId = request.getParameter("login_id");
		String password = request.getParameter("password");

		try {
			UserDAO dao = new UserDAO();

			// Step 1: 아이디 중복 확인
			if (dao.doesUserExist(loginId)) {
				response.sendRedirect("Signup.jsp?error=duplicate_id");
				return;
			}

			// Step 2: 아이디 검증 (예: 최소 5자 이상, 영문+숫자 조합)
			if (!isValidLoginId(loginId)) {
				response.sendRedirect("Signup.jsp?error=invalid_id");
				return;
			}

			// Step 3: 회원가입 진행
			dao.registerUser(loginId, password);

			// 회원가입 성공 시 로그인 페이지로 리다이렉트하고 성공 메시지 전달
			response.sendRedirect("Login.jsp?signup=success");
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "회원가입 중 오류 발생");
		}
	}

	// 아이디 검증 로직
	private boolean isValidLoginId(String loginId) {
		// 아이디는 5자 이상, 알파벳과 숫자로만 구성
		return loginId.matches("^[a-zA-Z0-9]{5,}$");
	}
}
