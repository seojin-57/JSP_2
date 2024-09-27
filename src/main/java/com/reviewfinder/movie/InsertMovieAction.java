package com.reviewfinder.movie;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.json.simple.parser.ParseException;

import com.reviewfinder.action.Action;
import com.reviewfinder.action.ActionForward;
import com.reviewfinder.json.KobisJson;
import com.reviewfinder.movie.dao.MovieDAO;
import com.reviewfinder.movie.dao.MovieDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class InsertMovieAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) {
        ActionForward forward = new ActionForward();

        KobisJson kobisJson = new KobisJson();
        HashMap<String, MovieDTO> boxOfficeList = null;
        List<MovieDTO> movieList = null;

        // ExecutorService를 사용하여 3초 제한 시간 설정
        ExecutorService executor = Executors.newFixedThreadPool(2);  // 두 개의 쓰레드 사용
       
        // boxOfficeList 작업
        Callable<HashMap<String, MovieDTO>> boxOfficeTask = new Callable<HashMap<String, MovieDTO>>() {
            @Override
            public HashMap<String, MovieDTO> call() throws ParseException, IOException {
                return kobisJson.getBoxOffice();
            }
        };

        // recommendList 작업
        Callable<List<MovieDTO>> recommendTask = new Callable<List<MovieDTO>>() {
            @Override
            public List<MovieDTO> call() throws ParseException, IOException {
                return kobisJson.getRecommendList();
            }
        };

        try {
            // 각각 3초 안에 작업 실행
            Future<HashMap<String, MovieDTO>> boxOfficeFuture = executor.submit(boxOfficeTask);
            Future<List<MovieDTO>> recommendFuture = executor.submit(recommendTask);

            boxOfficeList = boxOfficeFuture.get(3, TimeUnit.SECONDS);  // 3초 제한
            movieList = recommendFuture.get(3, TimeUnit.SECONDS);      // 3초 제한

        } catch (TimeoutException e) {
            // 3초가 넘었을 때 처리
            System.out.println("작업이 3초를 초과했습니다. 다시 시도합니다.");
            try {
                // boxOffice와 recommendList 모두 재실행
                boxOfficeList = kobisJson.getBoxOffice();
                movieList = kobisJson.getRecommendList();
            } catch (ParseException | IOException ex) {
                ex.printStackTrace();
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();  // ExecutorService 종료
        }

        // MovieDAO 작업
        MovieDAO mdao = new MovieDAO();
        mdao.insertMovieDB(boxOfficeList);
        mdao.insertMovieDB(movieList);

        forward.setRedirect(true);
        forward.setPath("/test.jsp");

        return forward;
    }
>>>>>>> ad346f68fd92d5aed5a6bc9f6187ca1a3a6326d0
}
