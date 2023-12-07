package docrob.springblog.controllers;

import docrob.springblog.dtos.MyListComponents;
import docrob.springblog.models.MyList;
import docrob.springblog.models.MyListTodo;
import docrob.springblog.models.Post;
import docrob.springblog.models.User;
import docrob.springblog.repositories.MyListRepository;
import docrob.springblog.repositories.UserRepository;
import docrob.springblog.services.AuthBuddy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor

@Controller
@RequestMapping("/lists")
public class MyListController {
    private MyListRepository listDao;
    private UserRepository userDao;

    @GetMapping("")
    private String index(Model model) {
        User loggedInUser = AuthBuddy.getLoggedInUser();
        model.addAttribute("loggedInUser", loggedInUser);

        List<MyList> lists = listDao.findAll();
        model.addAttribute("lists", lists);
        return "lists/index";
    }

    @GetMapping("/create")
    private String create(Model model) {
        User loggedInUser = AuthBuddy.getLoggedInUser();
        model.addAttribute("loggedInUser", loggedInUser);

//        MyListComponents listStuff = new MyListComponents();
//        listStuff.setList(new MyList());
//        listStuff.getList().setName("New list");
//        listStuff.setTodos(new ArrayList<>());
        MyList list = new MyList();
        list.setName("New list");

        model.addAttribute("list", list);
        return "lists/create3";
    }

    @GetMapping("/create/{listId}")
    private String create(@PathVariable(name = "listId") long listId, Model model) {
        User loggedInUser = AuthBuddy.getLoggedInUser();
        model.addAttribute("loggedInUser", loggedInUser);

        MyList list = listDao.findById(listId).get();
        System.out.println("Reading " + list);

        model.addAttribute("list", list);
        return "lists/create3";
    }

    @PostMapping("/create")
//    public String doCreate(@ModelAttribute MyList list) {
    public String doCreate(@RequestBody MyList list) {
        System.out.println("This is what is received: " + list);

        User loggedInUser = userDao.findById(4L).get();

        for (MyListTodo todo : list.getTodos()) {
            System.out.println(todo);
        }

        list.setCreator(loggedInUser);
        listDao.save(list);

        return "redirect:/lists";
    }

}
