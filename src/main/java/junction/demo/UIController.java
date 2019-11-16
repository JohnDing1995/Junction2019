package junction.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UIController {

    @GetMapping("/step1")
    public String show1(Model model) {
        return "step1";
    }


    @GetMapping("/step2")
    public String show2(Model model) {
        return "step2";
    }

    @GetMapping("/step3")
    public String show3(Model model) {
        return "step3";
    }
}
