package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/task")
public class TaskController {

    @RequestMapping(method = RequestMethod.GET, value = "getTasks")
    public List<TaskDto> getTasks() {
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.GET,
            value = "getTask",
            params = "taskId")
    public TaskDto getTask(@RequestParam("taskId") String taskId) {
        return new TaskDto((long) 1, "test title", "Test content");
    }

    @RequestMapping(method = RequestMethod.DELETE,
            params = "taskId")
    public void deleteTask(@RequestParam("taskId") String taskId) {

    }

    @RequestMapping(method = RequestMethod.POST,
            value = "updateTask",
            params = "taskDto")
    public TaskDto updateTask(@RequestParam("taskDto") TaskDto taskDto) {
        return new TaskDto((long) 1, "Edited test title", "test_content");
    }

    @RequestMapping(method = RequestMethod.PUT,
            value = "createTask",
            params = "taskDto")
    public void createTask(@RequestParam("taskDto") TaskDto taskDto) {

    }
}
