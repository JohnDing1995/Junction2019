package junction.demo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;

@Service
public class ImageService {

    private Boolean isActive = false;

    private List<String> drawings = new ArrayList<>();

    public void activate() {
        isActive = true;
//        drawings = new ArrayList<>();
    }

    public void deactivate() {
        isActive = false;
    }

    public Boolean getActive() {
        return isActive;
    }

    public List<String> getImages() {
        return unmodifiableList(drawings);
    }

    public void addImage(String base64) {
        drawings.add(base64);
    }
}

