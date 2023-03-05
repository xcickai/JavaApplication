package dsw.gerumap.app.core.log;

import dsw.gerumap.app.core.messageGenerator.Message;

import java.util.Observable;

public class ConsoleLogger implements Logger {
    private void log(Message message) {
        System.out.println(message.toString());
    }

    @Override
    public void update(Observable o, Object arg) {
        Message message = (Message) arg;
        log(message);
    }
}
