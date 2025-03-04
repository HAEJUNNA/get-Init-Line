package com.application.getinitline.controller.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * packageName    : com.application.getinitline.controller.api
 * fileName       : APIAuthController
 * author         : NAHAEJUN
 * date           : 2025-02-22
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-22        NAHAEJUN              최초생성
 */
@RequestMapping(value = "/api")
@RestController
public class APIAuthController {
    
    @GetMapping("/sign-up")
    public String signUp() {
        return "done";
    }
}
