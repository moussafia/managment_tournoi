package ma.youcode.managment_tournoi_backend.rest.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/download")
public class DownloadFilesController {
    private final ResourceLoader resourceLoader;
    @GetMapping("/members")
    public void downloadSimpleXlsx(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Resource resource = resourceLoader.getResource("classpath:xlsx/membersUpload.xlsx");
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition",
                String.format("attachment; filename=" + resource.getFilename()));
        response.setContentLength((int) resource.contentLength());
        InputStream inputStream = resource.getInputStream();
        FileCopyUtils.copy(inputStream,response.getOutputStream());

    }
}
