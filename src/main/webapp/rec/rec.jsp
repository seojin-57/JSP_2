<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="rec.css">
    <title>영화 추천</title>
</head>
<body>
    <div id="wrap">
        <div id="img">
            <img src="img/trophy.png">
        </div>
        <h1>영화 추천</h1>
        <p>당신의 인생 영화를 선택해주세요</p>
        <div id="round">
            <p>총 라운드를 선택해주세요</p>
            <select id="select">
                <option>16강</option>
                <option>8강</option>
                <option>4강</option>
            </select>
        </div><br>
        <input type="submit" value="시작하기" id="submit">
    </div>
</body>
</html>