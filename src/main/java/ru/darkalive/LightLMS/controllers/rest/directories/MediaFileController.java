package ru.darkalive.LightLMS.controllers.rest.directories;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
public class MediaFileController {

    @GetMapping(value = "/logo/{subject}/{file}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Resource> getLogo(@PathVariable("subject") int subjectId, @PathVariable("file") String fileName) throws IOException {

        return getInlineMedia("./subjects/" + subjectId + "/" + fileName);
    }

    @GetMapping(value = "/subjects/{subject}/{file}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Resource> getManual(@PathVariable("subject") int subjectId, @PathVariable("file") String fileName) throws IOException {

        return getInlineMedia("./subjects/" + subjectId + "/docs/" + fileName);
    }

    @GetMapping(value = "/subjects/{subject}/download/{file}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Resource> downloadOther(@PathVariable("subject") int subjectId, @PathVariable("file") String fileName) throws IOException {

        return getAttachmentMedia("./subjects/" + subjectId + "/other/" + fileName);
    }

    private ResponseEntity<Resource> getAttachmentMedia(String path) throws UnsupportedEncodingException {

        FileSystemResource resource = new FileSystemResource(path);

        MediaType mediaType = MediaTypeFactory
                .getMediaType(resource)
                .orElse(MediaType.APPLICATION_OCTET_STREAM);
        ContentDisposition disposition = ContentDisposition
                .attachment()
                .filename(URLEncoder.encode(resource.getFilename(), StandardCharsets.UTF_8))
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);
        headers.setContentDisposition(disposition);

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }

    private ResponseEntity<Resource> getInlineMedia(String path) throws UnsupportedEncodingException {

        FileSystemResource resource = new FileSystemResource(path);

        MediaType mediaType = MediaTypeFactory
                .getMediaType(resource)
                .orElse(MediaType.APPLICATION_OCTET_STREAM);
        ContentDisposition disposition = ContentDisposition
                .inline()
                .filename(URLEncoder.encode(resource.getFilename(), StandardCharsets.UTF_8))
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);
        headers.setContentDisposition(disposition);

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }
}
