create table if not exists COM.CMUSER (
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
	constraint pk_cmuser primary key(USER_ID)
);

create table if not exists COM.CMAUTHORITY (
	SYS_DT				DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 			VARCHAR(20)		NULL		COMMENT '최초등록유저',
	UPD_DT				DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER			VARCHAR(20)		NULL		COMMENT '최종수정유저',
    AUTHORITY_NAME		VARCHAR(50)		NOT NULL	COMMENT '권한명',
    DESCRIPTION			VARCHAR(500)	NULL		COMMENT '권한설명',
	constraint pk_cmauthority primary key(AUTHORITY_NAME)
);

create table if not exists COM.CMUSERAUTHORITY (
	SYS_DT				DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 			VARCHAR(20)		NULL		COMMENT '최초등록유저',
	UPD_DT				DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER			VARCHAR(20)		NULL		COMMENT '최종수정유저',
    USER_ID				VARCHAR(20)		NOT NULL	COMMENT '유저ID',
    AUTHORITY_NAME		VARCHAR(50)		NOT NULL	COMMENT '권한명',
	constraint pk_cmauthority 	primary key(USER_ID, AUTHORITY_NAME),
	constraint fk_cmuser 		foreign key(USER_ID) references CMUSER(USER_ID),
	constraint fk_cmauthority 	foreign key(AUTHORITY_NAME) references CMAUTHORITY(AUTHORITY_NAME),
);

create table if not exists COM.CMPROGRAM (
	SYS_DT				DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 			VARCHAR(20)		NULL		COMMENT '최초등록유저',
	UPD_DT				DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER			VARCHAR(20)		NULL		COMMENT '최종수정유저',
    PROGRAM_CODE		VARCHAR(10)		NOT NULL	COMMENT '프로그램코드',
    PROGRAM_NAME		VARCHAR(50)		NOT NULL	COMMENT '프로그램명',
    URL					VARCHAR(500)	NULL		COMMENT 'URL',
    DESCRIPTION			VARCHAR(500)	NULL		COMMENT '설명',
	constraint pk_cmprogram	primary key(PROGRAM_CODE)
);


create table if not exists COM.CMMENUGROUP (
	SYS_DT				DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 			VARCHAR(20)		NULL		COMMENT '최초등록유저',
	UPD_DT				DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER			VARCHAR(20)		NULL		COMMENT '최종수정유저',
    MENU_GROUP_CODE		VARCHAR(10)		NOT NULL	COMMENT '메뉴그룹코드',
    MENU_GROUP_NAME		VARCHAR(50)		NOT NULL	COMMENT '메뉴그룹명',
    DESCRIPTION			VARCHAR(500)	NULL		COMMENT '설명',
	constraint pk_cmmenugroup	primary key(MENU_GROUP_CODE)
);

create table if not exists COM.CMMENU (
	SYS_DT				DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 			VARCHAR(20)		NULL		COMMENT '최초등록유저',
	UPD_DT				DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER			VARCHAR(20)		NULL		COMMENT '최종수정유저',
	MENU_GROUP_CODE		VARCHAR(10)		NOT NULL	COMMENT '메뉴그룹코드',
    MENU_CODE			VARCHAR(10)		NOT NULL	COMMENT '메뉴코드',    
    MENU_NAME			VARCHAR(50)		NOT NULL	COMMENT '메뉴명',
    P_MENU_CODE			VARCHAR(10)		NULL		COMMENT '상위메뉴코드',
    PROGRAM_CODE		VARCHAR(10)		NULL		COMMENT '프로그램코드',
    SEQ					INT(11)			NULL		COMMENT '계층별 순번',
    LVL					INT(11)			NULL		COMMENT '계층레벨',    
	constraint pk_cmmenu		primary key(MENU_CODE),
	constraint fk_cmmenu	 	foreign key(P_MENU_CODE) references CMMENU(MENU_CODE),
	constraint fk_cmmenugroup 	foreign key(MENU_GROUP_CODE) references CMMENUGROUP(MENU_GROUP_CODE),
	constraint fk_cmprogram 	foreign key(PROGRAM_CODE) references CMPROGRAM(PROGRAM_CODE),
);

create table if not exists COM.CMUSERMENUGROUP (
	SYS_DT				DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 			VARCHAR(20)		NULL		COMMENT '최초등록유저',
	UPD_DT				DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER			VARCHAR(20)		NULL		COMMENT '최종수정유저',
	USER_ID				VARCHAR(20)		NOT NULL	COMMENT '유저ID',
    MENU_GROUP_CODE		VARCHAR(10)		NOT NULL	COMMENT '메뉴그룹코드',
	constraint pk_cmusermenugroup 	primary key(USER_ID, MENU_GROUP_CODE),
	constraint fk_cmuser 		foreign key(USER_ID) references CMUSER(USER_ID),
	constraint fk_cmauthority 	foreign key(MENU_GROUP_CODE) references CMMENUGROUP(MENU_GROUP_CODE),
);





  