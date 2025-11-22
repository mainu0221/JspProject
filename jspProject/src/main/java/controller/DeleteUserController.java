package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FortuneDAO;
import dao.UserDAO;
import dao.UserProfileDAO;

@WebServlet("/DeleteUserController")
public class DeleteUserController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		try {
			int userId = Integer.parseInt(request.getParameter("userId"));

			// 1. 운세 기록 삭제
			FortuneDAO fortuneDao = new FortuneDAO();
			fortuneDao.deleteRecordsByUserId(userId);

			// 2. 사용자 프로필 삭제
			UserProfileDAO profileDao = new UserProfileDAO();
			profileDao.deleteProfileByUserId(userId);

			// 3. 사용자 로그인 정보 삭제
			UserDAO userDao = new UserDAO();
			userDao.deleteUserById(userId);

			// 삭제 완료 후 관리자 페이지로 리다이렉트
			response.sendRedirect("AdminUserPage.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "사용자 삭제 중 오류 발생");
		}
	}
}
