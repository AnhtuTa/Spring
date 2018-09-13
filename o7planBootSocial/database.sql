create table USER_CONNECTION
(
USER_ID         VARCHAR(255) not null,
PROVIDER_ID     VARCHAR(255) not null,
PROVIDER_USER_ID VARCHAR(255) not null,
RANK           INTEGER not null,
DISPLAY_NAME    VARCHAR(255),
PROFILE_URL     VARCHAR(512),
IMAGE_URL       VARCHAR(512),
ACCESS_TOKEN    VARCHAR(512),
SECRET         VARCHAR(512),
REFRESH_TOKEN   VARCHAR(512),
EXPIRE_TIME     BIGINT
) ;
 
-- Create/Recreate primary, unique and foreign key constraints
alter table USER_CONNECTION
add primary key (USER_ID, PROVIDER_ID, PROVIDER_USER_ID) ;
 
create table PERSISTENT_LOGINS
(
	SERIES varchar(64) not null,
    USERNAME varchar(64) not null,
    TOKEN varchar(64) not null,
    LAST_USED datetime not null,
    primary key (SERIES)
);
   
CREATE TABLE `app_user` (
  `USER_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(36) NOT NULL,
  `EMAIL` varchar(128) NOT NULL,
  `FIRST_NAME` varchar(36) DEFAULT NULL,
  `LAST_NAME` varchar(36) DEFAULT NULL,
  `ENCRYPTED_PASSWORD` varchar(128) NOT NULL,
  `ENABLED` tinyint(4) NOT NULL,
  PRIMARY KEY (`USER_ID`),
  UNIQUE KEY `APP_USER_UK` (`USER_NAME`),
  UNIQUE KEY `APP_USER_UK2` (`EMAIL`)
);

CREATE TABLE `app_role` (
  `ROLE_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ROLE_NAME` varchar(30) NOT NULL,
  PRIMARY KEY (`ROLE_ID`),
  UNIQUE KEY `APP_ROLE_UK` (`ROLE_NAME`)
);

CREATE TABLE `user_role` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `USER_ID` bigint(20) NOT NULL,
  `ROLE_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `USER_ROLE_UK` (`USER_ID`,`ROLE_ID`),
  CONSTRAINT `USER_ROLE_FK1` FOREIGN KEY (`USER_ID`) REFERENCES `app_user` (`USER_ID`),
  CONSTRAINT `USER_ROLE_FK2` FOREIGN KEY (`ROLE_ID`) REFERENCES `app_role` (`ROLE_ID`)
);
    