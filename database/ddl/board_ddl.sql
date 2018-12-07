DROP TABLE IF EXISTS GRWBOARD;

CREATE TABLE GRWBOARD (
	SYS_DT			DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER		VARCHAR(50)		NULL		COMMENT '최초등록유저',
	UPD_DT			DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER		VARCHAR(50)		NULL		COMMENT '최종 수정 유저',
	PK_BOARD		INT				NOT NULL	AUTO_INCREMENT COMMENT '게시판 키',
	PPK_BOARD		INT				NULL		COMMENT '상위 게시판키',		
	BOARD_TYPE		VARCHAR(10)		NULL		COMMENT '게시판 타입',
    BOARD_NAME		VARCHAR(2000)	NOT NULL	COMMENT '게시판 명칭',
	BOARD_DESC		VARCHAR(2000)	NULL		COMMENT '게시판 설명',	
	FROM_DT			DATE			NULL		COMMENT '시작일',
	TO_DT			DATE			NULL		COMMENT '종료일',	
	USE_YN			BOOL			NULL		COMMENT '사용여부',
	ARTICLE_CNT		INT				NULL		COMMENT '게시글 갯수(페이징에 사용)',
	SEQ				INT				NULL		COMMENT '순번',	
    constraint pk_board 	primary key(PK_BOARD)    
);

DROP TABLE IF EXISTS GRWARTICLE;

CREATE TABLE GRWARTICLE (
	SYS_DT			DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 		VARCHAR(50)		NULL		COMMENT '최초등록유저',
	UPD_DT			DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER		VARCHAR(50)		NULL		COMMENT '최종수정유저',
	PK_ARTICLE		INT 			NOT NULL 	AUTO_INCREMENT COMMENT '게시글 키',
	FK_BOARD 		INT 			NOT NULL	COMMENT '게시판 키',	
	PPK_ARTICLE		INT 			NULL		COMMENT '상위게시글키',
	TITLE			VARCHAR(2000)	NOT NULL	COMMENT '제목',
	CONTENTS 		TEXT			NULL		COMMENT '내용',
	PWD				VARCHAR(2000) 	NULL		COMMENT '비밀번호',
	HIT_CNT			INT 			NULL		COMMENT '조회수',
	FROM_DT			DATE			NULL		COMMENT '게시시작일자',
	TO_DT			DATE			NULL		COMMENT '게시종료일',
	SEQ				INT  			NULL		COMMENT '순번',
	HIER_DEPTH 		INT				NULL		COMMENT '계층',
    PWD_YN			BOOL			NULL		COMMENT '비밀번호사용여부',
	HASH_METHOD		VARCHAR(20)		NULL		COMMENT '암호방식',
    constraint pk_article 	primary key(PK_ARTICLE),
    constraint fk_board 	foreign key(FK_BOARD) references GRWBOARD(PK_BOARD)
);


DROP TABLE IF EXISTS GRWARTICLECHECK;

CREATE TABLE GRWARTICLECHECK (
	SYS_DT			DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 		VARCHAR(50)		NULL		COMMENT '최초등록유저',
	UPD_DT			DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER		VARCHAR(50)		NULL		COMMENT '최종수정유저',
	PK_ARTICLECHECK	INT 			NOT NULL 	AUTO_INCREMENT COMMENT '게시글조회 키',
	FK_ARTICLE 		INT 			NOT NULL	COMMENT '게시글 외래키',			
    constraint pk_articlecheck 	primary key(PK_ARTICLECHECK),
    constraint fk_article 	foreign key(FK_ARTICLE) references GRWARTICLE(PK_ARTICLE)
);


CREATE TABLE GRWARTICLEFILES (
	SYS_DT			DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 		VARCHAR(50)		NULL		COMMENT '최초등록유저',
	UPD_DT			DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER		VARCHAR(50)		NULL		COMMENT '최종수정유저',
	PK_FILE			INT 			NOT NULL 	COMMENT '첨부파일 키',
	PK_ARTICLE 		INT 			NOT NULL	COMMENT '게시글 키'			    
);
