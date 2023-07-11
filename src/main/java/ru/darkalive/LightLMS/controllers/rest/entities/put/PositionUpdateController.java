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
public class PositionUpdateController {

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

    @PutMapping(value = "/api/theme/position")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public @ResponseBody void updateThemePosition(@RequestParam int[] positions) {

        for (int i = 0; i < positions.length; i++) {
            Theme theme = themeRepo.findFirstById(positions[i]);
            theme.setPosition(i + 1);
            themeRepo.save(theme);
        }

        printMessage("Отработал PUT-запрос, обновлены позиции тем");
    }

    @PutMapping(value = "/api/manual/position")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public @ResponseBody void updateManualPosition(@RequestParam int[] positions) {

        for (int i = 0; i < positions.length; i++) {
            ManualResource manualResource = manualResourceRepo.findFirstById(positions[i]);
            manualResource.setPosition(i + 1);
            manualResourceRepo.save(manualResource);
        }

        printMessage("Отработал PUT-запрос, обновлены позиции методических указаний");
    }

    @PutMapping(value = "/api/external/position")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public @ResponseBody void updateExternalPosition(@RequestParam int[] positions) {

        for (int i = 0; i < positions.length; i++) {
            ExternalResource externalResource = externalResourceRepo.findFirstById(positions[i]);
            externalResource.setPosition(i + 1);
            externalResourceRepo.save(externalResource);
        }

        printMessage("Отработал PUT-запрос, обновлены позиции сторонних ссылок");
    }

    @PutMapping(value = "/api/practice/position")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public @ResponseBody void updatePracticePosition(@RequestParam int[] positions) {

        for (int i = 0; i < positions.length; i++) {
            Practice practice = practiceRepo.findFirstById(positions[i]);
            practice.setPosition(i + 1);
            practiceRepo.save(practice);
        }

        printMessage("Отработал PUT-запрос, обновлены позиции практических работ");
    }

    private void printMessage(String message) { System.out.println("[LightLMS - Position]\t" + message); }
}
