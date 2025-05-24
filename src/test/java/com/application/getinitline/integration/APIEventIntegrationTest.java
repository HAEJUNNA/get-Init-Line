package com.application.getinitline.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

/**
 * packageName    : com.application.getinitline.integration
 * fileName       : APIEventIntegrationTest
 * author         : NAHAEJUN
 * date           : 2025-05-18
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-05-18        NAHAEJUN              최초생성
 */
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class APIEventIntegrationTest {

    @Autowired private MockMvc mvc;

}
