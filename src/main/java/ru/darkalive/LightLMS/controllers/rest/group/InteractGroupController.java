package ru.darkalive.LightLMS.controllers.rest.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.darkalive.LightLMS.entities.Group;
import ru.darkalive.LightLMS.repos.GroupRepository;

@RestController
public class InteractGroupController {

    @Autowired
    private GroupRepository groupRepo;

    @PutMapping(value = "/groups/put")
    @ResponseBody
    public void editGroup(@RequestParam String oldName, @RequestParam String newName) {

        Group group = groupRepo.getFirstByName(oldName);

        group.setName(newName);

        groupRepo.save(group);
        printMessage("Название группы " + oldName + " изменено на " + newName);
    }

    @DeleteMapping(value = "/groups/delete")
    @ResponseBody
    public void deleteGroup(@RequestParam String name) {

        Group group = groupRepo.getFirstByName(name);

        groupRepo.delete(group);
        printMessage("Удалена группа - " + name);
    }

    private void printMessage(String message) { System.out.println("[MultimediaAI - Groups]\t" + message); }
}
