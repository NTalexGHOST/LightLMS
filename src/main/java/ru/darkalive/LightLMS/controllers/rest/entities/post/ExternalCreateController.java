package ru.darkalive.LightLMS.controllers.rest.entities.post;

import com.google.common.collect.Streams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import java.util.Arrays;
import java.util.Optional;

@RestController
public class ExternalCreateController {

    @Autowired
    private ThemeRepository themeRepo;
    @Autowired
    private ExternalResourceRepository externalResourceRepo;
    @Autowired
    private PracticeRepository practiceRepo;
    @Autowired
    private ResourceTypeRepository resourceTypeRepo;
    @Autowired
    private ExamRepository examRepo;

    @PostMapping(value = "/api/external")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public @ResponseBody void createExternal(@RequestParam String displayName, @RequestParam String url, @RequestParam int typeId,
                                           @RequestParam(defaultValue = "0") int themeId, @RequestParam(defaultValue = "0") int taskId,
                                           @RequestParam(defaultValue = "0") int examId) {

        ExternalResource external = new ExternalResource();

        external.setDisplayName(displayName);
        external.setUrl(url);
        external.setType(resourceTypeRepo.findFirstById(typeId));

        if (themeId != 0) external = createExternalForTheme(external, themeId);
        else if (taskId != 0) external = createExternalForTask(external, taskId);
        else external = createExternalForExam(external, examId);

        externalResourceRepo.save(external);
        printMessage("Отработал POST-запрос, создана сторонняя ссылка - " + external.getDisplayName());
    }

    private ExternalResource createExternalForTheme(ExternalResource external, int themeId) {

        Optional<Integer> position = externalResourceRepo.lastPositionByTheme(themeId);
        if (position.isEmpty()) external.setPosition(1);
        else external.setPosition(position.get() + 1);
        external.setTheme(themeRepo.findFirstById(themeId));

        return external;
    }

    private ExternalResource createExternalForTask(ExternalResource external, int taskId) {

        Optional<Integer> position = externalResourceRepo.lastPositionByTask(taskId);
        if (position.isEmpty()) external.setPosition(1);
        else external.setPosition(position.get() + 1);
        external.setTask(practiceRepo.findFirstById(taskId));

        return external;
    }

    private ExternalResource createExternalForExam(ExternalResource external, int examId) {

        Optional<Integer> position = externalResourceRepo.lastPositionByExam(examId);
        if (position.isEmpty()) external.setPosition(1);
        else external.setPosition(position.get() + 1);
        external.setExam(examRepo.findFirstById(examId));

        return external;
    }

    private void printMessage(String message) { System.out.println("[LightLMS - External]\t" + message); }
}
