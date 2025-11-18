package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.security.JwtUtils;
import com.example.demo.service.UserService;
import io.jsonwebtoken.Claims;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserService userService;
    private final JwtUtils jwtUtils;

    @Autowired
    public AuthController(UserService userService, JwtUtils jwtUtils) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
    }

    // ------------------ SIGNUP ------------------
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody Map<String, String> body) {
        String name = body.get("name");
        String email = body.get("email");
        String password = body.get("password");

        if (userService.existsByEmail(email)) {
            return ResponseEntity.badRequest().body(Map.of("message", "Email already exists"));
        }

        User user = userService.registerUser(name, email, password);
        String token = jwtUtils.generateToken(
                user.getId(),
                user.getEmail(),
                user.getRole().name()   // 🔥 FIX
        );


        return ResponseEntity.ok(Map.of(
                "token", token,
                "user", Map.of(
                        "id", user.getId(),
                        "name", user.getName(),
                        "email", user.getEmail()
                )
        ));
    }

    // ------------------ LOGIN ------------------
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String password = body.get("password");

        Optional<User> userOpt = userService.findByEmail(email);

        if (userOpt.isEmpty())
            return ResponseEntity.status(401).body(Map.of("message", "Invalid credentials"));

        User user = userOpt.get();

        if (!userService.checkPassword(password, user.getPassword()))
            return ResponseEntity.status(401).body(Map.of("message", "Invalid credentials"));

        String token = jwtUtils.generateToken(
                user.getId(),
                user.getEmail(),
                user.getRole().name()   // 🔥 FIX
        );


        return ResponseEntity.ok(Map.of(
                "token", token,
                "user", Map.of(
                        "id", user.getId(),
                        "name", user.getName(),
                        "email", user.getEmail()
                )
        ));
    }

    // ------------------ ME (Get authenticated user) ------------------
    @GetMapping("/me")
    public ResponseEntity<?> me(@RequestHeader("Authorization") String authHeader) {

        if (!authHeader.startsWith("Bearer "))
            return ResponseEntity.status(401).body(Map.of("message", "Invalid token"));

        String token = authHeader.substring(7);

        if (!jwtUtils.validateToken(token))
            return ResponseEntity.status(401).body(Map.of("message", "Invalid token"));

        Claims claims = jwtUtils.getClaims(token);

        return ResponseEntity.ok(Map.of(
                "id", claims.getSubject(),
                "email", claims.get("email"),
                "role", claims.get("role")
        ));
    }
}
