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

@RestController
public class DescriptionUpdateController {

    @Autowired
    private ThemeRepository themeRepo;
    @Autowired
    private PracticeRepository practiceRepo;
    @Autowired
    private ExamRepository examRepo;

    @PutMapping(value = "/api/theme/{id}/description")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public @ResponseBody void updateThemeDescription(@PathVariable int id, @RequestParam String description) {

        Theme theme = themeRepo.findFirstById(id);

        theme.setDescription(description);

        themeRepo.save(theme);
        printMessage("Отработал PUT-запрос, обновлено описание темы - " + id);
    }

    @PutMapping(value = "/api/practice/{id}/description")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public @ResponseBody void updatePracticeDescription(@PathVariable int id, @RequestParam String description) {

        Practice task = practiceRepo.findFirstById(id);

        task.setDescription(description);

        practiceRepo.save(task);
        printMessage("Отработал PUT-запрос, обновлено описание задания - " + id);
    }

    @PutMapping(value = "/api/exam/{id}/description")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public @ResponseBody void updateExamDescription(@PathVariable int id, @RequestParam String description) {

        Exam exam = examRepo.findFirstById(id);

        exam.setDescription(description);

        examRepo.save(exam);
        printMessage("Отработал PUT-запрос, обновлено описание экзамена - " + id);
    }

    private void printMessage(String message) { System.out.println("[LightLMS - Description]\t" + message); }
}
