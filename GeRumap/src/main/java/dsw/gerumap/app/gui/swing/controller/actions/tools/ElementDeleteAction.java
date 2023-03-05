package dsw.gerumap.app.gui.swing.controller.actions.tools;

import dsw.gerumap.app.gui.swing.controller.actions.AbstractGeRuMapAction;
import dsw.gerumap.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class ElementDeleteAction extends AbstractGeRuMapAction {
    public ElementDeleteAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_DELETE, InputEvent.ALT_DOWN_MASK
        ));
        putValue(NAME, "Delete");
        putValue(SHORT_DESCRIPTION, "Delete the selected concept");
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getProjectView().startDeleteState();
    }
}
