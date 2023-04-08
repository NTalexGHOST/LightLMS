package ru.darkalive.LightLMS.controllers.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.darkalive.LightLMS.entities.LinkUserSubject;
import ru.darkalive.LightLMS.entities.Subject;
import ru.darkalive.LightLMS.entities.User;
import ru.darkalive.LightLMS.repos.*;

import java.util.List;

@Controller
public class MainController {

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

    @GetMapping("/login")
    public String login(Model model) throws Exception {

        printMessage("Вызов /login");

        return "login";
    }

    @GetMapping("/login")
    public String loginError(Model model) throws Exception {

        printMessage("Вызов /login");

        return "login";
    }

    @GetMapping("/")
    public String main(Model model) throws Exception {

        User authorizedUser = userRepo.findFirstByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("authorizedUser", authorizedUser);
        printMessage("Вызов / - " + authorizedUser.getFullName());

        List<LinkUserSubject> subjects = linkUserSubjectRepo.findAllByUser(authorizedUser);
        model.addAttribute("subjects", subjects);

        return "home";
    }

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
