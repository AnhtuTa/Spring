## How to run
- Đầu tiên phải start zookeeper và kafka trước đã
- Sau đó run file SimpleProducer.java để tạo Producer sản xuất message
- Tiếp đến run file SimpleConsumer.java, file này sẽ lắng nghe từ 1 topic mà Producer ở trên gửi tới

## Note
Các file trong package demo này tách riêng biệt với project, tức là có thể chạy độc lập mà ko cần spring hay spring boot, chỉ cần chạy như app java bình thường