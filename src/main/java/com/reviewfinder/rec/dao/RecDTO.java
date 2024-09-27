package com.reviewfinder.rec.dao;

public class RecDTO {
    private String movieTitle;
    private String moviePoster;

    // 기본 생성자
    public RecDTO() {}

    // 매개변수를 받는 생성자
    public RecDTO(String movieTitle, String moviePoster) {
        this.movieTitle = movieTitle;
        this.moviePoster = moviePoster;
    }

    // Getter와 Setter
    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getMoviePoster() {
        return moviePoster;
    }

    public void setMoviePoster(String moviePoster) {
        this.moviePoster = moviePoster;
    }
}