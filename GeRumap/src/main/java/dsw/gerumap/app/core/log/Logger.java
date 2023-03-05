package dsw.gerumap.app.core.log;

import java.util.Observable;
import java.util.Observer;

interface Logger extends Observer {
    void update(Observable o, Object arg);
}
