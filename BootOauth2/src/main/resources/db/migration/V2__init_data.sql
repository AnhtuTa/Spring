INSERT INTO `boot_oauth2`.`book` (`id`, `title`, `author`, `price`, `created_date`, `modified_date`) VALUES ('1', 'Những tháng năm rực rỡ', 'Ae-ran Kim', '96000', '2020-01-14', '2020-01-14');
INSERT INTO `boot_oauth2`.`book` (`id`, `title`, `author`, `price`, `created_date`, `modified_date`) VALUES ('2', '21 bài học cho thế kỉ 21', 'Yuval Noah Harari', '210000', '2020-01-14', '2020-01-14');
INSERT INTO `boot_oauth2`.`book` (`id`, `title`, `author`, `price`, `created_date`, `modified_date`) VALUES ('3', 'Conan 97', 'Gosho Aoyama', '20000', '2020-01-14', '2020-01-14');
INSERT INTO `boot_oauth2`.`book` (`id`, `title`, `author`, `price`, `created_date`, `modified_date`) VALUES ('4', 'Battle Royale', 'Koushun Takami', '230000', '2020-01-14', '2020-01-14');
INSERT INTO `boot_oauth2`.`book` (`id`, `title`, `author`, `price`, `created_date`, `modified_date`) VALUES ('5', 'Tôi là ai – và nếu vậy thì bao nhiêu', 'Richard David Precht', '115000', '2020-01-14', '2020-01-14');

INSERT INTO `boot_oauth2`.`user` (`id`, `username`, `password`) VALUES ('1', 'admin', '{bcrypt}$2a$10$4TENNUAwIX4uG8GX2UWPBOuVSB3mBIlMcSVzAePDI/DHieJKQ0P7O');
INSERT INTO `boot_oauth2`.`user` (`id`, `username`, `password`) VALUES ('2', 'att', '{bcrypt}$2a$10$4TENNUAwIX4uG8GX2UWPBOuVSB3mBIlMcSVzAePDI/DHieJKQ0P7O');

INSERT INTO `boot_oauth2`.`role` (`id`, `name`) VALUES ('1', 'ADMIN');
INSERT INTO `boot_oauth2`.`role` (`id`, `name`) VALUES ('2', 'USER');

INSERT INTO `boot_oauth2`.`user_role` (`id`, `user_id`, `role_id`) VALUES ('1', '1', '1');
INSERT INTO `boot_oauth2`.`user_role` (`id`, `user_id`, `role_id`) VALUES ('2', '1', '2');
INSERT INTO `boot_oauth2`.`user_role` (`id`, `user_id`, `role_id`) VALUES ('3', '2', '2');
