package junction.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Random;

@Controller
public class UIController {

    private static final String SESSION = "ses";
    private static final Random RAND = new Random();
    private final ImageService imageService;

    public UIController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping({"", "/start"})
    public String show1(Model model) {
        model.addAttribute(SESSION, Math.abs(RAND.nextInt()) % 1000);
        return "step1";
    }

    @GetMapping("/{sessionId}/step2")
    public String show2(@PathVariable String sessionId, Model model) {
        model.addAttribute(SESSION, sessionId);
        return "step2";
    }

    @GetMapping("/{sessionId}/step3")
    public String show3(@PathVariable String sessionId, Model model) {
        imageService.activate(sessionId);
        model.addAttribute(SESSION, sessionId);
        return "step3";
    }

    @GetMapping("/{sessionId}/step4")
    public String show4(@PathVariable String sessionId, Model model) {
        imageService.deactivate(sessionId);
        model.addAttribute(SESSION, sessionId);
        model.addAttribute("images", imageService.getImages(sessionId));
        return "step4";
    }

    @GetMapping("/{sessionId}/step5")
    public String show5(@PathVariable String sessionId, Model model) {
        model.addAttribute(SESSION, sessionId);
        imageService.activate(sessionId);
        return "step5";
    }
}
