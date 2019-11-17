package junction.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;


@RestController
public class MyRestController {

    private final ImageService imageService;

    public MyRestController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/active")
    public String isUploadingOpen() {
        return isUploadingOpen("439");
    }

    @GetMapping("/{sessionId}/active")
    public String isUploadingOpen(@PathVariable String sessionId) {
        System.out.println("RECEIVED: ACTIVE");
        return imageService.getActive(sessionId).toString();
    }

    @PostMapping("/drawings")
    public ResponseEntity uploadDrawings(@RequestBody String base64) throws UnsupportedEncodingException {
        return uploadDrawings("439", base64);
    }

    @PostMapping("/{sessionId}/drawings")
    public ResponseEntity uploadDrawings(@PathVariable String sessionId, @RequestBody String base64) throws UnsupportedEncodingException {

        System.out.println("RECEIVED:" + base64);

        //fix URL decoding
        String s = "data:image/png;base64,"+ base64.replace("%2F", "/");

        imageService.addImage(sessionId, s);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/delete")
    public void deleteSession(){
        imageService.deleteSession("439");
    }
}
