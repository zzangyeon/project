package com.hello.project.controller;

import com.hello.project.config.auth.PrincipalDetails;
import com.hello.project.domain.article.Article;
import com.hello.project.domain.article.ArticleRepository;
import com.hello.project.domain.article.S3UploadService;
import com.hello.project.domain.user.User;
import io.swagger.annotations.ApiOperation;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.util.UUID;

@Slf4j
@NoArgsConstructor
@Controller
public class EditorController {

    @Autowired
    private S3UploadService s3Upload;
    @Autowired
    private ArticleRepository articleRepository;

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
        String title = URLDecoder.decode(data.substring(data.indexOf("=")+1,data.lastIndexOf("=")-5), "UTF-8");
        String content = URLDecoder.decode(data.substring(data.lastIndexOf("=")+1), "UTF-8");
        System.out.println(content + title);
        Article article = Article.builder().content(content).title(title).user(User.builder().id(userId).build()).build();
        articleRepository.save(article);
        return "hello";
    }

    @ApiOperation(value = "에디터 사진 저장", notes = "에디터 사진 저장하기")
    @ResponseBody
    @PostMapping("/edit/file")
    public String imageUpload3(HttpServletResponse response, @RequestParam MultipartFile upload) throws Exception {
        String fileUrl = s3Upload.upload(upload);
        response.setContentType("application/json;charset=utf-8");
        return "{\"filename\" : \"hello\", \"uploaded\" : 1, \"url\":\"" + fileUrl + "\"}";
    }

    /*@ApiOperation(value = "에디터 사진 저장", notes = "에디터 사진 저장하기")
    @ResponseBody
    @PostMapping("/edit/file333")
    public String imageUpload2(HttpServletResponse response, @RequestParam MultipartFile upload) throws Exception {
        //파일
        byte[] bytes = upload.getBytes();
        //파일 이름
        String fileName = UUID.randomUUID() + "_" + upload.getOriginalFilename();
        //폴더 경로
        File folder = new File(CK_PATH);
        //파일 업로드 경로
        String ckUploadPath = CK_PATH + fileName;
        //파일 요청 mapping
        String fileUrl = "/edit/file2/" + fileName;

        try (OutputStream out = new FileOutputStream(new File(ckUploadPath));) {
            //디렉토리 유무
            if (!folder.exists()) {
                folder.mkdirs();
            }
            //파일 저장 & url값 응답
            out.write(bytes);
            out.flush();
            response.setContentType("application/json;charset=utf-8");
            return "{\"filename\" : \"" + fileName + "\", \"uploaded\" : 1, \"url\":\"" + fileUrl + "\"}";

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }*/

    /*@ApiOperation(value = "사진 가져오기", notes = "사진 가져오기")
    @GetMapping("/edit/file2/{fileName}")
    public void ckSubmit2(@PathVariable String fileName, HttpServletResponse response) throws IOException {

        String sDirPath = CK_PATH + fileName;
        File imgPath = new File(sDirPath);

        byte[] buf = new byte[9999];
        int readByteLen;

        FileInputStream fis = new FileInputStream(imgPath);
        BufferedInputStream bis = new BufferedInputStream(fis);
        ServletOutputStream out = response.getOutputStream();

        try {
            while ((readByteLen = bis.read(buf)) != -1) {
                out.write(buf, 0, readByteLen);
            }
            out.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            fis.close();
            bis.close();
            out.close();
        }
    }*/


}

