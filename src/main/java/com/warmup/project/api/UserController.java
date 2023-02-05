package com.warmup.project.api;

import com.warmup.project.dto.UserResponseDTO;
import com.warmup.project.entity.User;
import com.warmup.project.dto.UserRequestDTO;
import com.warmup.project.service.UserService;
import com.warmup.project.util.CustomPage;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping(path="/users")
public class UserController {
    private final UserService userService;
    UserController(UserService userService){
        this.userService=userService;
    }

//    public User createUser(@RequestBody User user){
//        System.out.println("Create user is triggered.");
//        return user;
//    }
    @PostMapping(path="")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO body){
        System.out.println("Create user is triggered.");
        UserResponseDTO user = userService.createUser(body);
        return ResponseEntity.ok(user);
    }
    @GetMapping(path="")
    public ResponseEntity<List<UserResponseDTO>> getUsers(Pageable pageable, @RequestParam Map<String, String> params){
        System.out.println("Get all users is triggered.");
        List<UserResponseDTO> users = userService.getUsers(params, pageable);
        return ResponseEntity.ok(users);
    }
    @GetMapping(path="/{userId}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable("userId") Long id){
        UserResponseDTO user = userService.getUser(id);
        return ResponseEntity.ok(user);
    }
    @PutMapping(path="/{userId}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable("userId") Long id, @RequestBody UserRequestDTO body){
        System.out.println(body);
        UserResponseDTO newUser = userService.updateUser(id, body);
        return ResponseEntity.ok(newUser);
    }
    @PatchMapping(path="/{userId}")
    public ResponseEntity<UserResponseDTO> patchUpdateUser(@PathVariable("userId") Long id, @RequestBody UserRequestDTO body){
        System.out.println(body);
        UserResponseDTO newUser = userService.patchUpdateUser(id, body);
        return ResponseEntity.ok(newUser);
    }
    @DeleteMapping(path="/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") Long id){
        String message = userService.deleteUser(id);
        return Objects.isNull(message) ? ResponseEntity.status(400).body("User is not found.") : ResponseEntity.ok("");

    }
}
