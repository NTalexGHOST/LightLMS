package ru.darkalive.LightLMS.controllers.rest.entities.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.darkalive.LightLMS.entities.*;
import ru.darkalive.LightLMS.repos.*;

import java.util.Optional;

@RestController
public class SubjectCreateController {

    @Autowired
    private SubjectRepository subjectRepo;
    @Autowired
    private ThemeRepository themeRepo;

    @PostMapping(value = "/api/theme", params = { "subjectId", "name" })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createTheme(@RequestParam int subjectId, @RequestParam String name) {

        Theme theme = new Theme();

        theme.setName(name);
        Optional<Integer> position = themeRepo.lastPosition(subjectId);
        if (position.isEmpty()) theme.setPosition(1);
        else theme.setPosition(position.get() + 1);
        theme.setSubject(subjectRepo.findFirstById(subjectId));

        themeRepo.save(theme);
        printMessage("Отработал POST-запрос, создана тема - " + theme.getName());
    }

    private void printMessage(String message) { System.out.println("[LightLMS - SubjectEditor]\t" + message); }
}
