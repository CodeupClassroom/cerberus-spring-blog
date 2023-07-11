package docrob.springblog.controllers;

import docrob.springblog.models.Dog;
import docrob.springblog.models.EmailService;
import docrob.springblog.models.User;
import docrob.springblog.repositories.DogRepository;
import docrob.springblog.services.AuthBuddy;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor

@Controller
@RequestMapping("/dogs")
public class DogController {
    private DogRepository dogDao;
    private EmailService emailService;

    @GetMapping
    public String index(Model model) {
        User loggedInUser = AuthBuddy.getLoggedInUser();
        model.addAttribute("loggedInUser", loggedInUser);
        System.out.println(loggedInUser);

        List<Dog> dogs = dogDao.findAll();

        model.addAttribute("dogs", dogs);

        return "/dogs/index";
    }

    @GetMapping("/{id}")
    public String fetchById(@PathVariable Long id, Model model) {
        User loggedInUser = AuthBuddy.getLoggedInUser();
        model.addAttribute("loggedInUser", loggedInUser);

        Optional<Dog> optionalDog = dogDao.findById(id);
        if(optionalDog.isEmpty()) {
            return "no dog found. return a 404 instead";
        }
        Dog dog = optionalDog.get();
        model.addAttribute("dog", dog);
        return "/dogs/show";
    }

    @GetMapping("/create")
    public String create(Model model) {
        User loggedInUser = AuthBuddy.getLoggedInUser();
        model.addAttribute("loggedInUser", loggedInUser);

        model.addAttribute("dog", new Dog());
        return "/dogs/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Dog dog) {
        User loggedInUser = AuthBuddy.getLoggedInUser();

        System.out.printf("%s %d \n", dog.getName(), dog.getAge());
//        emailService.prepareAndSend(dog, "You saved a new dog!", "Your dogs name is:" + dog.getName());
        dogDao.save(dog);

        return "redirect:/dogs";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        User loggedInUser = AuthBuddy.getLoggedInUser();

        dogDao.deleteById(id);
        return "redirect:/dogs";
    }

}
