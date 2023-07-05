package docrob.springblog.controllers;

import docrob.springblog.models.Dog;
import docrob.springblog.repositories.DogRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor

@Controller
@RequestMapping("/dogs")
public class DogController {
    private DogRepository dogDao;

    @GetMapping
    @ResponseBody
    public String index() {
        List<Dog> dogs = dogDao.findAll();

        System.out.println(dogs);

        List<Dog> sDogs = dogDao.searchByName("u");
        System.out.println(sDogs);

        return "show all dogs here";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public String fetchById(@PathVariable Long id) {
        Optional<Dog> optionalDog = dogDao.findById(id);
        if(optionalDog.isEmpty()) {
            return "no dog found. return a 404 instead";
        }
        Dog dog = optionalDog.get();
        return dog.toString();
    }

    @GetMapping("/create")
    public String create() {
        return "/dogs/create";
    }

    @PostMapping("/create")
    @ResponseBody
    public String create(@RequestParam Long id
            , @RequestParam String dogName
            , @RequestParam int age
            , @RequestParam String ownerName) {
        System.out.printf("%d %s %d %s\n", id, dogName, age, ownerName);
        Dog dog = new Dog(id, dogName, age, ownerName);

        dogDao.save(dog);

        return "dog created???";
    }

    @GetMapping("/{id}/delete")
    @ResponseBody
    public String delete(@PathVariable Long id) {
        dogDao.deleteById(id);
        return "dog " + id + " deleted";
    }

}
