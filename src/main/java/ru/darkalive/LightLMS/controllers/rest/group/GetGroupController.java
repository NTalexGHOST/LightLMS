package ru.darkalive.LightLMS.controllers.rest.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.darkalive.LightLMS.repos.GroupRepository;

import java.util.List;

@RestController
public class GetGroupController {

    @Autowired
    private GroupRepository groupRepo;

    @GetMapping(value = "/groups")
    @ResponseBody
    public List<String> getGroups() {

        printMessage("Отработал GET-запрос /groups");

        List<String> groups = groupRepo.getAll();

        return groups;
    }

    private void printMessage(String message) { System.out.println("[LightLMS - Groups]\t" + message); }
}
