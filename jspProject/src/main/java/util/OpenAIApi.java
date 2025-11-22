package util;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class OpenAIApi {
	private static final String API_URL = "https://api.openai.com/v1/chat/completions";
	private static final String API_KEY = "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"; // OpenAI
																																																					// API
																																																					// 키

	public String getFortune(String name, String gender, String birthDate, String calendarType, String birthTime)
			throws Exception {

		String prompt = String.format(
				"무조건 공백 포함 300자 이내로 말해주세요! 중요합니다! 그리고 저의 이름은 %s이고, 성별은 %s입니다. 생년월일은 %s, 달력 유형은 %s이며, 태어난 시간은 %s입니다. "
						+ "이 정보를 바탕으로 오늘의 운세를 작성해주세요. " + "그리고 점술사 처럼 말해주세요. 오늘의 운세와 함께 행운의 숫자와 색깔도 알려주세요!",
				name, gender.equals("male") ? "남성" : "여성", birthDate, calendarType.equals("solar") ? "양력" : "음력",
				birthTime);

		JSONObject requestBody = new JSONObject();
		requestBody.put("model", "gpt-3.5-turbo");

		JSONArray messages = new JSONArray();
		JSONObject userMessage = new JSONObject();
		userMessage.put("role", "user");
		userMessage.put("content", prompt);
		messages.put(userMessage);
		requestBody.put("messages", messages);

		requestBody.put("max_tokens", 300);

		URL url = new URL(API_URL);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Authorization", "Bearer " + API_KEY);
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setDoOutput(true);

		try (OutputStream os = connection.getOutputStream()) {
			os.write(requestBody.toString().getBytes());
			os.flush();
		}

		int responseCode = connection.getResponseCode();
		if (responseCode != HttpURLConnection.HTTP_OK) {
			throw new RuntimeException("HTTP " + responseCode + ": OpenAI API 호출 실패");
		}

		try (Scanner scanner = new Scanner(connection.getInputStream(), "UTF-8")) {
			scanner.useDelimiter("\\A");
			String response = scanner.hasNext() ? scanner.next() : "";

			JSONObject jsonResponse = new JSONObject(response);
			return jsonResponse.getJSONArray("choices").getJSONObject(0).getJSONObject("message").getString("content")
					.trim();
		}
	}
}
