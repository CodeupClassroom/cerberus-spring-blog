package docrob.springblog.controllers;

import jakarta.annotation.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/hello")
public class HelloController {
    /*
    /hello -> Hello world!
    /hello?name=bob -> Hello bob!
    /hello/bob -> Hello bob!

    GET /ads -> list of ads
    GET /ads?param=val -> search for subset of ads using param
    GET /ads/1 -> show page for ad with id 1
    GET /ads/create -> show page to add an ad
    GET /ads/1/edit -> show page to update ad with id 1
    POST /ads/create -> insert the new add
    PUT /ads/1 -> update an existing ad with id 1
    DELETE /ads/1 ->  delete existing ad with id 1
     */
    @GetMapping()
    @ResponseBody
    public String sayHello(@RequestParam @Nullable String name) {
        if(name == null) {
            name = "world";
        }
        return getHelloString(name);
    }

    @GetMapping("/{personName}")
    @ResponseBody
    public String sayHelloToName(@PathVariable String personName) {
        return getHelloString(personName);
    }

    private String getHelloString(String personName) {
        return "<h1>Hello " + personName + "!</h1>";
    }
}
