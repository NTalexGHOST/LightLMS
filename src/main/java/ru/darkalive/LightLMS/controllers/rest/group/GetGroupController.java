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

    @GetMapping(value = "/groups/get")
    @ResponseBody
    public List<String> getGroups() {
        List<String> groups = groupRepo.getAll();
        return groups;
    }
}
