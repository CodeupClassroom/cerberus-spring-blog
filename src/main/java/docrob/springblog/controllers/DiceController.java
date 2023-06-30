package docrob.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Controller
public class DiceController {
    @GetMapping("/roll-dice")
    public String dice(){
        return "rolldice";
    }

    @GetMapping("/roll-dice/{num}")
    public String diceRoll(@PathVariable int num, Model model){
        int rand_int = ThreadLocalRandom.current().nextInt(1,7);
        int rand_int2 = ThreadLocalRandom.current().nextInt(1,7);
        int rand_int3 = ThreadLocalRandom.current().nextInt(1,7);
        int rand_int4 = ThreadLocalRandom.current().nextInt(1,7);
        int rand_int5 = ThreadLocalRandom.current().nextInt(1,7);

        List<Integer> dicerolls = new ArrayList<>();

        dicerolls.add(rand_int);
        dicerolls.add(rand_int2);
        dicerolls.add(rand_int3);
        dicerolls.add(rand_int4);
        dicerolls.add(rand_int5);

        model.addAttribute("result",dicerolls);
        model.addAttribute("choice",num);

        int bucket = 0;

        for (var i = 0; i< dicerolls.size(); i++){
            if (num == dicerolls.get(i)){
                bucket++;
            }
        }

            model.addAttribute("match",bucket);


        return "rolldice";
    }
}
