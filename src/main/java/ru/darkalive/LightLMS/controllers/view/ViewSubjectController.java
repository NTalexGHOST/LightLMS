package ru.darkalive.LightLMS.controllers.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.server.ResponseStatusException;
import ru.darkalive.LightLMS.entities.*;
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
    private PracticeRepository practiceRepo;
    @Autowired
    private LinkUserSubjectRepository linkUserSubjectRepo;
    @Autowired
    private LinkUserPracticeRepository linkUserPracticeRepo;
    @Autowired
    private ExamRepository examRepo;
    @Autowired
    private RoleRepository roleRepo;


    @GetMapping("/subjects/{subjectId}")
    public String subject(Model model, @PathVariable("subjectId") int subjectId) throws Exception {

        User authorizedUser = userRepo.findFirstByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("authorizedUser", authorizedUser);

        Subject subject = subjectRepo.findFirstById(subjectId);
        model.addAttribute("subject", subject);

        if (linkUserSubjectRepo.countByUserAndSubject(authorizedUser, subject) < 1) throw new ResponseStatusException(HttpStatus.FORBIDDEN);

        List<Theme> themes = themeRepo.findAllBySubjectOrderByPosition(subject);
        model.addAttribute("themes", themes);

        Exam exam = examRepo.findFirstBySubject(subject);
        model.addAttribute("exam", exam);

        return "subject";
    }

    @GetMapping("/subjects/{subjectId}/practice/{practiceId}")
    public String practice(Model model, @PathVariable("subjectId") int subjectId, @PathVariable("practiceId") int practiceId) throws Exception {

        User authorizedUser = userRepo.findFirstByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("authorizedUser", authorizedUser);

        Subject subject = subjectRepo.findFirstById(subjectId);
        model.addAttribute("subject", subject);

        if (linkUserSubjectRepo.countByUserAndSubject(authorizedUser, subject) < 1) throw new ResponseStatusException(HttpStatus.FORBIDDEN);

        Practice practice = practiceRepo.findFirstById(practiceId);
        model.addAttribute("practice", practice);

        if (practice.isTask()) {
            if (linkUserSubjectRepo.countByUserAndSubjectAndRole(authorizedUser, subject, roleRepo.findFirstById(2)) < 1) {
                LinkUserPractice linkUserPractice = linkUserPracticeRepo.findFirstByUserAndPractice(authorizedUser, practice);
                if (linkUserPractice == null) {
                    linkUserPractice = new LinkUserPractice();
                    linkUserPractice.setUser(authorizedUser);
                    linkUserPractice.setPractice(practice);
                    linkUserPracticeRepo.save(linkUserPractice);
                }
                model.addAttribute("linkUserPractice", linkUserPractice);
                model.addAttribute("isTeacher", false);
            } else { model.addAttribute("isTeacher", true); }
            return "task";
        } else {

            return "test";
        }
    }

    @GetMapping("subjects/{subjectId}/performance")
    public String performance(Model model, @PathVariable("subjectId") int subjectId) {

        User authorizedUser = userRepo.findFirstByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("authorizedUser", authorizedUser);

        Subject subject = subjectRepo.findFirstById(subjectId);
        model.addAttribute("subject", subject);

        if (linkUserSubjectRepo.countByUserAndSubject(authorizedUser, subject) < 1) throw new ResponseStatusException(HttpStatus.FORBIDDEN);

        return "performance";
    }

    private void printMessage(String message) { System.out.println("[LightLMS - View]\t" + message); }
}
