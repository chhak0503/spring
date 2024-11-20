package co.kr.chhak.controller;

import co.kr.chhak.dto.UserDTO;
import co.kr.chhak.entity.User;
import co.kr.chhak.jwt.JwtProvider;
import co.kr.chhak.security.MyUserDetails;
import co.kr.chhak.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.transform.OutputKeys;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Log4j2
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @PostMapping("/user/login")
    public ResponseEntity login(@RequestBody UserDTO userDTO) {

        try {
            // 시큐리티 사용자 검증
            UsernamePasswordAuthenticationToken token
                    = new UsernamePasswordAuthenticationToken(userDTO.getUid(), userDTO.getPass());

            Authentication authentication = authenticationManager.authenticate(token);

            MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
            User user = userDetails.getUser();
            log.info("user : " + user);
            
            // JWT 토큰 발행
            String accessToken = jwtProvider.createToken(user, 1);
            String refreshToken = jwtProvider.createToken(user, 7);
            log.info("accessToken : " + accessToken);

            // 리프레쉬 토큰 DB저장
            
            // 토큰 전송
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("username", user.getUid());
            resultMap.put("role", user.getRole());
            resultMap.put("accessToken", accessToken);
            resultMap.put("refreshToken", refreshToken);

            return ResponseEntity.ok(resultMap);

        }catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("USER NOT FOUND");
        }

    }

    @PostMapping("/user")
    public ResponseEntity register(@RequestBody UserDTO userDTO){

        log.info("userDTO : " + userDTO);

        UserDTO savedUser = userService.save(userDTO);

        log.info("savedUser : " + savedUser);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(savedUser);
    }

}
