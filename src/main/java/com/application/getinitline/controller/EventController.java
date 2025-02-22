package com.application.getinitline.controller;

import io.micrometer.core.instrument.config.validate.Validated;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * packageName    : com.application.getinitline.controller
 * fileName       : EventController
 * author         : NAHAEJUN
 * date           : 2025-02-22
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-22        NAHAEJUN              최초생성
 */
@RequestMapping(value = "/events")
@Controller
public class EventController {

    @GetMapping(value = "/")
    public String events() {return "event/index";}

    @GetMapping(value = "{eventId}")
    public String eventDetail(@PathVariable Integer id) {return "event/detail";}
}
