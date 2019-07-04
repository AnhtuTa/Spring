package tama.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tama.common.Result;
import tama.entity.Task;
import tama.model.TaskRequest;
import tama.service.TaskService;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public Result<List<Task>> getAllTaks() {
        List<Task> taskList = taskService.getAllTask();
        Result<List<Task>> res = new Result<>();
        res.setSuccessResult(taskList);
        return res;
    }

    @PostMapping
    public Result<Object> createTask(@RequestBody TaskRequest taskReq) {
        Result<Object> res = new Result<>();
        taskService.createTask(taskReq);
        res.setSuccessResult(null);
        return res;
    }

    @PutMapping("/{taskId}")
    public Result<Object> updateTask(
            @PathVariable Integer taskId, @RequestBody TaskRequest taskReq) {
        Result<Object> res = new Result<>();
        taskService.updateTask(taskId, taskReq);
        res.setSuccessResult(null);
        return res;
    }

    @DeleteMapping("/{taskId}")
    public Result<Object> deteleTask(
            @PathVariable Integer taskId) {
        Result<Object> res = new Result<>();
        taskService.deleteTask(taskId);
        res.setSuccessResult(null);
        return res;
    }
}
