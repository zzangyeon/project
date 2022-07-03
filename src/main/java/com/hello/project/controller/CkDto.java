package com.hello.project.controller;

import lombok.Data;
import org.springframework.context.annotation.Bean;

@Data
public class CkDto {

    private int uploaded;
    private String fileUrl;
}
