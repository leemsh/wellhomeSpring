package com.luke.wellhome.controller;


import com.luke.wellhome.domain.UserEntity;
import com.luke.wellhome.service.JoinService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class JoinController {

    private final JoinService joinService;

    public JoinController(JoinService joinService) {

        this.joinService = joinService;
    }

    @PostMapping("/join")
    public String joinProcess(UserEntity userEntity) {

        joinService.joinProcess(userEntity);

        return "ok";
    }
}
