<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>영화 상세 페이지</title>
<style>
* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
}

body {
    font-family: Arial, sans-serif;
    background-color: #f4f4f4;
    color: #333;
}

.movie-banner {
    position: relative;
    max-width: 100%;
    height: 500px;
    overflow: hidden;
}

.movie-still-container {
    /* position: relative; */
}

.movie-still {
    width: 100%;
    height: 70%;
    object-fit: cover;
}

.movie-info-overlay {
    position: absolute;
    bottom: 20px;
    left: 20px;
    background-color: rgba(0, 0, 0, 0.6);
    color: white;
    padding: 20px;
    border-radius: 10px;
}

.movie-title {
    font-size: 2.5rem;
    margin-bottom: 10px;
}

.movie-release-date, .movie-genre, .movie-runtime {
    font-size: 1.2rem;
    margin-bottom: 5px;
}

.movie-details {
    display: flex;
    justify-content: flex-start;
    align-items: center;
    padding: 20px;
    margin-top: 20px;
}

.movie-poster {
    width: 200px;
    margin-right: 20px;
}

.movie-poster img {
    width: 100%;
    height: auto;
    border-radius: 10px;
}

.movie-rating {
    flex-grow: 1;
}

.reviews {
    margin: 20px;
    background-color: white;
    padding: 20px;
    border-radius: 10px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

.comments {
    list-style: none;
}

.comments ul {
    margin: 0;
    padding: 0;
    list-style: none;
}

.comments li {
    margin-bottom: 10px;
}

</style>
</head>
<body>
	<c:set var="movie" value="${requestScope.moviedto }"/>
    <!-- 메인 콘텐츠 -->
    <main>
        <!-- 영화 스틸 이미지와 영화 정보 섹션 -->
        <section class="movie-banner">
            <!-- 영화 스틸 이미지 -->
            <div class="movie-still-container">
                <img src="${movie.movie_still_image }" alt="영화 스틸 이미지" class="movie-still">
                <div class="movie-info-overlay">
                    <h1 class="movie-title">${movie.movie_title}</h1>
                    <p class="movie-release-date">상영 날짜: ${movie.movie_date}</p>
                    <p class="movie-genre">장르: ${movie.movie_genre}</p>
                </div>
            </div>
        </section>

        <!-- 영화 포스터 및 평점 섹션 -->
        <section class="movie-details">
            <!-- 영화 포스터 -->
            <div class="movie-poster">
                <img src="${movie.movie_poster }" alt="영화 포스터">
            </div>

            <!-- 영화 평점 -->
            <div class="movie-rating">
                <h2>평점: ${movie.movie_star_rate}</h2>
                <%-- <p>${movie.ratingCount}명이 평가함</p> --%>
            </div>
        </section>

        <!-- 리뷰 섹션 -->
        <section class="reviews">
            <div class="comments">
                <h2>리뷰</h2>
                <ul>
                   <%--  <c:forEach var="comment" items="${movie.comments}">
                        <li>${comment.user}: ${comment.text}</li>
                    </c:forEach> --%>
                </ul>
            </div>
        </section>
    </main>
</body>
</html>