<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, java.util.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="rec_pick.css">
    <title>영화 추천 16강</title>
</head>
<body>
    <div id="wrap">
        <div id="title">
            <h1>영화 추천 16강</h1>
        </div>

        <div id="img">
            <%
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

                    // 수정된 SQL 쿼리: 랜덤으로 2개의 포스터 선택
                    String sql = "SELECT movie_poster FROM (SELECT movie_poster FROM MOVIE_DB ORDER BY DBMS_RANDOM.VALUE) WHERE ROWNUM <= 2";
                    pstmt = conn.prepareStatement(sql);
                    rs = pstmt.executeQuery();

                    List<String> posters = new ArrayList<>();
                    while (rs.next()) {
                        posters.add(rs.getString("movie_poster"));
                    }

                    // 포스터 두 개가 제대로 선택되었다면 이미지를 출력
                    if (posters.size() == 2) {
            %>
            <div id="option_1">
                <a href="rec.html">
                    <img src="<%= request.getContextPath() + posters.get(0) %>" alt="Poster 1">
                </a>
            </div>
            <div id="option_2">
                <a href="index.html">
                    <img src="<%= request.getContextPath() + posters.get(1) %>" alt="Poster 2">
                </a>
            </div>
            <div id="vs">
                <img src="img/vs.png" alt="VS">
            </div>
            <%
                    } else {
                        out.println("<p>포스터를 불러오는데 실패했습니다.</p>");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (rs != null) try { rs.close(); } catch (SQLException e) {}
                    if (pstmt != null) try { pstmt.close(); } catch (SQLException e) {}
                    if (conn != null) try { conn.close(); } catch (SQLException e) {}
                }
            %>
        </div>
    </div>
</body>
</html>