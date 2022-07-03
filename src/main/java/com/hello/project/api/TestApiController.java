package com.hello.project.api;

import com.hello.project.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class TestApiController {

    @GetMapping("/test2")
    public ResponseEntity<?> test2(@Valid UserDto userDto, BindingResult bindingResult) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
