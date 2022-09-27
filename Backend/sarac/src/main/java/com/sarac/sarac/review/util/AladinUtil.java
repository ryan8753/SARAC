package com.sarac.sarac.review.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sarac.sarac.review.payload.response.AladinDTO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;

@Component
public class AladinUtil {

    @Value("${aladin.ttbkey}")
    private String ttbkey;

    public HashMap<String, Object> getBookInfo() {
        HashMap<String, Object> bookInfo = new HashMap<String, Object>();
        String reqUrl = "http://www.aladin.co.kr/ttb/api/ItemLookUp.aspx?ttbkey="+ttbkey+"&itemIdType=ISBN13&ItemId=9788950977962&output=js";
        try {
            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
//            conn.setRequestProperty("Content-Type","application/json");
            conn.setDoOutput(true);
            conn.setDoInput(true);
//            JsonObject jsonObject =


            int responseCode = conn.getResponseCode();
            System.out.println("responseCode =" + responseCode);



            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
//            System.out.println(br.readLine());

//            StringBuilder sb = new StringBuilder();
//
//
//            String result = "";
//            String line = "";
//
//            while((line = br.readLine()) != null) {
//                sb.append(br.readLine());
//            }
//            System.out.println(sb.toString());
            String json = br.readLine();
            json.replaceAll("&apm;", "&");
            System.out.println("json입니당"+json);

            ObjectMapper objectMapper = new ObjectMapper();

            JSONParser parser = new JSONParser();

            Object obj = parser.parse(json);

            JSONObject jsonObj = (JSONObject) obj;

//            String code = (String) jsonObj.get("item");

//            String name = (String) jsonObj.get("name");

//            JSONObject objData = (JSONObject)new JSONParser(). parse(br);
//            System.out.println(objData);

            // 첫 번째 JSONObject
//            JSONObject item = (JSONObject)objData.get("item");
            JSONArray item = (JSONArray)jsonObj.get("item");
//            System.out.println(Arrays.toString(item));

//
//            JsonObject jsonObject = JsonParser.parseString(result).getAsJsonObject();
//            System.out.println(jsonObject.toString());

//            JsonObject properties = jsonObject.get("item").getAsJsonObject();
//            System.out.println("88888888888properties"+properties);
//
//            String description = properties.getAsJsonObject().get("description").getAsString();
//            String price = properties.getAsJsonObject().get("pricesales").getAsString();
//            String id = jsonObject.get("id").getAsString();
//
//
//            bookInfo.put("description", description);
//            bookInfo.put("price",price);
//            bookInfo.put("id",id);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookInfo;
    }


}
