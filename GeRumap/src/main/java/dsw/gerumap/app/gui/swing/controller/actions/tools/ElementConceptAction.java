package dsw.gerumap.app.gui.swing.controller.actions.tools;

import dsw.gerumap.app.gui.swing.controller.actions.AbstractGeRuMapAction;
import dsw.gerumap.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class ElementConceptAction extends AbstractGeRuMapAction {

    public ElementConceptAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_N, InputEvent.ALT_DOWN_MASK
        ));
        putValue(NAME, "Create Concept");
        putValue(SHORT_DESCRIPTION, "Create new concept");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getProjectView().startConceptState();
    }
}
