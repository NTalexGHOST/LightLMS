package ru.darkalive.LightLMS.controllers.rest.entities.delete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.darkalive.LightLMS.entities.*;
import ru.darkalive.LightLMS.repos.*;

import java.io.File;
import java.util.List;

@RestController
public class SubjectDeleteController {

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
    private LinkUserPracticeRepository linkUserPracticeRepo;

    @DeleteMapping(value = "/api/subject", params = "id")
    @ResponseBody
    public void deleteSubject(@RequestParam int id) {

        Subject subject = subjectRepo.findFirstById(id);

        List<Theme> themes = themeRepo.findAllBySubjectOrderByPosition(subject);
        themes.forEach(theme -> deleteTheme(theme.getId()));
        File file = new File("./subjects/" + id);
        file.delete();

        subjectRepo.delete(subject);
        printMessage("Отработал DELETE-запрос, удалена дисциплина - " + subject.getName());
    }

    @DeleteMapping(value = "/api/theme", params = "id")
    @ResponseBody
    public void deleteTheme(@RequestParam int id) {

        Theme theme = themeRepo.findFirstById(id);

        theme.getManualResources().forEach(manualResource -> deleteManual(manualResource.getId()));
        theme.getExternalResources().forEach(externalResource -> externalResourceRepo.delete(externalResource));
        theme.getPractices().forEach(practice -> deletePractice(practice.getId()));

        themeRepo.delete(theme);
        printMessage("Отработал DELETE-запрос, удалена тема - " + theme.getName());
    }

    @DeleteMapping(value = "/api/manual", params = "id")
    @ResponseBody
    public void deleteManual(@RequestParam int id) {

        ManualResource manual = manualResourceRepo.findFirstById(id);
        File file;

        if (manual.isDoc())
            file = new File("./subjects/" + manual.getTheme().getSubject().getId() + "/docs/" + manual.getFileName());
        else
            file = new File("./subjects/" + manual.getTheme().getSubject().getId() + "/other/" + manual.getFileName());
        file.delete();

        manualResourceRepo.delete(manual);
        printMessage("Отработал DELETE-запрос, удален Word-файл - " + manual.getDisplayName());
    }

    @DeleteMapping(value = "/api/external", params = "id")
    @ResponseBody
    public void deleteExternal(@RequestParam int id) {

        ExternalResource external = externalResourceRepo.findFirstById(id);

        externalResourceRepo.delete(external);
        printMessage("Отработал DELETE-запрос, удалена сторонняя ссылка - " + external.getDisplayName());
    }

    @DeleteMapping(value = "/api/practice", params = "id")
    @ResponseBody
    public void deletePractice(@RequestParam int id) {

        Practice practice = practiceRepo.findFirstById(id);

        practice.getManualResources().forEach(manualResource -> deleteManual(manualResource.getId()));
        practice.getExternalResources().forEach(externalResource -> externalResourceRepo.delete(externalResource));



        practiceRepo.delete(practice);
        printMessage("Отработал DELETE-запрос, удалена практическая работа - " + practice.getDisplayName());
    }

    private void printMessage(String message) { System.out.println("[LightLMS - Delete]\t" + message); }
}
