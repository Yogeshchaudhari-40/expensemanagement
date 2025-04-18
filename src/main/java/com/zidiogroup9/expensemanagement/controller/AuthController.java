package com.zidiogroup9.expensemanagement.controller;

import com.zidiogroup9.expensemanagement.advices.ApiResponse;
import com.zidiogroup9.expensemanagement.dtos.*;
import com.zidiogroup9.expensemanagement.services.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody @Valid SignUpDto signUpDto){
        return new ResponseEntity<>(authService.signUp(signUpDto), HttpStatus.CREATED);
    }
    @PostMapping(path = "/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto loginDTO, HttpServletResponse httpServletResponse){
        String[] tokens = authService.login(loginDTO);
        Cookie cookie = new Cookie("refreshToken",tokens[1]);
        cookie.setHttpOnly(true);
        httpServletResponse.addCookie(cookie);
        return ResponseEntity.ok(new LoginResponseDto(tokens[0]));
    }

    @PostMapping(path = "/refresh")
    public ResponseEntity<LoginResponseDto> refresh(HttpServletRequest request){
        String refreshToken= Arrays.stream(request.getCookies())
                .filter(cookie -> "refreshToken".equals(cookie.getValue()))
                .findFirst()
                .map(Cookie::getValue)
                .orElseThrow(()->new AuthenticationServiceException("Refresh token not found inside the Cookies"));
        String[] tokens = authService.refreshToken(refreshToken);
        return ResponseEntity.ok(new LoginResponseDto(tokens[0]));
    }

    @PostMapping(path = "/forgetPassword")
    public ResponseEntity<ApiResponse<?>> forgetPasswordRequest(@RequestBody ForgetPasswordRequestDto forgetPasswordRequestDto){
        authService.sendResetLink(forgetPasswordRequestDto.getEmail());
        ApiResponse<String> response = new ApiResponse<>("Password reset link sent to your email");
        return ResponseEntity.ok(response);
    }
    @PatchMapping(path = "/resetPassword")
    public ResponseEntity<ApiResponse<?>> resetPassword(@RequestBody ResetPasswordDto resetPasswordDto){
        authService.resetPassword(resetPasswordDto.getToken(),resetPasswordDto.getNewPassword());
        ApiResponse<String> response = new ApiResponse<>("Password has been reset successfully");
        return ResponseEntity.ok(response);
    }
}
