package com.reviewfinder.rec.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.reviewfinder.movie.dao.MovieDTO;

public class RecDAO {
	private String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";  // Oracle JDBC URL
    private String dbUser = "project";  // Oracle 사용자 이름
    private String dbPassword = "1234";  // Oracle 비밀번호

    // 데이터베이스 연결을 위한 메서드
    private Connection getConnection() throws Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        return DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
    }

    // 랜덤으로 2개의 영화 정보를 가져오는 메서드
    public List<RecDTO> getRandomMovies() {
        List<RecDTO> movies = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            String sql = "SELECT movie_title, movie_poster FROM (SELECT movie_title, movie_poster FROM MOVIE_DB ORDER BY DBMS_RANDOM.VALUE) WHERE ROWNUM <= 2";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String title = rs.getString("movie_title");
                String poster = rs.getString("movie_poster");
                RecDTO movie = new RecDTO(title, poster);
                movies.add(movie);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) {}
            if (pstmt != null) try { pstmt.close(); } catch (SQLException e) {}
            if (conn != null) try { conn.close(); } catch (SQLException e) {}
        }
        return movies;
    }
}
