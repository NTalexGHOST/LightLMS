package ru.darkalive.LightLMS.controllers.rest.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.darkalive.LightLMS.entities.Group;
import ru.darkalive.LightLMS.repos.GroupRepository;

@RestController
public class InteractGroupController {

    @Autowired
    private GroupRepository groupRepo;

    @PutMapping(value = "/groups", params = { "oldName", "newName" })
    @ResponseBody
    public void editGroup(@RequestParam String oldName, @RequestParam String newName) {

        Group group = groupRepo.getFirstByName(oldName);

        group.setName(newName);

        groupRepo.save(group);
        printMessage("Отработал PUT-запрос, название группы " + oldName + " изменено на " + newName);
    }

    @DeleteMapping(value = "/groups", params = "name")
    @ResponseBody
    public void deleteGroup(@RequestParam String name) {

        Group group = groupRepo.getFirstByName(name);

        groupRepo.delete(group);
        printMessage("Отработал DELETE-запрос, удалена группа - " + name);
    }

    private void printMessage(String message) { System.out.println("[LightLMS - Groups]\t" + message); }
}
