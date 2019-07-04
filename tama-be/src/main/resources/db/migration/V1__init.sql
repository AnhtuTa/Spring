-- Hiện tại chưa có flyway mà chỉ có lệnh SQL. Sau này chỉ cần thêm flyway là được

CREATE SCHEMA `tama` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci ;

CREATE TABLE `tama`.`task` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NOT NULL,
  `status` VARCHAR(45) NOT NULL COMMENT '{ACTIVE, DONE}',
  `created_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`));


INSERT INTO `tama`.`task` (`name`, `status`) VALUES ('Play COC', 'ACTIVE');
INSERT INTO `tama`.`task` (`name`, `status`) VALUES ('Chạy bộ', 'ACTIVE');
INSERT INTO `tama`.`task` (`name`, `status`) VALUES ('Hít đất, lên xà', 'DONE');
INSERT INTO `tama`.`task` (`name`, `status`) VALUES ('Học ReactJS', 'ACTIVE');
INSERT INTO `tama`.`task` (`name`, `status`) VALUES ('Học Redux', 'DONE');