package com.application.getinitline.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * packageName    : com.application.getinitline.controller
 * fileName       : BaseController
 * author         : NAHAEJUN
 * date           : 2025-02-19
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-19        NAHAEJUN              최초생성
 */
@Controller
//public class BaseController extends ErrorController { // 내가 직접 에러페이지를 컨트롤 할게 아니면 상속받아 구현 x
public class BaseController {
    // 기본 뷰가 없을시, 스프링은 가장전통적인 방식의 view를 찾게된다.
    /*
    * 일반적으로, main 바로하단에 webapp에 폴더를 만들어 생성한다.
    * JspTemplateAvailabilityProvider 클래스를 확인해도 마찬가지다.
    *  src/main/webapp 을 기본으로 가리키고 있다.
    * JspTemplateAvailabilityProvider사용시 톸캣 제스퍼 디펜던시를 추가해야 한다.
    * 기본적으로 JspTemplateAvailabilityProvider바로 보게되어있음
    * 경로 설정시 쉬프트2번 후, alt + shitf + a키 , ProjectStructer 선택해서 module 클릭하여, 경로 추가
    * */
    @GetMapping(value = "/")
    public String root() throws Exception{
        return "index";
    }

}
