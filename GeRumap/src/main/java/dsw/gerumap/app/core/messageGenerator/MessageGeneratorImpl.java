package dsw.gerumap.app.core.messageGenerator;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.core.log.ConsoleLogger;
import dsw.gerumap.app.core.log.FileLogger;

import java.util.Observable;

public class MessageGeneratorImpl extends Observable implements MessageGenerator {
    MessageFactory messageFactory = new MessageFactory();
    ConsoleLogger consoleLogger = new ConsoleLogger();
    FileLogger fileLogger = new FileLogger();

    public MessageGeneratorImpl() {
        // Subscribe observers to message generator
        this.addObserver(ApplicationFramework.getInstance().getGui());
        this.addObserver(consoleLogger);
        this.addObserver(fileLogger);
    }

    @Override
    public Message generateMessage(EventType type) {
        Message message = messageFactory.generateMessage(type);
        this.setChanged();
        this.notifyObservers(message);
        return message;
    }
}
