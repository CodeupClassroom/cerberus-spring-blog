package docrob.springblog.controllers;

import docrob.springblog.models.EmailService;
import docrob.springblog.models.Post;
import docrob.springblog.models.User;
import docrob.springblog.repositories.PostRepository;
import docrob.springblog.repositories.UserRepository;
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
        List<Post> posts = postDao.findAll();

        model.addAttribute("posts",posts);
        return "/posts/index";
    }

    @GetMapping("/{id}")
    public String showSinglePost(@PathVariable Long id, Model model){
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
    public String showCreate() {
        return "/posts/create";
    }

    @PostMapping("/create")
    public String doCreate(@RequestParam String title, @RequestParam String body) {
        Post post = new Post();
        post.setTitle(title);
        post.setBody(body);

        // TODO: use user id 1 for now. change later to currently logged in user
        User loggedInUser = userDao.findById(1L).get();
        post.setCreator(loggedInUser);
        emailService.prepareAndSend(post,title,body);
        postDao.save(post);

        return "redirect:/posts";
    }
}