package dsw.gerumap.app.gui.swing.controller.actions.tools;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.gui.swing.controller.actions.AbstractGeRuMapAction;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
public class UndoAction extends AbstractGeRuMapAction {

    public UndoAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
        putValue(MNEMONIC_KEY, KeyEvent.VK_U);
        putValue(SMALL_ICON, loadIcon("/images/editundo.png"));
        putValue(NAME, "Undo");
        putValue(SHORT_DESCRIPTION, "Undo");
    }

    public void actionPerformed(ActionEvent e) {
        ApplicationFramework.getInstance().getGui().getCommandManager().undoCommand();
    }

}
