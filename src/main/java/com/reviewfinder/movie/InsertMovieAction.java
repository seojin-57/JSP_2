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

public class InsertMovieAction implements Action {
    private static final int TIMEOUT_SECONDS = 3;  // 제한 시간 설정

    @Override
    public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) {
        ActionForward forward = new ActionForward();

        KobisJson kobisJson = new KobisJson();
        HashMap<String, MovieDTO> boxOfficeList = null;
        List<MovieDTO> movieList = null;

        // ExecutorService를 사용하여 쓰레드 풀 생성
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

        // 작업이 성공할 때까지 반복
        while (boxOfficeList == null || movieList == null) {
            try {
                Future<HashMap<String, MovieDTO>> boxOfficeFuture = executor.submit(boxOfficeTask);
                Future<List<MovieDTO>> recommendFuture = executor.submit(recommendTask);

                // 3초 안에 작업 완료 대기
                boxOfficeList = boxOfficeFuture.get(TIMEOUT_SECONDS, TimeUnit.SECONDS);
                movieList = recommendFuture.get(TIMEOUT_SECONDS, TimeUnit.SECONDS);

            } catch (TimeoutException e) {
                // 작업이 3초를 초과했을 때
                System.out.println("작업이 3초를 초과했습니다. 다시 시도합니다.");
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                break; // 예외가 발생한 경우 반복 중지
            }
        }

        // ExecutorService 종료
        executor.shutdown();

        // MovieDAO 작업
        MovieDAO mdao = new MovieDAO();
        if (boxOfficeList != null) {
            mdao.insertMovieDB(boxOfficeList);
        }
        if (movieList != null) {
            mdao.insertMovieDB(movieList);
        }

        forward.setRedirect(true);
        forward.setPath("/test.jsp");

        return forward;
    }
}
