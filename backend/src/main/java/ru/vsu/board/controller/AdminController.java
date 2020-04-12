package ru.vsu.board.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.vsu.board.dto.MessageResponse;
import ru.vsu.board.dto.SignupRequest;
import ru.vsu.board.dto.UserResponse;
import ru.vsu.board.model.ERole;
import ru.vsu.board.model.Role;
import ru.vsu.board.model.User;
import ru.vsu.board.service.RoleService;
import ru.vsu.board.service.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin/users")
@Api
//@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    PasswordEncoder encoder;

    @GetMapping("")
    public List<UserResponse> getUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") Long id){
        User user = userService.findById(id);
        if (user == null)
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: User is not exists!"));
        UserResponse userResponse = new UserResponse();
        userResponse.setUser_id(user.getId());
        userResponse.setUsername(user.getUsername());
        userResponse.setPassword(user.getPassword());
        userResponse.setRole(user.getRoles().get(0).getName().toString());
        return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userService.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        User user = new User(signUpRequest.getUsername(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        List<Role> roles = new ArrayList<>();

        if (strRoles == null) {
            Role userRole = roleService.findByName(ERole.ROLE_STUDENT)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleService.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "teacher":
                        Role modRole = roleService.findByName(ERole.ROLE_TEACHER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleService.findByName(ERole.ROLE_STUDENT)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userService.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@Valid @RequestBody SignupRequest signUpRequest, @PathVariable("id") String id) {
        //Person's role can't be changed
        User user = userService.findById(Long.parseLong(id));
//        if(userService.existsByUsername(signUpRequest.getUsername()) || user ==null){
//            return ResponseEntity
//                    .badRequest()
//                    .body(new MessageResponse("Error: Username is already taken!"));
//        }
        user.setUsername(signUpRequest.getUsername());
        user.setPassword(signUpRequest.getPassword());
        userService.save(user);
        return ResponseEntity.ok(new MessageResponse("User was updated successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") String id) {
        userService.delete(Long.parseLong(id));
        return ResponseEntity.ok(new MessageResponse("User was deleted successfully"));
    }
}