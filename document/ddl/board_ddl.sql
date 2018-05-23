DROP TABLE IF EXISTS GRARTICLE;

CREATE TABLE GRARTICLE (
	SYS_DT			DATETIME		null		COMMENT '최초등록일시',
	SYS_USER 		VARCHAR(50)		null		COMMENT '최초등록유저',
	UPD_DT			DATETIME		null		COMMENT '최종수정일시',
	UPD_USER		VARCHAR(50)		null		COMMENT '최종수정유저',
	PK_ARTICLE		INT 			NOT NULL 	AUTO_INCREMENT primary KEY	COMMENT '게시글 키',
	FK_BOARD 		INT 			NOT NULL	COMMENT '게시판 키',	
	PPK_ARTICLE		INT 			null		COMMENT '상위게시글키',
	TITLE			VARCHAR(2000)	not null	COMMENT '제목',
	CONTENTS 		TEXT			null		COMMENT '내용',
	PWD				VARCHAR(2000) 	null		COMMENT '비밀번호',
	HIT_CNT			INT 			null		COMMENT '조회수',
	FROM_DT			DATE			null		COMMENT '게시시작일자',
	TO_DT			DATE			null		COMMENT '게시종료일',
	SEQ				INT  			null		COMMENT '순번',
	HIER_DEPTH 		INT				null		COMMENT '계층'		
);

DROP TABLE IF EXISTS GRBOARD;

CREATE TABLE GRBOARD (
	SYS_DT			DATETIME		null		COMMENT '최초등록일시',
	SYS_USER		VARCHAR(50)		null		COMMENT '최초등록유저',
	UPD_DT			DATETIME		null		COMMENT '최종수정일시',
	UPD_USER		VARCHAR(50)		null		COMMENT '최종 수정 유저',
	PK_BOARD		INT				not null	AUTO_INCREMENT primary key	COMMENT '게시판 키',
	PPK_BOARD		INT				null		COMMENT '상위 게시판키',	
	BOARD_NAME		VARCHAR(2000)	not null	COMMENT '게시판 명칭',
	BOARD_TYPE		VARCHAR(10)		null		COMMENT '게시판 타입',
	BOARD_DESC		VARCHAR(2000)	null		COMMENT '게시판 설명',	
	FROM_DT			DATE			null		COMMENT '시작일',
	TO_DT			DATE			null		COMMENT '종료일',	
	USE_YN			BOOL			null		COMMENT '사용여부',
	ARTICLE_CNT		INT				null		COMMENT '게시글 갯수(페이징에 사용)',
	SEQ				INT				null		COMMENT '순번',
	PWD_YN			BOOL			null		COMMENT '비밀번호사용여부',
	PWD_METHOD		VARCHAR(20)		null		COMMENT '암호방식'
);

/*DROP TABLE IF EXISTS `grarticle_files`;

CREATE TABLE `grarticle_files` (
	`pk_article`	int	NOT NULL	COMMENT '게시글키',
	`pk_file`	int	NOT NULL
);


ALTER TABLE `grarticle` ADD CONSTRAINT `FK_grboard_TO_grarticle_1` FOREIGN KEY (
	`fk_board`
)
REFERENCES `grboard` (
	`pk_board`
);

ALTER TABLE `grarticle_files` ADD CONSTRAINT `FK_grarticle_TO_grarticle_files_1` FOREIGN KEY (
	`pk_article`
)
REFERENCES `grarticle` (
	`pk_article`
);

ALTER TABLE `grarticle_files` ADD CONSTRAINT `FK_cmfileinfo_TO_grarticle_files_1` FOREIGN KEY (
	`pk_file`
)
REFERENCES `cmfileinfo` (
	`pk_file`
);*/

