package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserProfileDAO;
import model.UserProfile;

@WebServlet("/UpdateUserController")
public class UpdateUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");

		// 폼에서 전달받은 데이터
		int userId = Integer.parseInt(request.getParameter("userId"));
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String birthDate = request.getParameter("birthDate");
		String calendarType = request.getParameter("calendarType");
		String birthTime = request.getParameter("birthTime");

		// 데이터베이스 업데이트
		UserProfileDAO profileDao = new UserProfileDAO();
		UserProfile profile = new UserProfile(userId, name, gender, birthDate, calendarType, birthTime);
		boolean isUpdated = profileDao.updateProfile(profile);

		// 결과 처리
		if (isUpdated) {
			response.sendRedirect("AdminUserPage.jsp"); // 성공 시 사용자 관리 페이지로 리다이렉트
		} else {
			response.sendRedirect("AdminEditProfile.jsp?userId=" + userId); // 실패 시 수정 페이지로 다시 이동
		}
	}
}
