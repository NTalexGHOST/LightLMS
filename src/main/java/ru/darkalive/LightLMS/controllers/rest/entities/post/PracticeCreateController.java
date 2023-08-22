package ru.darkalive.LightLMS.controllers.rest.entities.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.darkalive.LightLMS.entities.Practice;
import ru.darkalive.LightLMS.repos.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Optional;

@RestController
public class PracticeCreateController {

    @Autowired
    private ThemeRepository themeRepo;
    @Autowired
    private PracticeRepository practiceRepo;
    @Autowired
    private ResourceTypeRepository resourceTypeRepo;
    @Autowired
    private ExamRepository examRepo;

    @PostMapping(value = "/api/practice")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public @ResponseBody void createPractice(@RequestParam String displayName, @RequestParam Timestamp openingDate,
                                             @RequestParam Timestamp closingDate, @RequestParam Time duration,
                                             @RequestParam int typeId, @RequestParam(defaultValue = "0") int themeId,
                                             @RequestParam(defaultValue = "0") int examId) {

        Practice practice = new Practice();

        practice.setDisplayName(displayName);
        practice.setOpeningDate(openingDate);
        practice.setClosingDate(closingDate);
        practice.setDuration(duration);
        practice.setType(resourceTypeRepo.findFirstById(typeId));

        if (themeId != 0) createPracticeForTheme(practice, themeId);
        else createPracticeForExam(practice, examId);

        practiceRepo.save(practice);
        printMessage("Отработал POST-запрос, создана практическая работа - " + practice.getDisplayName());
    }

    private void createPracticeForTheme(Practice practice, int themeId) {

        Optional<Integer> position = practiceRepo.lastPositionByTheme(themeId);
        if (position.isEmpty()) practice.setPosition(1);
        else practice.setPosition(position.get() + 1);
        practice.setTheme(themeRepo.findFirstById(themeId));
    }

    private void createPracticeForExam(Practice practice, int examId) {

        Optional<Integer> position = practiceRepo.lastPositionByExam(examId);
        if (position.isEmpty()) practice.setPosition(1);
        else practice.setPosition(position.get() + 1);
        practice.setExam(examRepo.findFirstById(examId));
    }

    private void printMessage(String message) { System.out.println("[LightLMS - Practice]\t" + message); }
}
