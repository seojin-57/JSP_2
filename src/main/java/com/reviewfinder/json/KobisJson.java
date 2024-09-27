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

// 이름과 상영날짜로 영화 검색을 최초로 실행되는 클래스
// 최종적으로 movie를 return 해줄 클래스 [ex) 박스오피스 순위 별, {이름, 상영날짜} 건네 받고]
public class KobisJson extends APIController{
	String keytype = "key";
	String key = "b841d040f088509344cac5574340c4cd";
	HashMap<String, String> url_key = new HashMap<String, String>();
	HashMap<String, String> var_data = new HashMap<String, String>();
	
	public KobisJson() {
		url_key.put("keytype", keytype);
		url_key.put("key", key);
	}
	
	// 제목으로 검색
	public List<MovieDTO> searchToMovieName(String movieNm) throws IOException, ParseException {
		List<MovieDTO> movieList = new ArrayList<MovieDTO>();
		MovieDTO moviedto = null;
		String url = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieList.json";
		url_key.put("url", url);
		
		String var = "movieNm";
		String data = movieNm;
		var_data.put("var1", var);
		var_data.put("data1", data);
		
		String movieListData = callAPI(url_key, var_data);
		
		JSONObject objData = (JSONObject)new JSONParser().parse(movieListData);
		
		JSONObject movieListResult = (JSONObject)objData.get("movieListResult");
		JSONArray movieListArr = (JSONArray)movieListResult.get("movieList");
		if(movieListArr.size()==0) { // 영화 리스트가 비어있는지 체크
			return null;
		}
		
		for(Object obj : movieListArr) {
			JSONObject movie = (JSONObject)obj;
			moviedto = new MovieDTO();
			
			String movieName = (String)movie.get("movieNm");
			String movieDate = (String)movie.get("openDt");
			
			if(movieDate.equals("")) {
				continue;
			}
			
			System.out.println(moviedto.getMovie_title()); 
			System.out.println(moviedto.getMovie_date()); 
			
			moviedto = new KoreafilmJson().getMovieInfo(moviedto);
			
			movieList.add(moviedto);
		}
		
		return movieList;
	}
	
	// 영화 추천에 쓸 영화 박스오피스 기간별로 총 50개 받아오기
	public List<MovieDTO> getRecommendList() throws IOException, ParseException{
		List<MovieDTO> movieList = new ArrayList<MovieDTO>();
		
		for(int i=0;i<5;i++) {
			HashMap<String, MovieDTO> movieMap = null;
			String date = null;
			switch(i) {
			case 0:
				date = "20230101"; 
				url_key.put("key", "6974fb00d854190ff2f7728d4a150966");
				break;
			case 1:
				date = "20230501";
				url_key.put("key", "b841d040f088509344cac5574340c4cd");
				break;
			case 2:
				date = "20230701";
				url_key.put("key", "6974fb00d854190ff2f7728d4a150966");
				break;
			case 3:
				date = "20240201";
				url_key.put("key", "b841d040f088509344cac5574340c4cd");
				break;
			case 4:
				date = "20240501";
				url_key.put("key", "6974fb00d854190ff2f7728d4a150966");
				break;
			}
			
			movieMap = getBoxOffice(date);
			for(int j=1;j<=10;j++) {
				movieList.add(movieMap.get(""+j+""));
			}
		}

		return movieList;
	}
	
	
	// 박스오피스 1~10위 가져오기
	public HashMap<String, MovieDTO> getBoxOffice(String date) throws IOException, ParseException {
		HashMap<String, MovieDTO> boxOfficeList = new HashMap<>();
		String url = "https://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json";
		url_key.put("url", url);
		
		String var = "targetDt";
		String data = "20240923";
		if(date != null) {
			data = date;
		}
		var_data.put("var1", var);
		var_data.put("data1", data);
		
		String boxOfficeData = callAPI(url_key, var_data);
		
		MovieDTO movie = null;
		
		JSONObject objData = (JSONObject)new JSONParser().parse(boxOfficeData);
		JSONObject boxOfficeResult = (JSONObject)objData.get("boxOfficeResult");
		
		JSONArray dailyBoxOfficeList = null;
		
		try{
			dailyBoxOfficeList = (JSONArray)boxOfficeResult.get("dailyBoxOfficeList");
		}catch (NullPointerException e) {
			getBoxOffice(date);
		}
		
		for(Object obj : dailyBoxOfficeList) {
			JSONObject movieData = (JSONObject)obj;
			movie = new MovieDTO();
			
			movie.setMovie_title((String)movieData.get("movieNm"));
			movie.setMovie_date((String)movieData.get("openDt"));
			// 영화 상세정보 전부 받아오기
			
			movie = new KoreafilmJson().getMovieInfo(movie);
			
			boxOfficeList.put((String)movieData.get("rank"), movie);
		}
		return boxOfficeList;
	}
	// 오버로딩
	public HashMap<String, MovieDTO> getBoxOffice() throws IOException, ParseException{
		return getBoxOffice(null);
	}
	
//	public static void main(String[] args) throws IOException, ParseException {
//		KobisJson k = new KobisJson();
//		List<MovieDTO> movie = k.getRecommendList();
//		for(MovieDTO temp : movie) {
//			System.out.println(temp.getMovie_title());
//		}
//	}
}
