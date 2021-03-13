CREATE TABLE `post` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(200) NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `post_details` (
  `post_id` INT NOT NULL,
  `created_on` DATETIME NULL,
  `content` TEXT NULL,
  PRIMARY KEY (`post_id`),
  CONSTRAINT `fk_post_detail_post`
    FOREIGN KEY (`post_id`)
    REFERENCES `post` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `author` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NULL,
  `address` VARCHAR(200) NULL,
  PRIMARY KEY (`id`));

ALTER TABLE `post` 
  ADD COLUMN `author_id` INT NULL AFTER `title`,
  ADD INDEX `fk_post_author_idx` (`author_id` ASC);

ALTER TABLE `post`
  ADD CONSTRAINT `fk_post_author`
    FOREIGN KEY (`author_id`)
    REFERENCES `author` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

