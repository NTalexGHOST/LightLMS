package ru.darkalive.LightLMS.controllers.rest.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.darkalive.LightLMS.repos.UserRepository;

import java.util.List;

@RestController
public class GetUserController {

    @Autowired
    private UserRepository userRepo;

    @GetMapping(value = "/students")
    @ResponseBody
    public List<String> getStudentsByGroup() {

        printMessage("Отработал /students");

        List<String> students = userRepo.findUserFullNames();

        return students;
    }

    @GetMapping(value = "/students", params = "group")
    @ResponseBody
    public List<String> getStudentsByGroup(@RequestParam String group) {

        printMessage("Отработал /students?group=" + group);

        List<String> students = userRepo.findStudentFullNamesByGroup(group);

        return students;
    }

    private void printMessage(String message) { System.out.println("[MultimediaAI - Users]\t" + message); }
}
