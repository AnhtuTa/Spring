Các topic trong project này:
- Redis
- Cache
- ExceptionHandler
- Chú ý: bài này tập trung vào việc Cache (dùng Redis) chứ ko tập trung nhiều vào Redis

Nguồn: https://medium.com/@MatthewFTech/spring-boot-cache-with-redis-56026f7da83a
How it works?
- Đầu tiên cần chạy redis trước (Xem thêm cách cài và chạy redis trên windows:
https://github.com/ServiceStack/redis-windows#current-version-30503-june-28-2016)
- Sau đó run app
- Truy cập vào http://localhost:8080/posts/IDX001
Với lần đầu truy cập thì sẽ tốn 1s server mới return dữ liệu về,
nhưng lần truy cập sau thì server return luôn, vì data này đã được lưu
trong cache. Mở redis-client.ext, gõ lệnh:
keys *
sẽ thấy 1 key tên là "post-single::IDX001", nghĩa là post có id = IDX001
đã được lưu trong cache, và server sẽ ko cần thông qua PostService để lấy
post này mỗi lần user request nữa.
(Xem PostController.getPostByID())
- Chú ý rằng method PostController.getPostByID() có điều kiện unless = "#result.shares < 500"
nghĩa là các post có field shares < 500 sẽ ko được lưu vào cache