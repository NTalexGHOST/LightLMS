package ru.darkalive.LightLMS.controllers.rest.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.darkalive.LightLMS.entities.Group;
import ru.darkalive.LightLMS.repos.GroupRepository;

@RestController
public class PostGroupController {

    @Autowired
    private GroupRepository groupRepo;

    @PostMapping(value = "/groups", params = "name")
    @ResponseBody
    public void addGroup(@RequestParam String name) {

        Group group = new Group();

        group.setName(name);

        groupRepo.save(group);
        printMessage("Отработал POST-запрос, добавлена группа - " + name);
    }

    private void printMessage(String message) { System.out.println("[LightLMS - Groups]\t" + message); }
}
