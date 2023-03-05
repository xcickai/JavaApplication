package dsw.gerumap.app.core.log;

import dsw.gerumap.app.core.messageGenerator.Message;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Observable;

public class FileLogger implements Logger{
    private File file;

    public FileLogger() {
        try {
            file = new File("log.txt");
            if (file.createNewFile()) {
                System.out.println("Log file: " + file.getName());
            } else {
                System.out.println("Log file already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private void log(Message message) {
        if (file != null) {
            try {
                FileWriter writer = new FileWriter(file, true);
                writer.write(message.toString() + "\n");
                writer.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        Message message = (Message) arg;
        log(message);
    }
}
