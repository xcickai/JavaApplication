package dsw.gerumap.app.core.messageGenerator;

import lombok.Getter;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
public class Message {
    EventType type;
    String message;

    public Message(EventType type, String message) {
        this.type = type;
        this.message = message;
    }

    @Override
    public String toString() {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return "[" + dateFormat.format(new Date()) + "][" + type + "] " + message;
    }
}
