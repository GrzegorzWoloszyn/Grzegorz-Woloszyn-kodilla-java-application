package com.crud.tasks.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class AdminConfig {

    @Value("do.logowan01@gmail.com")
    private String adminMail;

    @Value("${admin.name}")
    private String adminName;
}
