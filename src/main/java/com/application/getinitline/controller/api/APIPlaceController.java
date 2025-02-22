package com.application.getinitline.controller.api;

import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * packageName    : com.application.getinitline.controller.api
 * fileName       : APIPlaceController
 * author         : NAHAEJUN
 * date           : 2025-02-22
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-22        NAHAEJUN              최초생성
 */
@RequestMapping("/api")
@RestController
public class APIPlaceController {

    @GetMapping("/places")
    public List<String> getplaces() {return List.of("placel","place2");}

    @PostMapping("/places")
    public Boolean createPlaces(){
        return true;
    }

    @GetMapping("/palces/{placesId}")
    public String getPlaces(@PathVariable String placesId){
        return "places " + placesId;
    }

    @PutMapping("/places/{placeId}")
    public Boolean modifyPlace(@PathVariable String placeId){
        return true;
    }

    @DeleteMapping("/places/{placeId}")
    public Boolean deletePlace(@PathVariable String placeId){
        return true;
    }

}
