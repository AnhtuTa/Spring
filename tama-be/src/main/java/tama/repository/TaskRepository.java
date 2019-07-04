package tama.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tama.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {

}
