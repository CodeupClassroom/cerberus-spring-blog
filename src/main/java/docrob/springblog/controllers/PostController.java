package docrob.springblog.controllers;

import docrob.springblog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {
    @GetMapping("/post")
    public String post(Model model){
        model.addAttribute("post", new Post("Hello", "Hello World"));
        return "/posts/index";
    }

    @GetMapping("/posts")
    public String posts(Model model){
        List<Post> posts = new ArrayList<>();
        posts.add(new Post("Hello", "Hello World"));
        posts.add(new Post("Hello Part 2", "Hello World Again :)"));
        model.addAttribute("posts",posts);
        return "/posts/show";
    }

}
