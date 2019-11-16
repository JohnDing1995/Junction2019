package junction.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import static java.net.URLDecoder.decode;


@RestController
public class MyRestController {

    private final ImageService imageService;

    public MyRestController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/active")
    public String isUploadingOpen() {
        return imageService.getActive().toString();
    }

    @PostMapping("/drawings")
    public ResponseEntity uploadDrawings(@RequestBody String base64) throws UnsupportedEncodingException {

        //fix URL decoding
        String s = base64.replace("data%3Aimage%2Fpng%3Bbase64%2C", "data:image/png;base64,")
                .replace("%2", "/");

        imageService.addImage(s);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
