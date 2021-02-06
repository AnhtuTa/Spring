INSERT INTO `author` (`id`, `name`, `address`) VALUES ('1', 'Tuzaku', 'Hanoi');
INSERT INTO `author` (`id`, `name`, `address`) VALUES ('2', 'Nguyên Bka', 'Hanoi, Vietnam');
INSERT INTO `author` (`id`, `name`, `address`) VALUES ('3', 'Huyga', 'Hanoi');
INSERT INTO `author` (`id`, `name`, `address`) VALUES ('4', 'Toàn tvt', 'Bắc Giang');


INSERT INTO `post` (`id`, `title`, `author_id`) VALUES ('1', 'First post', '1');
INSERT INTO `post` (`id`, `title`, `author_id`) VALUES ('2', 'Bitcoin is increasing rapidly', '1');
INSERT INTO `post` (`id`, `title`, `author_id`) VALUES ('3', 'Trump lần đầu lên tiếng sau khi mãn nhiệm', '2');
INSERT INTO `post` (`id`, `title`, `author_id`) VALUES ('4', 'Dùng hóa chất ngâm 1,3 tấn thịt ốc', '1');
INSERT INTO `post` (`id`, `title`, `author_id`) VALUES ('5', 'Xác hổ hơn 250 kg trong nhà dân', '2');


INSERT INTO `post_details` (`post_id`, `created_on`, `content`) VALUES ('1', '2020-12-21', 'Đây là post đầu tiên, welcome every one!');
INSERT INTO `post_details` (`post_id`, `created_on`, `content`) VALUES ('2', '2020-12-23', 'Bitcoin đang có vài bước điều chỉnh giảm nhẹ sau nhiều ngày tăng fomo kinh khủng.');
INSERT INTO `post_details` (`post_id`, `created_on`, `content`) VALUES ('3', '2021-01-23', 'Trump úp mở về việc \"làm gì đó\" trong lần đầu trả lời câu hỏi của phóng viên sau khi rời Nhà Trắng về Florida');
INSERT INTO `post_details` (`post_id`, `created_on`, `content`) VALUES ('4', '2021-01-24', 'TP. HCM: Cảnh sát kiểm tra cơ sở chế biến ở quận 8, phát hiện nhân viên đang dùng hóa chất ngâm thịt ốc bươu, để tẩy trắng và tạo độ giòn trước khi giao cho các quán ăn.');
INSERT INTO `post_details` (`post_id`, `created_on`, `content`) VALUES ('5', '2021-01-26', 'Hàng chục cán bộ phòng Cảnh sát môi trường, Cảnh sát kinh tế (Công an Hà Tĩnh) và Công an huyện Hương Sơn đã kiểm tra nhà ông Nghệ, thu giữ con hổ bị trói hai chân sau bằng xây xích, miệng chảy máu, nằm bất động trên tấm bạt cỡ lớn phía sau khu vệ sinh. Bên cạnh là nồi hơi được cho dùng nấu cao động vật.');
