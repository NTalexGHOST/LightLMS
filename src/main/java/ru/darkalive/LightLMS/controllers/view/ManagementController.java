package ru.darkalive.LightLMS.controllers.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.darkalive.LightLMS.entities.Subject;
import ru.darkalive.LightLMS.entities.Theme;
import ru.darkalive.LightLMS.entities.User;
import ru.darkalive.LightLMS.repos.*;

import java.util.List;

@Controller
public class ManagementController {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private SubjectRepository subjectRepo;
    @Autowired
    private ThemeRepository themeRepo;

    @GetMapping("/subjects/{subjectId}/editor")
    public String subjectEditor(Model model, @PathVariable("subjectId") int subjectId) throws Exception {

        User authorizedUser = userRepo.findFirstByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("authorizedUser", authorizedUser);

        Subject subject = subjectRepo.findFirstById(subjectId);
        model.addAttribute("subject", subject);

        List<Theme> themes = themeRepo.findAllBySubjectOrderByPosition(subject);
        model.addAttribute("themes", themes);

        printMessage("Вызов страницы /subjects/" + subjectId + "/editor\n\t" + subject.getName() + "\n\t" + authorizedUser.getFullName());

        return "subject-editor";
    }

    @GetMapping("/journal")
    public String journal(Model model) throws Exception {

        User authorizedUser = userRepo.findFirstByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("authorizedUser", authorizedUser);



        printMessage("Вызов /journal - " + authorizedUser.getFullName());

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
