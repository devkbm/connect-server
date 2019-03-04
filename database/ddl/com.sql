create table if not exists COM.COMUSER (
	SYS_DT				DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 			VARCHAR(20)		NULL		COMMENT '최초등록유저',
	UPD_DT				DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER			VARCHAR(20)		NULL		COMMENT '최종수정유저',
    USER_ID				VARCHAR(20)		NOT NULL	COMMENT '유저ID',
    USER_NAME			VARCHAR(100)	NULL		COMMENT '유저명',
    PWD 		   		VARCHAR(2000)	NULL		COMMENT '비밀번호',
    NON_EXPIRED_YN		BOOLEAN			NOT NULL    COMMENT '계정만료여부',
    NON_LOCKED_YN		BOOLEAN			NOT NULL	COMMENT '계정잠금여부',
    PASS_NON_EXPIRED_YN	BOOLEAN			NOT NULL	COMMENT '비밀번호만료여부',
    ENABLED_YN			BOOLEAN			NOT NULL	COMMENT '사용여부',
	constraint pk_comuser primary key(USER_ID)
);

create table if not exists COM.COMAUTHORITY (
	SYS_DT				DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 			VARCHAR(20)		NULL		COMMENT '최초등록유저',
	UPD_DT				DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER			VARCHAR(20)		NULL		COMMENT '최종수정유저',
    AUTHORITY_NAME		VARCHAR(50)		NOT NULL	COMMENT '권한명',
    DESCRIPTION			VARCHAR(500)	NULL		COMMENT '권한설명',
	constraint pk_comauthority primary key(AUTHORITY_NAME)
);

create table if not exists COM.COMUSERAUTHORITY (
	SYS_DT				DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 			VARCHAR(20)		NULL		COMMENT '최초등록유저',
	UPD_DT				DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER			VARCHAR(20)		NULL		COMMENT '최종수정유저',
    USER_ID				VARCHAR(20)		NOT NULL	COMMENT '유저ID',
    AUTHORITY_NAME		VARCHAR(50)		NOT NULL	COMMENT '권한명',
	constraint pk_comuserauthority 	primary key(USER_ID, AUTHORITY_NAME),
	constraint fk_comuserauthority1	foreign key(USER_ID) references COMUSER(USER_ID),
	constraint fk_comuserauthority2	foreign key(AUTHORITY_NAME) references COMAUTHORITY(AUTHORITY_NAME)
);

create table if not exists COM.COMPROGRAM (
	SYS_DT				DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 			VARCHAR(20)		NULL		COMMENT '최초등록유저',
	UPD_DT				DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER			VARCHAR(20)		NULL		COMMENT '최종수정유저',
    PROGRAM_CODE		VARCHAR(10)		NOT NULL	COMMENT '프로그램코드',
    PROGRAM_NAME		VARCHAR(50)		NOT NULL	COMMENT '프로그램명',
    URL					VARCHAR(500)	NULL		COMMENT 'URL',
    DESCRIPTION			VARCHAR(500)	NULL		COMMENT '설명',
	constraint pk_comprogram	primary key(PROGRAM_CODE)
);


create table if not exists COM.COMMENUGROUP (
	SYS_DT				DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 			VARCHAR(20)		NULL		COMMENT '최초등록유저',
	UPD_DT				DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER			VARCHAR(20)		NULL		COMMENT '최종수정유저',
    MENU_GROUP_CODE		VARCHAR(10)		NOT NULL	COMMENT '메뉴그룹코드',
    MENU_GROUP_NAME		VARCHAR(50)		NOT NULL	COMMENT '메뉴그룹명',
    DESCRIPTION			VARCHAR(500)	NULL		COMMENT '설명',
	constraint pk_commenugroup	primary key(MENU_GROUP_CODE)
);

create table if not exists COM.COMMENU (
	SYS_DT				DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 			VARCHAR(20)		NULL		COMMENT '최초등록유저',
	UPD_DT				DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER			VARCHAR(20)		NULL		COMMENT '최종수정유저',
	MENU_GROUP_CODE		VARCHAR(10)		NOT NULL	COMMENT '메뉴그룹코드',
    MENU_CODE			VARCHAR(10)		NOT NULL	COMMENT '메뉴코드',    
    MENU_NAME			VARCHAR(50)		NOT NULL	COMMENT '메뉴명',
    MENU_TYPE			VARCHAR(1)		NOT NULL	COMMENT '메뉴타입',
    P_MENU_CODE			VARCHAR(10)		NULL		COMMENT '상위메뉴코드',
    PROGRAM_CODE		VARCHAR(10)		NULL		COMMENT '프로그램코드',
    SEQ					INT(11)			NULL		COMMENT '계층별 순번',
    LVL					INT(11)			NULL		COMMENT '계층레벨',    
	constraint pk_commenu		primary key(MENU_CODE),
	constraint fk_commenu1	 	foreign key(P_MENU_CODE) references COMMENU(MENU_CODE),
	constraint fk_commenu2	 	foreign key(MENU_GROUP_CODE) references COMMENUGROUP(MENU_GROUP_CODE),
	constraint fk_commenu3 		foreign key(PROGRAM_CODE) references COMPROGRAM(PROGRAM_CODE)
);

create table if not exists COM.COMUSERMENUGROUP (
	SYS_DT				DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 			VARCHAR(20)		NULL		COMMENT '최초등록유저',
	UPD_DT				DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER			VARCHAR(20)		NULL		COMMENT '최종수정유저',
	USER_ID				VARCHAR(20)		NOT NULL	COMMENT '유저ID',
    MENU_GROUP_CODE		VARCHAR(10)		NOT NULL	COMMENT '메뉴그룹코드',
	constraint pk_comusermenugroup 		primary key(USER_ID, MENU_GROUP_CODE),
	constraint fk_comusermenugroup1 	foreign key(USER_ID) references COMUSER(USER_ID),
	constraint fk_comusermenugroup2 	foreign key(MENU_GROUP_CODE) references COMMENUGROUP(MENU_GROUP_CODE)
);

create table if not exists COM.COMFILEINFO (
	SYS_DT				DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 			VARCHAR(20)		NULL		COMMENT '최초등록유저',
	UPD_DT				DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER			VARCHAR(20)		NULL		COMMENT '최종수정유저',
    PK_FILE				VARCHAR(40) 	NOT NULL 	COMMENT '키',
    PGM_ID				VARCHAR(10)		NOT NULL 	COMMENT '유저아이디',
    USER_ID				VARCHAR(20)		NULL		COMMENT '발령코드',
    CONTENT_TYPE		VARCHAR(50)		NULL 		COMMENT 'CONTENT-TYPE',
    UUID				VARCHAR(1000)	NOT NULL 	COMMENT 'UUID_서버에저장된파일명',
    FILE_PATH			VARCHAR(1000)	NULL 		COMMENT '파일경로',
    FILE_NM				VARCHAR(1000)	NULL 		COMMENT '파일명',
    FILE_SIZE			INT				NULL 		COMMENT '파일사이즈',
    DOWNLOAD_CNT		INT				NULL 		COMMENT '다운로드횟수',
	constraint pk_comfileinfo primary key(PK_FILE)
);


create table if not exists COM.COMCODE (
	SYS_DT				DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 			VARCHAR(20)		NULL		COMMENT '최초등록유저',
	UPD_DT				DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER			VARCHAR(20)		NULL		COMMENT '최종수정유저',
    CODE_ID				VARCHAR(255) 	NOT NULL 	COMMENT '코드ID',
    P_CODE_ID			VARCHAR(255)	NULL 		COMMENT '상위코드ID',
   	CODE				VARCHAR(10) 	NOT NULL 	COMMENT '공통코드',
	CODE_NAME			VARCHAR(255) 	NOT NULL 	COMMENT '코드명칭',
	CODE_NAME_ABBR		VARCHAR(255) 	NULL 		COMMENT '코드명칭약어',
	USE_YN				BOOLEAN			NOT NULL 	COMMENT '사용여부',
	FROM_DT				DATETIME		NOT NULL	COMMENT '시작일시',
	TO_DT				DATETIME		NOT NULL	COMMENT '종료일시'	DEFAULT '9999-12-31',
	HIERARCHY_LEVEL		INT				NOT NULL	COMMENT '계층단계(1부터 시작)' DEFAULT 1,
	PRT_SEQ				INT				NULL		COMMENT '출력순서',	
	FIXED_LENGTH_YN		BOOLEAN			NOT NULL 	COMMENT '고정길이여부',
   	CODE_LENGTH			INT				NULL		COMMENT '코드길이',
	CMT					VARCHAR(2000) 	NULL 		COMMENT '비고',
	constraint pk_comcode primary key(CODE_ID)	
);





  