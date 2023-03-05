package dsw.gerumap.app.core;

import dsw.gerumap.app.core.messageGenerator.MessageGenerator;
import dsw.gerumap.app.core.messageGenerator.MessageGeneratorImpl;
import dsw.gerumap.app.serializer.MindmapSerializer;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ApplicationFramework {

    protected Gui gui;
    protected MapRepository mapRepository;
    protected MessageGenerator messageGenerator;
    private Serializer serializer;

    public void run(){this.gui.start();};
    public void initialise(Gui gui, MapRepository mapRepository){
        this.gui = gui;
        this.mapRepository = mapRepository;
        this.messageGenerator = new MessageGeneratorImpl();
        serializer = new MindmapSerializer();
    }

    // Singleton
    private static ApplicationFramework instance;

    private ApplicationFramework() {}

    public static ApplicationFramework getInstance() {
        if (instance == null) {
            instance = new ApplicationFramework();
        }
        return instance;
    }
}
