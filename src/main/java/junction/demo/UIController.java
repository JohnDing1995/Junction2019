package junction.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UIController {

    private final ImageService imageService;

    public UIController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping({"", "/step1"})
    public String show1(Model model) {
        return "step1";
    }


    @GetMapping("/step2")
    public String show2(Model model) {
        return "step2";
    }

    @GetMapping("/step3")
    public String show3(Model model) {
        imageService.activate();
        return "step3";
    }

    @GetMapping("/step4")
    public String show4(Model model) {
        imageService.deactivate();
        model.addAttribute("images", imageService.getImages());
        return "step4";
    }
}
