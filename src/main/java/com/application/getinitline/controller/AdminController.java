package com.application.getinitline.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * packageName    : com.application.getinitline.controller
 * fileName       : AdminController
 * author         : NAHAEJUN
 * date           : 2025-02-22
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-22        NAHAEJUN              최초생성
 */
@RequestMapping(value = "/admin")
@Controller
public class AdminController {

    @GetMapping("/places")
    public String adminPlaces() {
        return "admin/place";
    }
    @GetMapping("/places/{placeId}")
    public String adminPlaceDetail(@PathVariable Integer id) {
        return "admin/place-detail";
    }
    @GetMapping(value = "/events")
    public String adminEvents() {return "admin/events";}

    @GetMapping(value = "/events/{eventId}")
    public String adminEventsDetail(@PathVariable Integer id) {return "admin/event-detail";}

}
