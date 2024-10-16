package com.luke.wellhome.controller;



import com.amazonaws.Response;
import com.luke.wellhome.domain.UserEntity;
import com.luke.wellhome.dto.HttpHeaderMaker;
import com.luke.wellhome.dto.SuccessResponse;
import com.luke.wellhome.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @CrossOrigin(origins = "*")
    @GetMapping(value="/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SuccessResponse<UserEntity>> getUser(@RequestParam String email) {
        SuccessResponse<UserEntity> response = userService.getUser(email);
        return new ResponseEntity<>(response, HttpHeaderMaker.makeHeader(), response.getStatus());
    }

    @PostMapping(value="/updateprice", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SuccessResponse<String>> updatePrice(@RequestBody UserEntity user) {
        SuccessResponse<String> response = userService.updatePrice(user);
        return new ResponseEntity<>(response, HttpHeaderMaker.makeHeader(), response.getStatus());
    }
}
