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
import java.util.List;
import java.util.Optional;

@RestController
public class ManualCreateController {

    @Autowired
    private ThemeRepository themeRepo;
    @Autowired
    private ManualResourceRepository manualResourceRepo;
    @Autowired
    private PracticeRepository practiceRepo;
    @Autowired
    private ResourceTypeRepository resourceTypeRepo;
    @Autowired
    private ExamRepository examRepo;

    @PostMapping(value = "/api/manual")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public @ResponseBody void createManual(@RequestParam int subjectId, @RequestParam String displayName,
                                           @RequestParam(defaultValue = "0") int themeId, @RequestParam(defaultValue = "0") int taskId,
                                           @RequestParam(defaultValue = "0") int examId, @RequestParam MultipartFile file) {

        ManualResource manual = new ManualResource();

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

        if (themeId != 0) manual = createManualForTheme(manual, themeId);
        else if (taskId != 0) manual = createManualForTask(manual, taskId);
        else manual = createManualForExam(manual, examId);

        manualResourceRepo.save(manual);
        printMessage("Отработал POST-запрос, создан Word-файл - " + manual.getDisplayName());
    }

    private ManualResource createManualForTheme(ManualResource manual, int themeId) {

        Theme theme = themeRepo.findFirstById(themeId);
        Optional<Integer> position = manualResourceRepo.lastPositionByTheme(themeId);
        if (position.isEmpty()) manual.setPosition(1);
        else manual.setPosition(position.get() + 1);
        manual.setTheme(theme);

        return manual;
    }

    private ManualResource createManualForTask(ManualResource manual, int taskId) {

        Practice practice = practiceRepo.findFirstById(taskId);
        Optional<Integer> position = manualResourceRepo.lastPositionByTask(taskId);
        if (position.isEmpty()) manual.setPosition(1);
        else manual.setPosition(position.get() + 1);
        manual.setTask(practice);

        return manual;
    }

    private ManualResource createManualForExam(ManualResource manual, int examId) {

        Exam exam = examRepo.findFirstById(examId);
        Optional<Integer> position = manualResourceRepo.lastPositionByExam(examId);
        if (position.isEmpty()) manual.setPosition(1);
        else manual.setPosition(position.get() + 1);
        manual.setExam(exam);

        return manual;
    }

    private void printMessage(String message) { System.out.println("[LightLMS - Manual]\t" + message); }
}
