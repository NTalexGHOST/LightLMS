package ru.darkalive.LightLMS.controllers.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.darkalive.LightLMS.repos.GroupRepository;
import ru.darkalive.LightLMS.repos.UserRepository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@Controller
public class FilesController {

    @Autowired
    private GroupRepository groupRepo;
    @Autowired
    private UserRepository userRepo;

    @GetMapping("/theory")
    public ResponseEntity<InputStreamResource> getTermsConditions() throws FileNotFoundException {

        String filePath = "./theory/";
        String fileName = "example.pdf";
        File file = new File(filePath+fileName);
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-disposition", "inline;filename=" +fileName);

        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/pdf"))
                .body(resource);
    }
}
