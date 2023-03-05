package dsw.gerumap.app.gui.swing.controller.actions.main;

import dsw.gerumap.app.gui.swing.controller.actions.AbstractGeRuMapAction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ExitAction extends AbstractGeRuMapAction {

    public ExitAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_F4, ActionEvent.ALT_MASK
        ));
        putValue(SMALL_ICON, loadIcon("/images/log-out.png"));
        putValue(NAME, "Exit");
        putValue(SHORT_DESCRIPTION, "Exit");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
