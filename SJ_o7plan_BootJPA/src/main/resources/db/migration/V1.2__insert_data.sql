INSERT INTO `boot_jpa`.`school` (`id`, `abbr`, `name`, `address`) VALUES ('1', 'HUST', 'Đại Học Bách Khoa', '1 Đại Cồ Việt, Bách Khoa, Hai Bà Trưng, Hà Nội');
INSERT INTO `boot_jpa`.`school` (`id`, `abbr`, `name`, `address`) VALUES ('2', 'HANU', 'Đại học Hà Nội', 'Nguyễn Trãi, P. Văn Quán, Hà Đông, Hà Nội');
INSERT INTO `boot_jpa`.`school` (`id`, `abbr`, `name`, `address`) VALUES ('3', 'PTIT', 'Học Viện Công nghệ Bưu Chính Viễn Thông', 'K10 P. Mộ Lao, Km10, Đường Nguyễn Trãi, Q. Đông, Hà, P. Mộ Lao, Hà Đông, Hà Nội');
INSERT INTO `boot_jpa`.`school` (`id`, `abbr`, `name`, `address`) VALUES ('4', 'FTU', 'Đại học Ngoại thương', '91 Chùa Láng, Láng Thượng, Đống Đa, Hà Nội');
INSERT INTO `boot_jpa`.`school` (`id`, `abbr`, `name`, `address`) VALUES ('5', 'NEU', 'Đại học Kinh tế Quốc dân', '207 Giải Phóng, Đồng Tâm, Hai Bà Trưng, Hà Nội');

INSERT INTO `boot_jpa`.`student` (`id`, `name`, `school_id`) VALUES ('1', 'Anhtu', '1');
INSERT INTO `boot_jpa`.`student` (`id`, `name`, `school_id`) VALUES ('2', 'Longporn', '1');
INSERT INTO `boot_jpa`.`student` (`id`, `name`, `school_id`) VALUES ('3', 'Nguyen Bka', '1');
INSERT INTO `boot_jpa`.`student` (`id`, `name`, `school_id`) VALUES ('4', 'Huy', '2');
INSERT INTO `boot_jpa`.`student` (`id`, `name`, `school_id`) VALUES ('5', 'Toan', '3');
INSERT INTO `boot_jpa`.`student` (`id`, `name`, `school_id`) VALUES ('6', 'Trung', '3');
INSERT INTO `boot_jpa`.`student` (`id`, `name`, `school_id`) VALUES ('7', 'Nam', '5');
INSERT INTO `boot_jpa`.`student` (`id`, `name`, `school_id`) VALUES ('8', 'Phuong', '3');
INSERT INTO `boot_jpa`.`student` (`id`, `name`, `school_id`) VALUES ('9', 'Linh', '4');
INSERT INTO `boot_jpa`.`student` (`id`, `name`, `school_id`) VALUES ('10', 'Phanh Lee', '4');
INSERT INTO `boot_jpa`.`student` (`id`, `name`, `school_id`) VALUES ('11', 'Quynh', '5');
INSERT INTO `boot_jpa`.`student` (`id`, `name`, `school_id`) VALUES ('12', 'Huyen', '4');
INSERT INTO `boot_jpa`.`student` (`id`, `name`, `school_id`) VALUES ('13', 'Tuan Anh', '1');
INSERT INTO `boot_jpa`.`student` (`id`, `name`, `school_id`) VALUES ('14', 'Dat', '1');
INSERT INTO `boot_jpa`.`student` (`id`, `name`, `school_id`) VALUES ('15', 'Kien', '2');


INSERT INTO `boot_jpa`.`post` (`id`, `content`) VALUES ('1', 'Hôm nay là thứ 3, còn tới 3 ngày nữa mới tới thứ 6! Lâu vc!');
INSERT INTO `boot_jpa`.`post` (`id`, `content`) VALUES ('2', 'This is a demo post! welcome to my blog! Today is a very nice day!');

INSERT INTO `boot_jpa`.`comment` (`id`, `post_id`, `content`) VALUES ('1', '1', 'á đù! Lâu vc');
INSERT INTO `boot_jpa`.`comment` (`id`, `post_id`, `content`) VALUES ('2', '1', 'Công nhận lâu thật, éo biết làm gì để giết time đây!');
INSERT INTO `boot_jpa`.`comment` (`id`, `post_id`, `content`) VALUES ('3', '1', 'chơi Clash of clan đê!');
INSERT INTO `boot_jpa`.`comment` (`id`, `post_id`, `content`) VALUES ('4', '1', 'Chán cmn cái game ý rồi. Đua Asphalt sướng hơn');
INSERT INTO `boot_jpa`.`comment` (`id`, `post_id`, `content`) VALUES ('5', '2', 'Hay lắm');
INSERT INTO `boot_jpa`.`comment` (`id`, `post_id`, `content`) VALUES ('6', '2', 'Chúc mừng!');
INSERT INTO `boot_jpa`.`comment` (`id`, `post_id`, `content`) VALUES ('7', '2', 'Waiting for your posts!');
