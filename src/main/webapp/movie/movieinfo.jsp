<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>영화 제목 예시</title>
    <style>
	    html {
	    	color: #f7175a;
	   		font-family: Pretendard, "Apple SD Gothic Neo", "Nanum Gothic", "Malgun Gothic", sans-serif;
		}
        /* 필요한 CSS 스타일을 여기에 추가하세요 */
        body {
            font-family: Arial, sans-serif;
        }
        .container {
            width: 80%;
            margin: 0 auto;
        }
        .poster {
            float: left;
            width: 30%;
        }
        .details {
            float: left;
            width: 70%;
            padding-left: 20px;
        }
        .section {
            clear: both;
            margin-top: 50px;
        }
        .section h2 {
            border-bottom: 1px solid #ccc;
            padding-bottom: 10px;
        }
        .cast-list, .comment-list, .gallery {
            list-style: none;
            padding: 0;
        }
        .cast-item, .comment-item, .gallery-item {
            margin-bottom: 15px;
        }
        .cast-item img, .gallery-item img {
            width: 100px;
            height: auto;
        }
        .comment-item {
            border-bottom: 1px solid #eee;
            padding-bottom: 10px;
        }
    </style>
</head>
<body>
    <div class="container">
        <!-- 영화 제목과 기본 정보 -->
        <h1>영화 제목 예시</h1>
        <div class="poster">
            <img src="poster_example.jpg" alt="영화 포스터" width="100%">
        </div>
        <div class="details">
            <p>감독: 감독 이름 예시</p>
            <p>출연: 배우 A, 배우 B, 배우 C</p>
            <p>개봉일: 2023-01-01</p>
            <p>줄거리: 이곳에 영화 줄거리가 들어갑니다. 이 영화는...</p>
            <!-- 추가 정보들을 여기에 추가하세요 -->
        </div>

        <!-- 출연/제작 섹션 -->
        <div class="section">
            <h2>출연/제작</h2>
            <ul class="cast-list">
                <!-- 출연진 리스트 예시 -->
                <li class="cast-item">
                    <img src="actor_a.jpg" alt="배우 A">
                    <p>배우 A - 역할 A</p>
                </li>
                <li class="cast-item">
                    <img src="actor_b.jpg" alt="배우 B">
                    <p>배우 B - 역할 B</p>
                </li>
                <li class="cast-item">
                    <img src="actor_c.jpg" alt="배우 C">
                    <p>배우 C - 역할 C</p>
                </li>
            </ul>
        </div>

        <!-- 코멘트 섹션 -->
        <div class="section">
            <h2>코멘트</h2>
            <ul class="comment-list">
                <!-- 코멘트 리스트 예시 -->
                <li class="comment-item">
                    <p><strong>사용자1</strong></p>
                    <p>이 영화 정말 재미있었어요!</p>
                    <p><small>2023-10-01</small></p>
                </li>
                <li class="comment-item">
                    <p><strong>사용자2</strong></p>
                    <p>스토리가 인상적이었습니다.</p>
                    <p><small>2023-10-02</small></p>
                </li>
            </ul>
            <!-- 코멘트 작성 폼 -->
            <form action="#" method="post">
                <textarea name="comment" rows="5" cols="50" placeholder="코멘트를 작성하세요."></textarea><br>
                <input type="submit" value="등록">
            </form>
        </div>

        <!-- 갤러리 섹션 -->
        <div class="section">
            <h2>갤러리</h2>
            <ul class="gallery">
                <!-- 갤러리 이미지 예시 -->
                <li class="gallery-item">
                    <img src="gallery_image1.jpg" alt="갤러리 이미지 1">
                </li>
                <li class="gallery-item">
                    <img src="gallery_image2.jpg" alt="갤러리 이미지 2">
                </li>
                <li class="gallery-item">
                    <img src="gallery_image3.jpg" alt="갤러리 이미지 3">
                </li>
            </ul>
        </div>
    </div>
</body>
</html>
