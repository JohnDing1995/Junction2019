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
        System.out.println("RECEIVED: ACTIVE");
        return imageService.getActive().toString();
    }

    @PostMapping("/drawings")
    public ResponseEntity uploadDrawings(@RequestBody String base64) throws UnsupportedEncodingException {

        System.out.println("RECEIVED:" + base64);

        //fix URL decoding
        String s = base64.replace("%2F", "/");

        imageService.addImage("data:image/png;base64,"+s);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
