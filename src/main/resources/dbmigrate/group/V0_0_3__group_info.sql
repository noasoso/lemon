

-------------------------------------------------------------------------------
--  group info
-------------------------------------------------------------------------------
CREATE TABLE GROUP_INFO(
        ID BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL,
        NAME VARCHAR(200),
	VALUE VARCHAR(200),
	GLOBAL_ID BIGINT,
	LOCAL_ID BIGINT,
	GROUP_BASE_ID BIGINT,
        CONSTRAINT PK_GROUP_INFO PRIMARY KEY(ID),
        CONSTRAINT FK_GROUP_BASE FOREIGN KEY(GROUP_BASE_ID) REFERENCES GROUP_BASE(ID)
);

