package docrob.springblog.controllers;

import docrob.springblog.models.EmailService;
import docrob.springblog.models.Post;
import docrob.springblog.models.User;
import docrob.springblog.repositories.PostRepository;
import docrob.springblog.repositories.UserRepository;
import docrob.springblog.services.AuthBuddy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor

@Controller
@RequestMapping("/posts")
public class PostController {
    private PostRepository postDao;
    private UserRepository userDao;
    private EmailService emailService;

    @GetMapping("")
    public String posts(Model model){
        User loggedInUser = AuthBuddy.getLoggedInUser();
        model.addAttribute("loggedInUser", loggedInUser);

        List<Post> posts = postDao.findAll();

        model.addAttribute("posts", posts);
        return "/posts/index";
    }

    @GetMapping("/{id}")
    public String showSinglePost(@PathVariable Long id, Model model) {
        User loggedInUser = AuthBuddy.getLoggedInUser();
        model.addAttribute("loggedInUser", loggedInUser);

        // find the desired post in the db
        Optional<Post> optionalPost = postDao.findById(id);
        if(optionalPost.isEmpty()) {
            System.out.printf("Post with id " + id + " not found!");
            return "home";
        }

        // if we get here, then we found the post. so just open up the optional
        model.addAttribute("post", optionalPost.get());
        return "/posts/show";
    }

    @GetMapping("/create")
    public String showCreate(Model model) {
        User loggedInUser = AuthBuddy.getLoggedInUser();
        if(loggedInUser.getId() == 0) {
            return "redirect:/login";
        }

        model.addAttribute("loggedInUser", loggedInUser);

        model.addAttribute("newPost", new Post());
        return "/posts/create";
    }

    @PostMapping("/create")
    public String doCreate(@ModelAttribute Post post) {
        User loggedInUser = AuthBuddy.getLoggedInUser();
        if(loggedInUser.getId() == 0) {
            return "redirect:/login";
        }

        post.setCreator(loggedInUser);
        postDao.save(post);

        return "redirect:/posts";
    }

    @GetMapping("/{id}/edit")
    public String showEdit(@PathVariable Long id, Model model) {
        User loggedInUser = AuthBuddy.getLoggedInUser();
        model.addAttribute("loggedInUser", loggedInUser);

        Post postToEdit = postDao.getReferenceById(id);
        model.addAttribute("newPost", postToEdit);
        return "/posts/create";
    }
}