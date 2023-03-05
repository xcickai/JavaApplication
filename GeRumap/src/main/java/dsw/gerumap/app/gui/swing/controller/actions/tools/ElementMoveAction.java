package dsw.gerumap.app.gui.swing.controller.actions.tools;

import dsw.gerumap.app.gui.swing.controller.actions.AbstractGeRuMapAction;
import dsw.gerumap.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class ElementMoveAction extends AbstractGeRuMapAction {
    public ElementMoveAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_M, InputEvent.ALT_DOWN_MASK
        ));
        putValue(NAME, "Move");
        putValue(SHORT_DESCRIPTION, "Move selected concept");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getProjectView().startMoveState();
    }
}
