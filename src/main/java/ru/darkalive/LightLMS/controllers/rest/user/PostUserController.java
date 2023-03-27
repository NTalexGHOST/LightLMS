package ru.darkalive.LightLMS.controllers.rest.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.darkalive.LightLMS.entities.User;
import ru.darkalive.LightLMS.repos.GroupRepository;
import ru.darkalive.LightLMS.repos.RoleRepository;
import ru.darkalive.LightLMS.repos.UserRepository;

@RestController
public class PostUserController {

    @Autowired
    private GroupRepository groupRepo;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private RoleRepository roleRepo;

    @PostMapping(value = "/students")
    @ResponseBody
    public void addStudent(@RequestParam String group, @RequestParam String fullName, @RequestParam String email,
                           @RequestParam String password) {

        User user = new User();

        user.setGroup(groupRepo.getFirstByName(group));
        user.setFullName(fullName);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(roleRepo.findFirstByName("Студент"));

        userRepo.save(user);
        printMessage("Отработал POST-запрос, создан новый студент группы " + group + " - " + fullName);
    }

    private void printMessage(String message) { System.out.println("[LightLMS - Users]\t" + message); }
}
