package ru.darkalive.LightLMS.controllers.rest.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.darkalive.LightLMS.entities.User;
import ru.darkalive.LightLMS.repos.GroupRepository;
import ru.darkalive.LightLMS.repos.UserRepository;

@RestController
public class InteractUserController {

    @Autowired
    private GroupRepository groupRepo;
    @Autowired
    private UserRepository userRepo;

    @PutMapping(value = "/students")
    @ResponseBody
    public void editStudent(@RequestParam String oldGroup, @RequestParam String oldFullName, @RequestParam String newGroup,
                            @RequestParam String newFullName, @RequestParam String newEmail, @RequestParam String newPassword) {

        User user = userRepo.findFirstByGroupAndFullName(groupRepo.getFirstByName(oldGroup), oldFullName);

        user.setGroup(groupRepo.getFirstByName(newGroup));
        user.setFullName(newFullName);
        user.setEmail(newEmail);
        user.setPassword(newPassword);

        userRepo.save(user);
        printMessage("Отработал PUT-запрос, изменен студент группы " + oldGroup + " - " + oldFullName);
    }

    @DeleteMapping(value = "/students", params = { "group", "fullName" })
    @ResponseBody
    public void deleteStudent(@RequestParam String group, @RequestParam String fullName) {

        User user = userRepo.findFirstByGroupAndFullName(groupRepo.getFirstByName(group), fullName);

        userRepo.delete(user);
        printMessage("Отработал DELETE-запрос, удален студент группы " + group + " - " + fullName);
    }

    private void printMessage(String message) { System.out.println("[LightLMS - Users]\t" + message); }
}
