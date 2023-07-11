package ru.darkalive.LightLMS.controllers.rest.entities.put;

import com.google.common.collect.Streams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import ru.darkalive.LightLMS.entities.*;
import ru.darkalive.LightLMS.repos.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;

@RestController
public class SubjectUpdateController {

    @Autowired
    private SubjectRepository subjectRepo;
    @Autowired
    private ThemeRepository themeRepo;
    @Autowired
    private ManualResourceRepository manualResourceRepo;
    @Autowired
    private ExternalResourceRepository externalResourceRepo;
    @Autowired
    private PracticeRepository practiceRepo;
    @Autowired
    private ResourceTypeRepository resourceTypeRepo;
    @Autowired
    private ExamRepository examRepo;

    @PutMapping(value = "/api/subject", params = "id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateSubject(@PathVariable int id) {

        Subject subject = subjectRepo.findFirstById(id);



        subjectRepo.save(subject);

        printMessage("Отработал PUT-запрос, обновлена дисциплина - " + subject.getName());
    }

    @PutMapping(value = "/api/theme", params = { "id", "name" })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTheme(@RequestParam int id, @RequestParam String name) {

        Theme theme = themeRepo.findFirstById(id);
        theme.setName(name);
        themeRepo.save(theme);

        printMessage("Отработал PUT-запрос, обновлена тема - " + theme.getName());
    }

    @PutMapping(value = "/api/manual", params = { "id", "displayName" })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateManualWithoutFile(@PathVariable int id, @RequestParam String displayName) {

        ManualResource manual = manualResourceRepo.findFirstById(id);
        manual.setDisplayName(displayName);
        manualResourceRepo.save(manual);

        printMessage("Отработал PUT-запрос, обновлено методическое указание - " + manual.getDisplayName());
    }

    @PutMapping(value = "/api/manual/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public @ResponseBody void updateManualWithFile(@PathVariable int id, @RequestParam int subjectId,
                                               @RequestParam String displayName, @RequestParam MultipartFile file) {

        ManualResource manual = manualResourceRepo.findFirstById(id);

        File deleteFile;
        if (manual.isDoc()) deleteFile = new File("./subjects/" + subjectId + "/docs/" + manual.getFileName());
        else deleteFile = new File("./subjects/" + subjectId + "/other/" + manual.getFileName());
        deleteFile.delete();

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String fileExtension = Streams.findLast(Arrays.stream(fileName.split("\\."))).get();

        Path path;
        if (fileExtension.equals("pdf")) {
            path = Paths.get("./subjects/" + subjectId + "/docs/" + fileName);
            manual.setType(resourceTypeRepo.findFirstById(1));
        }
        else {
            path = Paths.get("./subjects/" + subjectId + "/other/" + fileName);
            manual.setType(resourceTypeRepo.findFirstById(2));
        }

        try {
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (ResponseStatusException | IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        manual.setDisplayName(displayName);
        manual.setFileName(fileName);

        manualResourceRepo.save(manual);
        printMessage("Отработал PUT-запрос, обновлено методическое указание - " + manual.getDisplayName());
    }

    @PutMapping(value = "/api/external", params = { "id", "displayName", "url", "typeId" })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateExternal(@RequestParam int id, @RequestParam String displayName, @RequestParam String url, @RequestParam int typeId) {

        ExternalResource external = externalResourceRepo.findFirstById(id);

        external.setDisplayName(displayName);
        external.setUrl(url);
        external.setType(resourceTypeRepo.findFirstById(typeId));

        externalResourceRepo.save(external);
        printMessage("Отработал PUT-запрос, обновлена сторонняя ссылка - " + external.getDisplayName());
    }

    @PutMapping(value = "/api/exam", params = { "id", "name" })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateExam(@RequestParam int id, @RequestParam String name) {

        Exam exam = examRepo.findFirstById(id);
        exam.setName(name);

        examRepo.save(exam);
        printMessage("Отработал PUT-запрос, обновлен экзамен - " + exam.getName());
    }

    private void printMessage(String message) { System.out.println("[LightLMS - SubjectEditor]\t" + message); }
}
