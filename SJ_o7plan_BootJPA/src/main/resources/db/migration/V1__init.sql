CREATE TABLE `boot_jpa`.`school` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `abbr` VARCHAR(10) NULL,
  `name` VARCHAR(500) NULL,
  `address` VARCHAR(500) NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `boot_jpa`.`student` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NULL,
  `school_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `school_id_fk_student_idx` (`school_id` ASC),
  CONSTRAINT `school_id_fk_student`
    FOREIGN KEY (`school_id`)
    REFERENCES `boot_jpa`.`school` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `boot_jpa`.`post` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `content` TEXT NULL,
  `time` DATETIME NULL NOT NULL DEFAULT now(),
  PRIMARY KEY (`id`));

CREATE TABLE `boot_jpa`.`comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `post_id` INT NOT NULL,
  `content` TEXT NULL,
  `time` DATETIME NULL DEFAULT now(),
  PRIMARY KEY (`id`),
  INDEX `post_id_fk_comment_idx` (`post_id` ASC),
  CONSTRAINT `post_id_fk_comment`
    FOREIGN KEY (`post_id`)
    REFERENCES `boot_jpa`.`post` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    