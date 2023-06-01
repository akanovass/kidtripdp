package kz.iitu.kidtirp.service;

import kz.iitu.kidtirp.exceptions.ObjectNotFoundException;
import kz.iitu.kidtirp.exceptions.UserAlreadyExistsException;
import kz.iitu.kidtirp.model.dto.request.LoginRequest;
import kz.iitu.kidtirp.model.dto.request.SignupRequest;
import kz.iitu.kidtirp.model.dto.response.JwtResponse;
import kz.iitu.kidtirp.model.dto.response.MessageResponse;
import kz.iitu.kidtirp.model.entity.enums.ERole;
import kz.iitu.kidtirp.model.entity.User;
import kz.iitu.kidtirp.repository.UserRepository;
import kz.iitu.kidtirp.security.jwt.JwtUtils;
import kz.iitu.kidtirp.security.service.UserDetailsImpl;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    AuthenticationManager authenticationManager;
    PasswordEncoder encoder;
    JwtUtils jwtUtils;

    @Autowired
    public UserService(UserRepository userRepository, AuthenticationManager authenticationManager,
                       PasswordEncoder encoder, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }

    public ResponseEntity<?> login(LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails, roles.get(0)));
    }

    public ResponseEntity<?> registerUser(SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse(false, "Ошибка: Пользователь с таким логином уже существует!"));
        }

        User user = new User(signUpRequest.getUsername(), encoder.encode(signUpRequest.getPassword()));
        user.setGmail(signUpRequest.getGmail());
        user.setPhoneNumber(signUpRequest.getPhoneNumber());
        user.setFullName(signUpRequest.getFullName());
        user.setRole(signUpRequest.getRole());
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse(true, "Пользователь успешно зарегистрирован!"));
    }

    public ResponseEntity<?> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        if (currentPrincipalName == null || currentPrincipalName.equals("")) {
            return ResponseEntity.ok(new MessageResponse(false, "Current User not found"));
        }

        return ResponseEntity.ok(new MessageResponse(true, currentPrincipalName));
    }

    public boolean exitsUser(String username) {
        return userRepository.existsByUsername(username);
    }

    public User createUser(SignupRequest requestDto) {
        if (!userRepository.existsByUsername(requestDto.getUsername())) {
            User user = new User(requestDto.getUsername(), encoder.encode(requestDto.getPassword()));
            user.setGmail(requestDto.getGmail());
            user.setPhoneNumber(requestDto.getPhoneNumber());
            user.setFullName(requestDto.getFullName());
            user.setRole(requestDto.getRole());
            return userRepository.save(user);
        } else {
            throw new UserAlreadyExistsException(String.format("User with username %s already exists", requestDto.getUsername()), "500");
        }
    }

    public User updateUser(SignupRequest requestDto) {
        User user = userRepository.findById(requestDto.getId()).orElse(null);
        if (user != null) {
            user.setUsername(requestDto.getUsername());
            user.setPassword(encoder.encode(requestDto.getPassword()));
            user.setRole(requestDto.getRole());
            user.setGmail(requestDto.getGmail());
            user.setPhoneNumber(requestDto.getPhoneNumber());
            user.setFullName(requestDto.getFullName());
            user.setRole(requestDto.getRole());
            return userRepository.save(user);
        } else {
            throw new ObjectNotFoundException(String.format("User with id %s not found", requestDto.getId()), "404");
        }
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Error: User is not found.", "404"));
        userRepository.delete(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> getAllUsersByRole(ERole role) {
        return userRepository.findAllByRole(role);
    }

}
