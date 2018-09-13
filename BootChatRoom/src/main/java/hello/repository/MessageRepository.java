package hello.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hello.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

}
