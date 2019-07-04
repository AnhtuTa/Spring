package tama.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tama.entity.Task;
import tama.model.TaskRequest;
import tama.repository.TaskRepository;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }

    public void createTask(TaskRequest taskReq) {
        Task task = new Task();
        BeanUtils.copyProperties(taskReq, task);
        task.setCreatedDate(new Date());
        taskRepository.save(task);
    }

    public void updateTask(Integer taskId, TaskRequest taskReq) {
        Optional<Task> taskOp = taskRepository.findById(taskId);
        if(!taskOp.isPresent()) {
            throw new RuntimeException("Task not found");
        }
        Task task = taskOp.get();
        BeanUtils.copyProperties(taskReq, task);
        taskRepository.save(task);
    }

    public void deleteTask(Integer taskId) {
        Optional<Task> taskOp = taskRepository.findById(taskId);
        if(!taskOp.isPresent()) {
            throw new RuntimeException("Task not found");
        }
        Task task = taskOp.get();
        taskRepository.delete(task);
    }
}
