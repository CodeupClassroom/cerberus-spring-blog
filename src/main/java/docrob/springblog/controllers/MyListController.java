package docrob.springblog.controllers;

import docrob.springblog.dtos.MyListComponents;
import docrob.springblog.models.MyList;
import docrob.springblog.models.MyListTodo;
import docrob.springblog.models.Post;
import docrob.springblog.models.User;
import docrob.springblog.repositories.MyListRepository;
import docrob.springblog.repositories.MyListTodoRepository;
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
    private MyListTodoRepository todoDao;

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

        MyList list = new MyList();
        list.setName("New list");

        model.addAttribute("list", list);
        return "lists/create";
    }

    @GetMapping("/create/{listId}")
    private String create(@PathVariable(name = "listId") long listId, Model model) {
        User loggedInUser = AuthBuddy.getLoggedInUser();
        model.addAttribute("loggedInUser", loggedInUser);

        MyList list = listDao.findById(listId).get();
        System.out.println("Reading " + list);

        model.addAttribute("list", list);
        return "lists/create";
    }

    @PostMapping("/create")
    public String doCreate(@ModelAttribute MyList list) {
        System.out.println("Saving " + list);

        User loggedInUser = AuthBuddy.getLoggedInUser();
        if(loggedInUser.getId() == 0) {
            return "redirect:/login";
        }
        list.setCreator(loggedInUser);

        // reset the list id in the todos because list_id was not part of the form
        for (MyListTodo todo : list.getTodos()) {
            todo.setList(list);
        }

        // delete any todos from the db for the list that are not in the list parameter
        // note that this is an awful solution!
        List<MyListTodo> originalTodos = todoDao.findMyListTodoByList(list);
        for (MyListTodo todo : originalTodos) {
            // for each todo, look thru the todos in the list object and see if it is there
            // if not, then it needs to be deleted
            boolean found = false;
            for(MyListTodo todoCheck : list.getTodos()) {
                if(todoCheck.getId() == todo.getId()) {
                    found = true;
                }
            }
            if(!found) {
                todoDao.deleteById(todo.getId());
            }
        }

        listDao.save(list);

        return "redirect:/lists";
    }

}
