package dsw.gerumap.app.gui.swing.controller.actions.tools;

import dsw.gerumap.app.gui.swing.controller.actions.AbstractGeRuMapAction;
import dsw.gerumap.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class ElementLinkAction extends AbstractGeRuMapAction {

    public ElementLinkAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_J, InputEvent.ALT_DOWN_MASK
        ));
        putValue(NAME, "Create Link");
        putValue(SHORT_DESCRIPTION, "Create new link between two concepts");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getProjectView().startLinkState();
    }
}
