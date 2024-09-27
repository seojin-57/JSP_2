package com.reviewfinder.rec;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RecFrontController extends HttpServlet{
	private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 데이터베이스 연결 설정 (Oracle)
        String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";  // Oracle JDBC URL
        String dbUser = "project";  // Oracle 사용자 이름
        String dbPassword = "1234";  // Oracle 비밀번호

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Oracle JDBC 드라이버 로드
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Oracle 데이터베이스 연결
            conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);

            // SQL 쿼리: 랜덤으로 2개의 포스터 선택
            String sql = "SELECT movie_poster FROM (SELECT movie_poster FROM MOVIE_DB ORDER BY DBMS_RANDOM.VALUE) WHERE ROWNUM <= 2";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            List<String> posters = new ArrayList<>();
            while (rs.next()) {
                posters.add(rs.getString("movie_poster"));
            }

            // 포스터 리스트를 request 객체에 저장
            request.setAttribute("posters", posters);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) {}
            if (pstmt != null) try { pstmt.close(); } catch (SQLException e) {}
            if (conn != null) try { conn.close(); } catch (SQLException e) {}
        }

        // JSP로 포워딩
        request.getRequestDispatcher("rec_16.jsp").forward(request, response);
    }
}
