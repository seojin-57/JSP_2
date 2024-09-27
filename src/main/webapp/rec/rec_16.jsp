<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
                // request에서 movies 리스트를 가져옴
                Object movieObj = request.getAttribute("movies");

                // movies 객체가 List 타입인지 확인
                if (movieObj != null && movieObj instanceof List) {
                    List<RecDTO> movies = (List<RecDTO>) movieObj;

                    // movies 리스트가 2개인지 확인
                    if (movies.size() == 2) {
                        RecDTO movie1 = movies.get(0);
                        RecDTO movie2 = movies.get(1);
            %>
            <div id="option_1">
                <a href="rec.html">
                    <img src="<%= request.getContextPath() + movie1.getMoviePoster() %>" alt="<%= movie1.getMovieTitle() %>">
                </a>
                <p><%= movie1.getMovieTitle() %></p>
            </div>
            <div id="option_2">
                <a href="index.html">
                    <img src="<%= request.getContextPath() + movie2.getMoviePoster() %>" alt="<%= movie2.getMovieTitle() %>">
                </a>
                <p><%= movie2.getMovieTitle() %></p>
            </div>
            <div id="vs">
                <img src="img/vs.png" alt="VS">
            </div>
            <%
                    } else {
                        out.println("<p>영화가 2개 선택되지 않았습니다.</p>");
                    }
                } else {
                    out.println("<p>영화 리스트를 불러오지 못했습니다.</p>");
                }
            %>
        </div>
    </div>
</body>
</html> --%>