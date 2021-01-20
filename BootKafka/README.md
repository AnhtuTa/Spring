# BootKafka
Spring Boot with Kafka example

# Technologies are used
- Kafka

# How to run
- Tải và cài đặt kafka cho windows, giải nén, sửa config zookeeper và kafka
- Vào thư mục kafka vừa giải nén mở cmd
- Run zookeeper:
```bin\windows\zookeeper-server-start.bat config\zookeeper.properties```
- Start kafka server:
```bin\windows\kafka-server-start.bat config\server.properties```
- Tạo 1 topic tên là ```topic1```:
```bin\windows\kafka-topics.bat --create --topic topic1 --bootstrap-server localhost:9092```
- Start project và xem kq

# Refs:
- https://stackjava.com/spring-boot/code-vi-du-spring-boot-kafka-produce-consume-kafka-spring.html