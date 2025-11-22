package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserProfileDAO;
import model.UserProfile;

@WebServlet("/LoadUserProfileController")
public class LoadUserProfileController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("userId") == null) {
			response.sendRedirect("Login.jsp");
			return;
		}

		try {
			// 세션에서 사용자 ID 가져오기
			int userId = (Integer) session.getAttribute("userId");

			// DB에서 사용자 프로필 조회
			UserProfileDAO profileDao = new UserProfileDAO();
			UserProfile profile = profileDao.getProfileById(userId);

			// 프로필이 존재하면 요청 속성으로 전달
			if (profile != null) {
				request.setAttribute("name", profile.getName());
				request.setAttribute("gender", profile.getGender());
				request.setAttribute("birthDate", profile.getBirthDate());
				request.setAttribute("calendarType", profile.getCalendarType());
				request.setAttribute("birthTime", profile.getBirthTime());
			}

			// Input.jsp로 이동
			request.getRequestDispatcher("Input.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "프로필 로드 중 오류 발생");
		}
	}
}
