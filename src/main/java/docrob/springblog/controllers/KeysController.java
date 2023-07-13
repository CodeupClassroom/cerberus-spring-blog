package docrob.springblog.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class KeysController {
    @Value("${api.quote.url}")
    private String quoteAPIURL;

    @Value("${api.quote.key}")
    private String quoteAPIKey;

    @Value("${api.quote.host}")
    private String quoteAPIHost;

    @GetMapping(value = "/js/keys.js", produces = "text/javascript")
    @ResponseBody()
    public String keys() {
        // const test = "stuff";
        return quoteAPIURL
                + "\n" + quoteAPIKey
                + "\n" + quoteAPIHost;
    }
}
