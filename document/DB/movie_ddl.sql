-- 사용자 DB 테이블 생성
CREATE TABLE MEMBER(
	userid		varchar2(200) PRIMARY KEY,
	userpw 		varchar2(200) NOT NULL,
	username	varchar2(200) UNIQUE NOT null,
	usergrade 	varchar2(200) NOT NULL,
	profile 	varchar2(1000),
	-- usergrade 는 'ADMIN' 또는 'BASIC'만 가능 (반드시 대문자)
	CONSTRAINTS grade_check CHECK (usergrade IN ('ADMIN', 'BASIC'))	
);

-- 영화 DB 테이블 생성
CREATE TABLE MOVIE_DB(
	movie_num		NUMBER(20) PRIMARY KEY,
	movie_title		varchar2(300) NOT NULL,
	movie_date		DATE NOT NULL,
	movie_plot		varchar2(2000),
	movie_genre		varchar2(300),
	movie_poster	varchar2(3000),
	movie_still_image	varchar2(1000),
	movie_star_rate	number(10),		-- 추후 코멘트 테이블 참조 예정
	CONSTRAINTS rate_scope CHECK (movie_star_rate >=0 AND movie_star_rate <= 5)
);

-- movie_num에 사용할 시퀀스(번호)
CREATE SEQUENCE movie_seq
START WITH 1
INCREMENT BY 1;

-- 기간별 박스오피스 랭크 테이블 생성
CREATE TABLE BOXOFFICE_RANK(
	movie_num		number(20),
	movie_rank		number(10),
	boxoffice_date	DATE,
	CONSTRAINTS movie_num_fk FOREIGN KEY (movie_num) REFERENCES MOVIE_DB(movie_num) ON DELETE CASCADE
);
