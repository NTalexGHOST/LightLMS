package ru.darkalive.LightLMS.controllers.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.darkalive.LightLMS.entities.User;
import ru.darkalive.LightLMS.repos.GroupRepository;
import ru.darkalive.LightLMS.repos.RoleRepository;
import ru.darkalive.LightLMS.repos.UserRepository;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private GroupRepository groupRepo;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private RoleRepository roleRepo;

    @GetMapping("/login")
    public String login(Model model) throws Exception {

        printMessage("Вызов /login");

        List<String> groups = groupRepo.getAll();
        model.addAttribute("groups", groups);

        List<String> teachers = userRepo.findUserFullNamesByRoleName("Преподаватель");
        model.addAttribute("teachers", teachers);

        return "login";
    }

    @GetMapping("/")
    public String main(Model model) throws Exception {

        printMessage("Вызов /");

        User authorizedUser = userRepo.findFirstByFullName(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("authorizedUser", authorizedUser);

        return "home";
    }

    @GetMapping("/journal")
    public String journal(Model model) throws Exception {

        printMessage("Вызов /journal");

        List<String> groups = groupRepo.getAll();
        model.addAttribute("groups", groups);

        List<User> users = userRepo.findAll();
        model.addAttribute("users", users);

        User authorizedUser = userRepo.findFirstByFullName(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("authorizedUser", authorizedUser);

        return "journal";
    }

    private void printMessage(String message) { System.out.println("[MultimediaAI - View]\t" + message); }
}