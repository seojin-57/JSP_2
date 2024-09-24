package com.reviewfinder.json;

import java.io.IOException;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.reviewfinder.movie.dao.MovieDTO;

// 이름과 상영날짜로 영화 검색을 최초로 실행되는 클래스
// 최종적으로 movie를 return 해줄 클래스 [ex) 박스오피스 순위 별, {이름, 상영날짜} 건네 받고]
public class KobisJson extends APIController{
	String url = "https://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json";
	String keytype = "key";
	String key = "b841d040f088509344cac5574340c4cd";
	HashMap<String, String> url_key = new HashMap<String, String>();
	HashMap<String, String> var_data = new HashMap<String, String>();
	
	public KobisJson() {
		url_key.put("url", url);
		url_key.put("keytype", keytype);
		url_key.put("key", key);
	}
	
	public HashMap<String, MovieDTO> getBoxOffice() throws IOException, ParseException {
		String var = "targetDt";
		String data = "20240923";
		var_data.put("var1", var);
		var_data.put("data1", data);
		
		String boxOfficeData = callAPI(url_key, var_data);
		MovieDTO movie = null;
		HashMap<String, MovieDTO> boxOfficeList = new HashMap<>();
		
		JSONObject objData = (JSONObject)new JSONParser().parse(boxOfficeData);
		JSONObject boxOfficeResult = (JSONObject)objData.get("boxOfficeResult");
		
		JSONArray dailyBoxOfficeList = (JSONArray)boxOfficeResult.get("dailyBoxOfficeList");
		for(Object obj : dailyBoxOfficeList) {
			JSONObject movieData = (JSONObject)obj;
			movie = new MovieDTO();
			
			System.out.println((String)movieData.get("rank"));
			System.out.println((String)movieData.get("movieNm"));
			
			movie.setMovie_title((String)movieData.get("movieNm"));
			movie.setMovie_date((String)movieData.get("openDt"));
			// 영화 상세정보 전부 받아오기
			movie = new KoreafilmJson().getMovieInfo(movie);
			
//			System.out.println(movie.getMovie_plot());
			
			boxOfficeList.put((String)movieData.get("rank"), movie);
		}
		return boxOfficeList;
	}
	
	
}








