package com.reviewfinder.json;

import java.io.IOException;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.reviewfinder.movie.dao.MovieDTO;

// 영화 이름, 상영날짜를 넘겨받아서
// 장르, 줄거리, 포스터, 스틸이미지 가져올 클래스
public class KoreafilmJson extends APIController{	
	String url = "http://api.koreafilm.or.kr/openapi-data2/wisenut/search_api/search_json2.jsp";
	String keytype = "ServiceKey";
	String key = "066GO60N254R5Q89BD66";
	HashMap<String, String> url_key = new HashMap<String, String>();
	HashMap<String, String> var_data = new HashMap<String, String>();
	
	public KoreafilmJson(){
		url_key.put("url", url);
		url_key.put("keytype", keytype);
		url_key.put("key", key);
		var_data.put("var1", "collection");
		var_data.put("data1", "kmdb_new2");
	}
	
	public MovieDTO getMovieInfo(MovieDTO movie) throws IOException, ParseException {
		String title = movie.getMovie_title();
		String date = movie.getMovie_date();
		date = date.replace("-", "");
		
		var_data.put("var2", "title");
		var_data.put("data2", title);
		
		var_data.put("var3", "releaseDts");
		var_data.put("data3", date);
		
		String movieinfo = callAPI(url_key, var_data);
		
		JSONObject objData = (JSONObject)new JSONParser().parse(movieinfo);
		JSONArray data = (JSONArray)objData.get("Data");
		JSONObject data1 = (JSONObject)data.get(0);
		JSONArray result = (JSONArray)data1.get("Result");
		
		for(Object obj : result) {
			JSONObject movieresult = (JSONObject)obj;
			
			// 줄거리
			JSONObject plots = (JSONObject)movieresult.get("plots");
			JSONArray plot = (JSONArray)plots.get("plot");
			JSONObject plot1 = (JSONObject)plot.get(0);
			String plotText = (String)plot1.get("plotText");
			
			movie.setMovie_plot(plotText);
			
			// 장르
			String genre = (String)movieresult.get("genre");
			movie.setMovie_genre(genre);
			
			// 포스터
			String posters = (String)movieresult.get("posters");
			movie.setMovie_poster(posters);
			
			// 스틸 이미지
			String stlls = (String)movieresult.get("stlls");
			movie.setMovie_still_image(stlls);
		}
		return movie;
	}

}












