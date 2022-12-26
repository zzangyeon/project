package com.hello.project.controller;

import com.hello.project.config.auth.PrincipalDetails;
import com.hello.project.domain.article.Article;
import com.hello.project.repository.ArticleRepository;
import com.hello.project.service.EditorService;
import com.hello.project.service.S3UploadService;
import com.hello.project.domain.user.User;
import io.swagger.annotations.ApiOperation;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;

@Slf4j
@Controller
@RequiredArgsConstructor
public class EditorController {

    private final S3UploadService s3Upload;
    private final EditorService editorService;

    @Value("${ck.path}")
    String CK_PATH;

    @ApiOperation(value = "글 작성", notes = "글 작성하기")
    @GetMapping("/edit")
    public String editorForm(@ApiIgnore @AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {
        Long userId = principalDetails.getUser().getId();
        model.addAttribute("userId", userId);
        return "ck";
    }

    @ApiOperation(value = "에디터 글 저장", notes = "에디터 글 저장하기")
    @ResponseBody
    @PostMapping("/edit/text/{userId}")
    public String saveText(@PathVariable Long userId, @RequestBody String data) throws UnsupportedEncodingException {
        editorService.saveText(userId,data);
        return "hello";
    }

    @ApiOperation(value = "에디터 사진 저장", notes = "에디터 사진 저장하기")
    @ResponseBody
    @PostMapping("/edit/file")
    public String imageUpload(HttpServletResponse response, @RequestParam MultipartFile upload) throws Exception {
        String fileUrl = s3Upload.upload(upload);
        response.setContentType("application/json;charset=utf-8");
        return "{\"filename\" : \"hello\", \"uploaded\" : 1, \"url\":\"" + fileUrl + "\"}";
    }


}

