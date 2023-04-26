package kz.iitu.kidtirp.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import kz.iitu.kidtirp.model.dto.request.LoginRequest;
import kz.iitu.kidtirp.model.dto.request.SignupRequest;
import kz.iitu.kidtirp.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AuthController {

    UserService userService;

    @PostMapping("/public/login")
    @Operation(summary = "Аутентификация пользователя")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }

    @PostMapping("/public/signup")
    @Operation(summary = "Полная регистрация пользователя")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        return userService.registerUser(signUpRequest);
    }

    @PostMapping("/update")
    @Operation(summary = "Обновить данные юзера", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> updateUser(@Valid @RequestBody SignupRequest signUpRequest) {
        return ResponseEntity.ok(userService.updateUser(signUpRequest));
    }

    @GetMapping("/getCurrentUser")
    @Operation(summary = "Возвращает текущего авторизованного имя пользователя пользователя", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> getCurrentUser() {
        return userService.getCurrentUser();
    }

    @GetMapping("/public/exists/{username}")
    @Operation(summary = "Проверяет наличие зарегистрированного пользователя")
    public ResponseEntity<?> userExists(@PathVariable String username) {
        return ResponseEntity.ok(userService.exitsUser(username));
    }

}
