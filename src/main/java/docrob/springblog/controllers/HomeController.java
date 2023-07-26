package docrob.springblog.controllers;

import docrob.springblog.models.User;
import docrob.springblog.services.AuthBuddy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @Value("${api1.url}")
    private String api1URL;

    @GetMapping("/")
    public String welcome(Model model) {
        User loggedInUser = AuthBuddy.getLoggedInUser();
        model.addAttribute("loggedInUser", loggedInUser);

        System.out.println(api1URL);

        return "home";
    }
}
