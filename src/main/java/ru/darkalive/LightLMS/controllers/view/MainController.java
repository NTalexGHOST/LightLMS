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

    @GetMapping("/login-error")
    public String loginError(Model model) throws Exception {

        printMessage("Вызов /login с ошибкой авторизации");
        model.addAttribute("error", true);

        return "login";
    }

    @GetMapping({ "/", "/subjects" })
    public String main(Model model) throws Exception {

        User authorizedUser = userRepo.findFirstByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("authorizedUser", authorizedUser);
        printMessage("Вызов / - " + authorizedUser.getFullName());

        List subjects;
        if (authorizedUser.getRole().getName() == "Студент") subjects = linkUserSubjectRepo.findAllByUser(authorizedUser);
        else subjects = subjectRepo.findAllByTeacher(authorizedUser);
        model.addAttribute("subjects", subjects);

        return "home";
    }

    private void printMessage(String message) { System.out.println("[LightLMS - View]\t" + message); }
}
