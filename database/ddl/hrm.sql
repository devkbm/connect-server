
create table if not exists HRMEMPLOYEE (
	SYS_DT		DATETIME		null		COMMENT '최초등록일시',
	SYS_USER 	VARCHAR(50)		null		COMMENT '최초등록유저',
	UPD_DT		DATETIME		null		COMMENT '최종수정일시',
	UPD_USER	VARCHAR(50)		null		COMMENT '최종수정유저',
	EMP_ID		VARCHAR(10) 	not null  	COMMENT '사원ID',
	EMP_NAME	VARCHAR(50)		not null	COMMENT '성명',
	RREGNO		VARCHAR(20)		null		COMMENT '주민등록번호',
	constraint pk_hrmemployee primary key(EMP_ID)
);

create table if not exists HRMEMPDEPTHISTORY (
	SYS_DT		DATETIME		null		COMMENT '최초등록일시',
	SYS_USER 	VARCHAR(50)		null		COMMENT '최초등록유저',
	UPD_DT		DATETIME		null		COMMENT '최종수정일시',
	UPD_USER	VARCHAR(50)		null		COMMENT '최종수정유저',
	ID			INT				not null	COMMENT '부서이력ID'	AUTO_INCREMENT,
	EMP_ID		VARCHAR(10) 	not null  	COMMENT '사원ID',
	DEPT_TYPE	VARCHAR(10)		not null	COMMENT '부서타입',
	DEPT_CODE	VARCHAR(10)		not null	COMMENT '부서코드',
	FROM_DT		DATE			not null	COMMENT '시작일자',
	TO_DT		DATE			not null	COMMENT '종료일자',	
	constraint pk_hrmempdepthistory primary key(ID),
	constraint fk_hrmempdepthistory foreign key(EMP_ID) references HRMEMPLOYEE(EMP_ID)  
);

create table if not exists HRMEMPJOBHISTORY (
	SYS_DT		DATETIME		null		COMMENT '최초등록일시',
	SYS_USER 	VARCHAR(50)		null		COMMENT '최초등록유저',
	UPD_DT		DATETIME		null		COMMENT '최종수정일시',
	UPD_USER	VARCHAR(50)		null		COMMENT '최종수정유저',
	ID			INT				not null	COMMENT '부서이력ID'	AUTO_INCREMENT,
	EMP_ID		VARCHAR(10) 	not null  	COMMENT '사원ID',
	JOB_TYPE	VARCHAR(10)		not null	COMMENT '직제타입',
	JOB_CODE	VARCHAR(10)		not null	COMMENT '직제코드',
	FROM_DT		DATE			not null	COMMENT '시작일자',
	TO_DT		DATE			not null	COMMENT '종료일자',	
	constraint pk_hrmempjobhistory primary key(ID),
	constraint fk_hrmempjobhistory foreign key(EMP_ID) references HRMEMPLOYEE(EMP_ID)  
);

create table if not exists COM.APPOINMENTCODEDETAILS (
	SYS_DT				DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 			VARCHAR(20)		NULL		COMMENT '최초등록유저',
	UPD_DT				DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER			VARCHAR(20)		NULL		COMMENT '최종수정유저',
    PK_CODE_DETAIL		INT				NOT NULL	AUTO_INCREMENT COMMENT '발령상세키',
    CODE_GROUP			VARCHAR(20)		NULL		COMMENT '발령코드그룹',
    CODE				VARCHAR(20)		NULL		COMMENT '발령코드',
    CHANGE_TYPE			VARCHAR(20)		NULL		COMMENT '변경타입',
    TYPE_CODE			VARCHAR(20)		NULL		COMMENT '코드',
    TYPE_NAME			VARCHAR(20)		NULL		COMMENT '코드명',
	constraint pk_codedetail primary key(PK_CODE_DETAIL)
);
