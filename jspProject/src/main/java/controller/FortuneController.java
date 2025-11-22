package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.FortuneDAO;
import dao.UserProfileDAO;
import util.OpenAIApi;

@WebServlet("/FortuneController")
public class FortuneController extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("userId") == null) {
			response.sendRedirect("Login.jsp");
			return;
		}

		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String birthDate = request.getParameter("birthDate");
		String calendarType = request.getParameter("calendarType");
		String birthTime = request.getParameter("birthTime");

		if (gender == null || gender.isEmpty()) {
			gender = "male"; // 기본값: 남성
		}
		if (calendarType == null || calendarType.isEmpty()) {
			calendarType = "solar"; // 기본값: 양력
		}

		try {
			int userId = (Integer) session.getAttribute("userId");

			// 1. 프로필 저장/업데이트
			UserProfileDAO profileDao = new UserProfileDAO();
			profileDao.saveOrUpdateProfile(userId, name, gender, birthDate, calendarType, birthTime);

			// 2. 운세 생성
			OpenAIApi api = new OpenAIApi();
			String fortune = api.getFortune(name, gender, birthDate, calendarType, birthTime);

			// 3. 운세 저장
			FortuneDAO fortuneDao = new FortuneDAO();
			fortuneDao.saveFortune(userId, fortune);

			// 4. 결과 페이지로 이동
			request.setAttribute("name", name);
			request.setAttribute("fortune", fortune);
			request.getRequestDispatcher("Result.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "운세 생성 중 오류 발생");
		}
	}
}
