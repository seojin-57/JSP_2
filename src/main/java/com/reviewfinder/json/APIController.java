package com.reviewfinder.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

public class APIController {
	// 기본URL + 요청 인자로 api 호출
	public String callAPI(HashMap<String, String> url_key ,HashMap<String, String> var_data) throws IOException {
		StringBuilder urlBuilder = new StringBuilder(url_key.get("url"));
		urlBuilder.append("?" + URLEncoder.encode(url_key.get("keytype"), "UTF-8") + "=" + url_key.get("key"));
		
		for(int i=1;i<=var_data.size()/2;i++) {
			urlBuilder.append("&" + URLEncoder.encode(var_data.get("var"+i), "UTF-8") + "=" + URLEncoder.encode(var_data.get("data"+i),"UTF-8"));
		}
		URL url = new URL(urlBuilder.toString());
		if(url_key.get("url").equals("https://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json")) {
			System.out.println(urlBuilder);
		}
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		// 상태 코드 출력
		 System.out.println("Response code: " + conn.getResponseCode());
		 if(conn.getResponseCode()!=200) {
			return callAPI(url_key, var_data);
		 }
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();
		return sb.toString();
	}

}
