package com.sarac.sarac.book.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.sarac.sarac.book.util.dto.AladinDto;
import com.sarac.sarac.book.util.dto.AladinErrorDto;
import com.sarac.sarac.book.util.dto.AladinResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class AladinUtil {

    @Value("${aladin.ttbkey}")
    private String ttbkey;

    private final String IO_ERROR = "IO EXCEPTION OCCURRED";

    private final String ERROR_CODE = "errorCode";

    @SneakyThrows(JsonProcessingException.class)
    public AladinResponse getBookDetailData(String isbn) {
        ObjectMapper objectMapper = JsonMapper.builder()
                .enable(JsonReadFeature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER)
                .build();

        String jsonResult = getBookJsonString(isbn);

        if(IO_ERROR.equals(jsonResult))
            return new AladinErrorDto();

        if(jsonResult.contains(ERROR_CODE))
            return objectMapper.readValue(jsonResult, AladinErrorDto.class);

        return objectMapper.readValue(jsonResult, AladinDto.class);
    }

    private String getBookJsonString(String isbn) {
        String reqUrl = "http://www.aladin.co.kr/ttb/api/ItemLookUp.aspx?ttbkey="
                            + ttbkey + "&itemIdType=ISBN13&ItemId=" + isbn + "&output=js";

        try {
            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);
            conn.setDoInput(true);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));

            return br.readLine();

        } catch (IOException e) {
            log.error(e.getMessage());
            return IO_ERROR;
        }
    }


}
