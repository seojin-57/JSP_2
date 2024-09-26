package com.reviewfinder.json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
	
	// 장르별 영화 검색
	public List<MovieDTO> searchToGenre(String genre) throws IOException, ParseException{
		List<MovieDTO> movieList = new ArrayList<MovieDTO>();
		MovieDTO moviedto = null;
		
		var_data.put("var2", "genre");
		var_data.put("data2", genre);
		var_data.put("var3", "releaseDts");
		var_data.put("data3", "20200101");
		
		String movie = callAPI(url_key, var_data);
		
		JSONObject objData = (JSONObject)new JSONParser().parse(movie);
		JSONArray data = (JSONArray)objData.get("Data");
		
		// data 돌면서 result배열에서 title 빼오기, 
		// result배열의 ratings객체의 rating배열 0번째 releaseDate (substring(0,8) 할것) 빼오기
		// title이랑 date 구했으면 getMovieInfo 메서드에 dto 넣고 상세정보까지 가져오기
		for(Object obj : data) {
			JSONObject movieData = (JSONObject)obj;
			
			JSONArray result = (JSONArray)movieData.get("Result");
			
			for(Object obj2 : result) {
				JSONObject movieData2 = (JSONObject)obj2;
				moviedto = new MovieDTO();
				
				String title = (String)movieData2.get("title");
				// 개봉일자 빼기
				JSONObject ratings = (JSONObject)movieData2.get("ratings");
				JSONObject rating = (JSONObject)((JSONArray)ratings.get("rating")).get(0);
				String date = (String)rating.get("releaseDate");
				date = date.substring(0,8);
				
				moviedto.setMovie_title(title);
				moviedto.setMovie_date(date);
				
				moviedto = getMovieInfo(moviedto);
				movieList.add(moviedto);
			}
			
		}
		
		return movieList;
	}
	
	// 영화 상세정보 가져와서 MovieDTO로 반환
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
		if(result != null) {
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
		}
		return movie;
	}

}












