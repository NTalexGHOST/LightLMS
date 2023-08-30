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

    @DeleteMapping(value = "/api/subject", params = "id")
    @ResponseBody
    public void deleteSubject(@RequestParam int id) {

        Subject subject = subjectRepo.findFirstById(id);

        List<Theme> themes = themeRepo.findAllBySubjectOrderByPosition(subject);
        themes.forEach(theme -> deleteTheme(theme.getId()));
        File file = new File("./subjects/" + id);
        file.delete();

        subjectRepo.delete(subject);
        printMessage("Удалена дисциплина - " + subject.getName());
    }

    @DeleteMapping(value = "/api/theme", params = "id")
    @ResponseBody
    public void deleteTheme(@RequestParam int id) {

        Theme theme = themeRepo.findFirstById(id);

        theme.getManualResources().forEach(manualResource -> deleteManual(manualResource.getId()));
        theme.getExternalResources().forEach(externalResource -> deleteExternal(externalResource.getId()));
        theme.getPractices().forEach(practice -> deletePractice(practice.getId()));

        themeRepo.delete(theme);
        printMessage("Удалена тема - " + theme.getName());
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
        printMessage("Удален PDF-файл - " + manual.getDisplayName());
    }

    @DeleteMapping(value = "/api/external", params = "id")
    @ResponseBody
    public void deleteExternal(@RequestParam int id) {

        ExternalResource external = externalResourceRepo.findFirstById(id);

        externalResourceRepo.delete(external);
        printMessage("Удалена сторонняя ссылка - " + external.getDisplayName());
    }

    @DeleteMapping(value = "/api/practice", params = "id")
    @ResponseBody
    public void deletePractice(@RequestParam int id) {

        Practice practice = practiceRepo.findFirstById(id);

        practice.getManualResources().forEach(manualResource -> deleteManual(manualResource.getId()));
        practice.getExternalResources().forEach(externalResource -> deleteExternal(externalResource.getId()));

        practiceRepo.delete(practice);
        printMessage("Удалена практическая работа - " + practice.getDisplayName());
    }

    private void printMessage(String message) { System.out.println("[LightLMS - Delete]\t" + message); }
}
