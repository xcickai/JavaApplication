package dsw.gerumap.app.gui.swing;

import dsw.gerumap.app.core.Gui;
import dsw.gerumap.app.core.messageGenerator.Message;
import dsw.gerumap.app.gui.swing.controller.actions.commands.CommandManager;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import javax.swing.*;
import java.util.Observable;

public class SwingGui implements Gui {

    private MainFrame instance;
    public SwingGui() {
    }
    @Override
    public void start() {
        instance = MainFrame.getInstance();

        instance.setVisible(true);
    }

    @Override
    public void disableUndoAction() {
        MainFrame.getInstance().getActionManager().getUndoAction().setEnabled(false);
    }
    @Override
    public void disableRedoAction() {
        MainFrame.getInstance().getActionManager().getRedoAction().setEnabled(false);
    }
    @Override
    public void enableUndoAction() {
        MainFrame.getInstance().getActionManager().getUndoAction().setEnabled(true);
    }

    @Override
    public void enableRedoAction() {
        MainFrame.getInstance().getActionManager().getRedoAction().setEnabled(true);
    }

    @Override
    public CommandManager getCommandManager() {
        return MainFrame.getInstance().getProjectView().getActiveMindmap().getCommandManager();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Message){
            JOptionPane.showMessageDialog(null, ((Message) arg).getMessage());
        }
    }
}
