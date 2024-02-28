package com.example.demo.Controller;

import com.example.demo.Servis.TaskService;
import com.example.demo.model.Review;
import com.example.demo.model.task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
public class TaskController {

    @Autowired
    private TaskService taskRepository;
    @CrossOrigin
    @PostMapping("/create")
    public ResponseEntity<String> createTask(

            @Valid @RequestBody task task,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            StringBuilder errorMessage = new StringBuilder("Validation errors: ");
            for (FieldError error : fieldErrors) {
                errorMessage.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append("; ");
            }
            return ResponseEntity.badRequest().body(errorMessage.toString());
        } else {
            try {
                taskRepository.Create( task);
                return ResponseEntity.status(HttpStatus.OK)
                        .body("{\"message\": \"Task create successfully\"}");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("{\"error\": \"Error create task\"}");
            }
        }
    }
    @CrossOrigin
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteTask(@RequestParam("task_id") @Min(value = 1, message = "Task ID must be greater than zero") int taskId) {
        try {
            taskRepository.Delete(taskId);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("{\"message\": \"Task delete successfully\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\": \"Error delete task\"}");
        }
    }
    @CrossOrigin
    @PostMapping("/edit")
    public ResponseEntity<String> editTask(@Valid @RequestBody task task, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            StringBuilder errorMessage = new StringBuilder("Validation errors: ");
            for (FieldError error : fieldErrors) {
                errorMessage.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append("; ");
            }
            return ResponseEntity.badRequest().body(errorMessage.toString());
        } else {
            try {
                taskRepository.Edit(task);
                return ResponseEntity.status(HttpStatus.OK)
                        .body("{\"message\": \"Task edited successfully\"}");

            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("{\"error\": \"Error editing task\"}");

            }
        }
    }
    @CrossOrigin
    @GetMapping("/get")
    public ResponseEntity<?> getAllTasks() {
        try {
            Iterable<task> tasks = taskRepository.getAll();
            return ResponseEntity.ok(tasks);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error fetching tasks: " + e.getMessage());
        }
    }
    @CrossOrigin
    @GetMapping("/get1")
    public ResponseEntity<?> getAllTasks(int user_id) {
        try {
            Iterable<task> tasks = taskRepository.getAll(user_id);
            return ResponseEntity.ok(tasks);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error fetching tasks: " + e.getMessage());
        }
    }
    @CrossOrigin
    @GetMapping("/gettask")
    public ResponseEntity<?> getAllTask(int user_id) {
        try {
            Iterable<task> tasks = taskRepository.getAlltask(user_id);
            return ResponseEntity.ok(tasks);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error fetching tasks: " + e.getMessage());
        }
    }
    @CrossOrigin
    @GetMapping("/getbytask_id")
    public ResponseEntity<?> gettask_id(int task_id) {
        try {
            return ResponseEntity.ok( taskRepository.gettask(task_id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error fetching tasks: " + e.getMessage());
        }
    }
    @CrossOrigin
    @GetMapping("/rejecttask")
    public ResponseEntity<?> rejecttask(int task_id) {
        try {
            taskRepository.rejecttask(task_id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("{\"message\": \"Task rejecttask successfully\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\": \"Error rejecttask task\"}");
        }
    }

}
