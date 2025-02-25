package com.application.getinitline.domain;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * packageName    : com.application.getinitline.domain
 * fileName       : Admin
 * author         : NAHAEJUN
 * date           : 2025-02-26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-26        NAHAEJUN              최초생성
 */
@Data
 public class Admin {
    private Long id;
    private String name;
    private String nickname;
    private String password;
    private String phoneNumber;
    private String memo;

    private LocalDate createdAt;
    private LocalTime modifiedAt;
}
