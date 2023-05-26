package ru.darkalive.LightLMS.controllers.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ru.darkalive.LightLMS.entities.Subject;
import ru.darkalive.LightLMS.entities.Task;
import ru.darkalive.LightLMS.entities.Theme;
import ru.darkalive.LightLMS.entities.User;
import ru.darkalive.LightLMS.repos.*;

import java.util.List;

@Controller
public class ViewSubjectController {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private SubjectRepository subjectRepo;
    @Autowired
    private ThemeRepository themeRepo;
    @Autowired
    private TaskRepository taskRepo;


    @GetMapping("/subjects/{subjectId}")
    public String subject(Model model, @PathVariable("subjectId") int subjectId) throws Exception {

        User authorizedUser = userRepo.findFirstByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("authorizedUser", authorizedUser);

        Subject subject = subjectRepo.findFirstById(subjectId);
        model.addAttribute("subject", subject);

        List<Theme> themes = themeRepo.findAllBySubjectOrderByPosition(subject);
        model.addAttribute("themes", themes);

        printMessage("Вызов страницы /subjects/" + subjectId + "\n\t" + subject.getName() + "\n\t" + authorizedUser.getFullName());

        return "subject";
    }

    @GetMapping("/subjects/{subjectId}/practice/{taskId}")
    public String task(Model model, @PathVariable("subjectId") int subjectId, @PathVariable("taskId") int taskId) throws Exception {

        User authorizedUser = userRepo.findFirstByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("authorizedUser", authorizedUser);

        Subject subject = subjectRepo.findFirstById(subjectId);
        model.addAttribute("subject", subject);

        Task task = taskRepo.findFirstById(taskId);
        model.addAttribute("task", task);

        printMessage("Вызов страницы /subjects/" + subjectId + "/" + taskId + "\n\t" + subject.getName() + "\n\t" + authorizedUser.getFullName());

        return "subject";
    }

    private void printMessage(String message) { System.out.println("[LightLMS - View]\t" + message); }
}
