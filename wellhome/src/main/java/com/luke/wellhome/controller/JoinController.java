package com.luke.wellhome.controller;


import com.luke.wellhome.domain.UserEntity;
import com.luke.wellhome.dto.HttpHeaderMaker;
import com.luke.wellhome.dto.SuccessResponse;
import com.luke.wellhome.service.JoinService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class JoinController {

    private final JoinService joinService;

    public JoinController(JoinService joinService) {
        this.joinService = joinService;
    }

    @PostMapping("/join")
    public ResponseEntity<SuccessResponse<String>> joinProcess(@RequestBody UserEntity userEntity) {

        SuccessResponse<String> response = joinService.joinProcess(userEntity);

        return new ResponseEntity<>(response, HttpHeaderMaker.makeHeader(), response.getStatus());
    }
}
