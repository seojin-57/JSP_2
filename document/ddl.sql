-- 사용자 DB
CREATE TABLE MEMBER(
	userid		varchar2(200) PRIMARY KEY,
	userpw 		varchar2(200) NOT NULL,
	username	varchar2(200) UNIQUE NOT null,
	usergrade 	varchar2(200) NOT NULL,
	profile 	varchar2(1000),
	-- usergrade 는 'ADMIN' 또는 'BASIC'만 가능 (반드시 대문자)
	CONSTRAINTS grade_check CHECK (usergrade IN ('ADMIN', 'BASIC'))	
);

-- MEMEBER 테이블 조회
SELECT * FROM "MEMBER";			
-- 테스트 데이터 삽입
INSERT INTO "MEMBER" values(	
	'test', '1234', '테스트 계정', 'BASIC', null
);

-- 영화 DB
CREATE TABLE MOVIE_DB(
	movie_num		NUMBER(20) PRIMARY key,
	movie_title		varchar2(300) NOT NULL,
	movie_date		DATE NOT NULL,
	movie_plot		varchar2(2000),
	movie_genre		varchar2(300),
	movie_poster	varchar2(1000),
	movie_still_image	varchar2(1000),
	movie_star_rate	number(10)		-- 추후 코멘트 테이블 참조 예정
	CONSTRAINTS rate_scope CHECK (movie_star_rate >=0 AND movie_star_rate <= 5)
);

-- movie_num에 사용할 시퀀스(번호)
CREATE SEQUENCE movie_seq
START WITH 1
INCREMENT BY 1;

-- MOVIE_DB 테이블 조회
SELECT * FROM MOVIE_DB;
-- 테스트 데이터 삽입
INSERT INTO MOVIE_DB VALUES(
	movie_seq.nextval, '테스트 영화', '20240923', '영화 설명란', 
	'판타지', '포스터 없음',	'스틸 이미지 없음', 5  
);


