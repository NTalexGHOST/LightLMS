package ru.darkalive.LightLMS.controllers.rest.directories;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@RestController
public class MediaFileController {

    @GetMapping(value = "/subjects/{subject}/{file}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getLogo(@PathVariable("subject") String subject, @PathVariable("file") String file) throws IOException {
        File logo = new File("./subjects/" + subject + "/" + file).getAbsoluteFile();
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(Files.readAllBytes(logo.toPath()));
    }

    @GetMapping(value = "/subjects/{subject}/{file}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getPdf(@PathVariable("subject") String subject, @PathVariable("file") String file) throws IOException {
        File pdfFile = new File("./subjects/" + subject + "/" + file).getAbsoluteFile();
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(Files.readAllBytes(pdfFile.toPath()));
    }
}
