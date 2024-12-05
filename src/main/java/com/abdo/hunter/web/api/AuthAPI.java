package com.abdo.hunter.web.api;

import com.abdo.hunter.domain.entity.User;
import com.abdo.hunter.service.UserService;
import com.abdo.hunter.web.vm.mapper.UserVmMapper;
import com.abdo.hunter.web.vm.request.LoginRequest;
import com.abdo.hunter.web.vm.response.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class AuthAPI {

    private final UserService userService;
    private final UserVmMapper userVmMapper;


    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(
            @Valid @RequestBody LoginRequest loginRequest) {
        User userEntity = userVmMapper.toUser(loginRequest);
        User user = userService.login(userEntity);
        return ResponseEntity.ok(userVmMapper.toUserResponse(user));
    }



}
