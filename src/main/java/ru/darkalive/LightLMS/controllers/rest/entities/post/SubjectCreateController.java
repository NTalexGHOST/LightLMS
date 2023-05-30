package ru.darkalive.LightLMS.controllers.rest.entities.post;

import com.google.common.collect.Streams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import ru.darkalive.LightLMS.entities.*;
import ru.darkalive.LightLMS.repos.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class SubjectCreateController {

    @Autowired
    private SubjectRepository subjectRepo;
    @Autowired
    private ThemeRepository themeRepo;
    @Autowired
    private ManualResourceRepository manualResourceRepo;
    @Autowired
    private ExternalResourceRepository externalResourceRepo;
    @Autowired
    private TaskRepository taskRepo;
    @Autowired
    private ResourceTypeRepository resourceTypeRepo;

    @PostMapping(value = "/api/subject", params = "id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createSubject(@PathVariable int id) {

        Subject subject = new Subject();



        subjectRepo.save(subject);

        printMessage("Отработал POST-запрос, создана дисциплина - " + subject.getName());
    }

    @PostMapping(value = "/api/theme", params = { "subjectId", "name" })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createTheme(@RequestParam int subjectId, @RequestParam String name) {

        Theme theme = new Theme();

        theme.setName(name);
        theme.setPosition(themeRepo.lastPosition(subjectId) + 1);
        theme.setSubject(subjectRepo.findFirstById(subjectId));

        themeRepo.save(theme);

        printMessage("Отработал POST-запрос, создана тема - " + theme.getName());
    }

    @PostMapping(value = "/api/manual")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public @ResponseBody void createManualFile(@RequestParam String displayName, @RequestParam int themeId, @RequestParam MultipartFile file) {

        ManualResource manual = new ManualResource();

        Theme theme = themeRepo.findFirstById(themeId);
        int subjectId = theme.getSubject().getId();

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        List<String> docExtensions = Arrays.asList("pdf", "docx", "doc");
        String fileExtension = Streams.findLast(Arrays.stream(fileName.split("\\."))).get();

        Path path;
        if (docExtensions.contains(fileExtension)) {
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
        manual.setPosition(manualResourceRepo.lastPositionByTheme(themeId));
        manual.setTheme(theme);

        manualResourceRepo.save(manual);

        printMessage("Отработал POST-запрос, создан Word-файл - " + manual.getDisplayName());
    }

    @PostMapping(value = "/api/external", params = { "displayName", "url", "typeId", "themeId" })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createExternal(@RequestParam String displayName, @RequestParam String url, @RequestParam int typeId, @RequestParam int themeId) {

        ExternalResource external = new ExternalResource();

        external.setDisplayName(displayName);
        external.setUrl(url);
        external.setPosition(externalResourceRepo.lastPositionByTheme(themeId));
        external.setType(resourceTypeRepo.findFirstById(typeId));
        external.setTheme(themeRepo.findFirstById(themeId));

        externalResourceRepo.save(external);

        printMessage("Отработал POST-запрос, создана сторонняя ссылка - " + external.getDisplayName());
    }

    @PostMapping(value = "/api/task", params = "id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createTask(@PathVariable int id) {

        Task task = taskRepo.findFirstById(id);



        taskRepo.save(task);

        printMessage("Отработал POST-запрос, создана практическая работа - " + task.getDisplayName());
    }

    private void printMessage(String message) { System.out.println("[LightLMS - SubjectEditor]\t" + message); }
}
