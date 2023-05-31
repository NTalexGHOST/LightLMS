package ru.darkalive.LightLMS.controllers.rest.entities.put;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.darkalive.LightLMS.entities.*;
import ru.darkalive.LightLMS.repos.*;

import java.io.File;
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
    private TaskRepository taskRepo;
    @Autowired
    private ResourceTypeRepository resourceTypeRepo;

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

    @PutMapping(value = "/api/manual", params = "id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateManualFile(@PathVariable int id) {

        ManualResource manual = manualResourceRepo.findFirstById(id);

        

        manualResourceRepo.save(manual);

        printMessage("Отработал PUT-запрос, обновлен Word-файл - " + manual.getDisplayName());
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

    @PutMapping(value = "/api/task", params = "id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTask(@PathVariable int id) {

        Task task = taskRepo.findFirstById(id);



        taskRepo.save(task);

        printMessage("Отработал PUT-запрос, обновлена практическая работа - " + task.getDisplayName());
    }

    private void printMessage(String message) { System.out.println("[LightLMS - SubjectEditor]\t" + message); }
}
