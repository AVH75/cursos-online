package pe.todotic.mitienda_s5.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pe.todotic.mitienda_s5.service.StorageService;

import java.io.IOException;
import java.nio.file.Files;


@Slf4j
@Controller
@RequestMapping("/media")
public class MediaController {

    public final static String CONTENT_TYPE_HEADER = "Content-Type";

    @Autowired
    private StorageService storageService;

    @GetMapping("/{filename}")
    ResponseEntity<Resource> index(@PathVariable String filename) throws IOException {
        log.info("index {}", filename);

        Resource resource = storageService.loadAsResource(filename);
        String contentType = Files.probeContentType(resource.getFile().toPath());

        return ResponseEntity
                .ok()
                .header(CONTENT_TYPE_HEADER, contentType)
                .body(resource);
    }

}
