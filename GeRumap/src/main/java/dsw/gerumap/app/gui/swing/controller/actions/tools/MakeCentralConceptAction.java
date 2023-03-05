package dsw.gerumap.app.gui.swing.controller.actions.tools;

import dsw.gerumap.app.gui.swing.controller.actions.AbstractGeRuMapAction;
import dsw.gerumap.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class MakeCentralConceptAction extends AbstractGeRuMapAction {
    public MakeCentralConceptAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_K, InputEvent.SHIFT_DOWN_MASK
        ));
        putValue(NAME, "Make central topic");
        putValue(SHORT_DESCRIPTION, "Select an element to be presented in the middle with other elements creating the perimeter around it");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getProjectView().startCentralTopicState();
    }
}
