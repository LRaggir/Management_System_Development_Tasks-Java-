package com.example.demo.Controller;

import com.example.demo.Servis.UserService;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @CrossOrigin
    @PostMapping("CreateUser")
    public ResponseEntity<String> CreateUser(@Valid @RequestBody User user, BindingResult bindingResult) {
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

               String a = userService.Create(user);
               // return ResponseEntity.ok("User created successfully");
                return ResponseEntity.ok(a);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Error creating user: " + e.getMessage());
            }
        }
    }
    @CrossOrigin
    @PostMapping("authorization")
    public ResponseEntity<?> authorization (@Valid @RequestBody User user, BindingResult bindingResult) {
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
                return ResponseEntity.ok( userService.authorization(user));
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Error creating user: " + e.getMessage());
            }
        }
    }
    @CrossOrigin
    @PostMapping("Performer")
    public ResponseEntity<String> Performer(@RequestParam int task_id, @RequestParam int performer_id) {
        try {
            userService.purpose(task_id, performer_id);
            return ResponseEntity.ok("Task assigned to performer successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error assigning task to performer: " + e.getMessage());
        }
    }
        @CrossOrigin
        @GetMapping("get_id")
        public ResponseEntity<?> getUser ( @RequestParam int user_id) {
            try {
                return ResponseEntity.ok(userService.getUser(user_id));
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Not found: " + e.getMessage());
            }
        }

    @CrossOrigin
    @GetMapping("getAll")
    public ResponseEntity<?> getAllUser ( ) {
        try {
            return ResponseEntity.ok(userService.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Not found: " + e.getMessage());
        }
    }
}

