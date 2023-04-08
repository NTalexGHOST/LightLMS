package ru.darkalive.LightLMS.controllers.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.darkalive.LightLMS.entities.User;
import ru.darkalive.LightLMS.repos.GroupRepository;
import ru.darkalive.LightLMS.repos.RoleRepository;
import ru.darkalive.LightLMS.repos.UserRepository;

@Controller
public class ViewAIController {

    @Autowired
    private GroupRepository groupRepo;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private RoleRepository roleRepo;

    @GetMapping("/subjects/Мультимедиа технологии/Искусственный интеллект/chat-bot")
    public String chatBot(Model model) throws Exception {

        User authorizedUser = userRepo.findFirstByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("authorizedUser", authorizedUser);
        printMessage("Вызов страницы чат-бота - " + authorizedUser.getFullName());

        return "chat-bot";
    }

    private void printMessage(String message) { System.out.println("[LightLMS - View]\t" + message); }
}
