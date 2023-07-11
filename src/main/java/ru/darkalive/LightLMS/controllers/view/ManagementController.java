package ru.darkalive.LightLMS.controllers.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;
import ru.darkalive.LightLMS.entities.*;
import ru.darkalive.LightLMS.repos.*;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

@Controller
public class ManagementController {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private SubjectRepository subjectRepo;
    @Autowired
    private ThemeRepository themeRepo;
    @Autowired
    private RoleRepository roleRepo;
    @Autowired
    private EducationTypeRepository educationTypeRepo;
    @Autowired
    private GroupRepository groupRepo;
    @Autowired
    private LinkUserSubjectRepository linkUserSubjectRepo;
    @Autowired
    private ExamRepository examRepo;

    @GetMapping("/subjects/{subjectId}/editor")
    public String subjectEditor(Model model, @PathVariable("subjectId") int subjectId) throws Exception {

        User authorizedUser = userRepo.findFirstByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("authorizedUser", authorizedUser);

        Subject subject = subjectRepo.findFirstById(subjectId);
        model.addAttribute("subject", subject);

        if (linkUserSubjectRepo.countByUserAndSubjectAndRole(authorizedUser, subject, roleRepo.findFirstById(2)) < 1) throw new ResponseStatusException(HttpStatus.FORBIDDEN);

        List<Theme> themes = themeRepo.findAllBySubjectOrderByPosition(subject);
        model.addAttribute("themes", themes);

        Exam exam = examRepo.findFirstBySubject(subject);
        model.addAttribute("exam", exam);

        model.addAttribute("tinymceKey", getTinymceKey());

        printMessage("Вызов страницы /subjects/" + subjectId + "/editor | " + subject.getName() + " | " + authorizedUser.getFullName());
        return "subject-editor";
    }

    private String getTinymceKey() {

        String key = "";
        try {
            Scanner scanner = new Scanner(Paths.get("./tinymce_key.txt"));
            key = scanner.next();
            scanner.close();
        } catch (IOException exception) { printMessage("Не удалось прочитать файл с ключом TinyMCE"); }

        return key;
    }

    @GetMapping("/journal")
    public String journal(Model model) throws Exception {

        User authorizedUser = userRepo.findFirstByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("authorizedUser", authorizedUser);



        printMessage("Вызов страницы /journal | " + authorizedUser.getFullName());
        return "journal";
    }

    @GetMapping("/admin")
    public String admin(Model model) throws Exception {

        User authorizedUser = userRepo.findFirstByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("authorizedUser", authorizedUser);

        List<Subject> subjects = subjectRepo.findAll();
        model.addAttribute("subjects", subjects);

        List<User> teachers = userRepo.findAllByRole(roleRepo.findFirstById(2));
        model.addAttribute("teachers", teachers);

        List<EducationType> educationTypes = educationTypeRepo.findAll();
        model.addAttribute("educationTypes", educationTypes);

        List<Role> roles = roleRepo.findAll();
        model.addAttribute("roles", roles);

        List<Group> groups = groupRepo.findAll(Sort.by("AdmissionYear").descending());
        model.addAttribute("groups", groups);

        List<User> users = userRepo.findAll();
        model.addAttribute("users", users);

        printMessage("Вызов страницы /admin | " + authorizedUser.getFullName());
        return "admin";
    }

    private void printMessage(String message) { System.out.println("[LightLMS - View]\t" + message); }
}
