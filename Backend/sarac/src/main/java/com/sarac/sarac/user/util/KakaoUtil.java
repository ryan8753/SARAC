package com.sarac.sarac.user.util;
import com.google.gson.JsonParser;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@Component
public class KakaoUtil {

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String secretKey;

    @Value("${kakao.admin}")
    private String adminkey;


    public void kakaoLogout(Long id) {
        String reqURL = "https://kapi.kakao.com/v1/user/logout";
//        Long longId = Long.valueOf(id);
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            conn.setRequestProperty("Authorization", "KakaoAK " + adminkey);
            conn.setDoOutput(true);

            //POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("target_id_type=user_id");
            sb.append("&target_id="+id);

            bw.write(sb.toString());
            bw.flush();

            int responseCode = conn.getResponseCode();
            System.out.println("responseCode = " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String result = "";
            String line = "";

            while((line = br.readLine()) != null) {
                result+=line;
            }
            log.info(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void unlink(Long id) {
        String reqURL = "https://kapi.kakao.com/v1/user/unlink";
        log.info("회원아이디"+id);
        log.info("앱키"+adminkey);
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            conn.setRequestProperty("Authorization", "KakaoAK " + adminkey);
            conn.setDoOutput(true);

            //POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("target_id_type=user_id");
            sb.append("&target_id="+id);
            log.info("주소"+sb.toString());

            bw.write(sb.toString());
            bw.flush();


            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String result = "";
            String line = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println(result);
            br.close();
//            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
