package com.sieun.metaChat.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.*;


@Service
public class LoginService {

    @Value("${spring.security.oauth2.client.provider.kakao.token-uri}")
    private String tokenUrl;
    String accessToken = "";
    String refreshToken = "";

    public String getToken(String code) throws IOException {
        URL url = new URL(tokenUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
        StringBuilder sb = new StringBuilder();
        sb.append("grant_type=authorization_code");
        sb.append("&client_id=51025a8c8275528d7b14d0cd98d5c445");  //앱 KEY VALUE
        sb.append("&redirect_uri=http://localhost:8080/login/kakao"); // 앱 CALLBACK 경로
        sb.append("&code=" + code);
        bw.write(sb.toString());
        bw.flush();

        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String br_line = "";
        StringBuilder result = new StringBuilder();

        while ((br_line = br.readLine()) != null) {
            result.append(br_line);
        }
        JsonParser parser = new JsonParser();
        String s = result.toString();
        JsonElement element = parser.parse(s);

        accessToken = element.getAsJsonObject().get("access_token").getAsString();
        refreshToken = element.getAsJsonObject().get("refresh_token").getAsString();
        br.close();
        bw.close();
        return accessToken;
    }

    public Map<String, Object> getUserInfo(String accessToken) {
        Map<String,Object> resultMap =new HashMap<>();
        String reqURL = "https://kapi.kakao.com/v2/user/me";
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            //요청에 필요한 Header에 포함될 내용
            conn.setRequestProperty("Authorization", "Bearer " + accessToken);

            int responseCode = conn.getResponseCode();
//            System.out.println("responseCode : " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String br_line = "";
            StringBuilder result = new StringBuilder();

            while ((br_line = br.readLine()) != null) {
                result.append(br_line);
            }
            System.out.println("response:" + result);


            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result.toString());
            JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
            JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
            String nickname = properties.getAsJsonObject().get("nickname").getAsString();
            String email = kakao_account.getAsJsonObject().get("email").getAsString();
            resultMap.put("nickname", nickname);
            resultMap.put("email", email);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultMap;
    }
}
