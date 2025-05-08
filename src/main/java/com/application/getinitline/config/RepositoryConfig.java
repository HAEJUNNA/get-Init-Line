package com.application.getinitline.config;

import com.application.getinitline.repository.EventRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * packageName    : com.application.getinitline.config
 * fileName       : RepositoryConfig
 * author         : NAHAEJUN
 * date           : 2025-03-26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-03-26        NAHAEJUN              최초생성
 */
@Configuration
public class RepositoryConfig {

    @Bean
    public EventRepository eventRepository(){
        return new EventRepository() {};
    }
}
