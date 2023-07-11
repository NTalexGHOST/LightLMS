package ru.darkalive.LightLMS.controllers.rest.entities.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.darkalive.LightLMS.entities.*;
import ru.darkalive.LightLMS.repos.*;

import java.util.Optional;

@RestController
public class AdminCreateController {

    @Autowired
    private SubjectRepository subjectRepo;
    @Autowired
    private GroupRepository groupRepo;
    @Autowired
    private EducationTypeRepository educationTypeRepo;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private RoleRepository roleRepo;
    @Autowired
    private ExamRepository examRepo;

    @PostMapping(value = "/api/subject", params = { "name", "course", "semester" })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createSubject(@RequestParam String name, @RequestParam int course, @RequestParam int semester) {

        Subject subject = new Subject();

        subject.setName(name);
        subject.setCourse(course);
        subject.setSemester(semester);

        Exam exam = new Exam();
        exam.setSubject(subject);
        exam.setName("Экзамен");
        examRepo.save(exam);

        subjectRepo.save(subject);
        printMessage("Отработал POST-запрос, создана дисциплина - " + subject.getName());
    }

    @PostMapping(value = "/api/group", params = { "name", "year", "typeId" })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createGroup(@RequestParam String name, @RequestParam int year, @RequestParam int typeId) {

        Group group = new Group();

        group.setName(name);
        group.setAdmissionYear(year);
        group.setEducationType(educationTypeRepo.findFirstById(typeId));

        groupRepo.save(group);
        printMessage("Отработал POST-запрос, создана группа - " + group.getName());
    }

    @PostMapping(value = "/api/user")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public @ResponseBody void createUser(@RequestParam String username, @RequestParam String password,
                                         @RequestParam String fullname, @RequestParam String email, @RequestParam int roleId) {

        User user = new User();

        user.setUserName(username);
        user.setPassword(password);
        user.setFullName(fullname);
        user.setEmail(email);
        user.setRole(roleRepo.findFirstById(roleId));

        userRepo.save(user);
        printMessage("Отработал POST-запрос, создана учетная запись - " + user.getUserName());
    }

    private void printMessage(String message) { System.out.println("[LightLMS - SubjectEditor]\t" + message); }
}
