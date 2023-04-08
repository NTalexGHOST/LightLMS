package ru.darkalive.LightLMS.controllers.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.darkalive.LightLMS.entities.Subject;
import ru.darkalive.LightLMS.entities.User;
import ru.darkalive.LightLMS.repos.*;

import java.util.List;

@Controller
public class ManagementController {

    @Autowired
    private GroupRepository groupRepo;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private RoleRepository roleRepo;
    @Autowired
    private SubjectRepository subjectRepo;
    @Autowired
    private LinkUserSubjectRepository linkUserSubjectRepo;

    @GetMapping("/journal")
    public String journal(Model model) throws Exception {

        User authorizedUser = userRepo.findFirstByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("authorizedUser", authorizedUser);
        printMessage("Вызов /journal - " + authorizedUser.getFullName());

        List<Subject> subjects = subjectRepo.findAllByTeacher(authorizedUser);
        model.addAttribute("subjects", subjects);

        List<String> groups = groupRepo.getAll();
        model.addAttribute("groups", groups);

        List<User> users = userRepo.findAll();
        model.addAttribute("users", users);

        return "journal";
    }

    @GetMapping("/admin")
    public String admin(Model model) throws Exception {

        User authorizedUser = userRepo.findFirstByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("authorizedUser", authorizedUser);
        printMessage("Вызов /admin - " + authorizedUser.getFullName());

        return "admin";
    }

    private void printMessage(String message) { System.out.println("[LightLMS - View]\t" + message); }
}
