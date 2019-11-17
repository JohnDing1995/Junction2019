package junction.demo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.unmodifiableList;

@Service
public class ImageService {

    private Map<String, Session> sessions = new HashMap<>();

    private Session getSession(String sessionId) {
        return sessions.getOrDefault(sessionId, new Session(sessionId));
    }

    private void saveSession(Session session) {
        sessions.put(session.getSessionId(), session);
    }

    public Boolean getActive(String sessionId) {
        return getSession(sessionId).getActive();
    }

    public void activate(String sessionId) {
        Session session = getSession(sessionId);
        session.activate();
        saveSession(session);
    }

    public void deactivate(String sessionId) {
        Session session = getSession(sessionId);
        getSession(sessionId).deactivate();
        saveSession(session);
    }

    public List<String> getImages(String sessionId) {
        return getSession(sessionId).getImages();
    }

    public void addImage(String sessionId, String base64Image) {
        Session session = getSession(sessionId);
        getSession(sessionId).addImage(base64Image);
        saveSession(session);
    }

    static class Session {

        private final String sessionId;
        private Boolean isActive = false;
        private List<String> drawings = new ArrayList<>();

        Session(String sessionId) {
            this.sessionId = sessionId;
        }

        public String getSessionId() {
            return sessionId;
        }

        public void activate() {
            isActive = true;
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
}

