package com.hello.project.controller;

import com.hello.project.config.auth.PrincipalDetails;
import com.hello.project.domain.article.Article;
import com.hello.project.domain.article.ArticleRepository;
import com.hello.project.domain.user.User;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.nio.file.Paths;
import java.util.UUID;

@Slf4j
@NoArgsConstructor
@Controller
public class EditorController {

    @Autowired
    private ArticleRepository articleRepository;

    @Value("${ck.path}")
    String CK_PATH;

    @GetMapping("/edit")
    public String editorForm(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {
        Long userId = principalDetails.getUser().getId();
        model.addAttribute("userId", userId);
        return "ck";
    }

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

    @ResponseBody
    @PostMapping("/edit/file")
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
    }

    //@PostMapping("/edit/file")
    public void imageUpload(HttpServletRequest request, HttpServletResponse response,
                            MultipartHttpServletRequest multiFile,
                            @RequestParam MultipartFile upload) throws Exception {

        UUID uid = UUID.randomUUID();
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        //String callback = request.getParameter("CKEditorFuncNum");

        //파일
        byte[] bytes = upload.getBytes();
        //파일 이름
        String fileName = uid + "_" + upload.getOriginalFilename();
        //폴더 경로
        File folder = new File(CK_PATH);
        //파일 업로드 경로
        String ckUploadPath = CK_PATH + fileName;
        //파일 요청 경로
        String fileUrl = "/edit/file2/" + fileName;

        try (OutputStream out = new FileOutputStream(new File(ckUploadPath));
             PrintWriter printWriter = response.getWriter();) {
            //디렉토리 유무
            if (!folder.exists()) {
                folder.mkdirs();
            }
            //파일 저장
            out.write(bytes);
            out.flush();

            // 업로드시 메시지 출력
            printWriter.println("{\"filename\" : \"" + fileName + "\", \"uploaded\" : 1, \"url\":\"" + fileUrl + "\"}");
            printWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //@GetMapping("/edit/file2")
    public void ckSubmit(HttpServletResponse response) throws IOException {

        String sDirPath = CK_PATH + "f8983012-e791-447b-bce2-fed9009e5199_sql.png";
        File imgFile = new File(sDirPath);

        //사진 이미지 찾지 못하는 경우 예외처리로 빈 이미지 파일을 설정한다.
        if (imgFile.isFile()) {
            byte[] buf = new byte[1024];
            int readByteLen = 0;
            int length = 0;
            byte[] imgBuf = null;

            FileInputStream fileInputStream = new FileInputStream(imgFile);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ServletOutputStream out = response.getOutputStream();
            BufferedInputStream bis = new BufferedInputStream(fileInputStream);
            try {
                //저장소에서 파일 읽어오기 & 이미지 응답 값에 넣기
                while ((readByteLen = bis.read(buf)) != -1) {
                    out.write(buf, 0, readByteLen);
                }
                out.flush();
                //응답 값으로 이미지 넘겨주기
//                imgBuf = outputStream.toByteArray();
//                length = imgBuf.length;
//                out.write(imgBuf, 0, length);
//                out.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

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
    }




}

