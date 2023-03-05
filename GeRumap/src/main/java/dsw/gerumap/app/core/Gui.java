package dsw.gerumap.app.core;

import dsw.gerumap.app.gui.swing.controller.actions.commands.CommandManager;

import java.util.Observer;

public interface Gui extends Observer {

    void start();
    void disableUndoAction();
    void disableRedoAction();

    void enableUndoAction();
    void enableRedoAction();

    CommandManager getCommandManager();
}
