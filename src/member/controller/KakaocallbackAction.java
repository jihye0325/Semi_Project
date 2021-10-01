package member.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;

public class KakaocallbackAction /* implements Action */{
	@Override
	public void execute(HttpServletRequest request, HttpServletRequest response) throws ServletException, IOException {
		String code = request.getParameter("code");

		String endpoint = "https://kauth.kakao.com/oauth/token";
		URL url = new URL(endpoint);

		String bodyData = "grant_type=authorization_code&";
		bodyData += "client_id={앱키 넣기!}&";
		bodyData += "redirect_uri=http://localhost:8800/UnI_Jeju/login/kakaoLogin?cmd=callback&";
		bodyData += "code=" + code;

		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		conn.setDoOutput(true);

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
		bw.write(bodyData);
		bw.flush();

		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		String input = "";
		StringBuilder sb = new StringBuilder();
		while((input=br.readLine()) != null) {
			sb.append(input);
		}
		System.out.println(sb.toString());
		
		Gson gson = new Gson();
		
		OAuthToken oAuthToken = gson.fromJson(sb.toString(), OAuthToken.class);
	}
}